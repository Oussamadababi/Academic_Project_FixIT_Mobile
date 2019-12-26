/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entites.User;
import Service.Posteur_service;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class affiche_jobeur extends Form{
    Form f ;
     Button a;
     Label label_nom;
   public affiche_jobeur(){
    label_nom = new Label();
    f.add(label_nom);
    f = new Form();
    a= new Button("bachoul");
    f.add(a);
   label_nom.setText("aaasba");
   
     f.show();
   }
    affiche_jobeur(Resources res) {
        Posteur_service  a = new Posteur_service();
       User b=new User();
       ArrayList<User> listJobeurs = new ArrayList<>();
           listJobeurs=a.affich_jobeur("Electriciter");
            b= listJobeurs.get(0);
            System.out.println(b);
            
            for(User u:listJobeurs)
            {
             
               
                
     
        //To change body of generated methods, choose Tools | Templates.
    }
    
    
}}
