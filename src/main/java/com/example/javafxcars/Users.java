package com.example.javafxcars;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Users {
    private String username;
    private String  password;
    private String CIN;
    private String prenom;
    private String nom ;
    private int age ;
    private String tel;

    private boolean isAdmin=false;
    public static final SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

    Users(){
        this.isAdmin=false;
    }
    public Users(String username, String CIN, String prenom, String nom, int age, String tel)throws ParseException {
        this.username=username;
        this.CIN=CIN;
        this.prenom=prenom;
        this.nom=nom;
        this.age=age;
        this.tel = tel ;
        this.isAdmin=false;
    }

    // constructeur d admin
    Users(String username , String password){
        if(username =="root" && password=="root"){
            this.username="root";
            this.password="root";
            this.prenom="";
            this.CIN="";
            this.isAdmin=true; // if true --> admin
        }

    }
    public int getAge() {
        return age;
    }
    public String getTel()
    {
        return tel ;
    }
    public void setTel(String tel)
    {
        this.tel = tel ;
    }
    public void setAge(int age) {
        if(isAdmin==false){
            if(age>18)
            {
                this.age = age;
            }else
                this.age=18;
        }
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        if(isAdmin==false)
            this.CIN = CIN;
    }




    public String getPassword() {
        return password; // une recherche dans la DB si le mot de passe existe
    }

    public void setPassword(String password) {
        if(isAdmin==false )
            this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if(isAdmin==false)
            this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }



    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }







}





