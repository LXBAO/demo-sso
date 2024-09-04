package com.pub.oauth.client.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author lx
 * @data 2023/9/23 14:22
 */
@LocalTCC
public interface ITCCService {

  @TwoPhaseBusinessAction(name = "prepare", commitMethod = "commit", rollbackMethod = "rollback")
  Object prepare(@BusinessActionContextParameter(paramName = "userId") String userId);

  /**
   * 全局事物进行提交
   */
  boolean commit(BusinessActionContext actionContext);

  /**
   * 全局事务进行回滚
   */
  boolean rollback(BusinessActionContext actionContext);

}
