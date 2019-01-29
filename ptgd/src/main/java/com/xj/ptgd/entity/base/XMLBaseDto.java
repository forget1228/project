package com.xj.ptgd.entity.base;

public interface XMLBaseDto<T,V> {
    void setHead(T v);
    T getHead();
    void setBody(V v);
    V getBody();
}
