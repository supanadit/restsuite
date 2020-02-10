package com.supanadit.restsuite.entity;

import javax.persistence.*;

@Entity
@Table(name = "collection_body")
public class CollectionBodyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    private CollectionEntity collection;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "key", nullable = false)
    private String key;

    @Column(name = "value", nullable = false)
    private String value;

    public CollectionBodyEntity() {
    }

    public CollectionBodyEntity(CollectionEntity collection, String type, String key, String value) {
        this.collection = collection;
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CollectionEntity getCollection() {
        return collection;
    }

    public void setCollection(CollectionEntity collection) {
        this.collection = collection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
