package com.step2hell.factorypattern.FactoryMethod;

public class M4A1Factory extends Factory{

    @Override
    public Gun produceGun() {
        return new M4A1();
    }

}
