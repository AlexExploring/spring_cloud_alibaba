package com.zhx.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * stock
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock implements Serializable {
    /**
     * 库存表主键
     */
    private Long id;

    /**
     * product_id
     */
    private Long productId;

    /**
     * 库存大小
     */
    private Integer count;
}