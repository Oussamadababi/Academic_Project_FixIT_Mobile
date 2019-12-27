/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entites.Echange;
import Entites.Offre;
import Entites.User;
import Service.Posteur_service;
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
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class offreaccepter extends BaseForm{
      static int idj;
   
    public offreaccepter(Resources res) {
        
//         Form f ;
//        Button p;
//       Label label_nom;
//         label_nom = new Label();
//          f = new Form();
//      f.add(label_nom);
//     
//      p= new Button("bachoul");
//      f.add(p);
//      label_nom.setText("aaasba");
//        Posteur_service  a = new Posteur_service();
//       User b=new User();
//       ArrayList<User> listJobeurs = new ArrayList<>();
//           listJobeurs=a.affich_jobeur("Electriciter");
//            b= listJobeurs.get(0);
//            System.out.println(b);
//            
//            for(User u:listJobeurs)
//            {
//             
//               
//                
//     
//        //To change body of generated methods, choose Tools | Templates.
//    }
      
        
         super("Jobeurs Electriciter", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Mes Offres");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("electricte.JPG"), spacer1, "", "", " ");
        
                
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
        RadioButton all = RadioButton.createToggle("Mes Offres", barGroup);
        all.setUIID("SelectBar");
         RadioButton featured = RadioButton.createToggle("Offre Accepter ", barGroup);
        featured.setUIID("SelectBar");
        all.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent evt) {
   new offresayed(res).show();             }
         });
        featured .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        {
                       
                           new offreaccepter(res).show();
                          
                        }
                        }
                });
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2,all,featured),
                FlowLayout.encloseBottom(arrow)
        ));
        
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
            
        });
     int idjobeur=Session.getInstance().getLoggedInUser().getId();
        
        ConnectionRequest con = new ConnectionRequest();
         con.setUrl("http://localhost/fixitweb1/web/app_dev.php/oussama/mesoffres/"+idjobeur); 
      ArrayList<Offre> listoffre = new ArrayList<>();
            con.addResponseListener((NetworkEvent evt) -> {
            try {
                //(new String(con.getResponseData()));
                String aff=new String(con.getResponseData());
                JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
                Map<String, Object> tasks = j.parseJSON(new CharArrayReader(aff.toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
             for (Map<String, Object> obj : list) {
                Offre O = new Offre();
             LinkedHashMap<String,Object> obj5 =  (LinkedHashMap<String,Object>) obj.get("idposteurFg") ;
             float tel = Float.parseFloat(obj.get("tel").toString());
                    if(obj.get("etatOffre").equals("Accepter")){
           addButton3(res.getImage("electricte.JPG"),false,55,55,obj.get("adress").toString(),obj.get("descriptionOffre").toString(),obj.get("tel").toString(),obj.get("etatOffre").toString(),res,obj5.get("nom").toString());
                 
                 
                listoffre.add(O);
            }} }
            catch (IOException ex) {
            }
                });
              
        NetworkManager.getInstance().addToQueueAndWait(con);
       // addButton(res.getImage("news-item-1.jpg"), "Morbi per tincidunt tellus sit of amet eros laoreet.", false, 26, 32);
       // addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
        //addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
        //addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);
      
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

private void addButton3(Image img, boolean liked, int likeCount, int commentCount,String Proff,String souh,String description,String nom,Resources res,String nomposteur) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
      
     

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
      // Label likes1 = new Label("asazd" + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);

      
      
       Label num = new Label("Adress:  "+Proff , "NewsBottomLine"); 
      // FontImage.setMaterialIcon(num, FontImage.MATERIAL_PHONE);
      
       Label likes1 = new Label("Description  :  " + souh  , "NewsBottomLine");
      // FontImage.setMaterialIcon(likes1, FontImage.MATERIAL_PAYMENT);
        
       Label comments = new Label( " Tel : "+description , "NewsBottomLine"); 
     //  FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
         Label user = new Label( "Etat offre  : "+nom , "NewsBottomLine"); 
         
          Label nomposteur4 = new Label("CLIENT  :  " + nomposteur  , "NewsBottomLine");
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
            
    
    


