package com.pub.oauth.client.feign.client;


import com.common.vo.CommodityVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lx
 * @data 2023/9/20 9:55
 */
@FeignClient(value = "commodity2")
public interface CommodityFeignClient {

  @RequestMapping("/test/updateSp")
  void update();

  @RequestMapping("/test/delete")
  void delete(@RequestParam("userId") String userId );
}
