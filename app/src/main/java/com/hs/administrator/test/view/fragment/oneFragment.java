package com.hs.administrator.test.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hs.administrator.test.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * 2018/11/27 0027.
 */

public class oneFragment extends Fragment {
    private Button mButImport;
    private TextView mText;

    protected static final int RESULT_SPEECH = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,null);
        Log.d("oneFragment","onCreateView");
        mButImport = view.findViewById(R.id.but_import);
        mText =view.findViewById(R.id.test);
        initClick();
        return view;
    }

    public void initClick(){
        Intent intent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"en-US");
        try{
            startActivityForResult(intent,RESULT_SPEECH);
            mText.setText("");
        }catch (Exception e){

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULT_SPEECH:
                if (resultCode == RESULT_OK && null != data){
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mText.setText(text.get(0));
                }
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("oneFragment","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("oneFragment","onCreate");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("oneFragment","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("oneFragment","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("oneFragment","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("oneFragment","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("oneFragment","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("oneFragment","onDestroyView");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("oneFragment","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("oneFragment","onDestroy");
    }
}
