package com.supanadit.restsuite.model;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    protected String name;
    protected List<EnvironmentItem> environmentItem;

    public Environment(String name) {
        this.name = name;
        this.environmentItem = new ArrayList<>();
    }

    public void addEnvironmentItem(EnvironmentItem environmentItem) {
        this.environmentItem.add(environmentItem);
    }
}
