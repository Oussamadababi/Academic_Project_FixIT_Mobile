/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author waelb
 */
public class Categorie {
     private int id;
   private String Nomcategorie;

    public Categorie() {
    }

    public Categorie(int id, String Nomcategorie) {
        this.id = id;
        this.Nomcategorie = Nomcategorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomcategorie() {
        return Nomcategorie;
    }

    public void setNomcategorie(String Nomcategorie) {
        this.Nomcategorie = Nomcategorie;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", Nomcategorie=" + Nomcategorie + '}';
    }

   
}
