package com.liweiyang.lwyFrame.util;

import java.util.Objects;
import java.util.function.Predicate;

public interface LeYang<R, T> {

    T compareBean(R r);

    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> other.test(t);
    }
}
