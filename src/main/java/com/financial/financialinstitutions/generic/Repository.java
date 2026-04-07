package com.financial.financialinstitutions.generic;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {

    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    public T get(int index) {
        return items.get(index);
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Repository is empty");
        }
        return items.get(0);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<T> getAll() {
        return items;
    }

}
