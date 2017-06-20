package com.pptv.mylistviewadapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pptv.mylistviewadapter.R;

import java.util.List;

/**
 * @anthor LeiKang
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>
{
    private Context context;

    private List<String> data;

    public RecycleAdapter(Context context, List<String> data)
    {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position)
    {
        holder.tx.setText(data.get(position));
        // 必须得设置onclick事件 让down 事件在最上层view 被消耗掉 onTouchEvent 可以返回true itemGroup 的onInterceptOnTouchEvent就可以执行到事件move 和 up了
        holder.tx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e("TAG",data.get(position));
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tx;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            tx = (TextView) itemView.findViewById(R.id.tv_content);

        }
    }

}
