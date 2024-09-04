package com.sp.commodity2.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**commodity
 * @author lx
 * @data 2023/8/17 15:23
 */
@Data
@TableName(value = "t_order")
public class OrderVO {

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;


    @TableField("user_id")
    private String userId;

    @TableField("commodity_code")
    private String commodityCode;

    private Integer count;


    private Integer money;
}