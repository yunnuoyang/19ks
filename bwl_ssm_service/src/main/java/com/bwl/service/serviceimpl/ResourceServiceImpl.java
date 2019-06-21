package com.bwl.service.serviceimpl;


import com.bwl.mapper.ResourceMapper;
import com.bwl.pojo.Resource;
import com.bwl.pojo.ResourceExample;
import com.bwl.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Override
    public List<Resource> getAllResource() {
        return resourceMapper.selectByExample(null);
    }

    @Override
    public void insertResource(Resource resource) {
            resourceMapper.insertSelective(resource);
    }

    @Override
    public void deleteResource(Resource resource) {
                resourceMapper.deleteByPrimaryKey(resource.getId());
    }

    @Override
    public void updateResource(Resource resource) {
        ResourceExample resourceExample=new ResourceExample();

        resourceMapper.updateByExampleSelective(resource,resourceExample);
    }
}
