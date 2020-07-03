package com.liweiyang.lwyFrame.util.base.mode.tree;

public interface ModelBase<T, K> {

    void insert();

    T find(K key);

    T delete(K key);
}
