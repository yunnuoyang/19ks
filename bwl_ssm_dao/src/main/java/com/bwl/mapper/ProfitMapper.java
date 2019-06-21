package com.bwl.mapper;

import com.bwl.pojo.Profit;
import com.bwl.pojo.ProfitExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProfitMapper {
    int countByExample(ProfitExample example);

    int deleteByExample(ProfitExample example);

    int deleteByPrimaryKey(String id);

    int insert(Profit record);

    int insertSelective(Profit record);

    List<Profit> selectByExample(ProfitExample example);

    Profit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Profit record, @Param("example") ProfitExample example);

    int updateByExample(@Param("record") Profit record, @Param("example") ProfitExample example);

    int updateByPrimaryKeySelective(Profit record);

    int updateByPrimaryKey(Profit record);
}