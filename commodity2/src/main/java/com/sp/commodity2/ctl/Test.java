package com.sp.commodity2.ctl;


import com.sp.commodity2.service.ICommodityService;
import com.sp.commodity2.vo.CommodityVO;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

/**
 * @author lx
 * @data 2023/9/22 9:41
 */
@RequestMapping("/test")
@RestController

public class Test {

  @Autowired
  ICommodityService commodityService;

  @RequestMapping("/updateSp")

  public void updateSp()
  {
    CommodityVO vo1 = new CommodityVO();
    vo1.setId(1);
    vo1.setCommodityCode("123");
    vo1.setCount(22);
    vo1.setMoney(6);
    commodityService.update(vo1);
  }
}
