package com.hs.administrator.test.view.activity;


import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.hs.administrator.test.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SurfaceActivity extends AppCompatActivity {

    private SurfaceView mSurfaceView;
    private Button mButStart, mButBack;

    private SurfaceHolder mSurfaceHolder;

    private MediaPlayer mediaPlayer;

    private boolean isRecording;//标记判断当前是否在录制视屏

    private long mRecordCurrentTime = 0;//录制时间间隔

    //存储文件
    private File mVecordFile;
    private Camera mCamera;
    private MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mButStart = (Button) findViewById(R.id.but_statr);
        mButBack = (Button) findViewById(R.id.but_back);
        initSurface();

        mButBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mButStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mButStart.getText().equals("开始录制")) {
                    try {
                        startRecord();
                    } catch (Exception e) {
                        Log.d("test== ", e.toString());
                        Toast.makeText(SurfaceActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    mButStart.setText("录制完成");
                } else {
                    stopRecord();
                    mButStart.setText("开始录制");
                 /*   mediaPlayer = new MediaPlayer();
                    SurfaceHolder surfaceHolder = mSurfaceView.getHolder();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                        }
                    });
                    surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                        @Override
                        public void surfaceCreated(SurfaceHolder surfaceHolder) {
                            mediaPlayer.release();

                            mediaPlayer.setDisplay(surfaceHolder);
                            try {
                                mediaPlayer.setDataSource(SurfaceActivity.this, Uri.parse(mVecordFile.getAbsolutePath()));
                                mediaPlayer.prepareAsync();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


                        @Override
                        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                        }

                        @Override
                        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

                        }
                    });*/
                }
            }
        });
    }


    private void initSurface() {
        mSurfaceHolder = mSurfaceView.getHolder();
        //设置surface不需要维护自己的缓冲区
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceHolder.addCallback(mCallBack);
    }

    private SurfaceHolder.Callback mCallBack = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            initCamera();
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            if (mSurfaceHolder.getSurface() == null) {
                return;
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            stopCamera();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopCamera();
    }

    private void stopCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    private void initCamera() {
        mCamera = Camera.open(0);  //①
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
            mCamera.cancelAutoFocus();//此句加上 可自动聚焦 必须加
            Camera.Parameters parameters = mCamera.getParameters();
            //查询摄像头支持的分辨率
            parameters.getSupportedPreviewSizes();
            for (int i = 0; i < parameters.getSupportedPreviewSizes().size(); i++) {

            }
            parameters.setPreviewSize(1280, 720);
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            parameters.setRecordingHint(true);
            if (parameters.isVideoStabilizationSupported()) {
                parameters.setVideoStabilization(true);
            }
            mCamera.setParameters(parameters);
            mCamera.startPreview();

        } catch (IOException e) {
            Log.i("test==", "Error starting camera preview: " + e.getMessage());
        }
    }

    /*
    * 创建视屏文件
    * */
    private boolean createRecordDir() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return false;
        }
        File sampleDir = new File("/sdcard/myVideo/");
        if (!sampleDir.exists()) {
            sampleDir.mkdirs();
        }
        String videoName = "VID_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".mp4";
        mVecordFile = new File(sampleDir, videoName);
        return true;
    }

    private MediaRecorder.OnErrorListener onErrorListener = new MediaRecorder.OnErrorListener() {
        @Override
        public void onError(MediaRecorder mediaRecorder, int what, int extra) {
            try {
                if (mediaRecorder != null) {
                    mediaRecorder.reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 配置MediaRecorder()
     */
    private void setConfigRecord() {
        if (mediaRecorder != null) {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.reset();
            mediaRecorder.setCamera(mCamera);
            mediaRecorder.setOnErrorListener(onErrorListener);
            //录像角度
            mediaRecorder.setOrientationHint(270);
            //使用surfaceview预览
            mediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
            //采集声音
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置采集图像
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            //2.设置视频，音频的输出格式 mp4
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            //3.设置音频的编码格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            //设置图像的编码格式
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                mediaRecorder.setAudioSamplingRate(11025);
            }

            CamcorderProfile mProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_480P);
            mediaRecorder.setAudioEncodingBitRate(44100);
            if (mProfile.videoBitRate > 2 * 1024 * 1024) {
                mediaRecorder.setVideoEncodingBitRate(2 * 1024 * 1024);
            } else {
                mediaRecorder.setVideoEncodingBitRate(1024 * 1024);
            }
            mediaRecorder.setVideoFrameRate(mProfile.videoFrameRate);
             mediaRecorder.setVideoSize(1280, 720);
            mediaRecorder.setOutputFile(mVecordFile.getAbsolutePath());
          //  mediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
        }
    }

    private String getRecorderPath() {
        File file = Environment.getExternalStorageDirectory(); // SD卡根目录
        file = new File(file.getPath() + "/recorder");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getPath() + "/video_" + System.currentTimeMillis() + ".mp4"; // 文件名}

    }

    private void startRecord() throws IOException {
        //这是是判断视频文件有没有创建,如果没有就返回
        boolean creakOk = createRecordDir();
        if (!creakOk) {
            return;
        }
        mCamera.unlock();
        setConfigRecord();

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            Log.d("test==", "prepare   " + e.toString());
            e.printStackTrace();
        }


        isRecording = true;

        // mRecordTime.start();
    }


    /**
     * 停止录制
     */
    private void stopRecord() {
        if (isRecording && mediaRecorder != null) {
            mediaRecorder.setOnErrorListener(null);
            mediaRecorder.setPreviewDisplay(null);
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
            //Toast.makeText("test==" + mVecordFile.toString());
            Log.d("test==", mVecordFile.toString());
        }
    }


}

