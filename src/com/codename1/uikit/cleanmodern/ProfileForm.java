/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;


import Entites.User;
import Service.Session;
import Service.UserService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {
    private Form hi;

    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile Posteur");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("avatar5.png"), "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));

        Label username = new Label(Session.getInstance().getLoggedInUser().getCin());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);
        
        Label espac = new Label(" ");
        username.setUIID("TextFieldBlack");
        addStringValue("", espac);

        TextField email = new TextField(Session.getInstance().getLoggedInUser().getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        /*Mail m=new Mail();
         String msg = "n'aime pas votre profile";
        try {
            m.sendMail11(email.getText(),"Notifacation:", msg);
        } catch (MessagingException ex) {
           
        }*/
        TextField Nom = new TextField(Session.getInstance().getLoggedInUser().getNom(), "Nom", 20, TextField.ANY);
        Nom.setUIID("TextFieldBlack");
        addStringValue("Nom", Nom);
        
        TextField Prenom = new TextField(Session.getInstance().getLoggedInUser().getPrenom(), "Prenom", 20, TextField.ANY);
        Prenom.setUIID("TextFieldBlack");
        addStringValue("Prenom", Prenom);

        Picker dp = new Picker();
                dp.setDate(Session.getInstance().getLoggedInUser().getDate_naissance());
        dp.setUIID("TextFieldBlack");
        addStringValue("Birth Date", dp);
        System.out.println(Session.getInstance().getLoggedInUser().getTel());
        TextField Telephone = new TextField(Integer.toString(Session.getInstance().getLoggedInUser().getTel()), "Telephone", 20, TextField.ANY);
        Telephone.setUIID("TextFieldBlack");
        addStringValue("Telephone", Telephone);
       
        
        TextField Password = new TextField(Session.getInstance().getLoggedInUser().getPassword(), "Password", 5, TextField.PASSWORD);
        Password.setUIID("TextFieldBlack");
        addStringValue("Password", Password);
        
                CheckBox multiSelect = new CheckBox("Multi-select");

        final String[] jobPic = new String[1];
    Label jobIcon = new Label();

    Button image = new Button("Ajouter une image ");
    final String[] image_name = {""};
    final String[] pathToBeStored={""};

    /////////////////////Upload Image
    image.addActionListener((ActionEvent actionEvent) -> {
    Display.getInstance().openGallery(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            if (ev != null && ev.getSource() != null) {
                String filePath = (String) ev.getSource();
                int fileNameIndex = filePath.lastIndexOf("/") + 1;
                String fileName = filePath.substring(fileNameIndex);
                Image img = null;
                try {
                    img = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
    		
    	    } catch (IOException e) {
                    e.printStackTrace();
                }
                image_name[0] = System.currentTimeMillis() + ".jpg";
                jobIcon.setIcon(img);
                System.out.println(filePath);
                System.out.println(image_name[0]);
            

                try {
                         pathToBeStored[0] = FileSystemStorage.getInstance().getAppHomePath()+ image_name[0];
                        OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored[0]);
                        ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_JPEG, 0.9f);
                        os.close();
                        System.out.println(pathToBeStored);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    }, Display.GALLERY_IMAGE);});



            ////////////Copied with URL Symfony
            Button myButton = new Button("Valider");
            myButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    
                    String path = "C:\\wamp64\\www\\fixitweb1\\web\\upload\\"+image_name[0];
                   File file = new File(path);
                    System.out.println(file);

                }
            });

       
        
    

             /*   User user = Session.getInstance().getLoggedInUser();
                UserService S = new UserService();
                
                user.setEmail(email.getText());
                user.setNom(Nom.getText());
                user.setCin(username.getText());
                user.setPrenom(Prenom.getText());
                user.setPassword(Prenom.getText());
                user.setDate_naissance(dp.getDate());
                user.setTel(Integer.parseInt(Telephone.getText()));
                S.update(user);
                Session.getInstance().setLoggedInUser(user);
                new ProfileForm(res).show();*/
                
            
      Button btnn =new Button("mail");
        btnn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
      
Message m = new Message("Body of message");
Display.getInstance().sendMessage(new String[] {}, "Subject of message", m);
 
            }
        });
         Container content = BoxLayout.encloseY(
                image,
                 btnn
                 
        );
         Container content1 = BoxLayout.encloseY(
                myButton
                 
        );
     
        content.setScrollableY(true);
  
        add(content1);
    }
    
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
