package com.mcp.设计模式.工厂模式.工厂方法模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  14:37.
 * description :
 **/
public class JpgReaderFactory implements ReaderFactory{
    @Override
    public Reader getReader() {
        return new JpgReader();
    }
}
