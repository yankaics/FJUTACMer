package www.fjutoj.com.fjutacmer;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import Catch.MyImageLoader;
import bean.SingleMegSelf;
import tool.StatusColor;
import ui.CircleImageView;
import url.MYURL;

public class MegSelfActivity extends FragmentActivity implements View.OnClickListener{

    private Button back ;
    private CircleImageView circleImageView;
    private ImageView grander;
    private TextView nick;
    private TextView motto;
    private TextView email;
    private TextView school;
    private TextView acb;
    private TextView acnum;
    private TextView submitnum;
    private TextView rating;
    private  TextView ratingnum;
    View rootView;
    Animation animationBegin;
    Animation animationEnd;
    private ImageLoader imageLoader;
    private Activity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.megself_layout);
        rootView = findViewById(R.id.my_megself_layout);
        InitFind();
        Initdata();
        StatusColor.setTranslucentStatus(this,R.color.colortransparent);
        startRootAnimation(savedInstanceState);

    }

    private void InitFind() {
        circleImageView = (CircleImageView) findViewById(R.id.megself_touxiang);
        back = (Button) findViewById(R.id.megself_back);
        back.setOnClickListener(this);
        grander = (ImageView) findViewById(R.id.megself_grander);
        nick = (TextView) findViewById(R.id.megself_nick);
        motto = (TextView) findViewById(R.id.megself_motto);
        email = (TextView) findViewById(R.id.megself_email);
        school = (TextView) findViewById(R.id.megself_school);
        acb = (TextView) findViewById(R.id.megself_acb);
        acnum = (TextView) findViewById(R.id.megself_acnum);
        submitnum = (TextView) findViewById(R.id.megself_submitnum);
        rating = (TextView) findViewById(R.id.megself_rating);
        ratingnum = (TextView) findViewById(R.id.megself_ratingnum);

    }

    private void Initdata() {
        context = this;
        animationBegin = AnimationUtils.loadAnimation(this,R.anim.tran_in_amian2);
        animationEnd = AnimationUtils.loadAnimation(this,R.anim.tran_out_amian2);
        imageLoader = MyImageLoader.getMyImageLoader(this);
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(circleImageView,
                R.mipmap.default_touxiang,R.mipmap.default_touxiang);
        imageLoader.get(MYURL.ROOT_ICON_URL+ MYURL.HEADURL+
                SingleMegSelf.getSingleMegSelf().getUsername()+".jpg",imageListener);
        nick.setText(SingleMegSelf.getSingleMegSelf().getNick());
        setGranderImage(SingleMegSelf.getSingleMegSelf().getGender());
        motto.setText(SingleMegSelf.getSingleMegSelf().getMotto());
        email.setText(SingleMegSelf.getSingleMegSelf().getEmail());
        school.setText(SingleMegSelf.getSingleMegSelf().getSchool());
        acb.setText(SingleMegSelf.getSingleMegSelf().getAcb()+"");
        acnum.setText(SingleMegSelf.getSingleMegSelf().getAcnum()+"");
        submitnum.setText(SingleMegSelf.getSingleMegSelf().getSubmitnum()+"");
        rating.setText(SingleMegSelf.getSingleMegSelf().getRating()+"");
        ratingnum.setText(SingleMegSelf.getSingleMegSelf().getRatingnum()+"");
    }

    private void setGranderImage(int i) {
        if (i==1){
            grander.setImageResource(R.mipmap.men_icon);
        }else {
            grander.setImageResource(R.mipmap.girl_icon);
        }
    }

    private void startRootAnimation(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            rootView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    rootView.getViewTreeObserver().removeOnPreDrawListener(this);
                    rootView.setAnimation(animationBegin);
                    return true;
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.megself_back:
                endRootAnimation();
                break;
        }
    }

    private void endRootAnimation() {
        animationEnd.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                context.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rootView.startAnimation(animationEnd);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        endRootAnimation();

        return false;
       // return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        back =null;
        circleImageView = null;
        grander = null;
        nick = null;
        motto = null;
        email = null;
        school = null;
        acb = null;
        acnum  = null;
        submitnum = null;
        rating = null;
        ratingnum = null;
        rootView = null;
        context = null;
        animationBegin = null;
        animationEnd = null;


    }
}
