package com.step2hell.factorypattern.FactoryMethod;

public class Test {

    public static void main(String[] args) {
        Factory ak47Factory = new AK47Factory();
        Gun ak47 = ak47Factory.produceGun();
        ak47.shoot();

        Factory m4a1Factory = new M4A1Factory();
        Gun m4a1 = m4a1Factory.produceGun();
        m4a1.shoot();

        Factory awpFactory = new AWPFactory();
        Gun awp = awpFactory.produceGun();
        awp.shoot();
    }


    static class AWP extends Gun {
        @Override
        public void shoot() {
            System.out.println("Shooting with " + getClass().getSimpleName());
        }
    }

    static class AWPFactory extends Factory {
        @Override
        public Gun produceGun() {
            return new AWP();
        }
    }
}
