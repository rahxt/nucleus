package com.crossedproductions.nucleus.model;

public class Drugs {
    private int id;
    private String name;
    private String sciname;
    private int cdosage;
    private int adosage;
    private String maxdosage;
    private String note;

    public Drugs(int id, String name, String sciname, int cdosage, int adosage, String maxdosage, String note) {
        this.id = id;
        this.name = name;
        this.sciname = sciname;
        this.cdosage = cdosage;
        this.adosage = adosage;
        this.maxdosage = maxdosage;
        this.note = note;
    }

//id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//namme
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//sciname
    public String getSciname() {
        return sciname;
    }

    public void setSciname(String sciname) {
        this.sciname = sciname;
    }
//cdosage
    public int getCdosage() {
        return cdosage;
    }

    public void setCdosage(int cdosage) {
        this.cdosage = cdosage;
    }
//adosage
    public int getAdosage() {
        return adosage;
    }

    public void setAdosage(int sciname) {
        this.adosage = adosage;
    }

//maxdosage
    public String getMaxdosage() {
        return maxdosage;
    }

    public void setMaxdosage(String maxdosage) {
        this.maxdosage = maxdosage;
    }
//note
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}