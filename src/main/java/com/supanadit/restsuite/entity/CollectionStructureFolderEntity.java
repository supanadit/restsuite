package com.supanadit.restsuite.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "collection_structure_folder")
public class CollectionStructureFolderEntity {
    public final static String ROOT_FOLDER = "ROOT";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(cascade = CascadeType.ALL)
    private CollectionStructureFolderEntity parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CollectionStructureFolderEntity> child = new ArrayList<>();

    @OneToMany(mappedBy = "collectionStructureFolderEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CollectionStructureEntity> structure = new ArrayList<>();

    @Column(name = "name", nullable = false)
    private String name;

    public CollectionStructureFolderEntity() {
    }

    public CollectionStructureFolderEntity(String name) {
        this(null, name);
    }

    public CollectionStructureFolderEntity(CollectionStructureFolderEntity parent, String name) {
        this.parent = parent;
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

    public CollectionStructureFolderEntity getParent() {
        return parent;
    }

    public void setParent(CollectionStructureFolderEntity parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CollectionStructureFolderEntity> getChild() {
        return child;
    }

    public void setChild(List<CollectionStructureFolderEntity> child) {
        this.child = child;
    }

    public List<CollectionStructureEntity> getStructure() {
        return structure;
    }

    public void setStructure(List<CollectionStructureEntity> structure) {
        this.structure = structure;
    }
}
