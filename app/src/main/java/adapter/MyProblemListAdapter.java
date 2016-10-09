package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bean.MyProblemBean;
import www.fjutoj.com.fjutacmer.R;

/**
 * Created by Administrator on 2016/10/2.
 */
public class MyProblemListAdapter extends BaseAdapter{
    private ArrayList<MyProblemBean> list;
    private LayoutInflater layoutInflater;

    public MyProblemListAdapter(LayoutInflater layoutInflater,ArrayList<MyProblemBean> list){
        this.layoutInflater= layoutInflater;
        this.list = list;
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyProblemBean myProblemBean = list.get(position);
        ViewHorld viewHorld = null;
        if(convertView==null){
            viewHorld = new ViewHorld();
            convertView = layoutInflater.inflate(R.layout.myproblem_list_item,null);
            viewHorld.pid = (TextView) convertView.findViewById(R.id.my_myproblem_pid);
            viewHorld.title = (TextView) convertView.findViewById(R.id.myproblem_title);
            viewHorld.ac = (TextView) convertView.findViewById(R.id.myproblem_ac_num);
            viewHorld.submit = (TextView) convertView.findViewById(R.id.myproblem_subimt_num);
            convertView.setTag(viewHorld);
        }else {
            viewHorld = (ViewHorld) convertView.getTag();
        }

        viewHorld.pid.setText(myProblemBean.getPid()+"");
        viewHorld.title.setText(myProblemBean.getTitle());
        viewHorld.ac.setText(myProblemBean.getAc()+"");
        viewHorld.submit.setText(myProblemBean.getSubmit()+"");
        return convertView;
    }


    static class ViewHorld{
        TextView pid;
        TextView title;
        TextView ac;
        TextView submit;
    }

}
