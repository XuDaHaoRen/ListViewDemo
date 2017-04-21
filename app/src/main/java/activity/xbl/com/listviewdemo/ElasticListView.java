package activity.xbl.com.listviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ListView;

/**
 * Created by April on 2017/4/20.
 * 通过重新ListView实现弹性效果
 * 1.设置下拉的距离高度 2.将距离高度喜欢换成当前分辨率的像素值
 * 3.重写overScrollBy方法，修改maxOverScrollY的值
 * 仿照知乎的自动显示，隐藏头部布局
 * 1.添加一个头部布局，避免第一个Item被Toolbar遮挡
 *
 */

public class ElasticListView extends ListView {
    //下拉到最后弹出的高度
    int mMaxOverDistance=50;
    public ElasticListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //将dpi转换成pix的方法
        initView(context);


    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY,
                                   int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY,
                scrollX, scrollY, scrollRangeX,
                scrollRangeY, maxOverScrollX, mMaxOverDistance, isTouchEvent);
    }
    //将dpi转换成pix的方法
    private void initView(Context context){
        //描述物体的size,density的东西
        DisplayMetrics metrics=context.getResources().getDisplayMetrics();
        //将dip转换成pix
        float density=metrics.density;
        mMaxOverDistance=(int)(density*mMaxOverDistance);
    }

}
