package com.example.createxml.controller;

import cn.hutool.core.util.XmlUtil;
import com.example.createxml.vo.RuleBody;
import com.example.createxml.vo.RuleHeader;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/createXml")
public class CreateXml {

    //获取header标题对象
    //获取body对象
    //将加工好的文件生成xml文件
    @PostMapping("/toXml")
    public void toXml(@RequestBody RuleHeader ruleHeader, RuleBody ruleBody){
        //描述
        String desc= ruleHeader.getDesc();
        //规则key
        String ruleKey=ruleHeader.getRuleKey();
        //标题参数list
        List<RuleHeader.RuleInput> ruleInputs=ruleHeader.getRuleInputs();
        //请求内容
        List<RuleBody.Body> bodys=ruleBody.getBodys();

        XmlUtil.createXml();



    }


}
