package com.sp.commodity2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.sp.commodity2.vo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lx
 * @data 2023/8/17 15:24
 */
@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {

}
