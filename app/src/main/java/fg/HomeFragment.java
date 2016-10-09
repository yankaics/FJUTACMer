package fg;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;

import adapter.OjGameAdapter;
import bean.SingleMegSelf;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import fg.FragmentView.OjGameView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import persenter.OjGameImpl;
import persenter.persenterInterface.OjGamePersenter;
import ui.MyListView;
import ui.MyToast;
import www.fjutoj.com.fjutacmer.R;

/**
 * Created by Administrator on 2016/9/24.
 */
public class HomeFragment extends BaseFragment implements OjGameView,MyListView.LoadListener,PtrHandler {

    private Button  menuButton;
    private MyListView listView;
    private OjGamePersenter ojGamePersenter;
    private Handler handler;
    private OjGameAdapter ojGameAdapter;
    private PtrFrameLayout ptrFrameLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitData();
        InitJush();
    }

    private void InitJush() {
        JPushInterface.setAlias(getActivity(), SingleMegSelf.getSingleMegSelf().getUsername(), new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {

            }
        });

    }

    @Override
    public View InitView(LayoutInflater inflate) {

        return inflate.inflate(R.layout.home_fragment,null);
    }

    @Override
    public void InitFindView() {
        listView = (MyListView) rootView.findViewById(R.id.refresh_listview);
        menuButton = (Button) rootView.findViewById(R.id.home_menu_button);
        ptrFrameLayout = (PtrFrameLayout) rootView.findViewById(R.id.ptr);
        initRefreshHead(ptrFrameLayout);
        listView.setDividerHeight(0);
        listView.setInterface(this);
        listView.setptrFrameLayout(ptrFrameLayout);
        ptrFrameLayout.setPtrHandler(this);
        ojGamePersenter.requestOjGameListData();
    }

    @Override
    public void InitData() {
        handler = new Handler();
        ojGamePersenter = new OjGameImpl(this,handler);

    }

    private void setAdapter() {
        if(ojGameAdapter==null){
            ojGameAdapter = new OjGameAdapter(getActivity().getLayoutInflater(),ojGamePersenter.getOjGameList());
            listView.setAdapter(ojGameAdapter);
        }else {
            ojGameAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        menuButton = null;
        listView = null;
        ojGamePersenter = null;
        handler = null;
    }

    @Override
    public void ojGameRefreshSuccess() {
        setAdapter();
    }

    @Override
    public void ojGameRefreshFall() {
        MyToast.ShowToast(getActivity(),"加载失败", Toast.LENGTH_LONG);
    }

    @Override
    public void ojGameListSuccess() {
        setAdapter();
    }

    @Override
    public void ojGameListFall() {
        MyToast.ShowToast(getActivity(),"加载失败", Toast.LENGTH_LONG);
    }

    @Override
    public void onLoad() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.loadcomplete();
            }
        },3000);
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return true;
    }

    @Override
    public void onRefreshBegin(final PtrFrameLayout frame) {
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("main","refresh----");
                ptrFrameLayout.refreshComplete();
            }
        },2000);
    }
}
