package com.yyb.model;

import java.io.Serializable;

public class Country implements Serializable {
    private Integer id;

    private String countryname;

    private String countrycode;

    private Integer countrymonely;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname == null ? null : countryname.trim();
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode == null ? null : countrycode.trim();
    }

    public Integer getCountrymonely() {
        return countrymonely;
    }

    public void setCountrymonely(Integer countrymonely) {
        this.countrymonely = countrymonely;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryname='" + countryname + '\'' +
                ", countrycode='" + countrycode + '\'' +
                ", countrymonely=" + countrymonely +
                '}';
    }
}