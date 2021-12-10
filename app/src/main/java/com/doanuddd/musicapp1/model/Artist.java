package com.doanuddd.musicapp1.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist implements Parcelable  {

    @SerializedName("IdNgheSi")
    @Expose
    private String idNgheSi;
    @SerializedName("TenNgheSi")
    @Expose
    private String tenNgheSi;
    @SerializedName("HinhNgheSi")
    @Expose
    private String hinhNgheSi;

    public String getIdNgheSi() {
        return idNgheSi;
    }

    public void setIdNgheSi(String idNgheSi) {
        this.idNgheSi = idNgheSi;
    }

    public String getTenNgheSi() {
        return tenNgheSi;
    }

    public void setTenNgheSi(String tenNgheSi) {
        this.tenNgheSi = tenNgheSi;
    }

    public String getHinhNgheSi() {
        return hinhNgheSi;
    }

    public void setHinhNgheSi(String hinhNgheSi) {
        this.hinhNgheSi = hinhNgheSi;
    }

    public Artist() {

    }

    protected Artist(Parcel in) {
        idNgheSi = in.readString();
        tenNgheSi = in.readString();
        hinhNgheSi = in.readString();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(idNgheSi);
        dest.writeString(tenNgheSi);
        dest.writeString(hinhNgheSi);
    }
}
