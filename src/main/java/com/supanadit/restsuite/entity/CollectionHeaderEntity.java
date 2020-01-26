package com.supanadit.restsuite.entity;

import javax.persistence.*;

@Entity
@Table(name = "collection_header")
public class CollectionHeaderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "collection_id")
    private int collection_id;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    public CollectionHeaderEntity() {
    }

    public CollectionHeaderEntity(int collection_id, String key, String value) {
        this.collection_id = collection_id;
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(int collection_id) {
        this.collection_id = collection_id;
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
