package com.kupo.ElephantHead.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.utils.CheckNetwork;
import com.kupo.ElephantHead.widget.CustomProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by G400 on 2019/8/3.
 * 功能：Fragment基类
 * 作者：
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    protected View mFragmentView;
    protected CustomProgressDialog pd;
    protected boolean progressShow;

    public boolean isSavedInstanceState = false;  //是否存在保存的信息
    protected Unbinder unbinder;
    //该页面，是否已经准备完毕（onCreateView是否已经完成加载）
    protected boolean isPrepared;
    //该fragment是否已经执行过懒加载
    protected boolean isLazyLoad;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, viewRoot);
        return viewRoot;
    }

    /**
     * 得到布局
     *
     * @return
     */
    protected abstract int getLayoutId();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        mFragmentView = this.getView();
        isPrepared = true;
        if (savedInstanceState != null) {
            isSavedInstanceState = true;
        }
        init();
        lazyLoad();
    }

    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared && !isLazyLoad) {
            isLazyLoad = true;
            onInitPresenters();
        } else {
            isLazyLoad = false;
        }
    }

    protected abstract void init();

    protected abstract void onInitPresenters();

    public void showLoading() {
        showLoading("");
    }

    public void showLoading(String msg) {
        if (!progressShow) {
            progressShow = true;
            if (pd == null) {
                pd = new CustomProgressDialog(mActivity, msg);
                pd.setCanceledOnTouchOutside(false);
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        progressShow = false;
                    }
                });
            }
            pd.show();
        }
    }

    public void hideLoading() {
        if (pd != null) {
            pd.dismiss();
        }
        progressShow = false;
    }

    @Override
    public void onDestroyView() {
//        unbinder.unbind();
        super.onDestroyView();
    }

}
