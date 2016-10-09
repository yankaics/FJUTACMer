package fg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import Catch.MyImageLoader;
import bean.SingleMegSelf;
import ui.CircleImageView;
import url.MYURL;
import www.fjutoj.com.fjutacmer.MegSelfActivity;
import www.fjutoj.com.fjutacmer.R;

/**
 * Created by Administrator on 2016/9/24.
 */
public class MenuFragment extends BaseFragment implements View.OnClickListener{

    private CircleImageView circleImageView;
    private TextView nick;
    private TextView acb;
    private TextView motto;
    private ImageLoader imageLoader;
    private View layoutMegSelf;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // InitData();
    }

    @Override
    public View InitView(LayoutInflater inflate) {
        return inflate.inflate(R.layout.menu_layout,null);
    }

    @Override
    public void InitFindView() {
        circleImageView = (CircleImageView) rootView.findViewById(R.id.menu_touxiang);
        nick = (TextView) rootView.findViewById(R.id.menu_nick);
        acb = (TextView) rootView.findViewById(R.id.menu_acb);
        motto = (TextView) rootView.findViewById(R.id.menu_mottor);
        layoutMegSelf = rootView.findViewById(R.id.menu_megself);
        layoutMegSelf.setClickable(true);
        InitListener();
        InitData();
    }

    @Override
    public void InitData() {
        nick.setText(SingleMegSelf.getSingleMegSelf().getNick());
        acb.setText(SingleMegSelf.getSingleMegSelf().getAcb()+"");
        motto.setText(SingleMegSelf.getSingleMegSelf().getMotto());
        imageLoader = MyImageLoader.getMyImageLoader(getContext());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(circleImageView,
                R.mipmap.default_touxiang,R.mipmap.default_touxiang);
        imageLoader.get(MYURL.ROOT_ICON_URL+ MYURL.HEADURL+
                SingleMegSelf.getSingleMegSelf().getUsername()+".jpg",imageListener);
      //  Log.d("main",SingleMegSelf.getSingleMegSelf().toString());
     //   InitListener();
    }

    private void InitListener() {
        layoutMegSelf.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case   R.id.menu_megself :
                Intent intent = new Intent(getContext(),MegSelfActivity.class);
                startActivity(intent);
                break;
        }
    }
}
