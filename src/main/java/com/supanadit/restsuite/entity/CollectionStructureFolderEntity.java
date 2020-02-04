package com.supanadit.restsuite.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "collection_structure_folder")
public class CollectionStructureFolderEntity {
    public final static String ROOT_FOLDER = "ROOT";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private CollectionStructureFolderEntity parentFolder;

    @Column(name = "name", nullable = false)
    private String name;

    public CollectionStructureFolderEntity() {
    }

    public CollectionStructureFolderEntity(String name) {
        this(null, name);
    }

    public CollectionStructureFolderEntity(CollectionStructureFolderEntity parentFolder, String name) {
        this.parentFolder = parentFolder;
        this.name = name;
    }

    public static CollectionStructureFolderEntity RootFolder() {
        return new CollectionStructureFolderEntity(null, "Root");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CollectionStructureFolderEntity getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(CollectionStructureFolderEntity parentFolder) {
        this.parentFolder = parentFolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
