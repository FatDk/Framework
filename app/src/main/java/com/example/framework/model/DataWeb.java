package com.example.framework.model;
import java.io.Serializable;

public class DataWeb implements Serializable {
    String nameWeb,author,  baca_lebih_lanjut,  description, logo;
    public DataWeb(String nameWeb, String baca_lebih_lanjut,String author, String description, String logo) {
        this.nameWeb = nameWeb;
        this.author = author;
        this.baca_lebih_lanjut = baca_lebih_lanjut;
        this.description = description;
        this.logo = logo;
    }

    public String getNameWeb() {
        return nameWeb;
    }

    public void setNameWeb(String nameWeb) {
        this.nameWeb= nameWeb;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author;}

    public String getBaca_lebih_lanjut() {
        return baca_lebih_lanjut;
    }

    public void setBaca_lebih_lanjut(String baca_lebih_lanjut) {
        this.baca_lebih_lanjut = baca_lebih_lanjut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
