package com.pptv.mylistviewadapter.viewlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.pptv.mylistviewadapter.R;

import java.util.LinkedList;
import java.util.List;

/**
 * @anthor LeiKang
 */
public class ViewLayoutActivity extends Activity
{
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(virtualLayoutManager);
        final DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager, true);
        recyclerView.setAdapter(delegateAdapter);
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

    }

    static class Myadapter extends DelegateAdapter.Adapter<MainViewHolder>
    {

        public Myadapter()
        {


        }
        @Override
        public LayoutHelper onCreateLayoutHelper()
        {
            return null;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return null;
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position)
        {

        }

        @Override
        public int getItemCount()
        {
            return 0;
        }
    }

    static class MainViewHolder extends RecyclerView.ViewHolder
    {

        public MainViewHolder(View itemView)
        {
            super(itemView);
        }

    }
}
