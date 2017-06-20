package com.pptv.mylistviewadapter.textureView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pptv.mylistviewadapter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @anthor LeiKang
 */
public class TextureListViewActivity extends Activity
{
    private MyTextureView mvideoView;

    private ListView listView;

    private MyAdapter myAdapter;

    private int mLastX;

    private int mLastY;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_texture_view);
        listView = (ListView) findViewById(R.id.listView);
        mvideoView = (MyTextureView) findViewById(R.id.textureView);
        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        final List<String> list = new ArrayList<>();

        for (int i = 0; i < 20; i++)
        {
            list.add("aa" + i);
        }
        myAdapter.addDatas(list);
         new Thread(new Runnable() {
             @Override
             public void run() {
                 list.add("ff");
                 myAdapter.addDatas(list);
             }
         }).start();
        mvideoView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent ev)
            {
                int nowX = (int) ev.getX();
                int nowY = (int) ev.getY();
                switch (ev.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        mLastX = nowX;
                        mLastY = nowY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dex = nowX - mLastX;
                        int dey = nowY -mLastY;
                        mvideoView.layout(mvideoView.getLeft()+dex,mvideoView.getTop()+dey,mvideoView.getRight()+dex,mvideoView.getBottom()+dey);
                        //滑动的是container内部的view(view的内容)
//                        mvideoView.scrollTo(-dex,-dey);
                        // Gone 会导致整个view的invalidateSelf 所以view会回到原始位置
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        break;

                }
                return true;
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
            if (position == 6)
            {
                if (convertView == null)
                {
                    viewHolder = new ViewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.title_item, parent, false);
                    viewHolder.textView = (TextView) convertView.findViewById(R.id.tx_tab_item);
                    convertView.setTag(viewHolder);

                }
                else
                {
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                viewHolder.textView.setText(getItem(position));
            }
            else
            {
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
            }

            return convertView;
        }

        @Override
        public int getItemViewType(int position)
        {
            return position == 6 ? 0 : 1;
        }

        @Override
        public int getViewTypeCount()
        {
            return 2;
        }
    }

    static class ViewHolder
    {
        TextView textView;

    }
}
