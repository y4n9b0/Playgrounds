package com.step2hell.factorypattern.SimpleFactory;

public class Factory {

    public static Gun produceGun(String name) {
        Gun gun = null;
        switch (name) {
            case "AK47":
                gun = new AK47();
                break;
            case "M4A1":
                gun = new M4A1();
                break;
            default:
                break;
        }
        return gun;
    }

    /**
     * 简单工厂升级版，传入class参数、通过反射获取对象。
     * 对于工厂不提供的枪，调用方可以自行继承抽象类Gun，然后直接传入class参数，不用再修改工厂类的produce方法。
     */
    public static Gun produceGun_v2(Class<? extends Gun> clazz){
            Object object = null;
            try {
                object = Class.forName(clazz.getName()).newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        return (Gun) object;
    }

}
