package com.example.createxml.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lixing
 */
@Data
@ApiModel
public class RuleBody {
    @ApiModelProperty("规则内容")
    private List<Body> bodys;

    @Data
    public static class Body{
      @ApiModelProperty("规则内容编码")
      private String code;
      @ApiModelProperty("规则内容阈值")
      private BigDecimal threshold;
      @ApiModelProperty("规则内容比对结果 true or false")
      private boolean resultInfo;


    }



}
