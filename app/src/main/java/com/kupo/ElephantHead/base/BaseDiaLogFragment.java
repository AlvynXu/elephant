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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by G400 on 2019/8/3.
 * 功能：Fragment基类
 * 作者：
 */
public abstract class BaseDiaLogFragment extends DialogFragment {

    protected Activity mActivity;
    protected View mFragmentView;
    protected ProgressDialog pd;
    protected boolean progressShow;

    public boolean isSavedInstanceState = false;  //是否存在保存的信息
    protected Unbinder unbinder;

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

        if (savedInstanceState != null) {
            isSavedInstanceState = true;
        }
        init();
        onInitPresenters();

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
                pd = new ProgressDialog(getContext());
                pd.setCanceledOnTouchOutside(false);
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        progressShow = false;
                    }
                });
                pd.setMessage(msg);
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
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

}
