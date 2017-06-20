package com.pptv.mylistviewadapter.fresco;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.pptv.mylistviewadapter.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @anthor LeiKang
 */
public class FrescoActivity extends Activity
{
    private AsyncImageView simpleDraweeView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fresco_layout_activity);
        // 必须指定宽高
        simpleDraweeView  = (AsyncImageView) findViewById(R.id.imageView);
////        simpleDraweeView.setImageURI("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
////        simpleDraweeView.setImageURI("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1341010049,2167151703&fm=23&gp=0.jpg");
//        RoundingParams roundingParams = new RoundingParams();
//        roundingParams.setRoundAsCircle(true);
//        GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(getResources()).setRoundingParams(roundingParams)
//                .setFadeDuration(5000).build();
//        simpleDraweeView.setHierarchy(hierarchy);
//        DraweeController controller = Fresco.newDraweeControllerBuilder().setUri("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1341010049,2167151703&fm=23&gp=0.jpg").build();
//        simpleDraweeView.setController(controller);
        // fresco gif 不支持圆形图片
        simpleDraweeView.setCircleImageUrl("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
        ImageRequest imageRequest = ImageRequest.fromUri("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
        CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(imageRequest, null);
        ImagePipeline pl = Fresco.getImagePipeline();
        CloseableReference<CloseableImage> closeableImageCloseableReference = pl.getBitmapMemoryCache().get(cacheKey);
        Log.e("aa","ff");
    }
}
