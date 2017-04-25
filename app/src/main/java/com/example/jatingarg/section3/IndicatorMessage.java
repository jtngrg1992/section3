package com.example.jatingarg.section3;

import android.graphics.drawable.Drawable;

/**
 * Created by jatingarg on 25/04/17.
 */

public class IndicatorMessage {

    private String messageText;
    private int imageResource;

    public IndicatorMessage(String messageText, int imageResource) {
        this.messageText = messageText;
        this.imageResource = imageResource;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
