package com.supanadit.restsuite.entity;

import javax.persistence.*;

@Entity
@Table(name = "collection_structure_folder")
public class CollectionStructureFolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "parentID")
    private int parentID;

    @Column(name = "name", nullable = false)
    private String name;

    public CollectionStructureFolderEntity() {
    }

    public CollectionStructureFolderEntity(int parentID, String name) {
        this.parentID = parentID;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
