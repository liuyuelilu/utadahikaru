package com.heroku.mapper;

import com.heroku.entity.UPay;
import com.heroku.entity.UPayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UPayMapper {
	long countByExample(UPayExample example);

	int deleteByExample(UPayExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(UPay record);

	int insertSelective(UPay record);

	List<UPay> selectByExample(UPayExample example);

	UPay selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") UPay record, @Param("example") UPayExample example);

	int updateByExample(@Param("record") UPay record, @Param("example") UPayExample example);

	int updateByPrimaryKeySelective(UPay record);

	int updateByPrimaryKey(UPay record);
}