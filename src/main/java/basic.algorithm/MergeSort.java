package basic.algorithm;

import java.util.Arrays;

/**
 * 归并排序-递归法
 * 递归法比TM循环遍历好用多了
 * Created by zhaoxin on 17-9-18.
 */
public class MergeSort {
    /**
     * 将母数组中的两个有序子数组进行归并排序
     * @param array 需要被排序的数组
     * @param low   两相邻数组 左侧数组起始位置指针
     * @param mid   两相邻数组 左侧数组结束位置指针
     * @param high  两相邻数组 右侧数组结束位置指针
     */
    public static void merge(int[] array, int low, int mid, int high) {
        /**
         * 用来合并两个有序的相邻数组
         */
        int i = low; // i是第一段序列的下标
        int j = mid + 1; // j是第二段序列的小标
        int k = 0; // k是临时存放合并序列的下标
        int[] array2 = new int[high  - low + 1]; // array2是临时合并序列

        // 扫描第一段和第二段序列, 直到有一个扫面结束
        while (i <= mid && j <=high) {
            //判断第一段和第二段取出的数哪个更小, 将其存入合并序列, 并急需向下扫描
            if (array[i] <= array[j]){
                array2[k] = array[i];
                k++;
                i++;
            }
            else {
                array2[k] = array[j];
                k++;
                j++;
            }

        }
        // 此时应该两个数组中至少有一个已经被完全遍历, 将其余部分合并到序列
        while (i <= mid) {
            array2[k] = array[i];
            k++;
            i++;
        }
        while (j <= high) {
            array2[k] = array[j];
            k++;
            j++;
        }
        // 将有序临时数组覆盖到原始数组
        for (k=0, i = low; i <= high; i++, k++) {
            array[i] = array2[k];
        }
    }

    /**
     * 这个递归有点巧
     * @param array 需要被排序的数组
     * @param left  定义从哪个位置开始排序
     * @param right 定义在哪个位置结束排序
     */
    public static void sort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right)/2; //定义分割两个相邻数组的指针
            sort(array, left, mid); //左边进行归并排序
            sort(array, mid+1, right); //右边进行归并排序
            merge(array, left, mid, right); //以上两个归并排序的结果 再进行归并

        }
    }
    public static void main(String args[]) {
        // 整一下子看看
        int[] array = {9, 8, 6, 4, 2, 6, 2, 88, 22, 1};
        sort(array, 0, array.length);
        System.out.println(Arrays.toString(array));
    }
}
