package com.liweiyang.lwyFrame.util.design;

@FunctionalInterface
public interface Base<T, R> {

    R changeModel(T t);
}
