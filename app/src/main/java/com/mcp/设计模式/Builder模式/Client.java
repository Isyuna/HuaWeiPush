package com.mcp.设计模式.Builder模式;

/**
 * author : zhangyun.
 * date  : 2022/3/2  15:59.
 * description :builder模式比较简单，主要是隐藏构造方法，通过一个静态内部类当作桥梁去创建对象
 **/
public class Client {

    public static void main(String[] args) {
        Person.Builder builder = new Person.Builder();
        builder.setAge(18)
                .setHigh(180)
                .setName("老张")
                .setCallback(new Person.Callback() {

                    @Override
                    public void callback(String s) {
                        System.out.println(s);
                    }
                }).build();
    }
}
