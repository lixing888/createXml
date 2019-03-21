package com.example.createxml.controller;

import cn.hutool.core.util.XmlUtil;
import com.example.createxml.service.XmlService;
import com.example.createxml.vo.RuleBody;
import com.example.createxml.vo.RuleHeader;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/createXml")
@Api(tags = "xml文件生成")
public class CreateXml {

    @Autowired
    private XmlService xmlService;

    //获取header标题对象
    //获取body对象
    //将加工好的文件生成xml文件
    @PostMapping("/toXml")
    @ApiOperation(value = "生成xml文件",notes = "生成xml文件")
    public void toXml(@RequestBody RuleHeader ruleHeader, RuleBody ruleBody){

        xmlService.toXml(ruleHeader,ruleBody);

    }


}
