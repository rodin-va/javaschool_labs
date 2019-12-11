package org.javaschool.lab10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListStream<T> {
    private List<T> list;

    public ListStream(List<? extends T> list) {
        this.list = new ArrayList(list);
    }

    public static <T extends Object> ListStream of(List<? extends T> list) {
        return new ListStream<T>(list);
    }

    public ListStream filter(Predicate<T> predicate) {
        for (int i = 0; i < list.size(); ++i) {
            if (!predicate.test(list.get(i))) {
                list.remove(i);
                --i;
            }
        }
        return this;
    }

    public ListStream transform(Function<T, T> mapper) {
        ArrayList<T> tmp = new ArrayList<T>();
        this.list.forEach(item -> {
            tmp.add((T) mapper.apply(item));
        });
        this.list = tmp;
        return this;
    }

    public <R1 extends Object, R2 extends Object> Map toMap(
            Function<T, R1> keyMapper
            , Function<T, R2> valueMapper) {
        HashMap<R1, R2> tmp = new HashMap<>();
        this.list.forEach(item -> {
            tmp.put(keyMapper.apply(item), valueMapper.apply(item));
        });

        return tmp;
    }
}

