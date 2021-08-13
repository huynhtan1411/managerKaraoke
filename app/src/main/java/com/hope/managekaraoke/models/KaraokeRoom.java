package com.hope.managekaraoke.models;

public class KaraokeRoom {
    private String NameRoom;
    private String TypeRoom;
    private long price;
    private String status;

    public KaraokeRoom() {
    }

    public void setNameRoom(String nameRoom) {
        this.NameRoom = nameRoom;
    }
    public String getNameRoom() {
        return NameRoom;
    }

    public void setTypeRoom(String typeRoom) {this.TypeRoom = typeRoom;}
    public String getTypeRoom() {return TypeRoom;}

    public void setPrice(long  price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }
}
