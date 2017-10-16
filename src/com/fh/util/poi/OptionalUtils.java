package com.fh.util.poi;

import java.util.Optional;

public class OptionalUtils {

	 /**
     * 判断对象是否为空
     * 
     * @param 需要判断的对象
     * @return boolean 非空返回true,空返回false
     */
    public static <T extends Object> boolean isPersent(T obj) {
        Optional<T> optional = Optional.ofNullable(obj);
        return optional.isPresent();
    }
    /**
     * 判断对象是否空
     * 
     * @param 需要判断的对象
     * @return boolean 空返回true,非空返回false
     */
    public static <T extends Object> boolean notPersent(T obj) {
        Optional<T> optional = Optional.ofNullable(obj);

        return !optional.isPresent();
    }

}
