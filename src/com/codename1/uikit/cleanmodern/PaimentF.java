/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;
import Entites.Categorie;
import Entites.Task;
import Service.ControleSaisie;
import Service.ServicePaiement;
import Service.Session;
import com.codename1.charts.views.DialChart;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author waelb
 */
public class PaimentF extends BaseForm  {
    
        Image imgg;
        EncodedImage enc ;
     Container cnt = new Container();
     Container cnt0 = new Container();
     public boolean controlepayment=true;
    public PaimentF(Resources res,Image imgg, String title,String description,int Prix,int Num,int id) {
       
        super("Paiment", BoxLayout.y());
        ImageViewer img= new ImageViewer();
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Produits");
        getContentPane().setScrollVisible(false);
  
        
        super.addSideMenu(res);

    
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
         addTab(swipe,imgg, spacer1, "  ", "", " ");
        addTab(swipe, imgg, spacer2, " ", "", "");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xF4BE1B);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xF4BE1B);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        
        add(cnt0);
        addButton(imgg, title, focusScrolling, LEFT, CENTER, description, Prix, Num);
        
        TextField Card = new TextField();
        Card.setUIID("TextFieldBlack");
        addStringValue("Card N°",Card);
        TextField mois = new TextField();
        mois.setUIID("TextFieldBlack");
        addStringValue("MM",mois);
        TextField year = new TextField();
        year.setUIID("TextFieldBlack");
        addStringValue("YY",year);
        TextField cvc = new TextField(TextField.PASSWORD);
        cvc.setUIID("TextFieldBlack");
        addStringValue("CVC",cvc);
        Button payer=new Button("Payer");
        addStringValue("",payer);
        
        
        payer.addActionListener((evt) -> {
                 
                ControleSaisie C= new ControleSaisie();
                 if(year.getText().isEmpty())
            {controlepayment = false; }
          else if(!C.anneeisValid(year.getText()) )
        {controlepayment = false; } 
          else if(!year.getText().isEmpty()&&C.anneeisValid(year.getText())){controlepayment = true; }
     
           if(mois.getText().isEmpty())
        {controlepayment = false; }
           else if(!C.moisisValid(mois.getText()) )
        {controlepayment = false; } 
          else if(!mois.getText().isEmpty()&&C.moisisValid(mois.getText())){ controlepayment = true;}
       
       if(cvc.getText().isEmpty())
        {controlepayment = false; }
       else if(!C.cvcisValid(cvc.getText()) )
        {controlepayment = false; } 
          else if(!cvc.getText().isEmpty()&&C.cvcisValid(cvc.getText())) {controlepayment = true;}
   
        if(Card.getText().isEmpty())
        {controlepayment = false;  }
        else if(!C.cardisValid(Card.getText()) )
        {controlepayment = false; } 
          else if(!Card.getText().isEmpty()&&C.cardisValid(Card.getText())) {controlepayment = true;}
                
          
                if(controlepayment){
                ServicePaiement aa=new ServicePaiement();
                String card=Card.getText();
                String Cvc=cvc.getText();
                int Mois=Integer.parseInt(mois.getText());
                int Year=Integer.parseInt(year.getText());
                    ConnectionRequest con = new ConnectionRequest();
                    con.setUrl("http://localhost/fixitweb1/web/app_dev.php/wael/delete/"+id);   
                    NetworkManager.getInstance().addToQueueAndWait(con); 
                    try {
                    aa.payer(card,Year,Mois, Cvc, Prix);   
                
                    Dialog.show("Paiemment ", "Paiemment effectué avec succés " , "OK", null);
                    new Produit(res).show();
                    
                  
                    
                    
                    
                    
                } catch (InvalidRequestException ex) {
                   Dialog.show("Paiemment ", "Erreur de Paiement " , "OK", null);
                } catch (CardException ex) {
                    Dialog.show("Paiemment ", "Erreur de Paiement " , "OK", null);
                } catch (StripeException ex) {
                    Dialog.show("Paiemment ", "Erreur de Paiement " , "OK", null);
                }
                }
                else{
                    Dialog.show("Erreur de saisie ", "veuillez verifier vos coordonnées " , "OK", null);
                }
      
        });
  
  
  
    }
    
       
    
        
      private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xF4BE1B));}
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
   
   private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount,String description,int Prix,int Num) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label"); 
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
      cnt = BorderLayout.west(image);
       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
      // Label likes1 = new Label("asazd" + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);

      
      
       Label num = new Label(" Phone: "+ Num , "NewsBottomLine"); 
       FontImage.setMaterialIcon(num, FontImage.MATERIAL_PHONE);
      
       Label likes1 = new Label("Prix:  " + Prix +"  $" , "NewsBottomLine");
       FontImage.setMaterialIcon(likes1, FontImage.MATERIAL_PAYMENT);
        
       Label comments = new Label( " Description : "+description , "NewsBottomLine"); 
       FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes1,num ),
                       comments
                       
               ));
       
       
       
       
       
       
       
       cnt0.add(cnt);
       
       
       
      cnt.addPointerPressedListener((evt) -> {
          cnt0.removeAll();
      });
       image.addActionListener((evt) -> {
            
       });
       
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
