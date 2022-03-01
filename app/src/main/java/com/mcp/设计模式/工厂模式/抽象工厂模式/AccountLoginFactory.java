package com.mcp.设计模式.工厂模式.抽象工厂模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  15:45.
 * description :
 **/
public class AccountLoginFactory implements LoginFactory{
    @Override
    public TimeInterface getTimeInterface() {
        return new AccountCheckTime();
    }

    @Override
    public LoginInterface getLoginInterFace() {
        return new AccountLogin();
    }
}
