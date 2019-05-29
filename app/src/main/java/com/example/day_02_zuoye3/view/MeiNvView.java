package com.example.day_02_zuoye3.view;

import com.example.day_02_zuoye3.base.BaseView;
import com.example.day_02_zuoye3.bean.MeiNvBean;

public interface MeiNvView extends BaseView {
    void onFail(String mag);

    void onSuccess(MeiNvBean meiNvBean);
}
