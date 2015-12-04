/**
 * www.iadpush.com Inc. Copyright (c) 2012 All Rights Reserved.
 */
package com.ckx.lang.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Filename ListUtil.java
 * @Description List 工具类
 * @Version 1.0
 * @Author peigen
 * @Email peigen123@gmail.com
 * @History <li>Author: peigen</li> <li>Date: 2012-2-3</li> <li>Version: 1.0</li> <li>Content: create</li>
 */
public class ListUtil {

    /**
     * list to array
     *
     * @param list
     * @return if list is null return null
     */
    public static Object[] toArray(List<?> list) {

        if (null == list || list.isEmpty()) {
            return null;
        }
        Object[] arr = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    /**
     * array to list
     *
     * @param arr
     * @return if arr is null return null
     */
    public static List<String> toList(String[] arr) {

        if (null == arr) {
            return null;
        }

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    /**
     * 判断List是否为空
     *
     * @param list
     * @return 如果list为null或者empty返回true 反之 false
     */
    public static boolean isEmpty(List<?> list) {
        return null == list || list.isEmpty();
    }

    /**
     * 判断List是否不为空
     *
     * @param list
     * @return 如果list不为null和empty返回true 反之 false
     */
    public static boolean isNotEmpty(List<?> list) {
        return !isEmpty(list);
    }

    /**
     * 合并两个List
     **/
    public static List<String> merge(List<String> l1, List<String> l2) {

        if (isEmpty(l1)) {
            return l2;
        }

        if (isEmpty(l2)) {
            return l1;
        }

        for (String o : l2) {
            if (!l1.contains(o)) {
                l1.add(o);
            }
        }
        return l1;
    }

    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

    public static Map<String, Object> sumFieldFromList(List<Map<String, Object>> list, String... fields) {
        Map<String, Object> sumMap = new HashMap<String, Object>(fields.length + 2);
        BigDecimal lField = null;
        BigDecimal sumField = null;
        for (String fieldKey : fields) {
            sumMap.put(fieldKey, new BigDecimal(0));
        }

        for (Map<String, Object> map : list) {
            for (String fieldKey : fields) {
                lField = ObjectUtil.getBigDecimal(map.get(fieldKey));
                sumField = (BigDecimal) sumMap.get(fieldKey);
                if (lField != null) {
                    sumMap.put(fieldKey, sumField.add(lField));
                }
            }
        }
        return sumMap;
    }

    public static Map<String, Object> avgFieldFromList(List<Map<String, Object>> list, long iTotal, String... fields) {
        Map<String, Object> avgMap = new HashMap<String, Object>(fields.length + 2);
        BigDecimal lField = null;
        BigDecimal avgField = null;
        MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
        BigDecimal total = new BigDecimal(iTotal);
        for (String fieldKey : fields) {
            avgMap.put(fieldKey, new BigDecimal(0));
        }

        for (Map<String, Object> map : list) {
            for (String fieldKey : fields) {
                lField = ObjectUtil.getBigDecimal(map.get(fieldKey));
                avgField = (BigDecimal) avgMap.get(fieldKey);
                if (lField != null) {
                    avgMap.put(fieldKey, avgField.add(lField));
                }
            }
        }
        for (String fieldKey : fields) {
            avgField = (BigDecimal) avgMap.get(fieldKey);
            avgMap.put(fieldKey, avgField.divide(total, mc));
        }
        return avgMap;
    }
}
