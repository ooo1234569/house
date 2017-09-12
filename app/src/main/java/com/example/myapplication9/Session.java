package com.example.myapplication9;

import org.litepal.crud.DataSupport;

/**
 * Created by bingnanfeng02 on 2017/8/14.
 */
public class Session extends DataSupport{
    private int flag;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
