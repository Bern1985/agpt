package com.ancs.agpt.system.toolkit;
import java.util.UUID;
/**
 * <p>
 * 高效GUID产生算法(sequence),基于Snowflake实现64位自增ID算法。 <br>
 * </p>
 *
 */
public class IdWorker {

    /**
     * 主机和进程的机器码
     */
    private static final Sequence worker = new Sequence();

    public static long getId() {
        return worker.nextId();
    }

    /**
     * <p>
     * 获取去掉"-" UUID
     * </p>
     */
    public static synchronized String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    public static void main(String[] args) {
    	System.out.println(getId());
    }
}
