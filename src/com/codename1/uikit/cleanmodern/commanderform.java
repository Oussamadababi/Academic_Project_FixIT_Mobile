/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entites.Echange;
import Service.Session;
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
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Iheb
 */
public class commanderform extends BaseForm {
     public commanderform (Resources res) {
        super("Trocs", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Trocs");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("dog.jpg"), spacer1, "", "", " ");
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "", "", "");
                
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
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Mes Trocs", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Trocs", barGroup);
        popular.setUIID("SelectBar");
          RadioButton acc = RadioButton.createToggle("commander", barGroup);
       acc.setUIID("SelectBar");
        all.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        {
                       
                           new EchangeForm (res).show();
                          
                        }
                        }
                });
          popular.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        {
                       
                           new AjouterEchange (res).show();
                          
                        }
                        }
                });
     
     
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular,acc),
                FlowLayout.encloseBottom(arrow)
        ));
        
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(featured, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
      
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
            
        });
       
         int id=Session.getInstance().getLoggedInUser().getId();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixitweb1/web/app_dev.php/Iheb/afficherEchangecommanderMobile/"+id);  
      
            con.addResponseListener((NetworkEvent evt) -> {
            ArrayList<Echange> listTasks = new ArrayList<>();
            try {
                //(new String(con.getResponseData()));
                String aff=new String(con.getResponseData());
                JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
                Map<String, Object> tasks = j.parseJSON(new CharArrayReader(aff.toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
             for (Map<String, Object> obj : list) {
                Echange e = new Echange();
              
               e.setId(obj.get("id").toString());
               e.setPropositionofferte(obj.get("propositionOfferte").toString());
               e.setPropositionsouhaitée(obj.get("propositionSouhaitee").toString());
               e.setDescription_echange(obj.get("descriptionEchange").toString());
               e.setDate(obj.get("date").toString());
        //addButton(res.getImage("news-item-1.jpg"), obj.get("propositionOfferte").toString(), false, 26, 32);
       // addButton(res.getImage("news-item-2.jpg"), ("propositionSouhaitee").toString(), true, 15, 21);
       // addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
        //addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);
//               addButton2("propositionOfferte", obj.get("propositionOfferte").toString());
               
   
        
        //add(obj.get("propositionOfferte").toString());
           LinkedHashMap<String,Object> obj1 =  (LinkedHashMap<String,Object>) obj.get("id2") ;
           int pos = 1;
          e.setNom_posteur(obj1.get("username").toString());
         
  
                       
                            addButton3(res.getImage("dog.jpg"),false,55,55,obj.get("propositionOfferte").toString(),obj.get("propositionSouhaitee").toString(),obj.get("descriptionEchange").toString(),obj1.get("nom").toString());
                          
                      Button supprimer =new Button("supprimer ");
                       addStringValue("",supprimer );
                          supprimer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                         int id=Session.getInstance().getLoggedInUser().getId();
                       Echange ec = new Echange();
                    float idec = Float.parseFloat(obj.get("id").toString());
                     ConnectionRequest con1 = new ConnectionRequest();
               String Url1 ="http://localhost/fixitweb1/web/app_dev.php/Iheb/suppEchangecommanderMobile/"+idec;
                 con1.setUrl(Url1);// Insertion de l'URL de notre demande de connexion
                  Dialog.show("supp", "avec sucess", "OK", "Cancel");  
                  // commanderform.r
                    con1.addResponseListener((e) -> {
            String str = new String(con1.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
                       
   
    });
              NetworkManager.getInstance().addToQueueAndWait(con1);   
                    
     
                    
                    }
                    
                });

                listTasks.add(e);
            }} 
            catch (IOException ex) {
            }
  
            
                });
              
        NetworkManager.getInstance().addToQueueAndWait(con);
       // addButton(res.getImage("news-item-1.jpg"), "Morbi per tincidunt tellus sit of amet eros laoreet.", false, 26, 32);
       // addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
        //addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
        //addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);*/
      
    }
           private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xF4BE1B));
        
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

private void addButton3(Image img, boolean liked, int likeCount, int commentCount,String Proff,String souh,String description,String nom) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
     
     

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
      // Label likes1 = new Label("asazd" + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);

      
      
       Label num = new Label("propositionOfferte  :  "+Proff , "NewsBottomLine"); 
      // FontImage.setMaterialIcon(num, FontImage.MATERIAL_PHONE);
      
       Label likes1 = new Label("propositionSouhaitee  :  " + souh  , "NewsBottomLine");
      // FontImage.setMaterialIcon(likes1, FontImage.MATERIAL_PAYMENT);
        
       Label comments = new Label( " Description : "+description , "NewsBottomLine"); 
     //  FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
         Label user = new Label( "username  : "+nom , "NewsBottomLine"); 
      // FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
    
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                      
                       BoxLayout.encloseX(likes1,num ),
                       comments,user
                       
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
