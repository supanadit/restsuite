package com.supanadit.restsuite.entity;

import javax.persistence.*;

@Entity
@Table(name = "collection_structure")
public class CollectionStructureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_structure_folder_id")
    private CollectionStructureFolderEntity collectionStructureFolderEntity;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    private CollectionEntity collectionEntity;

    public CollectionStructureEntity() {
    }

    public CollectionStructureEntity(CollectionEntity collectionEntity, CollectionStructureFolderEntity collectionStructureFolderEntity) {
        this.collectionEntity = collectionEntity;
        this.collectionStructureFolderEntity = collectionStructureFolderEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CollectionStructureFolderEntity getCollectionStructureFolderEntity() {
        return collectionStructureFolderEntity;
    }

    public void setCollectionStructureFolderEntity(CollectionStructureFolderEntity collectionStructureFolderEntity) {
        this.collectionStructureFolderEntity = collectionStructureFolderEntity;
    }

    public CollectionEntity getCollectionEntity() {
        return collectionEntity;
    }

    public void setCollectionEntity(CollectionEntity collectionEntity) {
        this.collectionEntity = collectionEntity;
    }
}
