package com.hs.administrator.test.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;
import com.hs.administrator.test.R;
import com.hs.administrator.test.testObserver.Bee;
import com.hs.administrator.test.testObserver.Flower;
import com.hs.administrator.test.testObserver.Insect;
import com.hs.administrator.test.testObserver.Plant;
import com.hs.administrator.test.widget.AlignTextView;
import com.hs.administrator.test.widget.ShineTextView;
import com.tencent.stat.StatService;
import com.videoplayer.JavaActivity;
import com.videoplayer.UrlInfo;
import com.videoplayer.UrlInfoService;

import java.io.File;
import java.util.Properties;

import butterknife.Bind;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "fafafafafa";
    @Bind(R.id.tv_1)
    ShineTextView mTv1;//隐藏布局 非Gson 仿 淘宝购物车收藏删除布局
    @Bind(R.id.tv_2)
    ShineTextView mTv2;//圆角图片
    @Bind(R.id.tv_3)//评论分类
            ShineTextView mTv3;
    @Bind(R.id.tv_4)
    AlignTextView mTvPlayPhone;//拨打电话
    @Bind(R.id.tv_5)
    TextView mTvBack; //监听物理返回
    @Bind(R.id.tv_6)
    TextView mTv6;
    @Bind(R.id.tv_7)
    TextView mTv7;
    @Bind(R.id.tv_8)
    TextView mTv8;
    @Bind(R.id.tv_rxbus)
    TextView mTvRxBus;
    @Bind(R.id.tv_toast)
    TextView mTvToast;
    @Bind(R.id.tv_flash)
    TextView mTvFlash;
    @Bind(R.id.tv_test_greendao)
    TextView mTvTestGreenDao;
    @Bind(R.id.tv_test_login)
    TextView mTvTestLogin;
    @Bind(R.id.tv_test_login_two)
    TextView mTvtv_test_login_two;
    @Bind(R.id.tv_dagou)
    TextView mTvDagou;
    @Bind(R.id.tv_dp)
    TextView mTvDp;
    @Bind(R.id.tv_dialog)
    TextView mTvDialog;
    @Bind(R.id.tv10)
    TextView mTv10;
    @Bind(R.id.tv11)
    TextView mTv11;
    @Bind(R.id.tv12)
    TextView mTvSocket;
    @Bind(R.id.tv13)
    TextView mTv13;
    @Bind(R.id.tv14)
    TextView mTv14;
    @Bind(R.id.tv15)
    TextView mTv15;
    @Bind(R.id.tv16)
    TextView mTv16;
    @Bind(R.id.tv17)
    TextView mTv17;
    @Bind(R.id.tv18)
    TextView mTv18;
    @Bind(R.id.tv19)
    TextView mTv19;
    @Bind(R.id.tv20)
    TextView mTv20;
    @Bind(R.id.tv21)
    TextView mTv21;
    @Bind(R.id.tv22)
    TextView mTv22;

    private UrlInfoService mUrlInfoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initOnClick();
        loadPlugin(this);
        mUrlInfoService = new UrlInfoService();
        UrlInfo urlInfo = new UrlInfo();
        urlInfo.setUrl("file:///mnt/sdcard/1.mp4");
        mUrlInfoService.save(urlInfo);
        mTv1.setText(String.format(getResources().getString(R.string.testText), "用一个Textview", "分两行显示"));
        //创建被观察者
        Plant flowle = new Flower();
        //创建三个观察者;
        Insect one = new Bee(1);
        Insect two = new Bee(2);
        Insect three = new Bee(3);

        //注册观察者
        flowle.registerInsect(one);
        flowle.registerInsect(three);
        flowle.registerInsect(two);
        //改变被观察者的状态先后开合
        flowle.notifyInsect(true);
        Log.d("test===", "============十二个小时过去了=============");
        flowle.notifyInsect(false);
        flowle.unregisterInsect(one);
        flowle.unregisterInsect(two);
        flowle.unregisterInsect(three);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

        } else {
            MainActivityPermissionsDispatcher.AppIySuccessWithCheck(MainActivity.this);
        }

    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE
            , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    public void AppIySuccess() {
    }

    /**
     * 申请权限时告诉用户原因
     */
    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    void showRationaleForMap(PermissionRequest request) {
        showRationaleDialog("使用此功能需要打开定位权限", request);
    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE
            , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    void onMapNeverAskAgain() {
        AskForPermission();
    }

    private void initOnClick() {
        mTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                Properties properties = new Properties();
                properties.setProperty("name", "ok");
                StatService.trackCustomKVEvent(MainActivity.this, "button_click", properties);
            }
        });
        mTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main3Activity.class));
                Properties properties = new Properties();
                properties.setProperty("name", "okTwo");
                StatService.trackCustomKVEvent(MainActivity.this, "button_click", properties);
            }
        });
        mTv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main4Activity.class));
                Properties properties = new Properties();
                properties.setProperty("two", "testTwoBut");
                StatService.trackCustomKVEvent(MainActivity.this, "button_click_two", properties);
            }
        });
        mTvPlayPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PlayPhoneActivity.class));
            }
        });
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main5Activity.class));
            }
        });
        mTv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main6Activity.class));
            }
        });
        mTv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main7Activity.class));
            }
        });
        mTv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ExpandableListActivity.class));
            }
        });
        mTvRxBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RxBusActivity.class));
            }
        });
        mTvToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestToastActivity.class));
            }
        });
        mTvFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FlashActivity.class));

            }
        });
        mTvTestGreenDao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestGreenDaoActivity.class));
            }
        });
        mTvTestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestLoginActivity.class));
            }
        });
        mTvtv_test_login_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginTwoActivity.class));
            }
        });
        mTvDagou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivityAnimation.class));
            }
        });
        mTvDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UrlInfo urlInfo = new UrlInfo();
                urlInfo.setUrl("http://vd3.bdstatic.com/mda-igvnzpvagkhmfugr/mda-igvnzpvagkhmfugr.mp4");
                mUrlInfoService.save(urlInfo);
            /*	Intent intent = new Intent(this, VideoPlayerActivity.class);
                intent.putExtra(ConstData.IntentKey.VIDEO_URL, netAddress);
				startActivity(intent);*/
                Intent intent = new Intent(MainActivity.this, JavaActivity.class);
                intent.putExtra("extra_url", "http://vd3.bdstatic.com/mda-igvnzpvagkhmfugr/mda-igvnzpvagkhmfugr.mp4");
                startActivity(intent);
            }
        });
        mTvDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
            }
        });
        mTv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, dianjizhankaiActivity.class));
            }
        });
        mTv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestSocketActivity.class));
            }
        });
        mTvSocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SocketActivity.class));
            }
        });
        mTv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestServiceActivity.class));
            }
        });
        mTv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShareTestActivity.class));
            }
        });
        mTv15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, baidumapActivity.class));
            }
        });
        mTv16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, tiaodongdewenziActivity.class));
            }
        });
        mTv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AndroidSizeActivity.class));
            }
        });
        mTv18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, TestDownloadActivity.class));
            }
        });
        mTv19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, YBViewActivity.class));
            }
        });
        mTv20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, LifecycleOneActivity.class));
            }
        });
        mTv21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // test Activity and Service


                // test ContentProvider
//                Uri bookUri = Uri.parse("content://com.didi.virtualapk.demo.book.provider/book");
//                LoadedPlugin plugin = PluginManager.getInstance(MainActivity.this).getLoadedPlugin(pkg);
//                bookUri = PluginContentResolver.wrapperUri(plugin, bookUri);
//
//                Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
//                if (bookCursor != null) {
//                    while (bookCursor.moveToNext()) {
//                        int bookId = bookCursor.getInt(0);
//                        String bookName = bookCursor.getString(1);
//                        Log.d("ryg", "query book:" + bookId + ", " + bookName);
//                    }
//                    bookCursor.close();
//                }


                startActivity(new Intent(MainActivity.this, RecycleViewActivity.class));
                //Intent intent = new Intent();
//                intent.setClassName("com.huashitech.mvvmplug", "com.huashitech.mvvmplug.MainActivity");
//                startActivity(intent);
            }
        });
        mTv22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ConstraintLayoutActivity.class));
            }
        });

    }


// Given "com.didi.virtualapk.demo" is the package name of plugin APK,
// and there is an activity called `MainActivity`.


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(MainActivity.this, requestCode, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void AskForPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("当前应用缺少部分权限,请去设置界面打开\n打开之后按两次返回键可回到该应用哦");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + MainActivity.this.getPackageName())); // 根据包名打开对应的设置界面
                startActivity(intent);
            }
        });
        builder.create().show();
    }

    /**
     * 告知用户具体需要权限的原因 * @param messageResId * @param request
     */

    private void showRationaleDialog(String messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(MainActivity.this).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(@NonNull DialogInterface dialog, int which) {
                request.proceed();//请求权限
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(@NonNull DialogInterface dialog, int which) {
                request.cancel();
            }
        }).setCancelable(false).setMessage(messageResId).show();
    }

    private void loadPlugin(Context base) {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Toast.makeText(this, "sdcard was NOT MOUNTED!", Toast.LENGTH_SHORT).show();
        }


        PluginManager pluginManager = PluginManager.getInstance(base);
        File apk = new File(Environment.getExternalStorageDirectory(), "app-release.apk");
        if (apk.exists()) {
            try {
                pluginManager.loadPlugin(apk);
                Log.i(TAG, "Loaded plugin from apk: " + apk);
            } catch (Exception e) {
                Log.d("Test== ", e.toString());
            }
        } else {
            try {
                File file = new File(base.getFilesDir(), "app-release.apk");
                java.io.InputStream inputStream = base.getAssets().open("app-release.apk", 2);
                java.io.FileOutputStream outputStream = new java.io.FileOutputStream(file);
                byte[] buf = new byte[1024];
                int len;

                while ((len = inputStream.read(buf)) > 0) {
                    outputStream.write(buf, 0, len);
                }

                outputStream.close();
                inputStream.close();
                pluginManager.loadPlugin(file);
                Log.i(TAG, "Loaded plugin from assets: " + file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
