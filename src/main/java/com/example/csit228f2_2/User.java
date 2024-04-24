package com.example.csit228f2_2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final IntegerProperty id;
    private final StringProperty fname;
    private final StringProperty lname;
    private final StringProperty uname;
    private final StringProperty email;

    public User(int id, String fname, String lname, String uname, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.uname = new SimpleStringProperty(uname);
        this.email = new SimpleStringProperty(email);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty fnameProperty() {
        return fname;
    }

    public StringProperty lnameProperty() {
        return lname;
    }

    public StringProperty unameProperty() {
        return uname;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFName() {
        return fname.get();
    }

    public void setFName(String fname) {
        this.fname.set(fname);
    }

    public String getLName() {
        return lname.get();
    }

    public void setLName(String lname) {
        this.lname.set(lname);
    }

    public String getUName() {
        return uname.get();
    }

    public void setUName(String uname) {
        this.uname.set(uname);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
