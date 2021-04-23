package com.example.zt.weibo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zt.weibo.R;

import java.util.List;

public class CateAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public CateAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder viewHolder=null;

        if(view==null)
        {
            view=LayoutInflater.from(context).inflate(R.layout.gridviewitem,null);
            viewHolder=new MyViewHolder();
            viewHolder.textView= (TextView) view.findViewById(R.id.grid_textview);
            view.setTag(viewHolder);

        }
        else
        {
            viewHolder= (MyViewHolder) view.getTag();
        }
        viewHolder.textView.setText(list.get(i));

        return view;
    }
    static class MyViewHolder{
        private TextView textView;
    }
}
