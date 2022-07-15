package com.laozhang.project.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.laozhang.project.R;
import com.laozhang.project.databinding.ItemRecommendVideoBinding;
import com.laozhang.project.view.ZYVideoPlayer;
import com.zhy.common.view.XCRoundImageView;

/**
 * author : zhangyun.
 * date  : 2022/7/11  17:31.
 * description : 用于自定义 adapter
 **/
public class ProjectBindingAdapter {

    @BindingAdapter(value = { "videoUrl","voidImage"}, requireAll = false)
    public static void fansTextView(ZYVideoPlayer zyVideoPlayer, String videoUrl, String videoImage) {
        initVideoPlayer(zyVideoPlayer,videoUrl,videoImage);
        
    }
    /**
     * 处理视频播放
     *
     */
    private static void initVideoPlayer(ZYVideoPlayer zyVideoPlayer, String videoUrl, String videoImage) {
        if (videoUrl == null || "".equals(videoUrl)) {
            zyVideoPlayer.setVisibility(View.GONE);
            return;
        } else {
            zyVideoPlayer.setVisibility(View.VISIBLE);

        }
        String url = getUrl(videoUrl);
        String imageUrl = getUrl(videoImage);
        zyVideoPlayer.loadCoverImage(imageUrl, R.mipmap.like);
        zyVideoPlayer.setUpLazy(url, true, null, null, "这是title");
        //增加title
        zyVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        zyVideoPlayer.getBackButton().setVisibility(View.GONE);
        //设置全屏按键功能
        zyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zyVideoPlayer.startWindowFullscreen(zyVideoPlayer.getContext(), false, true);
            }
        });
        //防止错位设置
        zyVideoPlayer.setPlayTag("RecommendAdapter");
//        zyVideoPlayer.setPlayPosition(ge);
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        zyVideoPlayer.setAutoFullWithSize(true);
        //音频焦点冲突时是否释放
        zyVideoPlayer.setReleaseWhenLossAudio(false);
        //全屏动画
        zyVideoPlayer.setShowFullAnimation(true);
        //小屏时不触摸滑动
        zyVideoPlayer.setIsTouchWiget(false);

    }

    private static String getUrl(String url) {
        String newUrl = url.replace("ftp://", "");
        return AesUtils.aesDecrypt(newUrl);
    }
}
