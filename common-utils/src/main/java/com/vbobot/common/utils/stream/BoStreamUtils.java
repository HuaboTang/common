package com.vbobot.common.utils.stream;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author Liang.Zhuge
 * @date 2021/1/4
 */
public final class BoStreamUtils {

    public static <T, K, V> Collector<T, Map<K, V>, Map<K, V>> toMap(
            Function<T, K> keyFunction, Function<T, V> valueFunction) {
        return new Collector<T, Map<K, V>, Map<K, V>>() {

            @Override
            public Supplier<Map<K, V>> supplier() {
                return HashMap::new;
            }

            @Override
            public BiConsumer<Map<K, V>, T> accumulator() {
                return (m, t) -> m.put(keyFunction.apply(t), valueFunction.apply(t));
            }

            @Override
            public BinaryOperator<Map<K, V>> combiner() {
                return (m1, m2) -> {
                    m1.putAll(m2);
                    return m1;
                };
            }

            @Override
            public Function<Map<K, V>, Map<K, V>> finisher() {
                return Function.identity();
            }

            @Override
            public Set<Characteristics> characteristics() {
                return EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED);
            }
        };
    }
}
