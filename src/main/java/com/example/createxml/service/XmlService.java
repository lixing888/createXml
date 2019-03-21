package com.example.createxml.service;

import cn.hutool.core.util.XmlUtil;
import com.example.createxml.vo.RuleBody;
import com.example.createxml.vo.RuleHeader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author lixing
 */
@Service
public class XmlService {

    public void toXml(@RequestBody RuleHeader ruleHeader, RuleBody ruleBody){
        //描述
        String desc= ruleHeader.getDesc();
        //规则key
        String ruleKey=ruleHeader.getRuleKey();
        System.out.println("描述:"+desc+"规则key"+ruleKey);
        //标题参数list
        List<RuleHeader.RuleInput> ruleInputs=ruleHeader.getRuleInputs();
        //请求内容
        List<RuleBody.Body> bodys=ruleBody.getBodys();

        XmlUtil.createXml();



    }


}
