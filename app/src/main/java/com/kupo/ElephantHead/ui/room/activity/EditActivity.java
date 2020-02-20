package com.kupo.ElephantHead.ui.room.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UriUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.AppManager;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.holder.GlideLoader;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.ui.mine.adapter.EditItemAdapter;
import com.kupo.ElephantHead.ui.mine.fragment.ReleaseSuccessFragment;
import com.kupo.ElephantHead.ui.mine.fragment.TextInputFragment;
import com.kupo.ElephantHead.ui.mvp.contract.EditContract;
import com.kupo.ElephantHead.ui.mvp.model.EditItemModel;
import com.kupo.ElephantHead.ui.mvp.presenter.EditPresenter;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;
import com.kupo.ElephantHead.ui.room.model.ReleaseModel;
import com.kupo.ElephantHead.ui.room.model.RoomIDetailsModel;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.widget.CustomProgressDialog;
import com.kupo.ElephantHead.widget.CustomTypeList;
import com.kupo.ElephantHead.widget.FullImage;
import com.lcw.library.imagepicker.ImagePicker;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 编辑发布展位界面
 */
@Route(path = AppConfig.ACTIVITY_URL_MINE_EDIT)
public class EditActivity extends BaseActivity implements EditContract.IEditView {


    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.title_ll)
    LinearLayout titleLl;
    @BindView(R.id.recycler_edit_view)
    RecyclerView recyclerEditView;

    private List<EditItemModel> editItemModelList;
    private EditItemAdapter editItemAdapter;
    private ArrayList<String> mImagePaths;
    private static final int REQUEST_SELECT_IMAGES_CODE = 0x01;
    private static final int REQUEST_SELECT_IMAGES_VIDEO_CODE = 0x02;
    private boolean isFlag = false;
    private HeadViewHolder headViewHolder;
    private EditContract.IEditPresenter editPresenter;
    private File file;
    int count = 0;
    int typeId = 1;
    int orderNum = 0;
    int index = 0;
    int itemId = 1;
    int status = 1;
    private ItemTouchHelper mItemTouchHelper;

    CustomProgressDialog pd;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    private void initData() {
        editPresenter = new EditPresenter();
        editPresenter.attachView(this);
        editPresenter.getCategoryList(CommonUtils.getUserInfo().getToken());
        initHeadData();
        editItemAdapter = new EditItemAdapter(editItemModelList, this);
        recyclerEditView.setLayoutManager(new LinearLayoutManager(this));
        editItemAdapter.addHeaderView(getHeaderView());
        editItemAdapter.addFooterView(getFooterView());
        recyclerEditView.setAdapter(editItemAdapter);
        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = 0;
//                    final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(editItemModelList, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(editItemModelList, i, i - 1);
                    }
                }
                editItemAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }

            /**
             * 重写拖拽可用
             * @return
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            /**
             * 长按选中Item的时候开始调用
             *
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 手指松开的时候还原
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
            }
        });
    //    mItemTouchHelper.attachToRecyclerView(recyclerEditView);
        //移除
        editItemAdapter.setOnItemRemoveListener(new EditItemAdapter.OnItemRemoveListener() {
            @Override
            public void onItemRemoveClick(int position) {
                orderNum--;
                editItemModelList.remove(position);
                editItemAdapter.notifyDataSetChanged();
            }
        });
        //文字录入
        editItemAdapter.setOnItemTextInputListener(new EditItemAdapter.OnItemTextInputListener() {
            @Override
            public void onItemTextInputClick(int position) {
                TextInputFragment textInputFragment = new TextInputFragment();
                Bundle bundle = new Bundle();
                bundle.putString("txt", editItemModelList.get(position).getContent());
                bundle.putInt("position", position);
                textInputFragment.setArguments(bundle);
                textInputFragment.show(getSupportFragmentManager(), "inputText");
            }
        });
    }


    @Override
    protected void onInitPresenters() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        titleRightTxt.setText("发布");
        titleRightTxt.setTextColor(Color.parseColor("#ffcc00"));
        titleRightImg.setVisibility(View.GONE);
        titleLl.setBackgroundColor(getResources().getColor(R.color.white));
        titleTitleTxt.setText("编辑");
        editItemModelList = new ArrayList<>();
        titleReturnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.deleteFile(appDir);
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_ROOM_DETAILS)
                        .withInt("detailsId", itemId)
                        .withInt("status", status)
                        .withString("intentType", "project")
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                finish();
            }
        });
        titleRightTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("categoryId", typeId);
                map.put("id", itemId);
                String title = headViewHolder.editHeadTitleEt.getText().toString().trim();
                if (TextUtils.isEmpty(title) || title.length() > 40) {
                    ToastUtils.showShort("标题不能为空并且标题不能超过40个字符");
                    return;
                } else {
                    map.put("description", headViewHolder.editHeadTitleEt.getText().toString().trim());
                }
                String phone = headViewHolder.editHeadPhoneEt.getText().toString().trim();
                if (TextUtils.isEmpty(phone) || phone.length() > 20) {
                    ToastUtils.showShort("电话微信不能为空并且不能超过20个字符");
                    return;
                } else {
                    map.put("phone", headViewHolder.editHeadPhoneEt.getText().toString().trim());
                }
                String location = headViewHolder.editHeadLocationEt.getText().toString().trim();
                if (TextUtils.isEmpty(location) || location.length() > 50) {
                    ToastUtils.showShort("联系地址不能为空并且不能超过50个字符");
                    return;
                } else {
                    map.put("addressDetail", headViewHolder.editHeadLocationEt.getText().toString().trim());
                }
                if (file != null) {
                    if (editItemModelList.size() < 1) {
                        ToastUtils.showShort("请至少添加一条详情内容");
                        return;
                    }
                    titleRightTxt.setEnabled(true);
                    titleRightTxt.setTextColor(Color.parseColor("#999999"));
                    editPresenter.postEditInfo(CommonUtils.getUserInfo().getToken(), map, MultipartBody.Part
                            .createFormData("multipartFile", file.getName(),
                                    RequestBody.create(MediaType.parse("image/png"), file)));
                } else {
                    ToastUtils.showShort("请选择封面图");
                }

            }
        });
        initData();
    }

    /**
     * 初始化编辑的数据
     */
    private void initHeadData() {
        itemId = getIntent().getIntExtra("itemId", 1);
        status = getIntent().getIntExtra("status", 1);
        editPresenter.getRoomItemDetails(CommonUtils.getUserInfo().getToken(), itemId);

    }

    /**
     * 获取头部
     *
     * @return
     */
    private View getHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.activity_edit_head, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 60, 0, 0);
        view.setLayoutParams(layoutParams);
        headViewHolder = new HeadViewHolder(view);
        headViewHolder.addHeadLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFlag = true;
                ImagePicker.getInstance()
                        .setTitle("图片选择")//设置标题
                        .showCamera(true)//设置是否显示拍照按钮
                        .showImage(true)//设置是否展示图片
                        .showVideo(false)//设置是否展示视频
                        .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
                        .setSingleType(false)//设置图片视频不能同时选择
                        .setImageLoader(new GlideLoader())//设置自定义图片加载器
                        .start(EditActivity.this, REQUEST_SELECT_IMAGES_CODE);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
            }
        });
        return view;
    }

    File appDir = new File(Environment.getExternalStorageDirectory(), "dxg");

    @Override
    public void postEditInfoSuccess(ReleaseModel releaseModel) {
        if (releaseModel.getCode() == 0) {
            if (editItemModelList.size() == count) {
                ToastUtils.showShort("发布内容成功");
                return;
            }
            if (TextUtils.isEmpty(releaseModel.getData().getId() + "")) {
                ToastUtils.showShort("当前发布失败，请检查是否遗漏");
                return;
            }
            pd = new CustomProgressDialog(this);
            pd.setCanceledOnTouchOutside(false);
            pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    progressShow = false;
                }
            });
            pd.show();
            LogUtils.e(editItemModelList);
            for (int i = 0; i < editItemModelList.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("itemId", releaseModel.getData().getId());
                map.put("seq", i + 1);
                count++;
                if (editItemModelList.get(i).getType() == 1) {
                    map.put("type", 1);
                    editPresenter.postEditDetailsInfo(CommonUtils.getUserInfo().getToken(), map, MultipartBody.Part
                            .createFormData("description", editItemModelList.get(i).getContent()));
                } else if (editItemModelList.get(i).getType() == 2) {
                    map.put("type", 2);
                    File fileNew = null;
                    if (editItemModelList.get(i).getPicUrl().startsWith("https://")) {
                        fileNew = new File(appDir + "/" + editItemModelList.get(i).getOrderNum() + ".png");
                    } else {
                        fileNew = new File(editItemModelList.get(i).getPicUrl());
                    }
                    editPresenter.postEditDetailsInfo(CommonUtils.getUserInfo().getToken(), map, MultipartBody.Part
                            .createFormData("multipartFile", fileNew.getName(),
                                    RequestBody.create(MediaType.parse("image/png"), fileNew)));
                } else if (editItemModelList.get(i).getType() == 3) {
                    map.put("type", 3);
                    File fileVideo = null;
                    if (editItemModelList.get(i).getVideoUrl().startsWith("https://")) {
                        fileVideo = new File(appDir + "/" + editItemModelList.get(i).getOrderNum() + ".mp4");
                    } else {
                        fileVideo = new File(editItemModelList.get(i).getVideoUrl());
                    }
                    editPresenter.postEditDetailsInfo(CommonUtils.getUserInfo().getToken(), map, MultipartBody.Part
                            .createFormData("multipartFile", "temp.MP4",
                                    RequestBody.create(MediaType.parse("application/octet-stream"), fileVideo)));
                }
            }
        } else if (releaseModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
        } else {
            ToastUtils.showShort(releaseModel.getMessage());
        }

    }

    @Override
    public void postEditInfoFailed(int netCode, String msg) {

    }

    @Override
    public void postEditDetailsInfoSuccess(ReleaseModel loginModel) {
        if (loginModel.getCode() == 0) {
            if (index == 0) {
                ReleaseSuccessFragment releaseSuccessFragment = new ReleaseSuccessFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("detailsId", loginModel.getData().getMallItemInfoId());
                releaseSuccessFragment.setArguments(bundle);
                releaseSuccessFragment.show(getSupportFragmentManager(), "releaseFragment");
                pd.dismiss();
                index++;
            }

        }
    }

    @Override
    public void postEditDetailsInfoFailed(int netCode, String msg) {

    }

    @Override
    public void getCategoryListSuccess(CategoryModel categoryModel) {
        if (categoryModel.getCode() == 0) {
            headViewHolder.customTypeList.setItemsData(categoryModel.getData());
            if (categoryModel.getData().size() > 0) {
                typeId = categoryModel.getData().get(0).getId();
            }
        }

    }

    @Override
    public void getCategoryListFailed(int netCode, String msg) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void getRoomItemDetailsSuccess(RoomIDetailsModel roomIDetailsModel) {
        if (roomIDetailsModel.getCode() == 0) {
            headViewHolder.editHeadTitleEt.setText(roomIDetailsModel.getData().getDescription());
            headViewHolder.editHeadLocationEt.setText(roomIDetailsModel.getData().getAddressDetail());
            headViewHolder.editHeadPhoneEt.setText(roomIDetailsModel.getData().getPhone());
            new GlideLoader().loadImage(headViewHolder.editHeadBgIm, roomIDetailsModel.getData().getBannerPath());
            // 点击按钮，开启新的子线程进行网络请求。
            file = CommonUtils.getUrlToFile(roomIDetailsModel.getData().getBannerPath(), "head.png");
            headViewHolder.addBg.setVisibility(View.GONE);
            headViewHolder.editHeadBgIm.setVisibility(View.VISIBLE);
            for (int i = 0; i < roomIDetailsModel.getData().getDetailList().size(); i++) {
                if (roomIDetailsModel.getData().getDetailList().get(i).getType() == 1) {
                    EditItemModel editItemModel = new EditItemModel(roomIDetailsModel.getData().getDetailList().get(i).getDescription(), "", roomIDetailsModel.getData().getDetailList().get(i).getSeq(), "", 1);
                    editItemModelList.add(editItemModel);
                } else if (roomIDetailsModel.getData().getDetailList().get(i).getType() == 2) {
                    CommonUtils.getUrlToFile(roomIDetailsModel.getData().getDetailList().get(i).getFilePath(), roomIDetailsModel.getData().getDetailList().get(i).getSeq() + ".png");
                    EditItemModel editItemModel = new EditItemModel("", roomIDetailsModel.getData().getDetailList().get(i).getFilePath(), roomIDetailsModel.getData().getDetailList().get(i).getSeq(), "", 2);
                    editItemModelList.add(editItemModel);
                } else if (roomIDetailsModel.getData().getDetailList().get(i).getType() == 3) {
                    CommonUtils.getUrlToFile(roomIDetailsModel.getData().getDetailList().get(i).getFilePath(), roomIDetailsModel.getData().getDetailList().get(i).getSeq() + ".mp4");
                    EditItemModel editItemModel = new EditItemModel("", "", roomIDetailsModel.getData().getDetailList().get(i).getSeq(), roomIDetailsModel.getData().getDetailList().get(i).getFilePath(), 3);
                    editItemModelList.add(editItemModel);
                }
            }
            Collections.sort(editItemModelList, new Comparator<EditItemModel>() {
                @Override
                public int compare(EditItemModel bean1, EditItemModel bean2) {
                    return bean1.getOrderNum() - bean2.getOrderNum();
                }
            });
        } else {
            ToastUtils.showShort(roomIDetailsModel.getMessage());
        }
    }

    @Override
    public void getRoomItemDetailsFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    static
    class HeadViewHolder {
        @BindView(R.id.customTypeList)
        CustomTypeList customTypeList;
        @BindView(R.id.edit_head_bg_im)
        FullImage editHeadBgIm;
        @BindView(R.id.edit_head_title_et)
        EditText editHeadTitleEt;
        @BindView(R.id.edit_head_phone_et)
        EditText editHeadPhoneEt;
        @BindView(R.id.edit_head_location_et)
        EditText editHeadLocationEt;
        @BindView(R.id.add_bg)
        ImageView addBg;
        @BindView(R.id.add_head_ll)
        LinearLayout addHeadLl;


        HeadViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 获取底部
     *
     * @return
     */
    private View getFooterView() {
        View view = getLayoutInflater().inflate(R.layout.activity_edit_footer, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);
        view.setLayoutParams(layoutParams);
        FooterViewHolder footerViewHolder = new FooterViewHolder(view);
        footerViewHolder.popupEditTxtLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNum++;
                TextInputFragment textInputFragment = new TextInputFragment();
                textInputFragment.show(getSupportFragmentManager(), "inputText");
            }
        });
        footerViewHolder.popupEditPicLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNum++;
                ImagePicker.getInstance()
                        .setTitle("图片选择")//设置标题
                        .showCamera(true)//设置是否显示拍照按钮
                        .showImage(true)//设置是否展示图片
                        .showVideo(false)//设置是否展示视频
                        .setMaxCount(9)//设置最大选择图片数目(默认为1，单选)
                        .setSingleType(false)//设置图片视频不能同时选择
                        .setImageLoader(new GlideLoader())//设置自定义图片加载器
                        .start(EditActivity.this, REQUEST_SELECT_IMAGES_CODE);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
            }
        });
        footerViewHolder.popupEditVideoLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNum++;
                ImagePicker.getInstance()
                        .setTitle("视频选择")//设置标题
                        .showCamera(false)//设置是否显示拍照按钮
                        .showImage(false)//设置是否展示图片
                        .showVideo(true)//设置是否展示视频
                        .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
                        .setSingleType(false)//设置图片视频不能同时选择
                        .setImageLoader(new GlideLoader())//设置自定义图片加载器
                        .start(EditActivity.this, REQUEST_SELECT_IMAGES_VIDEO_CODE);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
            }
        });
        return view;
    }

    static
    class FooterViewHolder {
        @BindView(R.id.popup_edit_pic_ll)
        LinearLayout popupEditPicLl;
        @BindView(R.id.popup_edit_txt_ll)
        LinearLayout popupEditTxtLl;
        @BindView(R.id.popup_edit_video_ll)
        LinearLayout popupEditVideoLl;

        FooterViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        StringBuffer stringBuffer = new StringBuffer();
        if (requestCode == REQUEST_SELECT_IMAGES_CODE && resultCode == RESULT_OK) {
            mImagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
            if (isFlag) {
                this.imageCut(CommonUtils.getImageContentUri(this, mImagePaths.get(0)));
                isFlag = false;
            } else {
                stringBuffer.append("当前选中图片路径：\n\n");
                for (int i = 0; i < mImagePaths.size(); i++) {
                    stringBuffer.append(mImagePaths.get(i) + "\n\n");
                    EditItemModel editItemModel = new EditItemModel("", mImagePaths.get(i), orderNum, "", 2);
                    editItemModelList.add(editItemModel);
                    editItemAdapter.notifyDataSetChanged();
                }
            }
        } else if (requestCode == REQUEST_SELECT_IMAGES_VIDEO_CODE && resultCode == RESULT_OK) {
            mImagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
            stringBuffer.append("当前选中视频路径：\n\n");
            for (int i = 0; i < mImagePaths.size(); i++) {
                stringBuffer.append(mImagePaths.get(i) + "\n\n");
            }
            try {
                if (CommonUtils.getFileSize(new File(mImagePaths.get(0))) > 10485760) {
                    ToastUtils.showShort("请选择小于10M的视频");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            EditItemModel editItemModel = new EditItemModel("", "", orderNum, mImagePaths.get(0), 3);
            editItemModelList.add(editItemModel);
            editItemAdapter.notifyDataSetChanged();
        } else if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
            //裁剪专用
            final Uri croppedUri = UCrop.getOutput(data);
            try {
                if (croppedUri != null) {
                    Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(croppedUri));
                    headViewHolder.editHeadBgIm.setImageBitmap(bit);
                    headViewHolder.addBg.setVisibility(View.GONE);
                    headViewHolder.editHeadBgIm.setVisibility(View.VISIBLE);
                    //      new GlideLoader().loadPreImage(headViewHolder.editHeadBgIm, mImagePaths.get(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void imageCut(Uri uri) {
        //裁剪后保存到文件中
        file = new File(getCacheDir(), "myCroppedImage.jpg");
        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), "myCroppedImage.jpg"));
        UCrop uCrop = UCrop.of(uri, destinationUri);
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.black));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.black));
        //是否能调整裁剪框
        // options.setFreeStyleCropEnabled(true);
        options.withAspectRatio(16, 9);
        uCrop.withOptions(options);
        uCrop.start(this);
        //     startActivityForResult(intent, REQUEST_SELECT_IMAGES_CROP_CODE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit;
    }


    static
    class ViewHolder {
        @BindView(R.id.popup_edit_pic_ll)
        LinearLayout popupEditPicLl;
        @BindView(R.id.popup_edit_txt_ll)
        LinearLayout popupEditTxtLl;
        @BindView(R.id.popup_edit_video_ll)
        LinearLayout popupEditVideoLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    /**
     * 接受选中类别id
     *
     * @param baseResult
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveCategoryEventBus(BaseResult baseResult) {
        typeId = baseResult.getCode();
    }

    /**
     * 接受返回的输入文字
     *
     * @param singleModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveTextEventBus(SingleModel singleModel) {
        if (singleModel.getIcon() != -1) {
            editItemModelList.set(singleModel.getIcon(), new EditItemModel(singleModel.getTitle(), "", singleModel.getIcon(), "", 1));
            editItemAdapter.notifyDataSetChanged();
        } else {
            editItemModelList.add(new EditItemModel(singleModel.getTitle(), "", orderNum, "", 1));
            editItemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
