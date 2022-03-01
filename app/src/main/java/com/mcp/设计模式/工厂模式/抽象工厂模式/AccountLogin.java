package com.mcp.设计模式.工厂模式.抽象工厂模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  15:39.
 * description :
 **/
public class AccountLogin implements LoginInterface{
    @Override
    public void toLogin() {
        System.out.println("账号登录");

    }
}
