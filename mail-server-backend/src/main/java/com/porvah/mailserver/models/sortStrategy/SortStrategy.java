package com.porvah.mailserver.models.sortStrategy;

import com.porvah.mailserver.interfaces.ROMail;

import java.util.List;

public interface SortStrategy<T> {
    public List<T> sort(List<T> list);

}
