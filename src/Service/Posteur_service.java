/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Posteur_service {

    
         public ArrayList<User> affich_jobeur(String Specialite)
         {     ArrayList<User> listJobeurs1 = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixitweb1/web/app_dev.php/oussama/affichemobile/"+Specialite);  
        con.addResponseListener((NetworkEvent evt) -> {
             ArrayList<User> listJobeurs = new ArrayList<>();
            try {
                //(new String(con.getResponseData()));
                String aff=new String(con.getResponseData());
                JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
                Map<String, Object> jobeur = j.parseJSON(new CharArrayReader(aff.toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) jobeur.get("root");
             for (Map<String, Object> obj : list) {
                User u = new User();
                 
               u.setNom(obj.get("nom").toString());
               u.setPrenom(obj.get("prenom").toString());
               float tel = Float.parseFloat(obj.get("tel").toString());
               u.setTel((int)tel);
              
       
       // addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
       // addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
        //addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);
      
               listJobeurs.add(u);
                listJobeurs1.add(u);
            }} 
            catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         
             return listJobeurs1;
             
     

    
}
}