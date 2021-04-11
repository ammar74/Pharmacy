package com.ammar.pharmacy.more;

import android.graphics.drawable.Drawable;


public class MoreInfoItem {
    int icon;
    String name;

    public MoreInfoItem(int icon, String name) {
        this.setIcon(icon);
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
