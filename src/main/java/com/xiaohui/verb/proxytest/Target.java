package com.xiaohui.verb.proxytest;

public class Target implements TargetInterface {
    @Override
    public void save() {
        System.out.println("save running....");
    }

    @Override
    public void save2() {
        System.out.println("save running2222....");
    }
}

