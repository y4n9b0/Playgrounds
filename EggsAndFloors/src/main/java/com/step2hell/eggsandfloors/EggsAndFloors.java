package com.step2hell.eggsandfloors;

import java.util.Locale;

/**
 * https://www.ixigua.com/i6802491359077859851/
 * or
 * https://www.bilibili.com/video/av96214853
 */
public class EggsAndFloors {

    public static void main(String... args) {
        int floors = 100, eggs = 2;
        System.out.print(String.format(Locale.getDefault(), "floors=%d, eggs=%d => times=%d", floors, eggs, foo(floors, eggs)));
    }

    private static int foo(int floors, int eggs) {
        // cache temp result to avoid duplicated calculation
        // OOM risk while floors or eggs is big enough
        // rough optimization: replace int[][] with List<Integer>[]
        // ultimate optimization: cache with a file
        int[][] times = new int[floors][eggs];

        // did my utmost to make it simple & clear, catch me if you can:)
        // here we go..
        double ln2 = Math.log(2);
        for (int f = 0; f < floors; f++) {
            int t = (int) Math.floor(Math.log(f + 1) / ln2) + 1;
            for (int e = 0; e < eggs; e++) {
                if (f == 0 || e == 0) {
                    times[f][e] = f + 1;
                    continue;
                }
                if (e + 1 >= t) {
                    times[f][e] = t;
                    continue;
                }
                times[f][e] = Integer.MAX_VALUE;
                for (int k = 0; k < f; k++) {
                    times[f][e] = Math.min(times[f][e], Math.max(k == 0 ? 0 : times[k - 1][e - 1], times[f - 1 - k][e]) + 1);
                }
            }
        }

        return times[floors - 1][eggs - 1];
    }
}
