package com.pptv.mylistviewadapter.recycleview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.pptv.mylistviewadapter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @anthor LeiKang
 */
public class RecycleViewActivity extends Activity
{
    private MyRecycleView recyclerView;

    private RecycleAdapter adapter;

    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_layout);
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++)
        {
            data.add("aa" + i);
        }
        adapter = new RecycleAdapter(this, data);
        initView();
    }

    public void initView()
    {
        recyclerView = (MyRecycleView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        SimpleItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration((long) (500* 0.75f));
        itemAnimator.setRemoveDuration(0);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback()
        {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
            {
                final int dragFlags;
                final int swipeFlags;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager)
                {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT
                            | ItemTouchHelper.RIGHT;
                    swipeFlags = 0;
                }
                else
                {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    // 侧滑
                    swipeFlags = 0;
                }
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                    RecyclerView.ViewHolder target)
            {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                data.add(toPosition, data.remove(fromPosition));
                adapter.notifyItemMoved(fromPosition, toPosition);
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
            {
                // 侧滑
//                int position = viewHolder.getAdapterPosition();
//                data.remove(position);
//                adapter.notifyItemRemoved(position);
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState)
            {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
                {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
            {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundResource(0);
            }

            @Override
            public boolean isLongPressDragEnabled()
            {
                return true;
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
