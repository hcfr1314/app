package com.hhgs.app.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class BasicCalaUtils {

    /**
     * 累加运算
     *
     * @param dataRows
     * @return
     */
    public static double add(List<String[]> dataRows) {
        return dataRows.stream().mapToDouble(arr -> Double.parseDouble(arr[1])).sum();
    }

    /**
     * 获取平均值
     *
     * @param dataRows
     * @return
     */
    public static double avg(List<String[]> dataRows) {
        double result = dataRows.stream().mapToDouble(arr -> Double.parseDouble(arr[1])).average().getAsDouble();
        return new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 除法
     *
     * @param a
     * @param b
     * @return
     */
    public static double div(double a, double b) {
        if (b == 0) {
            return 0;
        }
        return new BigDecimal(a / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 乘法
     *
     * @param a
     * @param b
     * @return
     */
    public static double multi(double a, double b) {
        return new BigDecimal(a * b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 减法
     *
     * @param a
     * @param b
     * @return
     */
    public static double sub(double a, double b) {
        return a - b;
    }

    /**
     * （末值-初始值）*倍率
     *
     * @param init
     * @param end
     * @param timesRate
     * @return
     */
    public static double subAndMuli(List<String[]> init, List<String[]> end, double timesRate) {
        double initSum = BasicCalaUtils.add(init);
        double endSum = BasicCalaUtils.add(end);
        double sub = BasicCalaUtils.sub(endSum, initSum);
        double multi = BasicCalaUtils.multi(sub, timesRate);
        return multi;
    }

    /**
     * 获取给定数据每个点的最大值，并且求和
     *
     * @param datas
     * @return
     */
    public static double getTimeAreaMaxValueAndSum(List<String[]> datas) {
        return 0;
    }

    /**
     * 找出一组数中的最大值和最小值
     *
     * @return
     */
    public static long[] findMaxAndMin(List<String[]> dataRows) {
        List<String[]> collect = dataRows.stream().filter(ele -> Double.parseDouble(ele[1]) > 1).sorted(Comparator.comparingLong(ele -> Long.parseLong(ele[2]))).collect(Collectors.toList());
        long max = -1;
        long min = -1;
        if (collect.size() > 0) {
            max = Long.valueOf(collect.get(collect.size() - 1)[2]);
            min = Long.valueOf(collect.get(0)[2]);
        }
        return new long[]{min, max};
    }

    /**
     * 查找一组数的最小值
     *
     * @param dataRows
     * @return
     */
    public static double findMin(List<String[]> dataRows) {
        double min = 1000d;
        for (String[] ele : dataRows) {
            double val = Double.valueOf(ele[2]);
            if (val < min) {
                min = val;
            }
        }
        return min;
    }

    /**
     * 查找返回数据最大索引值
     *
     * @param dataRows
     * @return
     */
    public static int findMax(List<String[]> dataRows, double maxValue) {
        int maxIndex = -1;
        for (int i = 0; i < dataRows.size(); i++) {
            String[] arr = dataRows.get(i);
            double currVal = Double.parseDouble(arr[2]);
            if (currVal > maxValue) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    /**
     * 查找一组数中的最大值
     * @param dataRows
     * @return
     */
    public static double findMax(List<String[]> dataRows) {
        double max = 0;
        for (String[] arr : dataRows) {
            double val = Double.valueOf(arr[2]);
            if (val > max) {
                max = val;
            }
        }

        return max;
    }
}
