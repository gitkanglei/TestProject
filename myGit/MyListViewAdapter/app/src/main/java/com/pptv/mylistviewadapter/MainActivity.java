package com.pptv.mylistviewadapter;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends AppCompatActivity
{

    private FragmentTabHost mTabHost;

    private LayoutInflater inflater;

    // Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "消息", "好友", "广场", "更多"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fragment_tab);
        initView();
    }

    private void initView()
    {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realTabContent);
        inflater = LayoutInflater.from(this);
        for (int i = 0; i < 4; i++)
        {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(
                    initTabItem(mTextviewArray[i]));
            Bundle bundle = new Bundle();
            bundle.putString("tag", mTextviewArray[i]);
            mTabHost.addTab(tabSpec, AFragment.class, bundle);
        }

        mTabHost.getTabWidget().getChildAt(0).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AFragment fragment = (AFragment) getSupportFragmentManager().findFragmentByTag("首页");
                if(fragment!=null)
                {
                    Toast.makeText(MainActivity.this,"fragment",100).show();
                }
                mTabHost.setCurrentTab(0);
            }
        });
    }

    private View initTabItem(String tx)
    {
        View itemView = inflater.inflate(R.layout.tab_item, null);
        ImageView img = (ImageView) itemView.findViewById(R.id.imageview);
        TextView textTv = (TextView) itemView.findViewById(R.id.tx_tab_item);
        textTv.setText(tx);
        return itemView;
    }


}
