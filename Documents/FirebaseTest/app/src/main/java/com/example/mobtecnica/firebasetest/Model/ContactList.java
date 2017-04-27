package com.example.mobtecnica.firebasetest.Model;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mobtecnica on 4/25/2017.
 */

public class ContactList {
    String name;
    String phone;
    CircleImageView thumb;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CircleImageView getThumb() {
        return thumb;
    }

    public void setThumb(CircleImageView thumb) {
        this.thumb = thumb;
    }

}
