package com.pub.oauth.client.service.imp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.common.mapper.OrderMapper;
import com.common.mapper.UserMapper;
import com.common.vo.CommodityVO;
import com.common.vo.OrderVO;
import com.pub.oauth.client.feign.client.CommodityFeignClient;
import com.pub.oauth.client.service.IOrderService;
import com.pub.oauth.client.service.ITCCService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lx
 * @data 2023/9/20 9:52
 */
@Service
public class OrderService implements IOrderService  {
  @Resource
  private OrderMapper orderMapper;
  @Autowired
  private CommodityFeignClient commodityFeignClient;



  @GlobalTransactional( rollbackFor =  Exception.class)
  @Transactional
  @Override
  public void save() {
    OrderVO vo = new OrderVO();
    vo.setCommodityCode("123");
    vo.setUserId("111");
    vo.setCount(1);
    vo.setMoney(12);
    orderMapper.insert(vo);

    commodityFeignClient.update();
  }

}
