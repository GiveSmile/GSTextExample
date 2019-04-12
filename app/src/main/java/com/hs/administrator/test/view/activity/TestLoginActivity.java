package com.hs.administrator.test.view.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.LoginBeanDao;
import com.hs.administrator.test.R;
import com.hs.administrator.test.model.LoginBean;
import com.hs.hstechsdklibrary.commonutil.LogUtil;
import com.hs.hstechsdklibrary.commonutil.ToastUtils;
import com.hs.hstechsdklibrary.commonutil.Utils;

import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TestLoginActivity extends AppCompatActivity {
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.et_userName)
    EditText mEtUserName;
    @Bind(R.id.cb)
    CheckBox mCb;
    @Bind(R.id.login)
    TextView mTvLogin;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_password)
    TextView mTvPassword;
    @Bind(R.id.tv_query)
    TextView mTvQuery;
    @Bind(R.id.tv_ischeck)
    TextView mTvIscheck;
    @Bind(R.id.tv_delete)
    TextView mTvDelete;
    @Bind(R.id.tv_show_list_sum)
    TextView mTvShowListSum;
    @Bind(R.id.tv_sum)
    TextView mTvSum;

    private String passWord, userName, list;

    private boolean isCheck = true;

    private DaoSession daoSession;

    private static final int MSG_ONE = 1;
    private List<LoginBean> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_login);
        ButterKnife.bind(this);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(TestLoginActivity.this, "db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        Utils.init(this);
        daoSession = daoMaster.newSession();
        initData();
        mEtUserName.addTextChangedListener(textWatcher);
    }

    public void initData() {
        mTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passWord = mEtPassword.getText().toString().trim();
                userName = mEtUserName.getText().toString().trim();
                if (userName.equals("")){
                    ToastUtils.showShort("请输入账号或用户名");
                    return;
                }
                if (passWord.equals("")){
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                isCheck = mCb.isChecked();
                List<LoginBean> mDatas = daoSession.getLoginBeanDao().queryBuilder().where(LoginBeanDao.Properties.UserName.eq(userName)).list();
                if (mDatas.size() > 0) {
                    daoSession.getLoginBeanDao().deleteByKey(mDatas.get(0).getId());
                }
                LoginBean mData = new LoginBean();
                mData.userName = userName;
                if (isCheck == false) {
                    mData.passWord = "";
                } else {
                    mData.passWord = passWord;
                }
                mData.isCheck = isCheck;
                mData.Priority = "1";
                List<LoginBean> mList = daoSession.getLoginBeanDao().queryBuilder().list();
                if (mList.size() > 0) {
                    for (int i = 0; i < mList.size(); i++) {
                        if (mList.get(i).getPriority().equals("1")) {
                            mList.get(i).Priority = "2";
                            daoSession.getLoginBeanDao().update(mList.get(i));
                        }
                    }
                }
                daoSession.getLoginBeanDao().insert(mData);
                startActivity(new Intent(TestLoginActivity.this, MainActivity.class));
                finish();
            }
        });

        mTvShowListSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<LoginBean> mData = daoSession.getLoginBeanDao().queryBuilder().list();
                mTvSum.setText(mData.size() + "");
            }
        });

        mTvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.d("test==", list);
                List<LoginBean> mData = daoSession.getLoginBeanDao().queryBuilder().where(LoginBeanDao.Properties.UserName.eq(list)).list();
                if (mData.size() > 0) {
                    mTvName.setText(mData.get(0).getUserName());
                    mTvPassword.setText(mData.get(0).getPassWord());
                    if (mData.get(0).getIsCheck() == true) {
                        mTvIscheck.setText(true + "");
                    } else {
                        mTvIscheck.setText("fa");
                    }
                } else {
                    mTvIscheck.setText("");
                    mTvPassword.setText("");
                    mTvName.setText("");
                }

            }
        });
        mTvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daoSession.getLoginBeanDao().deleteAll();
            }
        });

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
            list = mEtUserName.getText().toString().trim();
            query(list);
        }
    };

    private void query(String list) {
        mDatas = daoSession.getLoginBeanDao().queryBuilder().where(LoginBeanDao.Properties.UserName.eq(list)).list();
        if (mDatas.size() > 0) {
            if (mDatas.get(0).isCheck == true) {
                mEtPassword.setText(mDatas.get(0).getPassWord());
                mCb.setChecked(true);
            } else {
                mEtPassword.setText("");
                mCb.setChecked(false);
            }
        } else {
            mEtPassword.setText("");
            mCb.setChecked(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoginBean mDatas = daoSession.getLoginBeanDao().queryBuilder().where(LoginBeanDao.Properties.Priority.eq("1")).unique();
        if (mDatas != null) {
            mEtUserName.setText(mDatas.getUserName());
            mEtPassword.setText(mDatas.getPassWord());
            mCb.setChecked(mDatas.getIsCheck());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
