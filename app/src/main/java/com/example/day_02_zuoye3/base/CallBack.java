package com.example.day_02_zuoye3.base;

public interface CallBack<T> {

    void onSuccess(T t);
    void onFail(String mag);
}
