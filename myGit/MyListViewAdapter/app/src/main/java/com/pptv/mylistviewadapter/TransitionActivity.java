package com.pptv.mylistviewadapter;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionManager;

/**
 * @anthor LeiKang
 */
public class TransitionActivity extends Activity
{

    private Scene scene1,scene2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        ViewGroup sceneRoot= (ViewGroup) findViewById(R.id.scene_root);
//        scene1=Scene.getSceneForLayout(sceneRoot,R.layout.scene_1,this);
//        scene2=Scene.getSceneForLayout(sceneRoot,R.layout.scene_2,this);
//        TransitionManager.go(scene1);
    }
}
