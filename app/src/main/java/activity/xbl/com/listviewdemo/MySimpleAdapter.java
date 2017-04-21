package activity.xbl.com.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by April on 2017/4/19.
 * 一个公用的Adapter 使用了ViewHolder
 */

public class MySimpleAdapter extends BaseAdapter {
    private List<String> data;
    private Context context;
    private LayoutInflater inflater;

    public MySimpleAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
         inflater=LayoutInflater.from(context);
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
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            //通过LayoutInflater实例化布局
            convertView=inflater.inflate(R.layout.item_simple,null);
            holder.item_simple_iv= (ImageView) convertView.findViewById(R.id.item_simple_iv);
            holder.item_simple_tv= (TextView) convertView.findViewById(R.id.item_simple_tv);
            convertView.setTag(holder);
        }else {
            //通过Tag找到缓存的布局
            holder= (ViewHolder) convertView.getTag();
        }
        //为控件设置视图
        holder.item_simple_iv.setBackgroundResource(R.drawable.img);
        holder.item_simple_tv.setText(data.get(position));

        return convertView;
    }
    class ViewHolder{
        public ImageView item_simple_iv;
        public TextView item_simple_tv;

    }
}
