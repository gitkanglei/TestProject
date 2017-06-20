package com.pptv.mylistviewadapter.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.pptv.mylistviewadapter.model.TypeFactory;
import com.pptv.mylistviewadapter.model.TypeFactoryImp;
import com.pptv.mylistviewadapter.model.visible;

import java.util.List;

/**
 * @author LeiKang
 * @time 2017/2/24
 */
public class MyAdapter extends BaseAdapter
{
    private List<visible> mDatas;

    private TypeFactory typeFactory;

    private Context mContext;

    public MyAdapter()
    {
        typeFactory =new TypeFactoryImp();
    }
    @Override
    public int getCount()
    {
        return mDatas.size();
    }

    @Override
    public visible getItem(int position)
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

        if(convertView ==null)
        {
            convertView = LayoutInflater.from(mContext).inflate(getItemViewType(position),parent,false);
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position)
    {
        return mDatas.get(position).type(typeFactory);
    }

    @Override
    public int getViewTypeCount()
    {
        return super.getViewTypeCount();
    }


}
