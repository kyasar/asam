package com.kyasar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by kadir on 25.09.2016.
 */
@Document(collection="products")
public class Product {
    @Id
    private String _id;
    private String name;
    private String barcode;
    private List<Point> points;

    public Product() {
    }

    public Product(String name, String barcode) {
        this.name = name;
        this.barcode = barcode;
    }

    public Product(String name, String barcode, List<Point> points) {
        this.name = name;
        this.barcode = barcode;
        this.points = points;
    }

    /*public Product(String _id, int no, String name, String barcode) {
        this._id = _id;
        this.no = no;
        this.name = name;
        this.barcode = barcode;
    }

    public Product(int no, String name, String barcode) {
        this.no = no;
        this.name = name;
        this.barcode = barcode;
    }*/

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
