package org.clxmm.service.shiro.service;

import org.clxmm.service.shiro.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author clxmm
 * @since 2021-05-20
 */
public interface ResourceService extends IService<Resource> {


    /**
     * 根据用户id查询相关的资源
     * @param userId
     * @return
     */
    List<String> findResourcesIds(String userId);

    List<Resource> getListByIds(List<String> resourceIds);
}
