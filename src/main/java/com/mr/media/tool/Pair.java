package com.mr.media.tool;

/**
 * Created by i321273 on 1/9/17.
 */
public class Pair<T, U> {
    public final T t;
    public final U u;

    public Pair(T t, U u) {
        this.t= t;
        this.u= u;
    }

    public T getFirst() {
        return t;
    }

    public U getSecond() {
        return u;
    }
}
