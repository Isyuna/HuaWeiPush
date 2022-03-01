package com.mcp.设计模式.工厂模式.工厂方法模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  14:39.
 * description :
 **/
public class GifReaderFactory implements ReaderFactory{
    @Override
    public Reader getReader() {
        return new GifReader();
    }
}
