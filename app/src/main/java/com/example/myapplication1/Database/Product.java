package com.example.myapplication1.Database;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "products_table")
//@Entity(tableName = "product_table") pierwsza wersja
public class Product implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int IdProduct;
    @NonNull
    private String name;

    private int price;
    private int count;
    private boolean bought;

    public  Product(){}

    public Product(String name, int price, int count, boolean bought) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.bought = bought;
    }

    protected Product(Parcel in) {
        IdProduct = in.readInt();
        name = in.readString();
        price = in.readInt();
        count = in.readInt();
        bought = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(IdProduct);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeInt(count);
        dest.writeByte((byte) (bought ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return this.getName()+ " Ilość: "+ this.getCount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
    public int getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(int idProduct) {
        IdProduct = idProduct;
    }

}
