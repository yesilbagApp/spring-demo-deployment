package com.example.demo_deployment.domain;

public class ApkControlModel {



    Boolean ApkAktifPasif;
    String Not;
    String YeniApkAdres;

    public ApkControlModel(Boolean apkAktifPasif, String not, String yeniApkAdres) {
        ApkAktifPasif = apkAktifPasif;
        Not = not;
        YeniApkAdres = yeniApkAdres;
    }

    public Boolean getApkAktifPasif() {
        return ApkAktifPasif;
    }

    public void setApkAktifPasif(Boolean apkAktifPasif) {
        ApkAktifPasif = apkAktifPasif;
    }

    public String getNot() {
        return Not;
    }

    public void setNot(String not) {
        Not = not;
    }

    public String getYeniApkAdres() {
        return YeniApkAdres;
    }

    public void setYeniApkAdres(String yeniApkAdres) {
        YeniApkAdres = yeniApkAdres;
    }

    public ApkControlModel(){
        //default
    }
}
