package com.step2hell.factorypattern.AbstractFactory;

public class M4A1Factory extends Factory {

    @Override
    public Gun produceGun() {
        return new M4A1();
    }

    @Override
    public Bullet produceBullet() {
        return new M4A1Bullet();
    }
}
