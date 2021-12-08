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

    public Song(String id, int idBaiHat, String tenBaiHat, String hinhBaiHat, String tenCaSi, String linkBaiHat, Integer luotThich, Integer idTheLoai, Integer idPlayList, Integer idAlbum) {
        this.id = id;
        this.idBaiHat = idBaiHat;
        this.tenBaiHat = tenBaiHat;
        this.hinhBaiHat = hinhBaiHat;
        this.caSi = tenCaSi;
        this.linkBaiHat = linkBaiHat;
        this.luotThich = luotThich;
        this.idTheLoai = idTheLoai;
        this.idPlayList = idPlayList;
        this.idAlbum = idAlbum;
    }

    protected Song(Parcel in) {
        id = in.readString();
        caSi = in.readString();
        hinhBaiHat = in.readString();
        idAlbum = in.readInt();
        idBaiHat = in.readInt();
        idPlayList = in.readInt();
        idTheLoai = in.readInt();
        linkBaiHat = in.readString();
        luotThich = in.readInt();
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
        dest.writeInt(idAlbum);
        dest.writeInt(idBaiHat);
        dest.writeInt(idPlayList);
        dest.writeInt(idTheLoai);
        dest.writeString(linkBaiHat);
        dest.writeInt(luotThich);
        dest.writeString(tenBaiHat);
    }
}
