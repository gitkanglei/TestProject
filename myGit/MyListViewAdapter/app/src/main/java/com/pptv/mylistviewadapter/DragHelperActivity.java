package com.pptv.mylistviewadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pptv.mylistviewadapter.view.DragHelperPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LeiKang
 * @time 2017/3/2
 */
public class DragHelperActivity extends Activity
{

    private ListView myListView;

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_drag_layout);
        initView();
    }

    private void initView()
    {
        myListView = (ListView) findViewById(R.id.listView);
        myAdapter = new MyAdapter(this);
        myListView.setAdapter(myAdapter);
        final List<String> list = new ArrayList<>();

        for (int i = 0; i < 20; i++)
        {
            list.add("aa" + i);
        }
        myAdapter.addDatas(list);
        myListView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {

            }
        });
    }

    static class MyAdapter extends BaseAdapter
    {
        public List<String> mDatas;

        private Context context;

        MyAdapter(Context context)
        {
            mDatas = new ArrayList<>();
            this.context = context;
        }

        public void addDatas(List<String> datas)
        {
            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }

        @Override
        public int getCount()
        {
            return mDatas.size();
        }

        @Override
        public String getItem(int position)
        {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder viewHolder;
            if (convertView == null)
            {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.tab_item, parent, false);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tx_tab_item);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(getItem(position));
            return convertView;
        }
    }

    static class ViewHolder
    {
        TextView textView;

    }
}
