package com.vbobot.common.utils.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Liang.Zhuge
 * @date 2020/9/24
 */
public final class BoListUtils {

    public static <T, R> List<R> map(Collection<T> list, Function<T, R> convert) {
        if (list == null || list.size() == 0) {
            return new ArrayList<>(0);
        }

        return list.stream().map(convert).collect(Collectors.toList());
    }
}
