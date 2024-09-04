package com.pub.oauth.client.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.common.mapper.OrderMapper;
import com.common.vo.OrderVO;
import com.pub.oauth.client.feign.client.CommodityFeignClient;
import com.pub.oauth.client.service.ITCCService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lx
 * @data 2023/9/23 14:36
 */
@Service
public class TCCService implements ITCCService {
  @Resource
  private OrderMapper orderMapper;
  @Autowired
  private CommodityFeignClient commodityFeignClient;
  @Override

  public Object prepare(String userId) {
    System.out.println("开始TCC xid:" + RootContext.getXID());
    OrderVO vo = new OrderVO();
    vo.setCommodityCode("456");
    vo.setUserId("777");
    vo.setCount(1);
    vo.setMoney(12);
    orderMapper.insert(vo);
    commodityFeignClient.update();
    return "执行完毕！";

  }

  @Override
  public boolean commit(BusinessActionContext actionContext) {
    System.out.println("xid = " + actionContext.getXid() + "提交成功");
    return true;
  }

  @Override
  public boolean rollback(BusinessActionContext actionContext) {
    orderMapper.delete( new LambdaQueryWrapper<OrderVO>().in(OrderVO::getUserId,actionContext.getActionContext("userId").toString()));
    System.out.println("xid = " + actionContext.getXid() + "进行回滚操作");
    return true;
  }
}
