/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author Iheb
 */
public class Echange {
    private String id ;
      private String propositionofferte;
    private String propositionsouhaitée;
    private String description_echange;
    private String  date;
    private int id_posteurfg;
    private String nom_posteur;
    private String prenom_posteur;
     private int id_jobeurfg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropositionofferte() {
        return propositionofferte;
    }

    public void setPropositionofferte(String propositionofferte) {
        this.propositionofferte = propositionofferte;
    }

    public String getPropositionsouhaitée() {
        return propositionsouhaitée;
    }

    public void setPropositionsouhaitée(String propositionsouhaitée) {
        this.propositionsouhaitée = propositionsouhaitée;
    }

    public String getDescription_echange() {
        return description_echange;
    }

    public void setDescription_echange(String description_echange) {
        this.description_echange = description_echange;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_posteurfg() {
        return id_posteurfg;
    }

    public void setId_posteurfg(int id_posteurfg) {
        this.id_posteurfg = id_posteurfg;
    }

    public String getNom_posteur() {
        return nom_posteur;
    }

    public void setNom_posteur(String nom_posteur) {
        this.nom_posteur = nom_posteur;
    }

    public String getPrenom_posteur() {
        return prenom_posteur;
    }

    public void setPrenom_posteur(String prenom_posteur) {
        this.prenom_posteur = prenom_posteur;
    }

    public int getId_jobeurfg() {
        return id_jobeurfg;
    }

    public void setId_jobeurfg(int id_jobeurfg) {
        this.id_jobeurfg = id_jobeurfg;
    }

    public Echange(String propositionofferte, String propositionsouhaitée, String description_echange) {
        this.propositionofferte = propositionofferte;
        this.propositionsouhaitée = propositionsouhaitée;
        this.description_echange = description_echange;
    }

    public Echange(String propositionofferte, String propositionsouhaitée, String description_echange, int id_posteurfg) {
        this.propositionofferte = propositionofferte;
        this.propositionsouhaitée = propositionsouhaitée;
        this.description_echange = description_echange;
        this.id_posteurfg = id_posteurfg;
    }
    

    public Echange() {
    }
     
    
}
