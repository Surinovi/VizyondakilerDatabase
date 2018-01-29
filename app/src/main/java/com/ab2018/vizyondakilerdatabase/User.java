package com.ab2018.vizyondakilerdatabase;

import java.io.Serializable;

/**
 * Created by neval on 28/01/2018.
 */

public class User implements Serializable {
    int id;
    String email;
    String password;
    String name;

    public User(int id,String email,String password,String name)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    } {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
