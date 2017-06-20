package com.pptv.mylistviewadapter.textureView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.pptv.mylistviewadapter.R;

/**
 * @anthor LeiKang
 */
public class ViewPagerPlayActivity extends Activity
{
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_play);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        PlayViewPagerAdapter playViewPagerAdapter = new PlayViewPagerAdapter();
        viewPager.setAdapter(playViewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
    }

    class PlayViewPagerAdapter extends PagerAdapter
    {

        @Override
        public int getCount()
        {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            View view = new MyTextureView(ViewPagerPlayActivity.this);
            container.addView(view);
            return view;
        }
    }
}
