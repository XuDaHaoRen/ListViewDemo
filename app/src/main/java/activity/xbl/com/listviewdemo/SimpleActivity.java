package activity.xbl.com.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现功能：
 * 1.ViewHolder模式提高效率
 * 2.设置项目之间的分割线，滚动条，点击效果
 * 3.数据更新，并将得到的数据显示在当前（可以增加用户体验）
 * 4.得到ListView一共包含多少个子控件（有问题）
 * 5.当list数据为空的时候，显示一张图片/或者提醒
 * 6.ListView的滑动监听---打印LOG日志 标签EVENT
 */

public class SimpleActivity extends AppCompatActivity {
    private ListView simple_lv;
    private List<String> data;
    private Button simple_refresh_btn;
    private MySimpleAdapter mySimpleAdapter;
    private Button simple_getname_btn;
    //添加的数据条目数
    int count=0;
    int lastVisibleItemPosition;
    int firstVisiblePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        initdata();
        initView();
        //获得可视区域最后一个Item的ID
        lastVisibleItemPosition=simple_lv.getLastVisiblePosition();
        //点击之后添加数据
        simple_refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add("我是新增的数据:"+count++);
                mySimpleAdapter.notifyDataSetChanged();
                //并将最新的数据显示到最前面
                simple_lv.setSelection(data.size()-1);
            }
        });
        //点击之后得到listview中item控件的名称
        simple_getname_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //遍历ListView中的所有item
                for(int i=0;i<simple_lv.getChildCount();i++){
                    Log.d("TAG",simple_lv.getChildCount()+"");
                    View view=simple_lv.getChildAt(i);
                    Log.d("TAG", view.getId()+"");
                }
            }
        });
        //滑动监听
        simple_lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //触摸时操作
                    case MotionEvent.ACTION_DOWN:
                        Log.d("EVENT","手指触摸");
                        break;
                    //移动时操作
                    case MotionEvent.ACTION_MOVE:
                        Log.d("EVENT","手指移动");
                        break;
                    //离开时操作
                    case MotionEvent.ACTION_UP:
                        Log.d("EVENT","手指离开");
                        Log.d("TAG","----------------------------------------");
                        break;
                }
                return false;
            }
        });
        //将滑动时的细节分的更详细
        simple_lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    //滑动停止时
                    case SCROLL_STATE_IDLE:
                        Log.d("EVENT","滑动停止");
                        break;
                    //正在滑动
                    case SCROLL_STATE_TOUCH_SCROLL:
                        Log.d("EVENT","正在滚动");
                        break;
                    //手指抛动  手指用力滑动
                    //在离开ListView后由于惯性继续滑动
                    case SCROLL_STATE_FLING:
                        Log.d("EVENT","手指抛动");
                        break;
                }

            }
            //这个方法在ListView滚动时会一直回调
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //获得可视区域第一个Item的ID
                firstVisiblePosition=simple_lv.getFirstVisiblePosition();
                Log.d("EVENT","最后一个显示的ItemID"+lastVisibleItemPosition);
                //firstVisibleItem 当前看到的第一个Item的ID
                //visibleItemCount 当前看到的Item的总数
                //totalItemCount 整个ListView 的Item的个数
                if(firstVisiblePosition>lastVisibleItemPosition){
                    Log.d("EVENT","我是一直滚动状态:上滑");
                }else if (firstVisiblePosition<lastVisibleItemPosition){
                    Log.d("EVENT","我是一直滚动状态:下滑");
                }
                //将最下面的设置为当前显示的第一个
                lastVisibleItemPosition=firstVisibleItem;
                if(firstVisibleItem+visibleItemCount==totalItemCount&&totalItemCount>0){
                    Toast.makeText(SimpleActivity.this,"已经滑动到最后一行",Toast.LENGTH_LONG).show();
                }



            }
        });




    }
    //控件实例化
    private void initView() {
        mySimpleAdapter=new MySimpleAdapter(data,this);
        simple_lv= (ListView) findViewById(R.id.simple_lv);
        simple_lv.setAdapter(mySimpleAdapter);
        //如果数据为空会显示View中的内容
        simple_lv.setEmptyView(findViewById(R.id.simple_datanull_iv));
        //选择一开始显示第10条数据,瞬间移动
        //simple_lv.setSelection(10);
        simple_refresh_btn= (Button) findViewById(R.id.simple_refresh_btn);
        simple_getname_btn= (Button) findViewById(R.id.simple_getname_btn);
//        //显示到第10条数据，平滑移动
//        simple_lv.smoothScrollBy();
//        simple_lv.smoothScrollByOffset();
//        simple_lv.smoothScrollToPosition();
    }
    //ListView的数据填充
    private void initdata() {
        data=new ArrayList<>();
        for(int i=0;i<20;i++){
            data.add("我是数据："+i);
        }
    }

}
