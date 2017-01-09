package com.kyasar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="products")
public class Product {
    @Id
    @Indexed
    private String _id;

    private String name;

    @Indexed
    private String barcode;

    @GeoSpatialIndexed
    private double[] location;

    public Product(String name, String barcode, double lat, double log) {
        location = new double[2];
        location[0] = lat;
        location[1] = log;
        this.name = name;
        this.barcode = barcode;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public Product() {
    }

    public Product(String name, String barcode) {
        this.name = name;
        this.barcode = barcode;
    }

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

}
