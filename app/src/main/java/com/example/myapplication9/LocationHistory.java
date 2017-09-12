package com.example.myapplication9;

import org.litepal.crud.DataSupport;

/**
 * Created by bingnanfeng02 on 2017/8/23.
 */
public class LocationHistory extends DataSupport {
    private String province;
    private String city;
    private String distrct;
    private String street;
    private double weidu;
    private double jingdu;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrct() {
        return distrct;
    }

    public void setDistrct(String distrct) {
        this.distrct = distrct;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public double getWeidu() {
        return weidu;
    }

    public void setWeidu(double weidu) {
        this.weidu = weidu;
    }

    public double getJingdu() {
        return jingdu;
    }

    public void setJingdu(double jingdu) {
        this.jingdu = jingdu;
    }
}
