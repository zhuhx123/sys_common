package com.ivymei.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 订单票号生成工具类
 * @author kandy
 *
 */
public class BillNoUtil {

    private static final int TICKET_CODE_LENGTH = 10;

    private static final int[] SEQ_ARR = new int[]{ 7, 8, 1, 4, 9, 0, 2, 5, 3, 6};

    //保证函数值的整数部分是10位数
    private static final int CONSTANT = 1234567890;

    private static final double MAX_VALUE_DOUBLE = 5000000.0;

    private static final Logger log = LoggerFactory.getLogger(BillNoUtil.class);

	public static String generateTicketCode(int orderId){
        Assert.isTrue(orderId > 0, "orderId不能小于0");
        int maxValueInt = Double.valueOf(MAX_VALUE_DOUBLE).intValue();
        Assert.isTrue(orderId <= maxValueInt, "目前只能生成orderId在[0, " + maxValueInt + "]内的票号");

        //原理：使用(y=x * x)函数，x不同，y肯定不同。但要保证x至少在1以上，才能使结果乘以CONSTANT后，是10位数
        double x = (orderId / MAX_VALUE_DOUBLE + Math.PI / 2);
        double calculateResult = x * x * CONSTANT;
        String formattedValue = String.format("%.2f", calculateResult);

        int formattedValueLength = formattedValue.length();
        String[] formattedValueArr = new String[formattedValueLength];
        for (int i=0; i<formattedValueLength; i++){
            formattedValueArr[i] = formattedValue.charAt(i) + "";
        }

        //打乱顺序重新组装，防止被看出规律
        StringBuilder ticketCode = new StringBuilder(TICKET_CODE_LENGTH);
        for (int i =0; i<TICKET_CODE_LENGTH; i++){
            ticketCode.append(formattedValueArr[SEQ_ARR[i]]) ;
        }
        log.info("order["+orderId+"] generate ticketCode:" + ticketCode.toString());
        return ticketCode.toString();
    }

    public static void main(String[] args) {
        /*for(int i=651; i<=1579; i++){
            String sql = "insert into order_ticket_code values(" + i + ", '" + generateTicketCode(i) + "');";
            System.out.println(sql);
        }*/
        //System.out.println(generateTicketCode(1580));
        Pattern pattern = Pattern.compile("[0-9]*");

        for (int i=1; i<=50; i++){
            String ticketCode = generateTicketCode(i);
            if (ticketCode.length() != TICKET_CODE_LENGTH){
                System.out.println("len: i=" + i + ",ticketCode=" + ticketCode);
            }
            Matcher isNum = pattern.matcher(ticketCode);
            if (!isNum.matches()){
                System.out.println("match: i=" + i + ",ticketCode=" + ticketCode);
            }
            System.out.println(ticketCode);
        }
        System.out.println("done..");
    }

}
