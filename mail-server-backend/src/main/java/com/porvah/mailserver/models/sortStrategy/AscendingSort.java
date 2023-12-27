package com.porvah.mailserver.models.sortStrategy;

import com.porvah.mailserver.interfaces.ROMail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AscendingSort<T extends ROMail> implements SortStrategy<T> {
    @Override
    public List<T> sort(List<T> list) {
        List<T> result = new ArrayList<T>(list);
        result.sort(Comparator.comparingInt(T::getId));
        return  result;
    }
}
