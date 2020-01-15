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

import Service.Session;
import Service.UserService;
import com.codename1.components.FloatingHint;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {
        String reponse="";
    public SignInForm(Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
        
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        ////////////
           

        ////////////
        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
signIn.addActionListener(new ActionListener() 
        {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                           
                           //////////////////////////////////
               if(!username.getText().equals("") && !password.getText().equals("") )
               {
                    ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/fixitweb1/web/app_dev.php/getcode?user="+username.getText();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            reponse = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(reponse);
        });
         NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
                    UserService us = new UserService();
                    if(us.Authentification(username.getText(), password.getText())!= null)
                    {   System.out.println("raed hh  "+Session.getInstance().getLoggedInUser());
                        if(Session.getInstance().getLoggedInUser().getEtat().contains("Attente"))
                        {
                            Dialog.show("Registration uncomplete","You must provide the verification token in order to use your account","ok",null);
                            new ActivateForm(res,Integer.parseInt(reponse),password.getText()).show();
                        }
                        else
                        {
                        if(Session.getInstance().getLoggedInUser().getRole().contains("POSTEUR"))
                        {
                       new ProfileForm(res).show();
                       Session.getInstance().getLoggedInUser().ShowUserDebug();
                        }
                        
                        else if(Session.getInstance().getLoggedInUser().getRole().contains("JOBEUR"))
                        {
                            new ProfileFormJobeur(res).show();
                       Session.getInstance().getLoggedInUser().ShowUserDebug();
                        }
                        }
                        
                        
                    }
                    else
                    {
                       Dialog.show("Authentification failed","Username and password does not match !\nPlease try again","ok",null);
                    }
               }
               else
                    Dialog.show("Missing info","You must provide both username and password","ok",null);
            }
        });    }
    
}
