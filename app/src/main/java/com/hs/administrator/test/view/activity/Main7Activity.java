package com.hs.administrator.test.view.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.icu.text.SimpleDateFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.hs.administrator.test.Broadcast.NetworkConnectChangedReceiver;
import com.hs.administrator.test.R;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main7Activity extends AppCompatActivity implements IVLCVout.OnNewVideoLayoutListener {

    @Bind(R.id.but_phone)
    Button mButPhone;
    @Bind(R.id.but_album)
    Button mButAlbun;
    @Bind(R.id.iv_tupian)
    ImageView mIvTuPian;
    @Bind(R.id.srfc)
    SurfaceView mSrfc;
    @Bind(R.id.video_surface_frame)
    FrameLayout mVideoSurfaceFrame;
    @Bind(R.id.viewView)
    VideoView mVideoview;
    @Bind(R.id.mTvNoWifi)
    TextView mTvNowifi;


    private int VIDEO_RESULT_CODE = 2;
    private File mVideoFile;
    private Uri mVideoUri;
    private static final boolean ENABLE_SUBTITLES = true;
    private static final int REQUEST_CODE_TAKE_PHOTO = 0x110;
    private static final int SURFACE_BEST_FIT = 0;
    private static final int SURFACE_FIT_SCREEN = 1;
    private static final int SURFACE_FILL = 2;
    private static final int SURFACE_16_9 = 3;
    private static final int SURFACE_4_3 = 4;
    private static final int SURFACE_ORIGINAL = 5;
    private int mVideoSarNum = 0;
    private int mVideoSarDen = 0;
    private int mVideoVisibleHeight = 0;
    private int mVideoVisibleWidth = 0;
    private static int CURRENT_SIZE = SURFACE_BEST_FIT;
    private final Handler mHandler = new Handler();
    LibVLC libVLC = null;
    private MediaPlayer mediaPlayer;
    private File file;
    private int mVideoHeight = 0;
    private int mVideoWidth = 0;
    private SurfaceView mSubtitlesSurface = null;
    private View.OnLayoutChangeListener mOnLayoutChangeListener = null;
    ArrayList<String> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        ButterKnife.bind(this);
        initWifi();
        OnClick();
        if (ENABLE_SUBTITLES) {
            final ViewStub stub = (ViewStub) findViewById(R.id.subtitles_stub);
            mSubtitlesSurface = (SurfaceView) stub.inflate();
            mSubtitlesSurface.setZOrderMediaOverlay(true);
            mSubtitlesSurface.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        }
        try {
            libVLC = new LibVLC(getApplication(), options);
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }

            mediaPlayer = new MediaPlayer(libVLC);
            String url = getString(R.string.http_video11_qtv_com_cn_qtv1_sd_manifest_m3u8);
            mediaPlayer.getVLCVout().setVideoSurface(mSrfc.getHolder().getSurface(), mSrfc.getHolder());
            mediaPlayer.getVLCVout().attachViews();
            Media media = new Media(libVLC, Uri.parse(url));
            mediaPlayer.setMedia(media);
            mediaPlayer.play();

            if (mOnLayoutChangeListener == null) {
                mOnLayoutChangeListener = new View.OnLayoutChangeListener() {
                    private final Runnable mRunnable = new Runnable() {
                        @Override
                        public void run() {
                            updateVideoSurfaces();
                        }
                    };

                    @Override
                    public void onLayoutChange(View v, int left, int top, int right,
                                               int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                        if (left != oldLeft || top != oldTop || right != oldRight || bottom != oldBottom) {
                            mHandler.removeCallbacks(mRunnable);
                            mHandler.post(mRunnable);
                        }
                    }
                };
            }
            mVideoSurfaceFrame.addOnLayoutChangeListener(mOnLayoutChangeListener);


        } catch (Exception e) {
            Toast.makeText(this, "播不了", Toast.LENGTH_SHORT).show();
        }
    }

    private void initWifi() {
     /*   if (!checkNetWork()) {
            mTvNowifi.setVisibility(View.VISIBLE);
            mTvNowifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
                    startActivity(intent);
                }
            });

        } else {
            mTvNowifi.setVisibility(View.GONE);
        }*/
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new NetworkConnectChangedReceiver(), filter);
    }

    private boolean checkNetWork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = connectivityManager.getActiveNetworkInfo();
        if (net != null && net.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    private void OnClick() {
        mButPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyWritePermission();
            }
        });
        mButAlbun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                Log.d("test==","启动相机完成");
                //创建文件
                createVideoFile();
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                     mVideoUri  = FileProvider.getUriForFile(Main7Activity.this,"test",mVideoFile);
                }else {
                    mVideoUri = Uri.fromFile(mVideoFile);
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT,mVideoUri);

                startActivityForResult(intent,VIDEO_RESULT_CODE);

            }

            private void createVideoFile() {
                Log.d("test==","开始创建图片文件");
                //设置图片文件名（带后缀），以当前时间的毫秒为名称
                String mVideoName = Calendar.getInstance().getTimeInMillis()+".mp4";
                Log.d("test==","设置的视频的名称为："+mVideoName);
                 mVideoFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/bhne/",mVideoName);
                String mVideoPath = mVideoFile.getAbsolutePath();
                mVideoFile.getParentFile().mkdirs();
                mVideoFile.setWritable(true);
            */
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                Uri fleUri = null;
                try {
                    fleUri = Uri.fromFile(createMediaFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fleUri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, VIDEO_RESULT_CODE);

            }

        });
    }

    private File createMediaFile() throws IOException {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "CameraDemo");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "VID_" + timeStamp;
        String suffix = ".mp4";
        File mediaFile = new File(mediaStorageDir + File.separator + imageFileName + suffix);
        return mediaFile;
    }


    public void applyWritePermission() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            int check = ContextCompat.checkSelfPermission(Main7Activity.this, permissions[0]);
            //判断权限是否已经打开  granted-- 已授权  dinied--拒绝
            if (check == PackageManager.PERMISSION_GRANTED) {
                useCamera();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {
            useCamera();
        }
    }

    private void useCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               /*      filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CANADA)
                            .format(new Date()) + ".png";
                     file = new File(Environment.getExternalStorageDirectory(),filename);
*/
        file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() +
                "/test/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();
        //  mCurrentPhotoPath = file.getAbsolutePath();

        Uri fileUrl = FileProvider.getUriForFile(Main7Activity.this, "com.yanbin.android7.fileprovider", file);
        takePicture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        takePicture.putExtra(MediaStore.EXTRA_OUTPUT, fileUrl);
        startActivityForResult(takePicture, REQUEST_CODE_TAKE_PHOTO);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            useCamera();
        } else {
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            mIvTuPian.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            mIvTuPian.setImageURI(Uri.fromFile(file));

        }
        Intent mdeia = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(file);
        mdeia.setData(contentUri);
        sendBroadcast(mdeia);

        if (requestCode == VIDEO_RESULT_CODE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            String s = videoUri.toString();
            String[] strings = s.split(":");
            Message msg = Message.obtain();
            msg.obj = strings[1];

            Log.d("test==", videoUri + "");

        }
    }


    private void changeMediaPlayerLayout(int displayW, int displayH) {
        /* Change the video placement using the MediaPlayer API */
        switch (CURRENT_SIZE) {
            case SURFACE_BEST_FIT:
                mediaPlayer.setAspectRatio(null);
                mediaPlayer.setScale(0);
                break;
            case SURFACE_FIT_SCREEN:
            case SURFACE_FILL: {
                Media.VideoTrack vtrack = mediaPlayer.getCurrentVideoTrack();
                if (vtrack == null)
                    return;
                final boolean videoSwapped = vtrack.orientation == Media.VideoTrack.Orientation.LeftBottom
                        || vtrack.orientation == Media.VideoTrack.Orientation.RightTop;
                if (CURRENT_SIZE == SURFACE_FIT_SCREEN) {
                    int videoW = vtrack.width;
                    int videoH = vtrack.height;

                    if (videoSwapped) {
                        int swap = videoW;
                        videoW = videoH;
                        videoH = swap;
                    }
                    if (vtrack.sarNum != vtrack.sarDen)
                        videoW = videoW * vtrack.sarNum / vtrack.sarDen;

                    float ar = videoW / (float) videoH;
                    float dar = displayW / (float) displayH;

                    float scale;
                    if (dar >= ar)
                        scale = displayW / (float) videoW; /* horizontal */
                    else
                        scale = displayH / (float) videoH; /* vertical */
                    mediaPlayer.setScale(scale);
                    mediaPlayer.setAspectRatio(null);
                } else {
                    mediaPlayer.setScale(0);
                    mediaPlayer.setAspectRatio(!videoSwapped ? "" + displayW + ":" + displayH
                            : "" + displayH + ":" + displayW);
                }
                break;
            }
            case SURFACE_16_9:
                mediaPlayer.setAspectRatio("16:9");
                mediaPlayer.setScale(0);
                break;
            case SURFACE_4_3:
                mediaPlayer.setAspectRatio("4:3");
                mediaPlayer.setScale(0);
                break;
            case SURFACE_ORIGINAL:
                mediaPlayer.setAspectRatio(null);
                mediaPlayer.setScale(1);
                break;
        }
    }

    private void updateVideoSurfaces() {
        int sw = getWindow().getDecorView().getWidth();
        int sh = getWindow().getDecorView().getHeight();

        // sanity check
        if (sw * sh == 0) {
            return;
        }

        mediaPlayer.getVLCVout().setWindowSize(sw, sh);

        ViewGroup.LayoutParams lp = mSrfc.getLayoutParams();
        if (mVideoWidth * mVideoHeight == 0) {
            /* Case of OpenGL vouts: handles the placement of the video using MediaPlayer API */
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mSrfc.setLayoutParams(lp);
            lp = mSrfc.getLayoutParams();
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mSrfc.setLayoutParams(lp);
            changeMediaPlayerLayout(sw, sh);
            return;
        }

        if (lp.width == lp.height && lp.width == ViewGroup.LayoutParams.MATCH_PARENT) {
            /* We handle the placement of the video using Android View LayoutParams */
            mediaPlayer.setAspectRatio(null);
            mediaPlayer.setScale(0);
        }

        double dw = sw, dh = sh;
        final boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        if (sw > sh && isPortrait || sw < sh && !isPortrait) {
            dw = sh;
            dh = sw;
        }

        // compute the aspect ratio
        double ar, vw;
        if (mVideoSarDen == mVideoSarNum) {
            /* No indication about the density, assuming 1:1 */
            vw = mVideoVisibleWidth;
            ar = (double) mVideoVisibleWidth / (double) mVideoVisibleHeight;
        } else {
            /* Use the specified aspect ratio */
            vw = mVideoVisibleWidth * (double) mVideoSarNum / mVideoSarDen;
            ar = vw / mVideoVisibleHeight;
        }

        // compute the display aspect ratio
        double dar = dw / dh;

        switch (CURRENT_SIZE) {
            case SURFACE_BEST_FIT:
                if (dar < ar)
                    dh = dw / ar;
                else
                    dw = dh * ar;
                break;
            case SURFACE_FIT_SCREEN:
                if (dar >= ar)
                    dh = dw / ar; /* horizontal */
                else
                    dw = dh * ar; /* vertical */
                break;
            case SURFACE_FILL:
                break;
            case SURFACE_16_9:
                ar = 16.0 / 9.0;
                if (dar < ar)
                    dh = dw / ar;
                else
                    dw = dh * ar;
                break;
            case SURFACE_4_3:
                ar = 4.0 / 3.0;
                if (dar < ar)
                    dh = dw / ar;
                else
                    dw = dh * ar;
                break;
            case SURFACE_ORIGINAL:
                dh = mVideoVisibleHeight;
                dw = vw;
                break;
        }

        // set display size
        lp.width = (int) Math.ceil(dw * mVideoWidth / mVideoVisibleWidth);
        lp.height = (int) Math.ceil(dh * mVideoHeight / mVideoVisibleHeight);
        mSrfc.setLayoutParams(lp);
        if (mSubtitlesSurface != null)
            mSubtitlesSurface.setLayoutParams(lp);

        // set frame size (crop if necessary)
        lp = mVideoSurfaceFrame.getLayoutParams();
        lp.width = (int) Math.floor(dw);
        lp.height = (int) Math.floor(dh);
        mVideoSurfaceFrame.setLayoutParams(lp);

        mSrfc.invalidate();
        if (mSubtitlesSurface != null)
            mSubtitlesSurface.invalidate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mOnLayoutChangeListener != null) {
            mVideoSurfaceFrame.removeOnLayoutChangeListener(mOnLayoutChangeListener);
            mOnLayoutChangeListener = null;
        }

        mediaPlayer.stop();

        mediaPlayer.getVLCVout().detachViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoview.setMediaController(new MediaController(Main7Activity.this));
        mVideoview.setVideoURI(Uri.parse("/storage/emulated/0/HuaShiIM/JCamera/video_1538202916528.mp4"));
        mVideoview.start();
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onNewVideoLayout(IVLCVout vlcVout, int width, int height, int visibleWidth, int visibleHeight, int sarNum, int sarDen) {
        mVideoWidth = width;
        mVideoHeight = height;
        mVideoVisibleWidth = visibleWidth;
        mVideoVisibleHeight = visibleHeight;
        mVideoSarNum = sarNum;
        mVideoSarDen = sarDen;
        updateVideoSurfaces();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }

}
