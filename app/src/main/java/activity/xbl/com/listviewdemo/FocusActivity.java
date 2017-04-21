package activity.xbl.com.listviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by April on 2017/4/21.
 * 实现将ListView中的Item拖拽到别的Item上面--只要功能在FocusAdapter中实现
 */

public class FocusActivity extends Activity {
    private ListView focus_lv;
    private List<String> data;
    private FocusAdapter focusAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        initView();
        focus_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                focusAdapter.setCurrentItem(position);
                focusAdapter.notifyDataSetChanged();
            }
        });

    }
    //ListView的数据填充
    private void initdata() {
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("我是数据：" + i);
        }
    }
    private void initView(){
        focus_lv= (ListView) findViewById(R.id.focus_lv);
        initdata();
        focusAdapter=new FocusAdapter(FocusActivity.this,data);
        focus_lv.setAdapter(focusAdapter);
    }

}
