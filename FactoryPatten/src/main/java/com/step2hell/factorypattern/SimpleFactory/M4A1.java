package com.step2hell.factorypattern.SimpleFactory;

public class M4A1 extends Gun {

    @Override
    public void shoot() {
        System.out.println("Shooting with " + getClass().getSimpleName());
    }

}