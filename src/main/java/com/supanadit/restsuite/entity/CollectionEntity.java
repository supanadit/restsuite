package com.supanadit.restsuite.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "collection")
public class CollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "structure_id")
    private CollectionStructureEntity collectionStructure;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "method", nullable = false)
    private String method;

    @Column(name = "body_type", nullable = false)
    private String bodyType;

    @Column(name = "body_raw_type", nullable = false)
    private String bodyRawType;

    @Column(name = "body_raw_value")
    @Type(type = "text")
    private String bodyRawValue;

    @OneToMany(mappedBy = "collection", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CollectionHeaderEntity> headers = new ArrayList<>();

    @OneToMany(mappedBy = "collection", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CollectionBodyEntity> body = new ArrayList<>();

    public CollectionEntity() {
    }

    public CollectionEntity(String title, String url, String method, String bodyType, String bodyRawType, String bodyRawValue) {
        this(null, title, url, method, bodyType, bodyRawType, bodyRawValue);
    }

    public CollectionEntity(ProjectEntity project, String title, String url, String method, String bodyType, String bodyRawType, String bodyRawValue) {
        this.title = title;
        this.url = url;
        this.method = method;
        this.bodyType = bodyType;
        this.bodyRawType = bodyRawType;
        this.bodyRawValue = bodyRawValue;
        this.project = project;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
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

    public CollectionStructureEntity getCollectionStructure() {
        return collectionStructure;
    }

    public void setCollectionStructure(CollectionStructureEntity collectionStructure) {
        this.collectionStructure = collectionStructure;
    }

    public List<CollectionHeaderEntity> getHeaders() {
        return headers;
    }

    public void setHeaders(List<CollectionHeaderEntity> headers) {
        this.headers = headers;
    }

    public List<CollectionBodyEntity> getBody() {
        return body;
    }

    public void setBody(List<CollectionBodyEntity> body) {
        this.body = body;
    }
}
