package activity.xbl.com.listviewdemo;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by April on 2017/4/21.
 * 模仿知乎，实现toolbar的自动隐藏于显示
 * 1.用相对布局放置ListView和toolbar
 * 2.为ListView添加头部，防止toolbar遮挡
 * 3.添加ListView的滚动监听事件，判断是手指是上滑还是下拉
 * 4.根据手指的不同状态为toolBar添加隐藏或者显示的动画
 */

public class HideToolBarActivity extends Activity {
    private ListView hide_lv;
    private List<String> data;
    private int touchSlop;
    //手指第一次落下时的坐标
    float firstY;
    //中途手指滑动的距离
    float moveY;
    //滑动模式
    private int direction;
    //当前的toolbar是否显示
    boolean isShow = true;
    //toolbar的动画效果
    private ObjectAnimator animator;
    //toolbar
    private Toolbar hide_toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_toolbar);
        hide_toolbar= (Toolbar) findViewById(R.id.hide_toolbar);
        initView();
        //添加一个头部布局
        View header = new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams
                (AbsListView.LayoutParams.MATCH_PARENT,
                        (int) getResources().
                                //定义toolBar的高度为56dp
                                        getDimension(R.dimen.abc_action_bar_default_height_material)));
        hide_lv.addHeaderView(header);
        //实例化toolbar

        //得到系统认为的最低滑动距离
        touchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        hide_lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        firstY = (int) event.getY();
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        moveY = (int) event.getY();
                        if (moveY - firstY > touchSlop) {
                            //向下滑动
                            direction = 1;
                        } else
                        //向上滑动
                        {
                            direction = 0;
                        }
                        if (direction == 1) {
                            if (isShow) {
                                toolbarAnim(0);//显示
                                isShow = false;
                            }
                        } else {
                            if (!isShow) {
                                toolbarAnim(1);//隐藏
                                isShow = true;
                            }

                        }
                        break;

                    }


                }
                return false;
            }
        });
    }

    private void initView() {
        hide_lv = (ListView) findViewById(R.id.hide_lv);
        initdata();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data);
        hide_lv.setAdapter(adapter);
    }

    //ListView的数据填充
    private void initdata() {
        data = new ArrayList<>();
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("我是数据：" + i);
        }
    }



    //控制布局显示隐藏的动画-----flag 当前状态，是隐藏还是显示
    private void toolbarAnim(int flag) {
        if (animator != null && animator.isRunning())
            animator.cancel();
        if (flag == 0) {//显示
            animator = ObjectAnimator.ofFloat(
                    hide_toolbar,
                    "translationY",
                    hide_toolbar.getTranslationY(),
                    0);
            Log.d("tag", "下滑" + hide_toolbar.getTranslationY());
        } else {//隐藏
            animator = ObjectAnimator.ofFloat(
                    hide_toolbar,
                    "translationY",
                    hide_toolbar.getTranslationY(),
                    -hide_toolbar.getHeight());
            Log.d("tag", "上滑getTranslationY" + hide_toolbar.getTranslationY());
            Log.d("tag", "上滑getHeight()" + hide_toolbar.getHeight());
        }
        animator.start();


    }
    }


