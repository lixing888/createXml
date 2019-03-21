package com.example.createxml;

import cn.hutool.core.util.XmlUtil;
import com.example.createxml.service.XmlService;
import com.example.createxml.vo.RuleBody;
import com.example.createxml.vo.RuleHeader;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class XmlTest {

    @Autowired
    private XmlService xmlService;
    @Test
    public void createXml() throws DocumentException {

        RuleBody ruleBody = new RuleBody();
        RuleHeader ruleHeader = new RuleHeader();

        ruleHeader.setRuleKey("ceo-approve-item");
        ruleHeader.setDesc("预算科目");
        List<RuleBody.Body> bodys = new ArrayList<>();
        ruleBody.setBodys(bodys);

        //描述
        String desc = ruleHeader.getDesc();
        //规则key
        String ruleKey = ruleHeader.getRuleKey();
        System.out.println("描述:" + desc + "===规则key" + ruleKey);
        //标题参数list
        List<RuleHeader.RuleInput> ruleInputs = ruleHeader.getRuleInputs();
        //请求内容
        List<RuleBody.Body> bodyList = ruleBody.getBodys();

        // 字符串转XML
        //这是一个符合xml格式的字符串
        String xml = desc;

        Document document = (Document) DocumentHelper.parseText(xml);//获取Document对象
        System.out.println("string转xml："+document.toString());


    }



}
