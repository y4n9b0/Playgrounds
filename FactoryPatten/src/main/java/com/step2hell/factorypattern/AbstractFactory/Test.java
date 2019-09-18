package com.step2hell.factorypattern.AbstractFactory;

/**
 * 抽象工厂模式稍微有点难度，建议结合如下的抽象工厂模式结构图理解：
 * https://design-patterns.readthedocs.io/zh_CN/latest/creational_patterns/abstract_factory.html#id4
 */
public class Test {

    public static void main(String[] args) {
        Factory ak47Factory = new AK47Factory();
        Bullet ak47Bullet = ak47Factory.produceBullet();
        Gun ak47 = ak47Factory.produceGun();
        ak47Bullet.load();
        ak47.shoot();

        Factory m4a1Factory = new M4A1Factory();
        Bullet m4a1Bullet = m4a1Factory.produceBullet();
        Gun m4a1 = m4a1Factory.produceGun();
        m4a1Bullet.load();
        m4a1.shoot();

        Factory awpFactory = new AWPFactory();
        Bullet awpBullet = awpFactory.produceBullet();
        Gun awp = awpFactory.produceGun();
        awpBullet.load();
        awp.shoot();
    }

    static class AWP extends Gun {
        @Override
        public void shoot() {
            System.out.println("Shooting with " + getClass().getSimpleName());
        }
    }

    static class AWPBullet extends Bullet {
        @Override
        public void load() {
            System.out.println("Loading " + getClass().getSimpleName());
        }
    }

    static class AWPFactory extends Factory {
        @Override
        public Gun produceGun() {
            return new AWP();
        }

        @Override
        public Bullet produceBullet() {
            return new AWPBullet();
        }
    }
}
