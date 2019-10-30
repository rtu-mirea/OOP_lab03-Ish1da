package com.company;

public class User {
    private String name;
    private String login;
    private String password;

    User(String n, String l, String p){
        name = n;
        login = l;
        password = p;
    }

    boolean enter(String l, String p){
        if (login.equals(l) && password.equals(p)) return true;
        else return false;
    }

    String getName(){
        return name;
    }
}
