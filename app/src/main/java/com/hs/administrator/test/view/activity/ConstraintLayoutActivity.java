package com.hs.administrator.test.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hs.administrator.test.R;
import com.hs.administrator.test.utils.ObjectContainer;

import java.util.ArrayList;
import java.util.List;

public class ConstraintLayoutActivity<T> extends AppCompatActivity {

    private T haha;

    Button mButton, mButTwo, mButThree, mButfour;

    private ObjectContainer myObj = new ObjectContainer();

    double money = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
        mButton = findViewById(R.id.but_one);
        mButTwo = findViewById(R.id.but_two);
        mButThree = findViewById(R.id.but_three);
        mButfour = findViewById(R.id.but_four);

        initOnClick();
    }

    private void initOnClick(){



        mButTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myObj.setObject(1);
                mButTwo.setText(myObj.getObject()+"");
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myObj.setObject("Test");
                Log.d("Value of myObj:", myObj.getObject() + "");
                mButton.setText(myObj.getObject()+"");
            }
        });

        mButThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List objList = new ArrayList();
                objList.add(myObj);
                String myStr = (String) ((ObjectContainer)objList.get(0)).getObject();
                mButThree.setText(myStr+"");
            }
        });
    }

}
