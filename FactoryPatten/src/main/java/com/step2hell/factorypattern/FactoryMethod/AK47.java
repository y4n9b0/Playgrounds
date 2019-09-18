package com.step2hell.factorypattern.FactoryMethod;

public class AK47 extends Gun {

    @Override
    public void shoot() {
        System.out.println("Shooting with " + getClass().getSimpleName());
    }

}
