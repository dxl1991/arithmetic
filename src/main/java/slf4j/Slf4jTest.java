package slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * slf4j是一个抽象，提供日志接口。logback 和 log4j 、jdk logging 用于日志实现
 * logback 默认读取 classPath:logback.xml配置
 */
public class Slf4jTest{
    private static final Logger logger = LoggerFactory.getLogger(Slf4jTest.class);
    public static void main(String[] args) {
        logger.debug("debug");
        logger.info("hello {},{}","dxl","nice"); //hello dxl,nice
    }
}
