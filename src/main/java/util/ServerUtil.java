package util;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * @author dengxinlong
 * @date 2021/10/27 14:16
 */
public class ServerUtil {
    public static void main(String[] args) {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        System.out.println("SystemCpuLoad = " + operatingSystemMXBean.getSystemCpuLoad());
        System.out.println("CpuLoad = " + operatingSystemMXBean.getProcessCpuLoad());
        System.out.println("TotalPhysicalMemorySize = " + operatingSystemMXBean.getTotalPhysicalMemorySize());
        System.out.println("FreePhysicalMemorySize = " + operatingSystemMXBean.getFreePhysicalMemorySize());
    }
}
