package com.mcp.设计模式.工厂模式.抽象工厂模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  15:40.
 * description :
 **/
public class PhoneLogin implements LoginInterface{
    @Override
    public void toLogin() {
        System.out.println("验证码登录");

    }
}
