/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import Service.Session;
import java.util.Date;

/**
 *
 * @author lenovo
 */
public class User {
    private int id;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private String sexe;
    private String password;
    private Date date_naissance;
    private int tel;
    private String etat;
    private String role;
    private String job;
    private String address;
    private int code;
    private String imgp;

    public User() {
    }

    public int getId() {
        return id;
    }

    public User(int id, String cin, String nom, String prenom, String email, String sexe, String password, Date date_naissance, int tel, String etat, String role, String job, String address) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.password = password;
        this.date_naissance = date_naissance;
        this.tel = tel;
        this.etat = etat;
        this.role = role;
        this.job = job;
        this.address = address;
    }

    public User(int id, String cin, String nom, String prenom, String email, String sexe, String password, Date date_naissance, int tel, String role, String job) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.password = password;
        this.date_naissance = date_naissance;
        this.tel = tel;
        this.role = role;
        this.job = job;
    }

    public User(int id, String cin, String nom, String prenom, String email, String sexe, String password, Date date_naissance, int tel, String etat, String role, String job, String address, int code) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.password = password;
        this.date_naissance = date_naissance;
        this.tel = tel;
        this.etat = etat;
        this.role = role;
        this.job = job;
        this.address = address;
        this.code = code;
    }

    public User(String cin, String nom, String prenom, String email, String sexe, String password, Date date_naissance, int tel, String etat, String role, String job, String address, int code) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.password = password;
        this.date_naissance = date_naissance;
        this.tel = tel;
        this.etat = etat;
        this.role = role;
        this.job = job;
        this.address = address;
        this.code = code;
    }

    public User(String cin, String nom, String prenom, String email, String sexe, String password, Date date_naissance, int tel, String etat, String role, String job, String address, int code, String imgp) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.password = password;
        this.date_naissance = date_naissance;
        this.tel = tel;
        this.etat = etat;
        this.role = role;
        this.job = job;
        this.address = address;
        this.code = code;
        this.imgp = imgp;
    }

    public User(int id, String cin, String nom, String prenom, String email, String sexe, String password, Date date_naissance, int tel, String etat, String role, String job, String address, int code, String imgp) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.password = password;
        this.date_naissance = date_naissance;
        this.tel = tel;
        this.etat = etat;
        this.role = role;
        this.job = job;
        this.address = address;
        this.code = code;
        this.imgp = imgp;
    }

    public User(String cin, String nom, String prenom, String email, String sexe, String password, Date date_naissance, int tel, String etat, String role, String job, String address, String imgp) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.password = password;
        this.date_naissance = date_naissance;
        this.tel = tel;
        this.etat = etat;
        this.role = role;
        this.job = job;
        this.address = address;
        this.imgp = imgp;
    }
    
    
    

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getImgp() {
        return imgp;
    }

    public void setImgp(String imgp) {
        this.imgp = imgp;
    }
    
    
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", sexe=" + sexe + ", password=" + password + ", date_naissance=" + date_naissance + ", tel=" + tel + ", etat=" + etat + ", role=" + role + ", job=" + job + ", address=" + address + '}';
    }
    
    public void ShowUserDebug()
    {
        User u = Session.getInstance().getLoggedInUser();
        System.out.println("id: "+ u.getId()+"\nUsername: "+u.getCin()+
        "\nEmail: "+u.getEmail()+"\npassword: "+u.getPassword()+
                "\nRoles: "+ u.getRole()
        );
    }
    
    

    
}
