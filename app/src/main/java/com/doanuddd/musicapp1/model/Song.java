package com.doanuddd.musicapp1.model;


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
    @SerializedName("idAlbum")
    @Expose
    private Integer idAlbum;
    @SerializedName("idBaiHat")
    @Expose
    private Integer idBaiHat;
    @SerializedName("idPlayList")
    @Expose
    private Integer idPlayList;
    @SerializedName("idTheLoai")
    @Expose
    private Integer idTheLoai;
    @SerializedName("linkBaiHat")
    @Expose
    private String linkBaiHat;
    @SerializedName("luotThich")
    @Expose
    private Integer luotThich;
    @SerializedName("tenBaiHat")
    @Expose
    private String tenBaiHat;

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

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Integer getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(Integer idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public Integer getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(Integer idPlayList) {
        this.idPlayList = idPlayList;
    }

    public Integer getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(Integer idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getLinkBaiHat() {
        return linkBaiHat;
    }

    public void setLinkBaiHat(String linkBaiHat) {
        this.linkBaiHat = linkBaiHat;
    }

    public Integer getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(Integer luotThich) {
        this.luotThich = luotThich;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

        dest.writeInt(idAlbum);
        dest.writeInt(idBaiHat);
        dest.writeInt(idPlayList);
        dest.writeInt(idTheLoai);
        dest.writeInt(luotThich);

        dest.writeString(caSi);
        dest.writeString(hinhBaiHat);
        dest.writeString(tenBaiHat);
        dest.writeString(linkBaiHat);
    }
}
