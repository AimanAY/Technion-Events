package com.tech.technionevents;

import java.io.Serializable;

/**
 * Created by aiman.ay.28 on 2/1/15.
 */
public class Event implements Serializable {
    String mTitle;
    String mDate;

    public Event(String title, String date) {
        mTitle = title;
        mDate = date;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDate() {
        return mDate;
    }
}
