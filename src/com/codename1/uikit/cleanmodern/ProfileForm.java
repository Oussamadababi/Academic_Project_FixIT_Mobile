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
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.util.Resources;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;

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
        setTitle("Profile");
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
                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
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

        Button update = new Button("Browse Images");
        update.addActionListener(e->{
            if (FileChooser.isAvailable()) {
                
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2-> {
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[])e2.getSource();
                        for (String path : paths) {
                            System.out.println("path  :"+path);
                            try {
                                Image img1 = Image.createImage(path);
                                hi.add(new Label(img1));
                            } catch (Exception ex) {
                                Log.e(ex);
                            }
                            
                        }
                        return;
                       
                    }
                    if(e2!=null && e2.getSource()!=null) {

                        String file = (String)e2.getSource();
                        try {
                            Image img1 = Image.createImage(file);
                            

                            System.out.println("img  :"+img1);
                            System.out.println("file  :"+file);

                            
                            if (true) return;
                        } catch (Exception ex) {
                            Log.e(ex);
                        }

                        String filestack = "http://solutions.weblite.ca/testupload.php";

                        MultipartRequest request = new MultipartRequest();

                        request.setUrl(filestack);

                        request.setPost(true);
                        try {
                            request.addData("fileUpload", file, "/");

                            request.setFilename("fileUpload", "myfile.png");

                            request.setReadResponseForErrors(true); 
                            NetworkManager.getInstance().addToQueueAndWait(request);
                        } catch (Throwable t) {
                            Log.e(t);
                        }
                    }
               });
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
                
            
      
        
         Container content = BoxLayout.encloseY(
                update
        );
     
        content.setScrollableY(true);
        add(content);
    }
    
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
