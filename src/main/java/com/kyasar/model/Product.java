package com.kyasar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kadir on 25.09.2016.
 */
@Document(collection="products")
public class Product {
    @Id
    private String _id;
    private int no;
    private String name;
    private String barcode;

    public Product() {
    }

    public Product(String _id, int no, String name, String barcode) {
        this._id = _id;
        this.no = no;
        this.name = name;
        this.barcode = barcode;
    }

    public Product(String name, String barcode) {
        this.name = name;
        this.barcode = barcode;
    }

    public Product(int id, String name, String barcode) {
        this.no = id;
        this.name = name;
        this.barcode = barcode;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int id) {
        this.no = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
