package com.pptv.mylistviewadapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pptv.mylistviewadapter.pullzoom.PullZoomListView;
import com.pptv.mylistviewadapter.view.ContainerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LeiKang
 * @time 2017/3/2
 */
public class ListViewActivity extends Activity
{
    private ListView myListView;

    private MyAdapter myAdapter;

    private LinearLayout footView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // LinearLayout l =new LinearLayout(this);
        // footView = new ContainerView(this);
        // myListView.addHeaderView(footView);
        // FrameLayout.LayoutParams params =new
        // FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,0);
        setContentView(R.layout.list_view_layout);
        // l.addView(footView,params);
        initView();
        // footView.setLayoutParams(params);
    }

    private void initView()
    {
        myListView = (ListView) findViewById(R.id.listView);
        // myListView.addHeaderView(footView);
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
                // if (firstVisibleItem + visibleItemCount == totalItemCount -
                // 1)
                // {
                // AbsListView.LayoutParams params =
                // (AbsListView.LayoutParams) footView.getLayoutParams();
                // params.height = 0;
                // }
                // ListView的滚动并不改变ListView的偏移量getScrollY();
                // Log.e("TAG", "y轴偏移量:" + myListView.getTranslationY());
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
            {
                Log.e("TAG", "parent :" + parent + " view:" + view);
//                 buildListRemoveAnimator(view,list,myAdapter,position).start();
                buildAnimation(position, list);
                // upAnimation(view, list, position);
                // list.remove(position);
                // myAdapter.addDatas(list);
                // AnimatorSet animatorSet = new AnimatorSet();
                // animatorSet.playTogether(highToZeroAnimation(view),
                // scaleAnimation(view));
                // animatorSet.setDuration(1000);
                // animatorSet.addListener(new AnimatorListenerAdapter()
                // {
                // @Override
                // public void onAnimationEnd(Animator animation)
                // {
                // super.onAnimationEnd(animation);
                // list.remove(position);
                // myAdapter.addDatas(list);
                // }
                // });
                // animatorSet.start();

            }
        });

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (list.size() <= 18)
                {
                    return;
                }
                View listItem = myAdapter.getView(18, null, myListView);
                listItem.measure(0, 0);
                int h = listItem.getMeasuredHeight();
                list.remove(18);
                list.remove(18);
                AbsListView.LayoutParams params = (AbsListView.LayoutParams) footView.getLayoutParams();
                params.height = h * 2;
                myAdapter.addDatas(list);
            }
        });
    }

    // 上移动的动画
    public void upAnimation(View verifyCodeView, List<String> list, int position)
    {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet1 = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        List<Animator> resultList = new LinkedList<Animator>();
        List<Animator> resultList1 = new LinkedList<Animator>();
        for (int i = position; i < myListView.getChildCount(); i++)
        {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myListView.getChildAt(i), "TranslationY", 0,
                    -verifyCodeView.getHeight());
            resultList.add(objectAnimator);
        }
        // resultList1.add(scaleAnimation(verifyCodeView));
        // resultList1.add(highToZeroAnimation(verifyCodeView));
        // animatorSet.playTogether(resultList1);
        animatorSet1.playTogether(resultList);
        // animatorSet2.playTogether(animatorSet, animatorSet1);
        animatorSet1.setDuration(1000);
        animatorSet1.start();
    }

    // 高度变成0 的动画
    public ValueAnimator highToZeroAnimation(final View verifyCodeView)
    {
        final AbsListView.LayoutParams params = (AbsListView.LayoutParams) verifyCodeView.getLayoutParams();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(verifyCodeView.getHeight(), 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                params.height = (int) animation.getAnimatedValue();
                // verifyCodeView.setLayoutParams(params);
                verifyCodeView.requestLayout();
                Log.e("TAG", verifyCodeView.getHeight() + "");
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                super.onAnimationEnd(animation);
                // params.height = 0;
                // verifyCodeView.setLayoutParams(params);
                Log.e("TAG", verifyCodeView.getHeight() + "end");
            }
        });
        return valueAnimator;
    }

    public ValueAnimator scaleAnimation(View verifyCodeView)
    {
        PropertyValuesHolder xValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f);
        PropertyValuesHolder yValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(verifyCodeView, xValuesHolder,
                yValuesHolder, alpha);
        return objectAnimator;
    }

    public AnimatorSet buildListRemoveAnimator(final View view, final List list, final MyAdapter adapter,
            final int index)
    {
        final int height = view.getMeasuredHeight();
        Animator.AnimatorListener al = new Animator.AnimatorListener()
        {

            @Override
            public void onAnimationStart(Animator animation)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                // TODO Auto-generated method stub
                list.remove(index);
                //
                 adapter.addDatas(list);
                for (int i = 0; i < myListView.getChildCount(); ++i)
                {
                    View v = myListView.getChildAt(i);

                    v.setAlpha(1f);
                    v.setScaleY(1f);
                    v.setScaleX(1f);
                    v.getLayoutParams().height = height;
                    v.requestLayout();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation)
            {
                // TODO Auto-generated method stub

            }
        };

        AnimatorSet animatorSet = new AnimatorSet();
        Animator anim = ObjectAnimator.ofFloat(view, "rotationX", 0, 90);
        PropertyValuesHolder xValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f);
        PropertyValuesHolder yValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, xValuesHolder, yValuesHolder,
                alpha);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                // TODO Auto-generated method stub
                if (animation.getAnimatedFraction() >= 1)
                {
                    view.setVisibility(View.GONE);
                }
                else
                {
                    view.getLayoutParams().height = height - (int) (height * animation.getAnimatedFraction());
                    view.requestLayout();
                    Log.e("TAG", "高度 ：" + view.getMeasuredHeight());
                }
            }
        });

        anim.setDuration(100);
        objectAnimator.setDuration(100);
        valueAnimator.setDuration(100 + 1000 + 100);
        animatorSet.playTogether(anim, objectAnimator, valueAnimator);
        animatorSet.addListener(al);
        return animatorSet;
    }

    private void buildAnimation(final int position, final List<String> list)
    {
        int firstVisiblePosition = myListView.getFirstVisiblePosition(); // 存储所有的Animator，利用AnimatorSet直接播放
        ArrayList<Animator> animators = new ArrayList<Animator>();
        View itemToDelete = myListView.getChildAt(position - firstVisiblePosition);

        int viewHeight = itemToDelete.getHeight();
        int dividerHeight = myListView.getDividerHeight();

        PropertyValuesHolder xValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f);
        PropertyValuesHolder yValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(itemToDelete, xValuesHolder, yValuesHolder,
                alpha);

        animators.add(objectAnimator);

        int delay = 0;
        int firstViewToMove = position + 1;
        for (int i = firstViewToMove; i <= myListView.getLastVisiblePosition(); i++)
        {
            View viewToMove = myListView.getChildAt(i-firstVisiblePosition);
            ObjectAnimator moveAnimator = ObjectAnimator.ofFloat(viewToMove, "translationY", 0,
                    -dividerHeight - viewHeight);
            moveAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            moveAnimator.setStartDelay(delay);

            delay += 100;

            animators.add(moveAnimator);
        }

        AnimatorSet set = new AnimatorSet();
        set.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animation)
            {
            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                list.remove(position);

                myAdapter.addDatas(list);
                // 动画结束后，恢复ListView所有子View的属性
                for (int i = 0; i < myListView.getChildCount(); ++i)
                {
                    View v = myListView.getChildAt(i);

                    v.setAlpha(1f);
                    v.setScaleY(1f);
                    v.setScaleX(1f);
                    v.setTranslationY(0);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation)
            {
            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {
            }
        });

        set.playTogether(animators);
        set.start();
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
