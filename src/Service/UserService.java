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
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class UserService {
     private User loggedUser= new User();
     private User loggedUser1= new User();
     
     public User Authentification(String username, String password) 
    {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/fixitweb1/web/app_dev.php/login/" + username + "/" + password;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);
                    System.out.println(Url);

            if(str.equals("123"))
        {
          loggedUser.setEtat("Attente");
          loggedUser.setCin(username);
          Session.getInstance().setLoggedInUser(loggedUser);
        }
            
            else if(str.equals("false"))
        {
            loggedUser = null;
            
        }
        else
        {
            UserService ser = new UserService();
                try {
                    loggedUser = ser.parseUserJson(new String(con.getResponseData()));
                    System.out.println("raed : "+ser.parseUserJson(new String(con.getResponseData())));
                                        System.out.println("raed1 : "+loggedUser.getEtat());

                } catch (ParseException ex) {
                    
                }
                loggedUser.setEtat("Active");
            Session.getInstance().setLoggedInUser(loggedUser);
        }
        
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        return loggedUser; 
    }
     
     public User parseUserJson(String json) throws ParseException {

        ArrayList<User> listUsers = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
               // u.setDate_naissance(new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("date_naissance").toString())) ;
               u.setNom(obj.get("nom").toString());
               u.setPrenom(obj.get("prenom").toString());
                u.setSexe(obj.get("sexe").toString());
                u.setCin(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                u.setPassword(obj.get("password").toString());
                u.setRole(obj.get("roles").toString());
                u.setTel((int) (double) obj.get("tel"));
                u.setDate_naissance(new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("dateNaissance").toString()));
                if(obj.get("attach")!=null)
                {
                u.setImgp(obj.get("attach").toString());
                }

                listUsers.add(u);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return listUsers.get(0);
    }
     
     public void update(User u) 
    {
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/fixitweb1/web/app_dev.php/updateprofile?id="+u.getId()+"&nom="+u.getNom()+"&prenom="+u.getPrenom()+"&username="+u.getCin()+"&email="+u.getEmail()+"&tel="+u.getTel()+"&birth="+u.getDate_naissance();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        System.out.println(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
     
     public void SignUp(User u) 
    {
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion

        String Url = "http://localhost/fixitweb1/web/app_dev.php/registermobile?nom="+u.getNom()+"&prenom="+u.getPrenom()
                + "&username="+u.getCin()+"&email="+u.getEmail()+"&tel="+u.getTel()+"&birth="+u.getDate_naissance()+"&sexe="+u.getSexe()+"&spec="+u.getJob()
                + "&pass="+u.getPassword()+"&role="+u.getRole()+"&code="+u.getCode();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
     
     
     
     /////////////////////////////////////
      public boolean updatecode(String user) 
    {
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion

        String Url = "http://localhost/fixitweb1/web/app_dev.php/userverfication?user="+user;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        
       return true;
    }
      
    public void envoyermail(String email,int code)
    {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        
        String Url = "http://localhost/fixitweb1/web/app_dev.php/envoyezmail?email="+email+"&code="+code;
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    /////////////////////////////////////ayed///
     public User GetUserdata(String id) 
    {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/fixitweb1/web/app_dev.php/getuserdata/" + id;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
        if(str.equals("false"))
        {
            loggedUser1 = null;
        }
        else
        {
            UserService ser = new UserService();
                try {
                    loggedUser1 = ser.parseUserJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    
                }
            Session1.getInstance().setLoggedInUser1(loggedUser1);
        }
        
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        return loggedUser; 
    }
}
