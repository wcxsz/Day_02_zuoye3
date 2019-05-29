package com.example.day_02_zuoye3.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseModel {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    public void destroy() {
        mCompositeDisposable.clear();
    }
}
