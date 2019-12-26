/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author bhk
 */
public class Task {
   private int id;
   private String nom;
   private String etat;
   
    private String nomproduit;
    private int prix;
    private String description;
    private int num;
    private String etat_vente;
    private String etat_validation;
    private int idposteur_fg;
    private String nom_proprietere;
    private String image_produit;
    private String date_produit;
    private int idcategorie_fg;
   private String username;
    
    public Task() {
    }

    
    public Task(int id, String nomproduit, int prix, String description, int num, String etat_vente, String etat_validation, int idposteur_fg, String nom_proprietere, String image_produit, String date_produit, int idcategorie_fg) {
        this.id = id;
        this.nomproduit = nomproduit;
        this.prix = prix;
        this.description = description;
        this.num = num;
        this.etat_vente = etat_vente;
        this.etat_validation = etat_validation;
        this.idposteur_fg = idposteur_fg;
        this.nom_proprietere = nom_proprietere;
        this.image_produit = image_produit;
        this.date_produit = date_produit;
        this.idcategorie_fg = idcategorie_fg;
    }

    public Task(int id, String nom, String etat, String nomproduit, int prix, String description, int num, String etat_vente, String etat_validation, String nom_proprietere, String image_produit, String date_produit) {
        this.id = id;
        this.nom = nom;
        this.etat = etat;
        this.nomproduit = nomproduit;
        this.prix = prix;
        this.description = description;
        this.num = num;
        this.etat_vente = etat_vente;
        this.etat_validation = etat_validation;
        this.nom_proprietere = nom_proprietere;
        this.image_produit = image_produit;
        this.date_produit = date_produit;
    }

    public Task(int id, String nomproduit, int prix, int num, String etat_vente, String etat_validation, String nom_proprietere, String image_produit, String date_produit) {
        this.id = id;
        this.nomproduit = nomproduit;
        this.prix = prix;
        this.num = num;
        this.etat_vente = etat_vente;
        this.etat_validation = etat_validation;
        this.nom_proprietere = nom_proprietere;
        this.image_produit = image_produit;
        this.date_produit = date_produit;
    }

    public Task(String nomproduit, String description, int prix ,int num) {
        this.nomproduit = nomproduit;
        this.description = description;  
        this.prix = prix;
        this.num = num;
    }

    public Task(int id, String nomproduit, int prix, String description, int num) {
        this.id = id;
        this.nomproduit = nomproduit;
        this.prix = prix;
        this.description = description;
        this.num = num;
    }

    public Task(int id, String nomproduit, String description) {
        this.id = id;
        this.nomproduit = nomproduit;
        this.description = description;
    }


    
    
    
    
    


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getEtat_vente() {
        return etat_vente;
    }

    public void setEtat_vente(String etat_vente) {
        this.etat_vente = etat_vente;
    }

    public String getEtat_validation() {
        return etat_validation;
    }

    public void setEtat_validation(String etat_validation) {
        this.etat_validation = etat_validation;
    }

    public int getIdposteur_fg() {
        return idposteur_fg;
    }

    public void setIdposteur_fg(int idposteur_fg) {
        this.idposteur_fg = idposteur_fg;
    }

    public String getNom_proprietere() {
        return nom_proprietere;
    }

    public void setNom_proprietere(String nom_proprietere) {
        this.nom_proprietere = nom_proprietere;
    }

    public String getImage_produit() {
        return image_produit;
    }

    public void setImage_produit(String image_produit) {
        this.image_produit = image_produit;
    }

    public String getDate_produit() {
        return date_produit;
    }

    public void setDate_produit(String date_produit) {
        this.date_produit = date_produit;
    }

    public int getIdcategorie_fg() {
        return idcategorie_fg;
    }

    public void setIdcategorie_fg(int idcategorie_fg) {
        this.idcategorie_fg = idcategorie_fg;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nomproduit=" + nomproduit + ", prix=" + prix + ", description=" + description + ", num=" + num + ", etat_vente=" + etat_vente + ", etat_validation=" + etat_validation + ", nom_proprietere=" + nom_proprietere + ", image_produit=" + image_produit + ", date_produit=" + date_produit + ", username=" + username + '}';
    }


 
  
    
    
    
   
           
}
