package com.step2hell.factorypattern.SimpleFactory;

public class Test {

    public static void main(String[] args) {
//        testSimpleFactory();
        testSimpleFactory_v2();
    }

    private static void testSimpleFactory(){
        Gun ak47 = Factory.produceGun("AK47");
        ak47.shoot();

        Gun m4a1 = Factory.produceGun("M4A1");
        m4a1.shoot();

        Gun awp = Factory.produceGun("AWP");    // awp == null, NullPointerException.
        awp.shoot();
    }

    private static void testSimpleFactory_v2(){
        Gun ak47 = Factory.produceGun_v2(AK47.class);
        ak47.shoot();

        Gun m4a1 = Factory.produceGun_v2(M4A1.class);
        m4a1.shoot();

        Gun awp = Factory.produceGun_v2(AWP.class);
        awp.shoot();
    }

    static class AWP extends Gun{
        @Override
        public void shoot() {
            System.out.println("Shooting with " + getClass().getSimpleName());
        }
    }
}
