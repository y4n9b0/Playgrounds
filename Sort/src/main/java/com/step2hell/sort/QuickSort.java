package com.step2hell.sort;

import java.util.Arrays;

public class QuickSort {

    // https://tryenough.com/arithmetic-quitsort

    public static void main(String[] args) {
        int[] a = {1, 6, 8, 7, 3, 5, 16, 4, 8, 36, 13, 44};
        quicksort(a, 0, a.length - 1);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }

    private static void QKSort(int[] a, int start, int end) {
        if (a.length <= 0) {
            return;
        }
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int temp = a[left];
        while (left < right) {
            while (left < right && a[right] > temp) {
                right--;
            }
            a[left] = a[right];
            while (left < right && a[left] < temp) {
                left++;
            }
            a[right] = a[left];
        }
        a[left] = temp;
        System.out.println(Arrays.toString(a));
        QKSort(a, start, left - 1);
        QKSort(a, left + 1, end);
    }

    private static void quicksort(int a[], int left, int right) {
        int low, high, temp, pivot;
        if (left > right)
            return;
        pivot = a[left]; //temp中存的就是基准数
        low = left;
        high = right;
        while (low != high) {
            //顺序很重要，要先从右边开始找
            while (a[high] >= pivot && low < high)
                high--;
            //再找右边的
            while (a[low] <= pivot && low < high)
                low++;
            //交换两个数在数组中的位置
            if (low < high) {
                temp = a[low];
                a[low] = a[high];
                a[high] = temp;
            }
        }
        //最终将基准数归位
        a[left] = a[low];
        a[low] = pivot;

        quicksort(a, left, low - 1);//继续处理左边的，这里是一个递归的过程
        quicksort(a, low + 1, right);//继续处理右边的 ，这里是一个递归的过程
    }
}
