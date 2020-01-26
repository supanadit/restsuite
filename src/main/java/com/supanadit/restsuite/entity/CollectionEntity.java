package com.supanadit.restsuite.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "collection")
public class CollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "project_id")
    private int projectID;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "method")
    private String method;

    @Column(name = "body_type")
    private String bodyType;

    @Column(name = "body_raw_type")
    private String bodyRawType;

    @Column(name = "body_raw_value")
    @Type(type = "text")
    private String bodyRawValue;

    public CollectionEntity() {
    }

    public CollectionEntity(String title, String url, String method, String bodyType, String bodyRawType, String bodyRawValue) {
        this.title = title;
        this.url = url;
        this.method = method;
        this.bodyType = bodyType;
        this.bodyRawType = bodyRawType;
        this.bodyRawValue = bodyRawValue;
    }

    public CollectionEntity(int projectID, String title, String url, String method, String bodyType, String bodyRawType, String bodyRawValue) {
        this(title, url, method, bodyType, bodyRawType, bodyRawValue);
        this.projectID = projectID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getBodyRawType() {
        return bodyRawType;
    }

    public void setBodyRawType(String bodyRawType) {
        this.bodyRawType = bodyRawType;
    }

    public String getBodyRawValue() {
        return bodyRawValue;
    }

    public void setBodyRawValue(String bodyRawValue) {
        this.bodyRawValue = bodyRawValue;
    }
}
