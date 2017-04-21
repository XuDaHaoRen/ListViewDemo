package activity.xbl.com.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by April on 2017/4/21.
 * 点击一个Item后实现一个动态改变布局的效果
 * 1.设置动态变化后的效果图，并将其返回
 * 2.将其余的Item 实例化
 * 3.在每次getView（）的时候将布局添加--通过ListView的点击Item事件判断哪个Item是要被变换的
 * 就将哪个布局部署上
 */

public class FocusAdapter extends BaseAdapter {
    private Context context;
    private List<String>data;
    //得到当前点击的item
    private int currentItem;

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    public FocusAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (currentItem==position){
            return addFocusView(position);
        }else {
            return addNormalView(position);
        }

    }
    //添加一个点击之后变化的布局
    private View addFocusView(int i){
        ImageView iv=new ImageView(context);
        iv.setImageResource(R.drawable.img);
        return iv;
    }
    //添加整个布局
    private View addNormalView(int i){
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.item_simple,null);
        TextView tv= (TextView) view.findViewById(R.id.item_simple_tv);
        ImageView iv= (ImageView) view.findViewById(R.id.item_simple_iv);
        tv.setText(data.get(i));
        iv.setImageResource(R.drawable.img);
        return view;
    }
}
