package com.example.myapplication9;

import java.util.List;

/**
 * Created by bingnanfeng02 on 2017/8/24.
 */
public class BigService {
    private String id;
    private String serviceTypeName;
    private List<SmallService> services;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public List<SmallService> getServices() {
        return services;
    }

    public void setServices(List<SmallService> services) {
        this.services = services;
    }
}
