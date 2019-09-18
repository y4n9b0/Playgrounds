package com.step2hell.factorypattern.AbstractFactory;

public class AK47Bullet extends Bullet {

    @Override
    public void load() {
        System.out.println("Loading " + getClass().getSimpleName());
    }
}
