package com.laozhang.project.main.model;

import java.time.OffsetDateTime;

/**
 * author : zhangyun.
 * date  : 2022/7/13  09:32.
 * description :
 **/
public class RecommendModel {
    public Joke joke;
    public Info info;
    public User user;
    public class Info {
        public long likeNum;
        public long shareNum;
        public long commentNum;
        public long disLikeNum;
        public boolean isLike;
        public boolean isUnlike;
        public boolean isAttention;
    }
    public class Joke {
        public long jokesId;
        public String addTime;
        public String content;
        public long userId;
        public long type;
        public String imageUrl;
        public boolean hot;
        public String latitudeLongitude;
        public String showAddress;
        public String thumbUrl;
        public String videoUrl;
        public long videoTime;
        public String videoSize;
        public String imageSize;
        public String auditMsg;


    }
    public class User {
        public long userId;
        public String nickName;
        public String signature;
        public String avatar;

    }

}

