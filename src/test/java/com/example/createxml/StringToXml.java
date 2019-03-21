package com.example.createxml;

import com.example.createxml.vo.RuleBody;
import com.example.createxml.vo.RuleHeader;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class StringToXml {

    public static void main(String[] args) {

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
        System.out.println("描述:" + desc + "===规则key:" + ruleKey);
        //标题参数list
        List<RuleHeader.RuleInput> ruleInputs = ruleHeader.getRuleInputs();
        //请求内容
        List<RuleBody.Body> bodyList = ruleBody.getBodys();
        StringBuffer head=new StringBuffer("<definitions xmlns=\"http://www.omg.org/spec/DMN/20151101\" id=\"definition_52ac4098-4abf-11e9-8557-0242ac110002\" name=\"ceo-approve-item\" namespace=\"http://www.flowable.org/dmn\">\n" +
                "  <decision id=\"ceo_approve_item\" name=\"ceo-approve-item\">\n" +
                "    <description>预算科目</description>\n" +
                "    <decisionTable id=\"decisionTable_52ac4098-4abf-11e9-8557-0242ac110002\" hitPolicy=\"FIRST\">");


//        StringBuffer buf = new StringBuffer();
//        for (int i = 0; i < ruleInputs.size(); ++i) {
//            ruleInputs.get(i).getCode();
//        }
//        String s = buf.toString();
        //这是一个符合xml格式的字符串
        String xml =
                "      <input label=\"预算科目金额\">\n" +
                        "        <inputExpression id=\"inputExpression_inputExpression_10\" typeRef=\"number\">\n" +
                        "          <text>costItemAmount</text>\n" +
                        "        </inputExpression>\n" +
                        "      </input>\n"+
                "    </decisionTable>\n" +
                "  </decision>\n" +
                "</definitions>";
        head.append(xml);

        try {
            Document document=DocumentHelper.parseText(head.toString());//获取Document对象
            System.out.println("string转xml:"+document.asXML().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
