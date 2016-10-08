package com.kyasar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kadir on 30.09.2016.
 */
@Document(collection = "points")
public class Point {

    @Id
    private String _id;

    private String subject;
    private GeoJsonPoint location;

    public Point() {
    }

    public Point(final String subject, final GeoJsonPoint location) {
        this.subject = subject;
        this.location = location;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }
}
