package com.hs.administrator.test.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.administrator.test.R;
import com.hs.administrator.test.adapter.MyExAdapter;
import com.hs.administrator.test.model.GroupBean;
import com.hs.administrator.test.model.itemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExpandableListActivity extends AppCompatActivity {

    @Bind(R.id.Expandable)
    ExpandableListView mExpandable;
    @Bind(R.id.edit_byte)
    EditText mEdByte;
    @Bind(R.id.tv_byte)
    TextView mTvByte;
    @Bind(R.id.but_byte)
    Button mButByte;

    private List<GroupBean> groupBeen = new ArrayList<>();
    private List<List> childList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);
        ButterKnife.bind(this);
        initData();
        MyExAdapter mAdapter = new MyExAdapter(ExpandableListActivity.this, R.layout.item_group_exlist, R.layout.item_exlist, groupBeen, childList);
        mExpandable.setAdapter(mAdapter);

        mButByte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String byteText = mEdByte.getText().toString().trim();
                if (byteText.equals("")) {
                    Toast.makeText(ExpandableListActivity.this, "请输入东西", Toast.LENGTH_SHORT).show();
                    return;
                }

                byteText = byteText.toUpperCase();//装换为大写
                int len = byteText.length()/2;
                byte[] sendData = new byte[24];
                char[] hc = byteText.toCharArray();
                for (int i = 0;i<len;i++){
                    int p = 2* i;
                  //  sendData[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p+1]));
                }


//                byte[] bytetest = byteText.getBytes();
//                mTvByte.setText(bytetest.toString());

            }
        });

    }


    private void initData() {
        groupBeen.add(new GroupBean("魔枪士"));
        groupBeen.add(new GroupBean("鬼剑士"));
        groupBeen.add(new GroupBean("气功师"));

        ArrayList mqs = new ArrayList();
        mqs.add(new itemBean("决战者"));
        mqs.add(new itemBean("征战者"));
        mqs.add(new itemBean("暗枪士"));
        mqs.add(new itemBean("光枪士"));
        childList.add(mqs);
        ArrayList gj = new ArrayList();
        gj.add(new itemBean("鬼泣"));
        gj.add(new itemBean("红狗"));
        gj.add(new itemBean("瞎子"));
        gj.add(new itemBean("吊机"));
        childList.add(gj);
        ArrayList qg = new ArrayList();
        qg.add(new itemBean("念帝"));
        qg.add(new itemBean("街霸"));
        qg.add(new itemBean("基柔"));
        qg.add(new itemBean("极武圣"));
        childList.add(qg);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
