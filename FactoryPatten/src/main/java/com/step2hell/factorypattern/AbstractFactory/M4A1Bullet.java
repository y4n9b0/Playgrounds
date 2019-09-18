package com.step2hell.factorypattern.AbstractFactory;

public class M4A1Bullet extends Bullet {

    @Override
    public void load() {
        System.out.println("Loading " + getClass().getSimpleName());
    }

}
