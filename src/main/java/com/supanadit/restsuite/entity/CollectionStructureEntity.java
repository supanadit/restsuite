package com.supanadit.restsuite.entity;

import javax.persistence.*;

@Entity
@Table(name = "collection_structure")
public class CollectionStructureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "collection_id", nullable = false)
    private int collectionID;

    @Column(name = "collection_structure_folder_id")
    private int collectionStructureFolderID;

    public CollectionStructureEntity() {
    }

    public CollectionStructureEntity(int collectionID, int collectionStructureFolderID) {
        this.collectionID = collectionID;
        this.collectionStructureFolderID = collectionStructureFolderID;
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

    public void setCollectionID(int collectionID) {
        this.collectionID = collectionID;
    }

    public int getCollectionStructureFolderID() {
        return collectionStructureFolderID;
    }

    public void setCollectionStructureFolderID(int collectionStructureFolderID) {
        this.collectionStructureFolderID = collectionStructureFolderID;
    }
}
