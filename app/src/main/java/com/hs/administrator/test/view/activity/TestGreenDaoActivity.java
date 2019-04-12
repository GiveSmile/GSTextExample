package com.hs.administrator.test.view.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.TestGreeDaoBeanDao;
import com.hs.administrator.test.R;
import com.hs.administrator.test.model.TestGreeDaoBean;
import com.hs.hstechsdklibrary.commonutil.LogUtil;
import com.hs.hstechsdklibrary.commonutil.ToastUtils;
import com.hs.hstechsdklibrary.commonutil.Utils;
import com.hs.administrator.test.utils.DBManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestGreenDaoActivity extends AppCompatActivity {

    @Bind(R.id.rt)
    EditText mEtAdd;
    @Bind(R.id.rv_add)
    TextView mTvAdd;
    @Bind(R.id.tv_session)
    TextView mTvSession;
    @Bind(R.id.tv_session_conntent)
    TextView mTvSessionConntent;
    @Bind(R.id.et_delete)
    EditText mEtDelete;
    @Bind(R.id.tv_delete)
    TextView mTvDelete;
    @Bind(R.id.tv_update)
    TextView mTvUpdate;
    @Bind(R.id.et_update_qian)
    EditText mEtUpdateQian;
    @Bind(R.id.et_update_hou)
    EditText mEtUpdateHou;
    @Bind(R.id.tv_delete_all)
    TextView mTvDeleteAll;
    @Bind(R.id.et_session_conntent)
    EditText mEtSeeionConntent;

    private List<TestGreeDaoBean> mData = new ArrayList<>();
    private DaoSession session;
    private DaoMaster daoMaster;
    private TestGreeDaoBeanDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_green_dao);
        ButterKnife.bind(this);
        Utils.init(this);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(TestGreenDaoActivity.this, "myDb", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        session = daoMaster.newSession();
        initData();
    }

    public void initData() {

        String delete = mEtDelete.getText().toString().trim();
        String upDataqian = mEtUpdateQian.getText().toString().trim();
        String upDataHou = mEtUpdateHou.getText().toString().trim();
        final String seeionConntent = mEtSeeionConntent.getText().toString().trim();

        mTvSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* if (!TextUtils.isEmpty(mTvSessionConntent.getText())) {
                    mTvSessionConntent.setText("");
                    List<TestGreeDaoBean> mData = session.getTestGreeDaoBeanDao().loadAll();
                    for (int i = 0; i < mData.size(); i++) {
                        LogUtil.d("testgreendao==", mData.size() + "");
                        if (mData.size() == 0){
                            mTvSessionConntent.setText("没有数据");
                        }else {
                            mTvSessionConntent.setText(mData.get(i).getTestTitle());
                        }
                    }
                } else {*/


                if (seeionConntent.toString().equals("")) {
                    List<TestGreeDaoBean> mData = session.getTestGreeDaoBeanDao().loadAll();
                    for (int i = 0; i < mData.size(); i++) {
                        if (mData.size() == 0) {
                            mTvSessionConntent.setText("没有数据");
                        } else {
                            mTvSessionConntent.setText(mData.get(i).getTestTitle());
                            LogUtil.d("test==",mData.get(i).getTestTitle());
                        }

                    }
                    LogUtil.d("test==", mData.size() + "");
                } else {
                    List<TestGreeDaoBean> mData = session.getTestGreeDaoBeanDao().queryBuilder().where(TestGreeDaoBeanDao.Properties.Id.eq(seeionConntent)).build().list();
                    if (mData.size() == 0) {
                        ToastUtils.showShort("查不到该数据");
                    } else {
                        mTvSessionConntent.setText(mData.get(0).getTestTitle());
                    }
                }

            }
        });

        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String add = mEtAdd.getText().toString().trim();
                if (TextUtils.isEmpty(add)) {
                    ToastUtils.showShort("请先输入要添加的数据~~~");

                } else {
                    TestGreeDaoBean mData = new TestGreeDaoBean();
                    mData.testTitle = add;
                    daoMaster = new DaoMaster(DBManager.getInstance(TestGreenDaoActivity.this).getWritableDatabase());
                    session = daoMaster.newSession();
                    session.getTestGreeDaoBeanDao().insert(mData);
                    mEtAdd.setText("");
                    ToastUtils.showShort("您成功添加了 " + add.toString() + "这条数据");
                    // ToastUitl.showShort("添加成功");
                }
            }
        });
        mTvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mTvDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.getTestGreeDaoBeanDao().deleteAll();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
