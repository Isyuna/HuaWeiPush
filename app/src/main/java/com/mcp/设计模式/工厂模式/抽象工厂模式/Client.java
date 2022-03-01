package com.mcp.设计模式.工厂模式.抽象工厂模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  15:06.
 * description : 抽象工厂模式，与工厂方法模式区别在于 工厂模式会有一组操作对象，而工厂方法模式只有一中（例子中的Reader对象）
 * 例登录分为验证码、账号密码、人脸登录，除了登录外还需要校验手机时间与服务器时间差。
 * https://github.com/Isyuna/HuaWeiPush.git
 **/
public class Client {

    public static void main(String[] args) {
        LoginFactory loginFactory = new AccountLoginFactory();
        LoginInterface loginInterface = loginFactory.getLoginInterFace();
        TimeInterface timeInterface = loginFactory.getTimeInterface();
        loginInterface.toLogin();
        timeInterface.toCheckTime();
    }

}
