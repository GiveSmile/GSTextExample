package com.hs.administrator.test.view.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hs.administrator.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShareTestActivity extends AppCompatActivity {
    @Bind(R.id.but_share_image)
    Button mButImage;
    @Bind(R.id.but_share_text)
    Button mButShareText;
    @Bind(R.id.ll_text)
    LinearLayout mllText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_test);
        ButterKnife.bind(this);
        Resources r = this.getResources();
        final Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getResources().getResourcePackageName(R.mipmap.testtoux) + "/"
                + getResources().getResourceTypeName(R.mipmap.testtoux) + "/" + getResources().getResourceEntryName(R.mipmap.testtoux));
        mButImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("test==",imageUri.toString());
                shareImage("分享对话框标题", "主题", "我是文字内容", imageUri);
            }
        });
        mButShareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareText("分享对话框标题", "主题", "我是文字内容");
            }
        });
    }

    /**
     * 分享文字内容
     *
     * @param dlgTitle 分享对话框标题
     * @param subject  主题
     * @param content  分享内容
     */
    private void ShareText(String dlgTitle, String subject, String content) {
        if (content.equals("")) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if (TextUtils.isEmpty(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        intent.putExtra(Intent.EXTRA_TEXT, content);

        //设置弹出框
        if (!dlgTitle.equals("")) {
            startActivity(Intent.createChooser(intent, dlgTitle));
        } else {
            startActivity(intent);
        }

    }


    /**
     * 分享带图片的文字内容
     *
     * @param dlgTitle 分享对话框主题
     * @param subject  主题
     * @param content  分享文字内容
     * @param uri      分享图片资源URI
     */
    private void shareImage(String dlgTitle, String subject, String content, Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        if (subject != null && !TextUtils.isEmpty(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        if (content != null && content.equals("")) {
            intent.putExtra(Intent.EXTRA_TEXT, content);
        }

        //设置弹出框标题
        if (!TextUtils.isEmpty(content)) {
            startActivity(Intent.createChooser(intent, dlgTitle));
        } else {
            startActivity(intent);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
