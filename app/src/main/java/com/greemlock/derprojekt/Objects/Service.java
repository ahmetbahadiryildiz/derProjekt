package com.greemlock.derprojekt.Objects;

public class Service {

    private String serviceTitle;
    private String serviceInfo;
    private float serviceCharge;

    public Service(String serviceTitle, String serviceInfo, float serviceCharge) {
        this.serviceTitle = serviceTitle;
        this.serviceInfo = serviceInfo;
        this.serviceCharge = serviceCharge;
    }

    public Service() {
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public float getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(float serviceCharge) {
        this.serviceCharge = serviceCharge;
    }
}
