package com.kupo.ElephantHead.ui.mine.fragment;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseDiaLogFragment;
import com.kupo.ElephantHead.ui.home.model.SingleModel;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;


/**
 * @ClassName: TextInputFragment
 * @Description: 编辑文字录入
 * @Author:
 * @CreateDate: 2019/8/14 10:05
 * @Version: 1.0
 */
public class TextInputFragment extends BaseDiaLogFragment {

    private Window window;
    @BindView(R.id.text_input_et)
    EditText textInputEt;
    @BindView(R.id.text_input_tv)
    TextView textInputTv;
    int position = -1;

    @Override
    protected void onInitPresenters() {
        textInputTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = textInputEt.getText().toString().trim();
                if (TextUtils.isEmpty(content) || content.length() > 500) {
                    ToastUtils.showShort("当前内容不能超过500个字符");
                    return;
                } else {
                    EventBus.getDefault().post(new SingleModel(textInputEt.getText().toString().trim(), position, true));
                    dismiss();
                }
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_text_input;
    }

    @Override
    protected void init() {
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        if (bundle != null) {
            String txt = bundle.getString("txt");
            position = bundle.getInt("position");
            if (!TextUtils.isEmpty(txt)) {
                textInputEt.setText(txt);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // 下面这些设置必须在此方法(onStart())中才有效
        window = getDialog().getWindow();
        // 如果不设置这句代码, 那么弹框就会与四边都有一定的距离
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        // 如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
        params.width = getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(params);

    }
}



