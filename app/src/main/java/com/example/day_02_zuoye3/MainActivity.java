package com.example.day_02_zuoye3;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day_02_zuoye3.adapter.RlvMeiNvAdapter;
import com.example.day_02_zuoye3.adapter.VpAdapter;
import com.example.day_02_zuoye3.base.BaseMvpActivity;
import com.example.day_02_zuoye3.bean.MeiNvBean;
import com.example.day_02_zuoye3.model.MeiNvModel;
import com.example.day_02_zuoye3.presenter.MeiNvPresenter;
import com.example.day_02_zuoye3.view.MeiNvView;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MeiNvPresenter, MeiNvView, MeiNvModel> implements MeiNvView, RlvMeiNvAdapter.OnItemLongClick {

    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<MeiNvBean.ResultsBean> mList;
    private RlvMeiNvAdapter mAdapter;
    private MeiNvBean meiNvBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected MeiNvModel initMvpModel() {
        return new MeiNvModel();
    }

    @Override
    protected MeiNvView initMvpView() {
        return this;
    }

    @Override
    protected MeiNvPresenter initMvpPresenter() {
        return new MeiNvPresenter();
    }

    @Override
    protected void initView() {
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mAdapter = new RlvMeiNvAdapter(this, mList);
        mRlv.setAdapter(mAdapter);

        mAdapter.setOnItemLongClick(this);

    }

    @Override
    protected void initData() {
        myPresenter.getData();
    }

    @Override
    public void onFail(String mag) {

    }

    @Override
    public void onSuccess(MeiNvBean meiNvBean) {
        this.meiNvBean = meiNvBean;
        mList.addAll(meiNvBean.getResults());
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnItemLongClick(int position, MeiNvBean.ResultsBean resultsBean) {
        mRlv.setVisibility(View.GONE);
        mVp.setVisibility(View.VISIBLE);

        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {

            View inflate = LayoutInflater.from(this).inflate(R.layout.layout_vp, null);
            TextView tv_num = inflate.findViewById(R.id.tv_num);
            TextView tv = inflate.findViewById(R.id.tv_length);
            ImageView img = inflate.findViewById(R.id.img);

            tv_num.setText(i+1+"");
            tv.setText(mList.size()+"");
            Glide.with(MainActivity.this)
                    .load(meiNvBean.getResults().get(i).getUrl())
                    .into(img);

            views.add(inflate);
        }

        VpAdapter vpAdapter = new VpAdapter(views);
        mVp.setAdapter(vpAdapter);
        vpAdapter.setViews(views);
        vpAdapter.notifyDataSetChanged();
        mVp.setCurrentItem(position);
    }
}
