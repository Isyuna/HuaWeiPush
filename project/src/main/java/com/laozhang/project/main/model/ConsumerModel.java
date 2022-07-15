package com.laozhang.project.main.model;

import java.io.Serializable;

/**
 * author : zhangyun.
 * date  : 2022/7/7  16:57.
 * description : 其他用户信息
 **/
public class ConsumerModel implements Serializable {

    public int userId;
    public String nickname;
    public String avatar;
    public String fansNum;
    public String jokesNum;
    public String isAttention;
}
