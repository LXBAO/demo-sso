package com.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.vo.OrderVO;
import com.common.vo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lx
 * @data 2023/8/17 15:24
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderVO> {

}
