package com.step2hell.factorypattern.AbstractFactory;

public class AK47Factory extends Factory {

    @Override
    public Gun produceGun() {
        return new AK47();
    }

    @Override
    public Bullet produceBullet() {
        return new AK47Bullet();
    }

}
