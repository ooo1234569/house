package com.example.myapplication9;

import java.io.Serializable;

/**
 * Created by bingnanfeng02 on 2017/9/11.
 */

public class Service  implements Serializable {
    public String date;
    public String time;
    public String months;
    public int num;
    public float howmuch;
    public float total;
    private boolean select=false;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
