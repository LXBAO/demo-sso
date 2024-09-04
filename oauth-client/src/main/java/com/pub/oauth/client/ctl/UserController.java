package com.pub.oauth.client.ctl;

import com.pub.oauth.client.service.IOrderService;
import com.pub.oauth.client.service.ITCCService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lx
 * @data 2023/8/18 14:21
 */
@RequestMapping("/user")
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {
   @Autowired
   private IOrderService orderService;

  @Autowired
  private ITCCService itccService;
    @GetMapping("/getCurrentUser")
    @PreAuthorize("hasAuthority('add')")
    public Object getCurrentUser(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/seataAT")
    public void seataAT()
    {
        orderService.save();
    }

  @GetMapping("/seataTcc")
  @GlobalTransactional
  public void seataTcc()
  {
    itccService.prepare("777");
  }
}
