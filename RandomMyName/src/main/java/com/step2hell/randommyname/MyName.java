package com.step2hell.randommyname;

import java.util.Random;

public class MyName {

    public static void main(String... args) {
        String[] names = new String[]{"yang", "bo"};
        for (String name : names)
            System.out.println("name=" + name + " => seed=" + findSeedByName(name));

        long yang = 9425302, bo = 38550;
        System.out.println(whatsMyName(yang, bo));
    }

    private static long findSeedByName(String name) {
        assert (name == null || name.isEmpty());

        char[] target = new char[name.length() + 1];
        System.arraycopy(name.toCharArray(), 0, target, 0, name.length());
        target[name.length()] = 96;

        long seed = 0;
        Random random = new Random();
        while (seed++ < Long.MAX_VALUE) {
            random.setSeed(seed);
            boolean match = true;
            for (char c : target) {
                if (random.nextInt(27) != c - 96) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return seed;
            }
        }
        return Long.MAX_VALUE;
    }

    private static String whatsMyName(long... seeds) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (long seed : seeds) {
            random.setSeed(seed);
            while (true) {
                int i = random.nextInt(27);
                if (i == 0) {
                    break;
                }
                sb.append((char) (96 + i));
            }
            sb.append(" ");
        }
        return sb.toString();
    }
}
