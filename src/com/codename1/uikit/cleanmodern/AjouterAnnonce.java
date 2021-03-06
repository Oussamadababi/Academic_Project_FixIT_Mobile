/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entites.Annonce;
import Entites.Echange;
import Service.Session;
import com.codename1.capture.Capture;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class AjouterAnnonce extends BaseForm{
    
public AjouterAnnonce(Resources res) {
        super("Annonce", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Annonce");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("troc.jpg"), spacer1, "", "", " ");
        addTab(swipe, res.getImage("troc.jpg"), spacer2, "", "", "");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
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
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All ", barGroup);
        all.setUIID("SelectBar");
        RadioButton mes = RadioButton.createToggle("Mes Annonce", barGroup);
        mes.setUIID("SelectBar");
        RadioButton ajouter = RadioButton.createToggle(" Ajouter Annonce", barGroup);
       ajouter.setUIID("SelectBar");
        // RadioButton acc = RadioButton.createToggle("Trocs commandée", barGroup);
      // acc.setUIID("SelectBar");
           all .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        {
                       
                           new AnnonceForm (res).show();
                          
                        }
                        }
                });
             mes .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        {
                       
                           new MesAnnonce (res).show();
                          
                        }
                        }
                });
             
           
     
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, all, mes, ajouter),
                FlowLayout.encloseBottom(arrow)
        ));
        
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(ajouter, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(mes, arrow);
        bindButtonSelection(ajouter, arrow);
      
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
            
        });
  TextField nom = new TextField();
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom annonce",  nom);

        TextField adresse = new TextField();
        adresse.setUIID("TextFieldBlack");
        addStringValue("Adresse",adresse);
     
        Picker date = new Picker();
        date.setUIID("TextFieldBlack");
        addStringValue("Date", date);
        
        TextArea des = new TextArea();
        des.setUIID("TextFieldBlack");
        addStringValue("Description Annonce",  des);
        
        TextArea tel = new TextArea();
        tel.setUIID("TextFieldBlack");
        addStringValue("telephone",  tel);
        
        TextArea prix = new TextArea();
        prix.setUIID("TextFieldBlack");
        addStringValue("Prix",  prix);
        
        
        
        Button bt = new Button("ajouter");
         
         addStringValue(" ", bt);
        
       
         
         bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           
     
                   int id=Session.getInstance().getLoggedInUser().getId();
                   System.out.println(id);
                     boolean test= true;
                       if( nom.getText().isEmpty()){
         
            test = false;
             Dialog.show("champs", "vide", "OK", "Cancel");  
        }
                        else if( adresse.getText().isEmpty()){
            test = false;
             Dialog.show("champs", "vide", "OK", "Cancel");  
        }
                       else if( des.getText().isEmpty()){
         
            test = false;
             Dialog.show("champs", "vide", "OK", "Cancel");  
        }
                       
                       else if( tel.getText().isEmpty()){
         
            test = false;
             Dialog.show("champs", "vide", "OK", "Cancel");  
        }
                       else if( prix.getText().isEmpty()){
         
            test = false;
             Dialog.show("champs", "vide", "OK", "Cancel");  
        }
       if(test)
       {
      
              Annonce a = new Annonce(nom.getText(),adresse.getText(),date.getText(),des.getText(),Integer.parseInt(tel.getText()),Integer.parseInt(prix.getText()),id);
               ConnectionRequest con = new ConnectionRequest();
               String Url ="http://localhost/fixitweb1/web/app_dev.php/heni/ajouterannonce?nomAnnonce="+a.getNom_annonce()+"&adress="+a.getAdress()+ "&date="+date.getDate()+"&descriptionAnnonce="+a.getDescription_annonce()+"&tel="+a.getTel()+"&prix="+a.getPrix()+"&idposteurfg="+a.getIdposteur_fg();
                 con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
           System.out.println(Url);
                  Dialog.show("Ajout", "avec sucess", "OK", "Cancel");  
                 new MesAnnonce(res).show();
                 
       
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
      
    });    NetworkManager.getInstance().addToQueueAndWait(con);
       }
    
            }
            
           
        
    });
         
                 }
                 
         
       private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
     
    
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

private void addButton3(Image img, boolean liked, int likeCount, int commentCount,String noma,String adresse,String date,String description,String tel,String prix,String nom) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
     
     

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
      // Label likes1 = new Label("asazd" + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);

      
      
       Label num = new Label("Nom annonce  :  "+nom , "NewsBottomLine"); 
      // FontImage.setMaterialIcon(num, FontImage.MATERIAL_PHONE);
      
       Label likes1 = new Label("Adresse  :  " + adresse  , "NewsBottomLine");
       Label likes2 = new Label("Date  :  " + date  , "NewsBottomLine");
      // FontImage.setMaterialIcon(likes1, FontImage.MATERIAL_PAYMENT);
        
       Label comments = new Label( " Description annonce: "+description , "NewsBottomLine");
       Label comments1 = new Label( " Telephone: "+tel , "NewsBottomLine");
       Label comments2 = new Label( " Prix: "+prix , "NewsBottomLine");
     //  FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
         Label user = new Label( "username  : "+nom , "NewsBottomLine"); 
      // FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                      
                       BoxLayout.encloseX(likes1,num ),likes2,
                       comments,comments1,comments2,user
                       
               ));
 
       add(cnt);
     
   }
    
   private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
}

