package com.example.createxml;

import com.example.createxml.vo.RuleBody;
import com.example.createxml.vo.RuleHeader;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Node;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StringToXml {

    public static void main(String[] args) {

        RuleBody ruleBody = new RuleBody();
        RuleHeader ruleHeader = new RuleHeader();

        ruleHeader.setRuleKey("ceo-approve-item");
        ruleHeader.setDesc("预算审核科目");
        List<RuleBody.Body> bodys = new ArrayList<>();
        RuleBody.Body body1=new RuleBody.Body();
        body1.setCode("MDBS00000002");
        body1.setThreshold(BigDecimal.valueOf(100000000));
        body1.setResultInfo(false);

        RuleBody.Body body2=new RuleBody.Body();
        body2.setCode("MDBS00000011");
        body2.setThreshold(BigDecimal.valueOf(250000000));
        body2.setResultInfo(false);

        RuleBody.Body body3=new RuleBody.Body();
        body3.setCode("MDBS00000012");
        body3.setThreshold(BigDecimal.valueOf(50000000));
        body3.setResultInfo(true);

        RuleBody.Body body4=new RuleBody.Body();
        body4.setCode("MDBS00000013");
        body4.setThreshold(BigDecimal.valueOf(999999999));
        body4.setResultInfo(true);

        bodys.add(body1);
        bodys.add(body2);
        bodys.add(body3);
        bodys.add(body4);

        ruleBody.setBodys(bodys);

        //描述
        String desc = ruleHeader.getDesc();
        //规则key
        String ruleKey = ruleHeader.getRuleKey();
        String ruleKey1=ruleKey.replace('-','_');
        System.out.println("描述:" + desc + "===规则key:" + ruleKey.replace('-','_'));

        //标题参数list
        List<RuleHeader.RuleInput> ruleInputs = new ArrayList<>();
        RuleHeader.RuleInput ruleInput1=new RuleHeader.RuleInput();
        ruleInput1.setId(9);
        ruleInput1.setCode("costItemId");
        ruleInput1.setName("预算科目code");
        ruleInput1.setType("string");

        RuleHeader.RuleInput ruleInput2=new RuleHeader.RuleInput();
        ruleInput2.setId(10);
        ruleInput2.setCode("costItemAmount");
        ruleInput2.setName("预算科目金额");
        ruleInput2.setType("number");

        ruleInputs.add(ruleInput1);
        ruleInputs.add(ruleInput2);
        ruleHeader.setRuleInputs(ruleInputs);

        //请求内容
        List<RuleBody.Body> bodyList = ruleBody.getBodys();
        //表头
        StringBuffer head=new StringBuffer(
                "<definitions xmlns=\"http://www.omg.org/spec/DMN/20151101\" id=\"definition_52ac4098-4abf-11e9-8557-0242ac110002\" name='"+ruleKey+"' namespace=\"http://www.flowable.org/dmn\">\n" +
                "  <decision id='"+ruleKey1+"' name='"+ruleKey+"'>\n" +
                "    <description>"+desc+"</description>\n" +
                "    <decisionTable id=\"decisionTable_52ac4098-4abf-11e9-8557-0242ac110002\" hitPolicy=\"FIRST\">\n");

//        StringBuffer buf = new StringBuffer();
//        for (int i = 0; i < ruleInputs.size(); ++i) {
//            ruleInputs.get(i).getCode();
//        }
//        String s = buf.toString();
        //这是一个符合xml格式的字符串
        //for循环添加标题
        String inputExpression ="";
        for(int i=0;i<ruleInputs.size();i++){
            inputExpression =
                    "      <input label='"+ruleInputs.get(i).getName()+"'>\n" +
                            "        <inputExpression id=\"inputExpression_inputExpression_"+ruleInputs.get(i).getId()+"\" typeRef='"+ruleInputs.get(i).getType()+"'>\n" +
                            "          <text>"+ruleInputs.get(i).getCode()+"</text>\n" +
                            "        </inputExpression>\n" +
                            "      </input>\n";
            head.append(inputExpression);
        }

        //添加output标签
        head.append("      <output id=\"outputExpression_outputExpression_6\" label=\"是否符合规则\" name=\"result\" typeRef=\"boolean\"></output>\n");
        //rule内容 for循环
        String rule="";
        for(int j=0;j<bodys.size();j++){
          rule=   "      <rule>\n" +
                  "        <inputEntry id=\"inputEntry_inputExpression_9_"+Integer.valueOf(j+1)+"\">\n" +
                  "          <text><![CDATA[== \""+bodys.get(j).getCode()+"\"]]></text>\n" +
                  "        </inputEntry>\n" +
                  "        <inputEntry id=\"inputEntry_inputExpression_10_"+Integer.valueOf(j+1)+"\">\n" +
                  "          <text><![CDATA[>= "+bodys.get(j).getThreshold()+"]]></text>\n" +
                  "        </inputEntry>\n" +
                  "        <outputEntry id=\"outputEntry_outputExpression_6_"+Integer.valueOf(j+1)+"\">\n" +
                  "          <text><![CDATA["+bodys.get(j).isResultInfo()+"]]></text>\n" +
                  "        </outputEntry>\n" +
                  "      </rule>\n";
            head.append(rule);
        }

        //结尾
        head.append("   </decisionTable>\n" +
                "  </decision>\n" +
                "</definitions>");

        try {
            Document document=DocumentHelper.parseText(head.toString());//获取Document对象
            System.out.println("string转xml:"+document.asXML().toString());
            //设置生成xml的格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            //设置编码格式
            format.setEncoding("UTF-8");
            //生成xml文件
            File file = new File(ruleKey+".xml");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            //设置是否转义，默认使用转义字符
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
            System.out.println("生成"+ruleKey+".xml成功");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
