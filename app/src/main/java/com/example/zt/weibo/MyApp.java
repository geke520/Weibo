package com.example.zt.weibo;

import android.app.Application;

import com.example.zt.weibo.model.BlogUser;

import org.xutils.x;


public class MyApp extends Application {
    private BlogUser blogUser;

    public BlogUser getBlogUser() {
        return blogUser;
    }

    public void setBlogUser(BlogUser blogUser) {
        this.blogUser = blogUser;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
