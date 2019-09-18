package com.step2hell.factorypattern.AbstractFactory;

public class AK47 extends Gun {

    @Override
    public void shoot() {
        System.out.println("Shooting with " + getClass().getSimpleName());
    }

}
