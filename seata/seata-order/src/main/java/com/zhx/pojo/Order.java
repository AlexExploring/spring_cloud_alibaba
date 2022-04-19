package com.zhx.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * order
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    /**
     * order 表主键
     */
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 数量
     */
    private Integer totalAmount;

    /**
     * 订单状态:'已支付','未支付'
     */
    private Object status;
}