package com.lan.controller;

import org.springframework.stereotype.Controller;

/**
 * @author xuminghao
 * @create 2020-09-10 9:04
 */
@Controller
public class BaseController {
    public static final Integer COMMON_PAGE_SIZE=10;
    public static final Integer PAGE_SIZE = 20;
    // 支付中心的调用地址
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";		// produce

    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    //                       |-> 回调通知的url
    String payReturnUrl = "http://cuhnsd.natappfree.cc/orders/notifyMerchantOrderPaid";
}
