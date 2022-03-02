package com.mcp.设计模式.Builder模式;

/**
 * author : zhangyun.
 * date  : 2022/3/2  16:00.
 * description :
 **/

public class Person  {
    private int ID;
    private int age;
    private String name;
    private int high;
    private int weight;
    private Callback callback;

    interface Callback {
        void callback(String s);
    }

    private Person(Builder builder) {
        this.ID = builder.ID;
        this.age = builder.age;
        this.name = builder.name;
        this.high = builder.high;
        this.weight = builder.weight;
        this.callback = builder.callback;
        if(callback != null){
            callback.callback(name+age+high);
        }
    }

    public static class Builder {
        private int ID;
        private int age;
        private String name;
        private int high;
        private int weight;
        private Callback callback;

        public Builder setID(int ID) {
            this.ID = ID;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setHigh(int high) {
            this.high = high;
            return this;
        }

        public Builder setWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder setCallback(Callback callback) {
            this.callback = callback;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
