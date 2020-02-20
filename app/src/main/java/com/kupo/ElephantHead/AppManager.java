package com.kupo.ElephantHead;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by G400 on 2019/8/3.
 * 功能：Activity管理
 * 作者：
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    public AppManager() {
    }

    /**
     * 单一实例(供外部调用的)
     *
     * @return
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前的Activity(堆栈中最后一个压入的)
     *
     * @return
     */
    public Activity currentActivity() {
        if (activityStack != null && !activityStack.empty()) {
            return activityStack.lastElement();
        }
        return null;
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        ArrayList<Activity> delList = new ArrayList<Activity>();
        for (Activity activity : delList) {
            if (activity.getClass().equals(cls)) {
                delList.add(activity);
            }
        }
        for (Activity activity : delList) {
            finishActivity(activity);
        }
    }

    /**
     * 结束所有的Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     *
     * @param context
     */
    public void AppExit(Context context) {
        try {
            //退出程序前要把该保存的数据进行保存操作
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将指定Activity移除堆栈
     *
     * @param activity
     */
    public void removeActivityFromStack(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * 搜索栈中是否存在当前类名Activity
     *
     * @param cls
     * @return
     */
    public boolean contains(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

}
