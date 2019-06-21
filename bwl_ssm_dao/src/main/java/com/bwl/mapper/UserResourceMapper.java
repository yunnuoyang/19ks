package com.bwl.mapper;

import com.bwl.pojo.UserResource;
import com.bwl.pojo.UserResourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserResourceMapper {
    int countByExample(UserResourceExample example);

    int deleteByExample(UserResourceExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserResource record);

    int insertSelective(UserResource record);

    List<UserResource> selectByExample(UserResourceExample example);

    UserResource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserResource record, @Param("example") UserResourceExample example);

    int updateByExample(@Param("record") UserResource record, @Param("example") UserResourceExample example);

    int updateByPrimaryKeySelective(UserResource record);

    int updateByPrimaryKey(UserResource record);
}