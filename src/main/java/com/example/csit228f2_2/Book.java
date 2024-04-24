package com.example.csit228f2_2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final IntegerProperty bid;
    private final StringProperty tit;
    private final StringProperty gen;
    private final StringProperty avail;

    public Book(int bid, String tit, String gen, String avail) {
        this.bid = new SimpleIntegerProperty(bid);
        this.tit = new SimpleStringProperty(tit);
        this.gen = new SimpleStringProperty(gen);
        this.avail = new SimpleStringProperty(avail);
    }

    public IntegerProperty bidProperty() {
        return bid;
    }

    public StringProperty titProperty() {
        return tit;
    }

    public StringProperty genProperty() {
        return gen;
    }

    public StringProperty availProperty() {
        return avail;
    }

    public int getBid() {
        return bid.get();
    }

    public void setBid(int bid) {
        this.bid.set(bid);
    }

    public String getTit() {
        return tit.get();
    }

    public void setTit(String tit) {
        this.tit.set(tit);
    }

    public String getGen() {
        return gen.get();
    }

    public void setGen(String gen) {
        this.gen.set(gen);
    }

    public String getAvail() {
        return avail.get();
    }

    public void setAvail(String avail) {
        this.avail.set(avail);
    }
}
