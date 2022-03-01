package com.mcp.设计模式.工厂模式.抽象工厂模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  15:39.
 * description :
 **/
public class AccountCheckTime implements TimeInterface{
    @Override
    public void toCheckTime() {
        System.out.println("检查账号密码登录时间效验");

    }
}
