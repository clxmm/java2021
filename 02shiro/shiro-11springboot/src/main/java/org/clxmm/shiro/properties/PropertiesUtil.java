package org.clxmm.shiro.properties;

import lombok.extern.slf4j.Slf4j;
import org.clxmm.shiro.utils.EmptyUtil;

/**
 * @author clxmm
 * @Description 读取Properties的工具类
 * @create 2021-05-22 8:34 下午
 */
@Slf4j
public class PropertiesUtil {
    public static LinkProperties propertiesShiro = new LinkProperties();


    /**
     * 读取properties配置文件信息
     */
    static {
        String sysName = System.getProperty("sys.name");
        if (EmptyUtil.isNullOrEmpty(sysName)) {
            sysName = "application.properties";
        } else {
            sysName += ".properties";
        }
        try {
            propertiesShiro.load(PropertiesUtil.class.getClassLoader()
                    .getResourceAsStream("authentication.properties"));
        } catch (Exception e) {
            log.warn("资源路径中不存在authentication.properties权限文件，忽略读取！");
        }
    }

    /**
     * 根据key得到value的值
     */
    public static String getShiroValue(String key) {
        return propertiesShiro.getProperty(key);
    }


}
