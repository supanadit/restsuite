package com.supanadit.restsuite.entity;

import javax.persistence.*;

@Entity
@Table(name = "collection_header")
public class CollectionHeaderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "collection_id", nullable = false)
    private int collectionID;

    @Column(name = "key", nullable = false)
    private String key;

    @Column(name = "value", nullable = false)
    private String value;

    public CollectionHeaderEntity() {
    }

    public CollectionHeaderEntity(int collectionID, String key, String value) {
        this.collectionID = collectionID;
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(int collection_id) {
        this.collectionID = collection_id;
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
