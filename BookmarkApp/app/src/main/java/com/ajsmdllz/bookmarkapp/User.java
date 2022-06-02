package com.ajsmdllz.bookmarkapp;

import java.io.Serializable;

public class User implements Serializable {
    public int id;
    public String Username;
    public String Password;

    public User(int id, String username, String password) {
        this.id = id;
        Username = username;
        Password = password;
    }


}
