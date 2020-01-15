/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.User;

/**
 *
 * @author ASUS
 */
public class Session1 {
    static Session1 instance;
    User loggedInUser1;

    private Session1()
    {
        
    }
    
    
    public static Session1 getInstance() {
       if(instance == null)
        {
            instance = new Session1();
            return instance;
        }
        else
            return instance;
    }

    public User getLoggedInUser1() {
        return loggedInUser1;
    }

    public void setLoggedInUser1(User loggedInUser1) {
        this.loggedInUser1 = loggedInUser1;
    }

   
    

    
}
