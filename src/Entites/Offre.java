/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Offre {
   
   private String adress;
   private Date date;
   private int tel;
   private String desciption;
   private int idpo;
   
 
    
     public Offre() {
    }

    public Offre(String adress, Date date, int tel, String desciption) {
        this.adress = adress;
        this.date = date;
        this.tel = tel;
        this.desciption = desciption;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getDesciption() {
        return desciption;
    }

    public int getIdpo() {
        return idpo;
    }

    public void setIdpo(int idpo) {
        this.idpo = idpo;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Offre(String adress, int tel, String desciption, int idpo) {
        this.adress = adress;
        this.tel = tel;
        this.desciption = desciption;
        this.idpo = idpo;
    }

  
     
    
}
