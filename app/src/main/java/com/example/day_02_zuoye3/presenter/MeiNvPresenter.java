package com.example.day_02_zuoye3.presenter;

import com.example.day_02_zuoye3.base.BasePresenter;
import com.example.day_02_zuoye3.base.CallBack;
import com.example.day_02_zuoye3.bean.MeiNvBean;
import com.example.day_02_zuoye3.model.MeiNvModel;
import com.example.day_02_zuoye3.view.MeiNvView;

public class MeiNvPresenter extends BasePresenter<MeiNvView, MeiNvModel> {


    public void getData() {
        myModel.getData(new CallBack<MeiNvBean>() {
            @Override
            public void onSuccess(MeiNvBean meiNvBean) {
                myView.onSuccess(meiNvBean);
            }

            @Override
            public void onFail(String mag) {
                myView.onFail(mag);
            }
        });
    }
}
