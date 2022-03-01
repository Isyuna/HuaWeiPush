package com.mcp.设计模式.工厂模式.工厂方法模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  14:27.
 * description :工厂方法模式 不在创建统一的工厂类 而是根据所创建的对象来
 * 创建工厂类，以读取jpg png gif 图片为例
 * （1）客户端不需要知道它所创建的对象的类。例子中我们不知道每个图片加载器具体叫什么名，只知道创建它的工厂名就完成了床架过程。
 * （2）客户端可以通过子类来指定创建对应的对象。
 **/
public class Client {
    public static void main(String[] args) {
        ReaderFactory readerFactory = new GifReaderFactory();
        Reader reader = readerFactory.getReader();
        reader.read();
    }
}
