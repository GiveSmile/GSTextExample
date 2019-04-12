package com.hs.administrator.test.widget.UpdataDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hs.administrator.test.R;


/**
 * @auther : SJC
 * @time : 2018/7/9 16:47
 * @describe : 下载完自动更新
 */

public class AutoInstallDialog extends Dialog {

    private Context mContext; // 上下文

    private ProgressBar mProgress; // 进度条

    private TextView mTvProgress; // 进度

    private String mUrl; // 下载链接

    private TaskInfoBean info;//任务信息

    private String apkName;//下载的apk名

    private DownloadRunnable runnable;//下载任务

    //用于更新进度的Handler
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //使用Handler制造一个200毫秒为周期的循环
            handler.sendEmptyMessageDelayed(1, 200);
            //计算下载进度
            int l = (int) ((float) info.getCompletedLen() / (float) info.getContentLen() * 100);
            //设置进度条进度
            mProgress.setProgress(l);
            mTvProgress.setText("新版本正在努力更新中，请稍后..." + l + "%");
            if (l >= 100) {//当进度>=100时，取消Handler循环
                handler.removeCallbacksAndMessages(null);
                dismiss();
            }
            return true;
        }
    });

    public AutoInstallDialog(@NonNull Context context, @StyleRes int themeResId, String url,String apkName) {
        super(context, themeResId);
        this.mContext = context;
        this.mUrl = url;
        this.apkName = apkName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_install);


        setCanceledOnTouchOutside(false);
        setCancelable(false);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        win.setAttributes(lp);

        initView();
        setDownload();
        startDownload();
    }

    private void initView() {
        mProgress = findViewById(R.id.progress);
        mTvProgress = findViewById(R.id.tv_progress);
    }

    private void setDownload() {
        //实例化任务信息对象
        info = new TaskInfoBean(apkName
                , Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/"
                , mUrl);
        //设置进度条的最大值
        mProgress.setMax(100);
    }

    /**
     * 开始下载按钮监听
     *
     * @param
     */
    public void startDownload() {
        //创建下载任务
        runnable = new DownloadRunnable(mContext, info);
        //开始下载任务
        new Thread(runnable).start();
        //开始Handler循环
        handler.sendEmptyMessageDelayed(1, 200);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        //在Activity销毁时移除回调和msg，并置空，防止内存泄露
//        if (handler != null) {
//            handler.removeCallbacksAndMessages(null);
//            handler = null;
//        }
    }

    /**
     * 停止下载按钮监听
     *
     * @param
     */
    public void stopDownload() {
        //调用DownloadRunnable中的stop方法，停止下载
        if (runnable != null) {
            runnable.stop();
            runnable = null;//强迫症，不用的对象手动置空
        }
    }

    /**
     * 停止线程
     */
    public void stopHandler() {
        //在Activity销毁时移除回调和msg，并置空，防止内存泄露
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }

//    public interface OnDownLoadCallback {
//        void onStart(View view);
//        void onStop(View view);
//    }
//
//    private OnDownLoadCallback onDownLoadCallback;
//
//    public void setOnDownLoadCallback(OnDownLoadCallback onDownLoadCallback) {
//        this.onDownLoadCallback = onDownLoadCallback;
//    }

}
