package com.ancs.agpt.system.toolkit;

import com.ancs.agpt.system.entity.enums.IEnum;

/**
 * <p>
 * 枚举处理工具类
 * </p>
 */
public class EnumUtils {
	/**
     * <p>
     * 值映射为枚举
     * </p>
     *
     * @param enumClass 枚举类
     * @param value     枚举值
     * @param <E>       对应枚举
     * @return
     */
    public static <E extends Enum<?> & IEnum> E valueOf(Class<E> enumClass, Object value) {
        E[] es = enumClass.getEnumConstants();
        for (E e : es) {
            if ((value instanceof String && e.getValue().equals(value))
                    || e.getValue() == value) {
                return e;
            }
        }
        return null;
    }
    
    public static <E extends Enum<?> & IEnum> E descOf(Class<E> enumClass, String desc) {
        E[] es = enumClass.getEnumConstants();
        for (E e : es) {
            if (desc instanceof String && e.getDesc().equals(desc)) {
                return e;
            }
        }
        return null;
    }
}
