package com.hs.administrator.test.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.hs.administrator.test.R;
import com.hs.hstechsdklibrary.commonutil.LogUtil;
import com.hs.hstechsdklibrary.commonutil.ToastUtils;
import com.hs.hstechsdklibrary.commonutil.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class LoginTwoActivity extends AppCompatActivity {

    @Bind(R.id.et_userName)
    EditText mEtUserName;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.cb)
    CheckBox mCb;
    @Bind(R.id.login)
    TextView mTvLogin;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String account, password;
    private  boolean isCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_two);
        ButterKnife.bind(this);
        Utils.init(LoginTwoActivity.this);
        pref = PreferenceManager.getDefaultSharedPreferences(LoginTwoActivity.this);
        isCheck = pref.getBoolean("remember", false);
        if (isCheck) {
            account = pref.getString("name", "");
            password = pref.getString("password", "");
            mEtUserName.setText(account);
            mEtPassword.setText(password);
            mCb.setChecked(true);
        } else {
            String account = pref.getString("name", "");
            mEtUserName.setText(account);
            mCb.setChecked(false);
        }

        mEtUserName.addTextChangedListener(textWatcher);
        initData();
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String userName = mEtUserName.getText().toString();
            query(userName);
        }

        private void query(String userName) {

            if (!userName.equals(account) ){
                mEtPassword.setText("");
                mCb.setChecked(false);
            }else  {
              mEtPassword.setText(password);
              mCb.setChecked(true);
            }
        }
    };



    private void initData() {
        mTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = mEtUserName.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    ToastUtils.showShort("请输入用户名");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                if (account.equals("admin") && password.equals("123456")||account.equals("yanbin")&&password.equals("123456")) {
                    editor = pref.edit();
                    //       if (mCb.isChecked()){
                    editor.putBoolean("remember", mCb.isChecked());
                    editor.putString("name", account);
                    editor.putString("password", password);
                    //     }
                    editor.apply();
                    startActivity(new Intent(LoginTwoActivity.this, MainActivity.class));
                    ToastUtils.showShort("登录成功");
                    finish();
                } else {
                    ToastUtils.showShort("账号或密码错误~~");
                }
            }
        });
    }

  /*  @OnTextChanged(R.id.et_userName)
    void onTextChang(CharSequence text) {
        if (text.equals(account)) {
            mEtPassword.setText(password);
            mCb.setChecked(true);
        } else {
            mEtPassword.setText("");
            mCb.setChecked(false);
        }
    }
*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
