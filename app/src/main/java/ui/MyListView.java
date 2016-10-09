package ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import www.fjutoj.com.fjutacmer.R;

/**
 * Created by Administrator on 2016/9/29.
 */
public class MyListView extends ListView implements AbsListView.OnScrollListener{
    private View footer;
    public boolean isloading;
    private int TotalItemCount;
    private int lastVisibleItem;
    private LoadListener LoadListener;
    private PtrFrameLayout ptrFrameLayout;
    public MyListView(Context context) {
        super(context);
        init(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footer =inflater.inflate(R.layout.listview_foot_layout,null);
        footer.findViewById(R.id.listview_foot_imageview).setVisibility(View.GONE);
        addFooterView(footer);
        this.setOnScrollListener(this);

    }

    public void setptrFrameLayout(PtrFrameLayout ptrFrameLayout){
        this.ptrFrameLayout = ptrFrameLayout;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(lastVisibleItem==TotalItemCount&&
                scrollState ==SCROLL_STATE_IDLE){
            if(!isloading){
                isloading = true;
                footer.findViewById(R.id.listview_foot_imageview).setVisibility(VISIBLE);
                if(LoadListener!=null)
                    LoadListener.onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
     //   Log.d("main","firstVisibleItem"+firstVisibleItem+" visibleItemCount"+visibleItemCount+" totalItemCount"+totalItemCount);
        View firstView = view.getChildAt(firstVisibleItem);
        if(ptrFrameLayout!=null)
        {
            if (firstVisibleItem == 0 && (firstView == null || firstView.getTop() == 0)) {
                ptrFrameLayout.setEnabled(true);
            } else {
                ptrFrameLayout.setEnabled(false);
            }
        }

        lastVisibleItem = firstVisibleItem + visibleItemCount;
        TotalItemCount = totalItemCount;
    }

    public void loadcomplete(){
        this.isloading = false;
        footer.findViewById(R.id.listview_foot_imageview).setVisibility(View.GONE);

    }

    public void setInterface(LoadListener LoadListener)
    {
        this.LoadListener = LoadListener;
    }

    public interface LoadListener{
        public void onLoad();
    }
}
