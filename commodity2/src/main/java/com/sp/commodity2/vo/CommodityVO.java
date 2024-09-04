package com.sp.commodity2.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lx
 * @data 2023/8/17 15:23
 */
@Data
@TableName(value = "t_commodity")
public class CommodityVO {

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("commodity_code")
    private String commodityCode;

    private Integer count;


    private Integer money;


}