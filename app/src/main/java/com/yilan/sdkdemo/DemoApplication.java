package com.yilan.sdkdemo;

import android.app.Application;

import com.yilan.sdk.ui.YLUIInit;
import com.yilan.sdk.ui.configs.CommentConfig;
import com.yilan.sdk.ui.configs.FeedConfig;
import com.yilan.sdk.ui.configs.YLUIConfig;

/**
 * Author And Date: liurongzhi on 2020/1/10.
 * Description: com.yilan.sdkdemo
 */
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化sdk
        YLUIInit.getInstance()
                .setApplication(this)
                .setAccessKey("")//请填入相应的AccessKey
                .setAccessToken("")
                .build();

        //页面个性化配置
        YLUIConfig.getInstance()
                //抖音样式小视频配置
                .littleLikeShow(false) //是否显示点赞
                .littleShareShow(false)//是否显示分享
                .littleComment(CommentConfig.CommentType.DISMISS_COMMENT)//评论,参数详见文档；
                //feed流短视频样式配置
                .videoComment(CommentConfig.CommentType.DISMISS_COMMENT)//评论,参数详见文档；
                .videoLikeShow(false)//是否显示点赞
                .videoShareShow(false)//是否显示分享
                .followAvailable(false)//关注功能是否可用
                .feedAvatarClickable(true)//头像是否支持点击跳转到作者页面
                .feedSwipeRefreshEnable(true);//feed是否可以下拉刷新
        FeedConfig.getInstance().setPlayerStyle(FeedConfig.STYLE_NATIVE);//FeedConfig.STYLE_STYLE_FEED_PLAY当前页面播放


    }
}
