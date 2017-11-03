package basic.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划问题
 * 过去的一年股市风起云涌。基金经理们在总结去年业绩的时候，需要在报告中说明如果去年只能买卖一次，在何点买入，在何点卖出收益最大。
 * 你，被叫进了办公室，帮助他设计一个算法，并分析算法复杂度。基金经理手里的表格显示每天的股票涨跌变化：-6，10，2，-4，8，-10，-2，8，3
 * 正数表示比昨天涨了多少，负数表示比昨天跌了多少。
 * 1、请设计一个算法，找出哪一天买入，那一天卖出收益最大。
 * 如上述例子，应该在第二天买入，第五天抛出，这样收益为10+2-4+8=16 。
 * 输入：以逗号分隔的去年涨跌列表
 * 输出：第几天买入，第几天卖出，总收益
 * 举例：
 * 输入: -6，10，2，-4，8，-10，-2，8，3
 * 输出: 2，5，16
 * 2、如果最多可以买卖两次，请问，如何做到收益最大？(注:每天最多只能买卖一次)
 * Created by zhaoxin on 2017/9/22.
 */
public class Stock {
    /**
     * 只买卖一次
     * @param pricesList
     * @return
     */
    public static Map<String, Integer> maxProfitForOnce(int[] pricesList) {
        Map<String, Integer> stringIntegerMap = new HashMap<String, Integer>();
        //初始化买入天数, 卖出天数， 总收益
        int boughtDay = 0; //0 代表不买， 1代表第一天
        int sellDay = 0;
        int amount = 0 ;

        //判断是否有效价格序列
        if (pricesList.length == 0) {
            return stringIntegerMap;
        }
        // i是从1开始的第几天
        for (int i=1; i <= pricesList.length; i++){
            //判断是否买入会带来收益
            if (pricesList[i-1] > amount) {
                boughtDay = i;
                
            }
        }
        //
        //判断应该在哪一日买入,即最小值
        int boughtPrice = pricesList[0];
        //遍历所有交易日价格
        for (int i = 1; i < pricesList.length; i++) {
            //判断本日是否能够卖出
            if (pricesList[i] > boughtPrice) {
                //判断如果本日卖出收益是否最大
                if (amount < (pricesList[i] - boughtPrice)) {
                    sellDay = i;
                    amount = pricesList[i] - boughtPrice;
                }
            } else {
                //判断本日是否为最低价格
                boughtPrice = pricesList[i];
            }
        }
        stringIntegerMap.put("boughtDay", boughtDay);
        stringIntegerMap.put("sellDay", sellDay);
        stringIntegerMap.put("amount", amount);

        return stringIntegerMap;
    }
    public static int maxProfitForTwice(int[] pricesList) {
        //判断是否为有效交易天数
        if (pricesList.length == 0) return 0;
        //存放左半部分最大收益
        int[] left = new int[pricesList.length];
        //存放右半部分最大收益
        int[] right = new int[pricesList.length];
        //初始化为0
        int leftMin = pricesList[0];
        int rightMax = pricesList[pricesList.length - 1];
        //总收益
        int sum = 0;
        //计算左半段最大收益
        for (int i = 1; i < pricesList.length; i++) {
            //获取左半部分的最低价
            leftMin = Math.min(pricesList[i], leftMin);
            //获取左半部分最大收益
            left[i] = Math.max(pricesList[i] - leftMin, left[i - 1]);
        }
        //计算右半段最大收益
        for (int i = pricesList.length - 2; i >= 0; i--) {
            //获取右半部分最低价
            rightMax = Math.max(pricesList[i], rightMax);
            //获取右半部分最大收益
            right[i] = Math.max(rightMax - pricesList[i], right[i + 1]);
        }
        //找出两次交易最大收益组合
        for (int i = 0; i < pricesList.length; i++) {
            if ((left[i] + right[i]) > sum) sum = left[i] + right[i];
        }
        return sum;
    }
    public static void main(String args[]) {
        int[] pricesList = new int[]{1, 7, 15, 6, 57, 32, 76};
        int[] list2 = new int[] {-6,10,2,-4,8,-10,-2,8,3};
        System.out.print("maxProfitForOnce is" + maxProfitForOnce(list2) + '\n');
        System.out.print("maxProfitForOnce is" + maxProfitForOnce(pricesList) + '\n');
        System.out.print("maxProfitForTwice is" + maxProfitForTwice(pricesList));
    }
}
