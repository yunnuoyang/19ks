package com.bwl.service;


import com.bwl.pojo.Resource;

import java.util.List;

/**
 * 管理资源权限
 */
public interface ResourceService {
    /**
     * 获取所有的资源信息
     * @return
     */
    List<Resource> getAllResource();

    /**
     * 添加资源信息
     */
    void insertResource(Resource resource);

    /**
     * 删除资源信息
     * @param resource 资源实体
     */
    void deleteResource(Resource resource);

    /**
     * 修改资源信息
     * @param resource
     */
    void updateResource(Resource resource);
}
