package com.example.createxml.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

/**
 * @author lixing
 */
@Data
@ApiModel
public class RuleHeader {

    @ApiModelProperty("规则Key")
    public String ruleKey;

    @ApiModelProperty("规则描述")
    public String desc;

    @ApiModelProperty("标题参数")
    public List<RuleInput> ruleInputs;

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    @Data
    public static class RuleInput{

      @ApiModelProperty("标题ID")
      private int id;
      @ApiModelProperty("标题名称")
      private String name;
      @ApiModelProperty("标题编码")
      private String code;
      @ApiModelProperty("标题类型")
      private String type;

    }


}
