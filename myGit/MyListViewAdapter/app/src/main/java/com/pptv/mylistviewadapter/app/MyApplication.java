package com.pptv.mylistviewadapter.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

/**
 * @author LeiKang
 * @time 2017/3/1
 */
public class MyApplication extends Application
{
    static  Context context;
    @Override
    public void onCreate()
    {
        super.onCreate();
        context =this.getApplicationContext();
        DiskCacheConfig diskCacheConfig = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            diskCacheConfig = DiskCacheConfig.newBuilder(context).setBaseDirectoryName(".image").setBaseDirectoryPath(
                    new File(Environment.getExternalStorageDirectory() + "/ppfinance/")).build();
        }
        ImagePipelineConfig config1 = ImagePipelineConfig.newBuilder(context).setMainDiskCacheConfig(diskCacheConfig).build();
        Fresco.initialize(context, config1);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).threadPriority(
                Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(
                new Md5FileNameGenerator()).memoryCache(new LruMemoryCache(2*1024*1024)).memoryCacheSizePercentage(
                80).
                tasksProcessingOrder(
                QueueProcessingType.LIFO).imageDownloader(new BaseImageDownloader(getApplicationContext())).build();
        ImageLoader.getInstance().init(config);

    }

    public static Context getContext()
    {
        return context;
    }
}
