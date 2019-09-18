package com.step2hell.factorypattern.FactoryMethod;

public class AK47Factory extends Factory {

    @Override
    public Gun produceGun() {
        return new AK47();
    }

}
