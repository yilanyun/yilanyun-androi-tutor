# Android SDK最简接入教程

<a name="e8482479"></a>
## 1. 初始化项目
<a name="fffec4b8"></a>
### 1.1 添加SDK到工程中

- 在project 的 gradle中加入maven仓库地址

```java
allprojects {
    repositories {
        google()
        jcenter()
        //添加一览 maven地址
        maven {
            url 'http://nexus.1lan.tv/repository/maven-releases/'
        }
        //添加阿里云maven地址
        maven {
            url 'http://maven.aliyun.com/nexus/content/repositories/releases/'
        }
    }
}
```

- 在使用的module中加入依赖，sdk版本根据发布的文档为准

```java
implementation "com.yilan.sdk:ui:1.8.0.4"//修改为具体的sdk版本
implementation "com.yilan.sdk:ad:1.8.0.4"//修改为具体的sdk版本,支持广点通、百度广告
implementation 'com.squareup.okhttp3:okhttp:3.11.0'
implementation 'com.google.code.gson:gson:2.8.5'
implementation 'com.android.support:recyclerview-v7:28.0.0'
implementation ('com.aliyun.ams:alicloud-android-httpdns:1.2.3@aar') {
        transitive true
    }
    //支持ijkplayer进行播放，建议添加
    implementation 'tv.danmaku.ijk.media:ijkplayer-java:0.8.8'
    implementation 'tv.danmaku.ijk.media:ijkplayer-armv7a:0.8.8'
    implementation 'tv.danmaku.ijk.media:ijkplayer-armv5:0.8.8'
    implementation 'tv.danmaku.ijk.media:ijkplayer-arm64:0.8.8'
    implementation 'tv.danmaku.ijk.media:ijkplayer-x86:0.8.8'
    implementation 'tv.danmaku.ijk.media:ijkplayer-x86_64:0.8.8'
```


<a name="d5b061ad"></a>
### 1.3 sdk 初始化

- 在Application中初始化sdk

```java
YLUIInit.getInstance()
  .setApplication(this)
  .setAccessKey("")//设置accesskey
  .setAccessToken("")//设置token
  .build();
  
  //页面UI配置
  YLUIConfig.getInstance().littleLikeShow(false)//是否显示小视频点赞
   .littleShareShow(false)//是否显示小视频分享
   .littleComment(CommentConfig.CommentType.SHOW_COMMENT_ALL)//小视频评论展现形式
   .videoComment(CommentConfig.CommentType.SHOW_COMMENT_ALL)//短视频评论展现形式
   .videoLikeShow(false)//是否显示短视频点赞
   .videoShareShow(false)//是否显示短视频分享
   .followAvailable(false)//是否显示关注
   .feedAvatarClickable(true)//feed页中头像是否可以点击
   .feedSwipeRefreshEnable(true);//feed是否可以下拉刷新

   
 //分享回调注册
 //1.建议在常驻页面如MainActivity  onCreate中调用
 YLUIConfig.getInstance().registerShareCallBack(ShareCallback);
 
 //2.建议在常驻页面如MainActivity  onDestroy中调用
 YLUIConfig.getInstance().unRegisterShareCallBack();
```
<a name="FmV8w"></a>
### 
<a name="IF58x"></a>
## 2. 添加多个Feed
```java
ChannelFragment channelFragment = new ChannelFragment();
getSupportFragmentManager()
    .beginTransaction()
    .add(R.id.fragment_container, channelFragment, ChannelFragment.class.getSimpleName())
    .commitAllowingStateLoss();
```

<a name="zw3QJ"></a>
## 3. 添加单个Feed流
<a name="G4wi1"></a>
### 3.1 创建fragment 并设置id
```java
Channel channel = new Channel();
channel.setId(String id);//设置频道id
FeedFragment fragment = FeedFragment.newInstance(channel);
getSupportFragmentManager()
    .beginTransaction()
    .add(R.id.fragment_container, feedFragment, FeedFragment.class.getSimpleName())
    .commitAllowingStateLoss();
```

<a name="sDcCa"></a>
### 3.2 配置Feed流

- <br />
```java
FeedConfig.getInstance().setPlayerStyle(FeedConfig.STYLE_FEED_PLAY);// 支持feed页面播放
//若设置当前页面播放，则可以监听播放事件如下：
FeedConfig.getInstance()
                .setUserCallback(new UserCallback() {
                    @Override
                    public boolean event(int type, //播放状态
                                         PlayData data, //播放的数据，可能为null
                                         int playerHash//哪个播放器ß) {
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
                        Log.e("player state", type + " ");
                        return false;
                    }
                })
                .setOnItemClickListener(new FeedConfig.OnClickListener() {
            @Override
            public boolean onClick(Context context, MediaInfo info) {
                Log.e("click ", "点击了 " + info);
                return false;
            }//点击回调
        });
```

<a name="5ccwO"></a>
## 4 添加小视频页

<a name="aYhK8"></a>
### 4.1 小视频初始化
```java
LittleVideoFragment fragment = LittleVideoFragment.newInstance();
littleVideoFragment.setUserCallBack(new UserCallback() {
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
getSupportFragmentManager().beginTransaction()
    .add(R.id.fragment_container, littleVideoFragment, LittleVideoFragment.class.getSimpleName())
    .commitAllowingStateLoss();
```

<a name="jksBv"></a>
### 4.2 配置小视频

- 设置小视频广告播放的监听
```java
LittleVideoConfig.getInstance()
    .setAdVideoCallback(new AdVideoCallback());//设置视频广告回调

```

<a name="nP57A"></a>
## 4 教程代码

- 本教程代码下载地址

```http
https://github.com/yilanyun/yilanyun-androi-tutor
```


