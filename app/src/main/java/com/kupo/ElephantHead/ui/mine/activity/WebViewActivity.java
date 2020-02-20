package com.kupo.ElephantHead.ui.mine.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;


import butterknife.BindView;

/**
 * @ClassName: WebViewActivity
 * @Description: 公共的webView跳转
 * @Author:
 * @CreateDate: 2019/8/12 15:26
 * @Version: 1.0
 */
@Route(path = AppConfig.ACTIVITY_URL_MAIN_WEB_VIEW)
public class WebViewActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    String href, title;


    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        href = getIntent().getStringExtra("downLoadUrl");
        title = getIntent().getStringExtra("title");
        titleTitleTxt.setText(title);
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            init();
            webView.loadUrl(href);
            //监听返回键
            webView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent keyEvent) {
                    if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) { //只处理一次
                            myLastUrl();
                        }
                        return true;
                    }
                    return false;
                }

            });
        } else {
            ToastUtils.showShort("当前手机版本太低，不好意思");
        }

        titleReturnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    private void init() {
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        setting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        setting.setDomStorageEnabled(true);
        setting.setBuiltInZoomControls(true);
        setting.setBlockNetworkImage(true);
        setting.setJavaScriptEnabled(true);
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        setting.setUseWideViewPort(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        setting.setSupportZoom(true); // 支持缩放
        setting.setLoadWithOverviewMode(true);
        setting.setDisplayZoomControls(false);
        setting.setBuiltInZoomControls(true); // 设置显示缩放按钮
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        if (mDensity == 240) {
            setting.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            setting.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 120) {
            setting.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
            setting.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == DisplayMetrics.DENSITY_TV) {
            setting.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else {
            setting.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }
        // TODO 自动生成的方法存根
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                showLoading("正在加载页面");
                // 在开始加载网页时会回调
                super.onPageStarted(view, url, favicon);
            }

            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                LogUtils.e(title);
                if ("下载地址".equals(title)) {
                    view.loadUrl(url);
                } else {
                    showLoading("正在加载页面");
                    // 拦截 url 跳转,在里边添加点击链接跳转或者操作
                    isTitleChange(url);
                    //不要加上下面注释掉的这句代码，会导致web界面报错
                    // view.loadUrl(url);
                    if (url.startsWith("http:") || url.startsWith("https:")) {
                        return false;
                        //  view.loadUrl(url);
                    }
                    //请务必使用try、catch 因为该处返回的url可能为无效url或者手机没有安转支付宝导致webview闪退
                    try {
                        //通过意图调起支付宝
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                    }

                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                hideLoading();
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed();//接受证书
                //handleMessage(Message msg); 其他处理
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                hideLoading();
                // 加载错误的时候会回调，在其中可做错误处理，比如再请求加载一次，或者提示404的错误页面
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根
                if (newProgress == 100) {
                    progressBar1.setVisibility(View.GONE);//加载完网页进度条消失
                    setting.setBlockNetworkImage(false);
                } else {
                    progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar1.setProgress(newProgress);//设置进度值
                }

            }
        });

    }

    /**
     * 拿到上一页的路径
     */
    private void myLastUrl() {
        WebBackForwardList backForwardList = webView.copyBackForwardList();
        if (backForwardList != null && backForwardList.getSize() != 0) {
            //当前页面在历史队列中的位置
            int currentIndex = backForwardList.getCurrentIndex();
            WebHistoryItem historyItem =
                    backForwardList.getItemAtIndex(currentIndex - 1);
            if (historyItem != null) {
                String backPageUrl = historyItem.getUrl();
//                Logger.t("111").d("拿到返回上一页的url"+backPageUrl);
                webView.goBack();
//              重新判断设置标题
                isTitleChange(backPageUrl);
            }
        }
    }

    /**
     * 判断标题是否改变
     */
    private void isTitleChange(String url) {
        String myurl = "type=sp";
        if (url.contains(myurl)) {
//                  包含说明是内页
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int top = dip2px(this, -35f);
            lp.setMargins(0, top, 0, 0);
            webView.setLayoutParams(lp);
        } else {
//                  不包含说明是外面页
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            lp.setMargins(0, 0, 0, 0);
            webView.setLayoutParams(lp);
        }

    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
