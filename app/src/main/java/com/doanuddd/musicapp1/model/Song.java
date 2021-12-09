package com.doanuddd.musicapp1.model;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song implements Parcelable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("caSi")
    @Expose
    private String caSi;
    @SerializedName("hinhBaiHat")
    @Expose
    private String hinhBaiHat;
    @SerializedName("idBaiHat")
    @Expose
    private String idBaiHat;
    @SerializedName("idPlayList")
    @Expose
    private String idPlayList;
    @SerializedName("idTheLoai")
    @Expose
    private String idTheLoai;
    @SerializedName("linkBaiHat")
    @Expose
    private String linkBaiHat;
    @SerializedName("luotThich")
    @Expose
    private String luotThich;
    @SerializedName("tenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("idNgheSi")
    @Expose
    private String idNgheSi;
    private Bitmap hinhBaiHatBit;

    public Song() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaSi() {
        return caSi;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }

    public String getHinhBaiHat() {
        return hinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.hinhBaiHat = hinhBaiHat;
    }

    public String getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(String idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public String getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(String idPlayList) {
        this.idPlayList = idPlayList;
    }

    public String getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(String idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getLinkBaiHat() {
        return linkBaiHat;
    }

    public void setLinkBaiHat(String linkBaiHat) {
        this.linkBaiHat = linkBaiHat;
    }

    public String getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(String luotThich) {
        this.luotThich = luotThich;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getIdNgheSi() {
        return idNgheSi;
    }

    public void setIdNgheSi(String idNgheSi) {
        this.idNgheSi = idNgheSi;
    }

    public Song(String id, String idBaiHat, String tenBaiHat, String hinhBaiHat, String tenCaSi, String linkBaiHat, String luotThich, String idTheLoai, String idPlayList, String idNgheSi) {
        this.id = id;
        this.idBaiHat = idBaiHat;
        this.tenBaiHat = tenBaiHat;
        this.hinhBaiHat = hinhBaiHat;
        this.caSi = tenCaSi;
        this.linkBaiHat = linkBaiHat;
        this.luotThich = luotThich;
        this.idTheLoai = idTheLoai;
        this.idPlayList = idPlayList;
        this.idNgheSi = idNgheSi;
    }

    protected Song(Parcel in) {
        id = in.readString();
        caSi = in.readString();
        hinhBaiHat = in.readString();
        idNgheSi = in.readString();
        idBaiHat = in.readString();
        idPlayList = in.readString();
        idTheLoai = in.readString();
        linkBaiHat = in.readString();
        luotThich = in.readString();
        tenBaiHat = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(id);
        dest.writeString(caSi);
        dest.writeString(hinhBaiHat);
        dest.writeString(idNgheSi);
        dest.writeString(idBaiHat);
        dest.writeString(idPlayList);
        dest.writeString(idTheLoai);
        dest.writeString(linkBaiHat);
        dest.writeString(luotThich);
        dest.writeString(tenBaiHat);
    }

}
