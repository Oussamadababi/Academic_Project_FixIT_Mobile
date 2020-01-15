/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;





/**
 *
 * @author lenovo
 */
public class ControleSaisie {
    

   
    
   
    
    public boolean cvcisValid(String cvc)
    {
        int taille = cvc.length();
        if( taille==3 && isInt(cvc))
        {
            return true;
        }
        else return false; 
    }
    
    
    
    
     public boolean cardisValid(String card)
    {
        int taille = card.length();
        if( taille==16 && isLong(card))
        {
            return true;
        }
        else return false; 
    }
     
      public boolean moisisValid(String mois)
    {
        int taille = mois.length();
        if( taille==2 && isIntmois(mois) )
        { 
            return true;
        }
        else return false;
       
    }
      
     public boolean anneeisValid(String annee)
    {
        int taille = annee.length();
        if( taille==4 && isIntannee(annee))
        { 
            return true;   
        }
        else return false; 
    }

     
    public boolean isIntmois(String s)
    {
        try
        { int i = Integer.parseInt(s);
         if(i<13){
           return true;   
         }
        }

        catch(NumberFormatException er)
        { return false; } 
        return false;
    }
    
    
    public boolean isIntannee(String s)
  {
        try
        { int i = Integer.parseInt(s);
         if(i>2019){
           return true;   
         }
        }

        catch(NumberFormatException er)
        { return false; } 
        return false;
    }
    
    
    public boolean isInt(String s)
    {
        try
        { int i = Integer.parseInt(s); return true; }

        catch(NumberFormatException er)
        { return false; } 
    }
    
       public boolean isLong(String s)
    {
        try
        { Long i = Long.parseLong(s); return true; }

        catch(NumberFormatException er)
        { return false; } 
    }
    
    
    
    
    
}
    

