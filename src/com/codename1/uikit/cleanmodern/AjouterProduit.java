/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;


import Entites.Categorie;
import Entites.Task;
import Service.Session;
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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
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
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author waelb
 */
public class AjouterProduit extends BaseForm {
    Image imgg;
        EncodedImage enc ;
    
    
    public AjouterProduit(Resources res) {
       
        super("AjouterProduit", BoxLayout.y());
        ImageViewer img= new ImageViewer();
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Produits");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("home1.jpg"), spacer1, "16 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
        addTab(swipe, res.getImage("home2.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
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
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
                all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new Produit(res).show();
            }
        });
       // all.setSelectedStyle(style);
        RadioButton featured = RadioButton.createToggle("My products", barGroup);
        featured.setUIID("SelectBar");
        featured.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new MesProduits(res).show();
            }
        });
        RadioButton popular = RadioButton.createToggle("Add products", barGroup);
        popular.setUIID("SelectBar");

        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, all, featured, popular),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(popular, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
       // bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
         ComboBox cb = new ComboBox();
        addStringValue("Categorie",cb);
        TextField NomProduit = new TextField();
        NomProduit.setUIID("TextFieldBlack");
        addStringValue("Produit",NomProduit);
        TextField Prix = new TextField();
        Prix.setUIID("TextFieldBlack");
        addStringValue("Prix",Prix);
        int tel=Session.getInstance().getLoggedInUser().getTel();
        String tel2=Integer.toString(tel);
        TextField Num = new TextField(tel2);
        Num.setUIID("TextFieldBlack");
        addStringValue("Portable",Num);
        TextArea Description = new TextArea();
        Description.setUIID("TextAreaBlack");
        addStringValue("Description",Description);
       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixitweb1/web/app_dev.php/wael/afficherCategorieMobile");  
        ArrayList<Categorie> listTasks = new ArrayList<>();
        con.addResponseListener((NetworkEvent evt) -> {
            try {
                String aff=new String(con.getResponseData());
                JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
                Map<String, Object> categorie = j.parseJSON(new CharArrayReader(aff.toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) categorie.get("root");
                for (Map<String, Object> obj : list)
                {
                    Categorie e = new Categorie();
                    
                    e.setNomcategorie(obj.get("categorie").toString()); 
                    cb.addItem(obj.get("categorie").toString());
                }   
            }
            catch (IOException ex) {
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        Button ajouter=new Button("Ajouter");
        addStringValue("",ajouter);
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int prix2=Integer.parseInt(Prix.getText());
                int num=Integer.parseInt(Num.getText());
                Task produit = new Task(NomProduit.getText(),Description.getText(),prix2,num);
                ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/fixitweb1/web/app_dev.php/wael/ajouterproduitMobile?nomproduit="+produit.getNomproduit()+ "&description=" + produit.getDescription()+"&prix="+produit.getPrix()+"&numero="+produit.getNum();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
                
                
            }
        });
        
        
        
        
                
                
                
      /*  int idd= Session.getInstance().getLoggedInUser().getId();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixitweb1/web/app_dev.php/wael/afficherMesproduitMobile/"+idd);  
        con.addResponseListener((NetworkEvent evt) -> {
            ArrayList<Task> listTasks = new ArrayList<>();
            try {
                //(new String(con.getResponseData()));
                String aff=new String(con.getResponseData());
                JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
                Map<String, Object> tasks = j.parseJSON(new CharArrayReader(aff.toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
             for (Map<String, Object> obj : list) {
                Task e = new Task();
                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                float num = Float.parseFloat(obj.get("num").toString());
                e.setId((int) id);
               int numero=(int) num-1;
               int prixx=(int) prix;
               String description= obj.get("description").toString();
               String nomproduit= obj.get("nomproduit").toString();

 
        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) { 
        }
 
        if (obj.get("imageProduit")==null)
         {
             String url2="http://localhost/fixitweb1/web/upload/aucune.jpg";
             imgg=URLImage.createToStorage(enc,url2,url2,URLImage.RESIZE_SCALE);
         }
         else{
            String url="http://localhost/fixitweb1/web/upload/"+obj.get("imageProduit").toString();
             imgg=URLImage.createToStorage(enc,url,url,URLImage.RESIZE_SCALE);
         }
        
        
        addButton(imgg, nomproduit, false, 26, 32, description,prixx,numero);

           LinkedHashMap<String,Object> obj1 =  (LinkedHashMap<String,Object>) obj.get("idposteurFg") ;
           int pos = 1;
           e.setUsername(obj1.get("username").toString());
                listTasks.add(e);
            }} 
            catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       */
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
    
   private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount,String description,int Prix,int Num) {
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
      // Label likes1 = new Label("asazd" + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
      /* if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xF4BE1B);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }*/
      
      
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

     private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xF4BE1B));
        
    }
}
