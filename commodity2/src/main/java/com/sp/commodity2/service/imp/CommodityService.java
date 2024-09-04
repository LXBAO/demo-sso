package com.sp.commodity2.service.imp;




import com.sp.commodity2.mapper.CommodityMapper;
import com.sp.commodity2.service.ICommodityService;


import com.sp.commodity2.vo.CommodityVO;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lx
 * @data 2023/9/20 9:52
 */
@Service
public class CommodityService implements ICommodityService {
  @Resource
  private CommodityMapper commodityMapper;



  @Override
  @Transactional
  public void update(CommodityVO vo) {

    commodityMapper.updateById(vo);

  }

}
