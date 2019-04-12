package com.hs.administrator.test.view.activity;

import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hs.administrator.test.R;
import com.hs.administrator.test.widget.UpdataDialog.AutoInstallDialog;
import com.hs.administrator.test.widget.UpdataDialog.UpdateDialog;
import com.hs.hstechsdklibrary.commonutil.LogUtil;

import java.io.File;

public class TestDownloadActivity extends AppCompatActivity {

    public Button mButDownload, mEtCancel;
    private UpdateDialog dialog;
    private AutoInstallDialog autoInstallDialog;


    private boolean isInstall = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_download);
        mButDownload = findViewById(R.id.but_down);
        mEtCancel = findViewById(R.id.et_status);
        mButDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DwonDialog(2);
            }
        });
        mEtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DwonDialog(1);
            }
        });

    }

    private void DwonDialog(int type) {
        final int cancel = type;
        String sure;
        if (cancel == 1) {
            sure = "取消";
        } else {
            sure = "忽略此次";
        }
        dialog = new UpdateDialog(TestDownloadActivity.this, R.style.custom_dialog_style, "11", "1:我就随便测测", sure, "确定");
        dialog.setOnUpdate(new UpdateDialog.OnUpdate() {
            @Override
            public void cancelUpdate() {
                dialog.dismiss();
                if (cancel == 1) {
                    finish();
                    System.exit(0);
                }
            }

            @Override
            public void sureUpdate() {
                dialog.dismiss();
                try {
                    File oldFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" + "zzjl.apk");
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                } catch (Exception e) {
                    Toast.makeText(TestDownloadActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                autoInstallDialog = new AutoInstallDialog(TestDownloadActivity.this, R.style.custom_dialog_style, "http://47.97.20.75:9999/group1/M00/00/28/L2EUS1vyK_mAI0-UAefGsso_jCI868.apk","text.apk");
                autoInstallDialog.show();
                isInstall = true;
                autoInstallDialog.startDownload();

            }
        });
        dialog.show();
    }

    @Override
    protected void onPause() {
        if (isInstall){
            autoInstallDialog.stopDownload();
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d("HomeActivity---", "onStop--->");
        if (isInstall) {
            if (autoInstallDialog != null) {
                autoInstallDialog.stopDownload();
            }
        }
    }

    @RequiresApi(api = 26)
    @Override
    protected void onPostResume() {
        super.onPostResume();
        LogUtil.d("HomeActivity---", "onPostResume--->");
        if (isInstall) {
            if (autoInstallDialog != null) {
                autoInstallDialog.startDownload();
            }
        }
    }
}
