package com.example.android.aidl_bean;

import android.os.Parcel;
import android.os.Parcelable;

public class AidlObject implements Parcelable {
    public  int age;
    public  String name;
    public  String address;
    public AidlObject(int age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }
    public AidlObject(Parcel source) {
        age = source.readInt();
        name=source.readString();
        address=source.readString();
    }
    public AidlObject() {
       super();
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(age);
            dest.writeString(name);
            dest.writeString(address);
    }
    public void readFromParcel(Parcel source){
        this.age = source.readInt();
        this.name=source.readString();
        this. address=source.readString();
    }
    public static final Creator<AidlObject> CREATOR= new Creator<AidlObject>() {
        @Override
        public AidlObject createFromParcel(Parcel source) {
            return new AidlObject(source);
        }
        @Override
        public AidlObject[] newArray(int size) {
            return new AidlObject[size];
        }
    };



}

