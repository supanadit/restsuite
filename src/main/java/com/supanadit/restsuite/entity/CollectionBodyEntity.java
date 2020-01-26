package com.supanadit.restsuite.entity;

import javax.persistence.*;

@Entity
@Table(name = "collection_body")
public class CollectionBodyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "collection_id")
    private int collectionID;

    @Column(name = "type")
    private String type;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    public CollectionBodyEntity() {
    }

    public CollectionBodyEntity(int collectionID, String type, String key, String value) {
        this.collectionID = collectionID;
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

    public int getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(int collection_id) {
        this.collectionID = collection_id;
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
