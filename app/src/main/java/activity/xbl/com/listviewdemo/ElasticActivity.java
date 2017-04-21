package activity.xbl.com.listviewdemo;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by April on 2017/4/20.
 * 装载自定义ElasticListView
 */

public class ElasticActivity extends Activity {
    private ElasticListView elastic_lv;
    private List<String> data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elastic);


        initView();


    }

    private void initView() {
        elastic_lv = (ElasticListView) findViewById(R.id.elastic_lv);
        initdata();
        elastic_lv.setAdapter(new MySimpleAdapter(data, ElasticActivity.this));
    }

    //ListView的数据填充
    private void initdata() {
        data = new ArrayList<>();
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("我是数据：" + i);
        }
    }








}
