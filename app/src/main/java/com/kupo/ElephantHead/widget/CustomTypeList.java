package com.kupo.ElephantHead.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.widget.PopupWindowCompat;

import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义下拉类别列表
 */
public class CustomTypeList extends LinearLayout {
    private TextView editText;
    private ImageView imageView;
    private PopupWindow popupWindow = null;
    private List<CategoryModel.DataBean> dataList = new ArrayList<CategoryModel.DataBean>();
    private View mView;
    private Window window;

    public CustomTypeList(Context context) {
        this(context, null);
    }

    public CustomTypeList(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTypeList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public void initView() {
        String infServie = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) getContext().getSystemService(infServie);
        View view = layoutInflater.inflate(R.layout.down_list_view, this, true);
        editText = (TextView) findViewById(R.id.edit_head_category_tv);
        imageView = (ImageView) findViewById(R.id.edit_head_category_choose_im);
        this.setOnClickListener(new OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (popupWindow == null) {
                    showPopWindow();
                } else {
                    closePopWindow();
                }
            }
        });
    }

    /**
     * 打开下拉列表弹窗
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showPopWindow() {
        // 加载popupWindow的布局文件
        String infServie = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) getContext().getSystemService(infServie);
        View contentView = layoutInflater.inflate(R.layout.popup_edit_type, null, false);
        ListView listView = (ListView) contentView.findViewById(R.id.listView);
        listView.setAdapter(new XCDropDownListAdapter(getContext(), dataList));
        popupWindow = new PopupWindow(contentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.white));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(findViewById(R.id.edit_ll), 0, 0, Gravity.CLIP_HORIZONTAL);

    }

    @SuppressWarnings("ResourceType")
    private static int makeDropDownMeasureSpec(int measureSpec) {
        int mode;
        if (measureSpec == ViewGroup.LayoutParams.WRAP_CONTENT) {
            mode = View.MeasureSpec.UNSPECIFIED;
        } else {
            mode = View.MeasureSpec.EXACTLY;
        }
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(measureSpec), mode);
    }

    /**
     * 关闭下拉列表弹窗
     */
    private void closePopWindow() {
        popupWindow.dismiss();
        popupWindow = null;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setItemsData(List<CategoryModel.DataBean> list) {
        dataList = list;
        editText.setText(list.get(0).getTitle());
    }

    /**
     * 数据适配器
     *
     * @author caizhiming
     */
    class XCDropDownListAdapter extends BaseAdapter {

        Context mContext;
        List<CategoryModel.DataBean> mData;
        LayoutInflater inflater;

        public XCDropDownListAdapter(Context ctx, List<CategoryModel.DataBean> data) {
            mContext = ctx;
            mData = data;
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 自定义视图
            ListItemView listItemView = null;
            if (convertView == null) {
                // 获取list_item布局文件的视图
                convertView = inflater.inflate(R.layout.item_type_lv, null);
                listItemView = new ListItemView();
                // 获取控件对象
                listItemView.tv = (TextView) convertView.findViewById(R.id.tv);
                listItemView.layout = (LinearLayout) convertView.findViewById(R.id.layout_container);
                // 设置控件集到convertView
                convertView.setTag(listItemView);
            } else {
                listItemView = (ListItemView) convertView.getTag();
            }
            // 设置数据
            listItemView.tv.setText(mData.get(position).getTitle());
            final String text = mData.get(position).getTitle();
            listItemView.layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new BaseResult(mData.get(position).getId()));
                    editText.setText(text);
                    closePopWindow();
                }
            });
            return convertView;
        }

    }

    private static class ListItemView {
        TextView tv;
        LinearLayout layout;
    }

}
