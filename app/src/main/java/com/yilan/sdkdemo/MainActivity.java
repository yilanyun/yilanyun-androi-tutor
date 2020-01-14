package com.yilan.sdkdemo;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yilan.sdk.entity.Channel;
import com.yilan.sdk.entity.MediaInfo;
import com.yilan.sdk.player.UserCallback;
import com.yilan.sdk.player.entity.PlayData;
import com.yilan.sdk.player.utils.Constant;
import com.yilan.sdk.ui.category.ChannelFragment;
import com.yilan.sdk.ui.configs.ShareCallback;
import com.yilan.sdk.ui.configs.YLUIConfig;
import com.yilan.sdk.ui.feed.FeedFragment;
import com.yilan.sdk.ui.littlevideo.LittleVideoFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView channelView;
    private TextView feedView;
    private TextView littleView;
    private ChannelFragment channelFragment;
    private FeedFragment feedFragment;
    private LittleVideoFragment littleVideoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册分享回调
        YLUIConfig.getInstance().registerShareCallBack(shareCallback);

        initView();
        showChannel();
    }

    private void initView() {
        channelView = findViewById(R.id.channel);
        feedView = findViewById(R.id.feed);
        littleView = findViewById(R.id.little);
        channelView.setOnClickListener(this);
        feedView.setOnClickListener(this);
        littleView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.channel:
                showChannel();
                break;
            case R.id.feed:
                showFeed();
                break;
            case R.id.little:
                showLittle();
                break;
        }
    }


    private void showChannel() {
        channelView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        feedView.setTextColor(ContextCompat.getColor(this, R.color.yl_color_6));
        littleView.setTextColor(ContextCompat.getColor(this, R.color.yl_color_6));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (channelFragment == null) {
            channelFragment = new ChannelFragment();
            transaction.add(R.id.fragment_container, channelFragment, ChannelFragment.class.getSimpleName());
        } else {
            transaction.show(channelFragment);
        }

        if (feedFragment != null && feedFragment.isAdded()) {
            transaction.hide(feedFragment);
        }

        if (littleVideoFragment != null && littleVideoFragment.isAdded()) {
            transaction.hide(littleVideoFragment);
        }

        transaction.commitAllowingStateLoss();
    }

    private void showFeed() {
        feedView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        channelView.setTextColor(ContextCompat.getColor(this, R.color.yl_color_6));
        littleView.setTextColor(ContextCompat.getColor(this, R.color.yl_color_6));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (feedFragment == null) {
            Channel channel = new Channel();
            channel.setId("1291");
            feedFragment = FeedFragment.newInstance(channel);
            transaction.add(R.id.fragment_container, feedFragment, FeedFragment.class.getSimpleName());
        } else {
            transaction.show(feedFragment);
        }

        if (channelFragment != null && channelFragment.isAdded()) {
            transaction.hide(channelFragment);
        }

        if (littleVideoFragment != null && littleVideoFragment.isAdded()) {
            transaction.hide(littleVideoFragment);
        }

        transaction.commitAllowingStateLoss();


    }

    private void showLittle() {
        littleView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        channelView.setTextColor(ContextCompat.getColor(this, R.color.yl_color_6));
        feedView.setTextColor(ContextCompat.getColor(this, R.color.yl_color_6));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (littleVideoFragment == null) {
            littleVideoFragment = LittleVideoFragment.newInstance();
            transaction.add(R.id.fragment_container, littleVideoFragment, LittleVideoFragment.class.getSimpleName());
            littleVideoFragment.
                    setUserCallBack(new UserCallback() {
                        @Override
                        public boolean event(int type, PlayData data, int playerHash) {
                            switch (type) {
                                case Constant.STATE_PREPARED:
                                    break;
                                case Constant.STATE_ERROR:
                                    break;
                                case Constant.STATE_PLAYING:
                                    break;
                                case Constant.STATE_COMPLETE:
                                    break;
                                case Constant.STATE_PAUSED:
                                    break;
                            }
                            return false;
                        }
                    });
        } else {
            transaction.show(littleVideoFragment);
        }

        if (feedFragment != null && feedFragment.isAdded()) {
            transaction.hide(feedFragment);
        }

        if (channelFragment != null && channelFragment.isAdded()) {
            transaction.hide(channelFragment);
        }
        transaction.commitAllowingStateLoss();
    }

    private ShareCallback shareCallback = new ShareCallback() {
        @Override
        public void onShare(Context context, MediaInfo mediaInfo) {
            //可在此处进行分享弹窗操作
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册分享回调
        YLUIConfig.getInstance().unRegisterShareCallBack();
    }
}
