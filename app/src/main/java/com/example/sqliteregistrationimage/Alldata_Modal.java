package com.example.sqliteregistrationimage;

public class Alldata_Modal {
    private String id;
    private String name;
    private byte[]image;

    public Alldata_Modal( String id,String name, byte[] image) {
        this.id = id;

        this.name = name;
        this.image = image;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
