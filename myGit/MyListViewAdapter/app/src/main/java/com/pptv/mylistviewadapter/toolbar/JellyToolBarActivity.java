package com.pptv.mylistviewadapter.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Window;

import com.pptv.mylistviewadapter.R;
import com.yalantis.jellytoolbar.listener.JellyListener;
import com.yalantis.jellytoolbar.widget.JellyToolbar;

/**
 * @anthor LeiKang
 */
public class JellyToolBarActivity extends AppCompatActivity
{
    private JellyToolbar toolbar;

    private AppCompatEditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jelly_toolbar_layout);

        toolbar = (JellyToolbar) findViewById(R.id.toolbar);
        toolbar.getToolbar().setNavigationIcon(R.mipmap.ic_menu);
        toolbar.setJellyListener(jellyListener);
        editText = (AppCompatEditText) LayoutInflater.from(this).inflate(R.layout.edit_text, null);
        toolbar.setContentView(editText);
    }

    private JellyListener jellyListener = new JellyListener()
    {
        @Override
        public void onCancelIconClicked()
        {
            if (TextUtils.isEmpty(editText.getText()))
            {
                toolbar.collapse();
            }
            else
            {
                editText.getText().clear();
            }
        }
    };
}
