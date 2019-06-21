package com.bwl.controller;

import com.bwl.pojo.Resource;
import com.bwl.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @RequestMapping("/getAllResource.do")
    @ResponseBody
    public List<Resource>  getAllResource(){
        List<Resource> allResource = resourceService.getAllResource();
        return allResource;
    }

}
