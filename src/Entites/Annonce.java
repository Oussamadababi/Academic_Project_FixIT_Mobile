/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author DELL
 */
public class Annonce {
    
    
    private int id ;
    private String nom_annonce;
    private String adress;
    private String  date;
    private String description_annonce;
    private String  etat_annonce;
    private int tel;
    private int prix;
    private int idposteur_fg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_annonce() {
        return nom_annonce;
    }

    public void setNom_annonce(String nom_annonce) {
        this.nom_annonce = nom_annonce;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription_annonce() {
        return description_annonce;
    }

    public void setDescription_annonce(String description_annonce) {
        this.description_annonce = description_annonce;
    }

    public String getEtat_annonce() {
        return etat_annonce;
    }

    public void setEtat_annonce(String etat_annonce) {
        this.etat_annonce = etat_annonce;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getIdposteur_fg() {
        return idposteur_fg;
    }

    public void setIdposteur_fg(int idposteur_fg) {
        this.idposteur_fg = idposteur_fg;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", nom_annonce=" + nom_annonce + ", adress=" + adress + ", date=" + date + ", description_annonce=" + description_annonce + ", etat_annonce=" + etat_annonce + ", tel=" + tel + ", prix=" + prix + ", idposteur_fg=" + idposteur_fg + '}';
    }
    
    
    
     public Annonce() {
    }
    
     
     
    
}