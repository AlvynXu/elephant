package com.kupo.ElephantHead.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.greendao.MaskInfo;
import com.kupo.ElephantHead.greendao.MaskInfoOperateDao;
import com.kupo.ElephantHead.greendao.UserInfo;
import com.kupo.ElephantHead.greendao.UserInfoOperateDao;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;
import static com.yalantis.ucrop.util.BitmapLoadUtils.calculateInSampleSize;


/**
 * @ClassName: CommonUtils
 * @Description: 基础公用
 * @Author:
 * @CreateDate: 2019/8/19 14:28
 * @Version: 1.0
 */
public class CommonUtils {

    public static final int VERIFY_SUCCESS = 0;

    private static final int VERIFY_LENGTH_ERROR = 1;

    private static final int VERIFY_TYPE_ERROR = 2;

    /**
     * 验证手机号
     *
     * @param phone
     * @return
     */
    public static boolean judPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("请输入您的电话号码");
            return false;
        } else if (phone.length() != 11) {
            ToastUtils.showShort("您的电话号码位数不正确");
            return false;
        } else {
            String phone_number = phone;
            String num = "[1][345789]\\d{9}";
            if (phone_number.matches(num))
                return true;
            else {
                ToastUtils.showShort("请输入正确的手机号码");
                return false;
            }
        }
    }

    /**
     * 验证验证码
     *
     * @param code
     * @return
     */
    public static boolean judCode(String code) {
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShort("请输入您的验证码");
            return false;
        } else if (code.length() != 4) {
            ToastUtils.showShort("您的验证码位数不正确");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证渠道码,后期可能需要做处理
     *
     * @param channelCode
     * @return
     */
    public static boolean judChannelCode(String channelCode) {
        if (TextUtils.isEmpty(channelCode)) {
            ToastUtils.showShort("请输入您的邀请码");
            return false;
        } else if (channelCode.length() != 6) {
            ToastUtils.showShort("您的邀请位数不正确");
            return false;
        } else {
            return true;
        }
    }

    /**
     * md5加密
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断是否符合身份证号码的规范
     *
     * @param IDCard
     * @return
     */
    public static boolean isIDCard(String IDCard) {
        if (IDCard != null) {
            String IDCardRegex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x|Y|y)$)";
            return IDCard.matches(IDCardRegex);
        }
        return false;
    }

    /**
     * 通过跟后台对接按照md5+base64规则(参数+后台提供的key)生成签名
     *
     * @param str
     * @param key
     * @return
     */
    public static String baseRequestSign(String str, String key) {
        String base64Str = null;
        base64Str = encode(str.getBytes());
        String mapStr = (md5(md5(base64Str).toLowerCase() + key)).toLowerCase();
        return mapStr;
    }


    private static final char last2byte = (char) Integer
            .parseInt("00000011", 2);
    private static final char last4byte = (char) Integer
            .parseInt("00001111", 2);
    private static final char last6byte = (char) Integer
            .parseInt("00111111", 2);
    private static final char lead6byte = (char) Integer
            .parseInt("11111100", 2);
    private static final char lead4byte = (char) Integer
            .parseInt("11110000", 2);
    private static final char lead2byte = (char) Integer
            .parseInt("11000000", 2);
    private static final char[] encodeTable = new char[]{'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'};

    /**
     * base64加密
     *
     * @param from
     * @return
     */
    public static String encode(byte[] from) {
        StringBuffer to = new StringBuffer((int) (from.length * 1.34) + 3);
        int num = 0;
        char currentByte = 0;
        for (int i = 0; i < from.length; i++) {
            num = num % 8;
            while (num < 8) {
                switch (num) {
                    case 0:
                        currentByte = (char) (from[i] & lead6byte);
                        currentByte = (char) (currentByte >>> 2);
                        break;
                    case 2:
                        currentByte = (char) (from[i] & last6byte);
                        break;
                    case 4:
                        currentByte = (char) (from[i] & last4byte);
                        currentByte = (char) (currentByte << 2);
                        if ((i + 1) < from.length) {
                            currentByte |= (from[i + 1] & lead2byte) >>> 6;
                        }
                        break;
                    case 6:
                        currentByte = (char) (from[i] & last2byte);
                        currentByte = (char) (currentByte << 4);
                        if ((i + 1) < from.length) {
                            currentByte |= (from[i + 1] & lead4byte) >>> 4;
                        }
                        break;
                }
                to.append(encodeTable[currentByte]);
                num += 6;
            }
        }
        if (to.length() % 4 != 0) {
            for (int i = 4 - to.length() % 4; i > 0; i--) {
                to.append("=");
            }
        }
        return to.toString();
    }


    /**
     * 当前账号在其他设备登录,进入登录页
     */
    public static void showLabelAlert(Activity mActivity, String msg) {
        if (TextUtils.isEmpty(msg)) {
            msg = "当前账号在其他设备登录，请重新登录";
        }
        new AlertDialog.Builder(mActivity)
                .setTitle("温馨提示")
                .setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserInfoOperateDao.deleteDataBean();
                        UserInfo userInfo = CommonUtils.getUserInfo();
                        //代表推出
                        userInfo.setPromoterId(-1);
                        UserInfoOperateDao.insertUserInfo(userInfo);
                        ARouter.getInstance()
                                .build(AppConfig.ACTIVITY_URL_MAIN_REGISTER)
                                //进入动画
                                .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                .navigation();
                        mActivity.finish();
                    }
                })
                .setCancelable(false)
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                            return true;
                        } else {
                            return false;
                        }

                    }
                })
                .create().show();
    }

    /**
     * 手机是否开启位置服务，如果没有开启那么所有app将不能使用定位功能
     */
    public static boolean isLocServiceEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }

    /**
     * 打开Gps设置界面
     */
    public static void openGpsSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 获取本项目的签名值,并进行判断防止被别人重新打包
     *
     * @param packageName
     * @param context
     * @return
     */
    public static int getSignature(String packageName, Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        int sig = 0;
        try {
            pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            Signature[] s = pi.signatures;
            sig = s[0].hashCode();
        } catch (Exception e) {
            sig = 0;
            e.printStackTrace();
        }
        return sig;
    }

    /**
     * 重置/存储(就是把现有字段存进数据库)
     *
     * @param userBean
     * @return
     */
    public static UserInfo resetUserInfo(LoginModel.DataBean.UserBean userBean) {
        UserInfo sub = new UserInfo();
        UserInfoOperateDao.deleteDataBean();
        UserInfoOperateDao.insertUserInfo(AppConfig.getSqlUserBean(userBean));
        if (UserInfoOperateDao.queryAll().size() > 0) {
            sub = UserInfoOperateDao.queryAll().get(0);
        }
        return sub;
    }


    /**
     * 获取UserInfo
     *
     * @return
     */
    public static UserInfo getUserInfo() {
        UserInfo sub = new UserInfo();
        if (UserInfoOperateDao.queryAll().size() > 0) {
            sub = UserInfoOperateDao.queryAll().get(0);
        }
        return sub;
    }

    /**
     * 获取MaskInfo
     *
     * @return
     */
    public static MaskInfo getMaskInfo() {
        MaskInfo sub = new MaskInfo();
        if (MaskInfoOperateDao.queryMaskAll().size() > 0) {
            sub = MaskInfoOperateDao.queryMaskAll().get(0);
        }
        return sub;
    }

    /**
     * 长度在4~20之间，数字+字母+标点
     *
     * @param password 密码
     * @return {@link #VERIFY_SUCCESS}, {@link #VERIFY_TYPE_ERROR}, {@link #VERIFY_LENGTH_ERROR}
     */
    public static int verifyPassword(@NonNull String password) {

        int length = password.length();
        if (length < 4 || length > 20) {
            return VERIFY_LENGTH_ERROR;
        }

        String regex = "[0-9a-zA-Z\\+]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) return VERIFY_TYPE_ERROR;
        return VERIFY_SUCCESS;
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    /**
     * 调用第三方浏览器打开
     *
     * @param context
     * @param url     要浏览的资源地址
     */
    public static void openBrowser(Context context, String url) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            final ComponentName componentName = intent.resolveActivity(context.getPackageManager()); // 打印Log   ComponentName到底是什么 L.d("componentName = " + componentName.getClassName());
            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
        } else {
            ToastUtils.showShort("请下载浏览器");
        }
    }

    /**
     * BigDecimal转string
     *
     * @param obj
     * @return
     */
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (obj.compareTo(BigDecimal.ZERO) == 0) {
            return "0.00";
        } else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
            return "0" + df.format(obj).toString();
        } else {
            return df.format(obj).toString();
        }
    }

    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            LogUtils.e("获取文件大小不存在!");
        }
        return size;
    }

    /**
     * dip转化成px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转化成dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * px转化成sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转化成px
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }


    /**
     * path转uri
     *
     * @param context
     * @param path
     * @return
     */
    public static Uri getImageContentUri(Context context, String path) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{path}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            // 如果图片不在手机的共享图片数据库，就先把它插入。
            if (new File(path).exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, path);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    /**
     * uri转file
     *
     * @param uri
     * @param context
     * @return
     */
    public static File uriToFile(Uri uri, Context context) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA}, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2以后
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();

            return new File(path);
        } else {
            //Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }


    /**
     * 获取屏幕的像素
     *
     * @param cx
     * @return
     */
    public static String getDisplayMetrics(Context cx) {
        String str = "";
        DisplayMetrics dm = new DisplayMetrics();
        dm = cx.getApplicationContext().getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        float density = dm.density;
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        int dpi = dm.densityDpi;
        int width = (int) (screenWidth / density);
        int height = (int) (screenHeight / density);

        str += "The absolute width:" + String.valueOf(screenWidth) + "pixels\n";
        str += "The absolute heightin:" + String.valueOf(screenHeight)
                + "pixels\n";
        str += "The logical density of the display.:" + String.valueOf(density)
                + "\n";
        str += "X dimension :" + String.valueOf(xdpi) + "pixels per inch\n";
        str += "Y dimension :" + String.valueOf(ydpi) + "pixels per inch\n";
        str += "The densityDpi of the display.:" + String.valueOf(dpi)
                + "\n";
        str += "The width:" + String.valueOf(width) + "dp\n";
        str += "The height:" + String.valueOf(height) + "dp\n";
        return str;
    }

    /**
     * 判断是否为整数
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 300;
    private static long lastClickTime;

    /**
     * 防止重复点击
     *
     * @return
     */
    public static boolean singleOnClick() {
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            lastClickTime = curClickTime;
            return true;
        }
        return false;
    }

    /**
     * 调用微信扫码
     */
    public static void toWeChatScan() {
        try {
            //利用Intent打开微信
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI"));
            intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction("android.intent.action.VIEW");
            startActivity(intent);
        } catch (Exception e) {
            //若无法正常跳转，在此进行错误处理
            ToastUtils.showShort("无法跳转到微信，请检查您是否安装了微信！");
        }
    }

    /**
     * 判断字符串中是否包含字母
     **/
    public static boolean isContainsLetter(String input) {
        if (!TextUtils.isEmpty(input)) {
            Matcher matcher = Pattern.compile(".*[a-zA-Z]+.*").matcher(input);
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * 读取网络文件
     *
     * @param url
     * @param fileName
     * @return
     */
    public static File getUrlToFile(String url, String fileName) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "dxg");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir + "/" + fileName);
        try {
            // 定义获取文件内容的URL
            URL myURL = new URL(url);
            // 打开URL链接
            URLConnection ucon = myURL.openConnection();
            // 使用InputStream，从URLConnection读取数据
            ucon.setConnectTimeout(10 * 1000);
            ucon.connect();
            InputStream inputStream = ucon.getInputStream();
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            file.createNewFile();
            byte[] buffer = new byte[1024 * 10];
            while (true) {
                int len = inputStream.read(buffer);
                if (len == -1) {
                    break;
                }
                arrayOutputStream.write(buffer, 0, len);
            }
            arrayOutputStream.close();
            inputStream.close();
            byte[] data = arrayOutputStream.toByteArray();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 删除的文件夹的所在位置
     *
     * @param file
     */
    //flie：要删除的文件夹的所在位置
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteFile(f);
            }
            //  file.delete();//如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete();
        }
    }

}

