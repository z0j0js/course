package com.sise.business.controller.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.sise.server.dto.MemberCourseDto;
import com.sise.server.service.CourseService;
import com.sise.server.service.MemberCourseService;
import com.sise.server.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController("webPayController")
@RequestMapping("/web/pay")
public class PayController {

    private static final Logger LOG = LoggerFactory.getLogger(PayController.class);
    public static final String BUSINESS_NAME = "会员课程支付";

    @Resource
    private AlipayClient alipayClient;

    @Resource
    private AlipayTradePagePayRequest alipayTradePagePayRequest;

    @Resource
    private MemberCourseService memberCourseService;

    @Resource
    private CourseService courseService;

    //处理支付请求
    @PostMapping("/buy")
    public String pay(@RequestBody MemberCourseDto memberCourseDto)
            throws AlipayApiException {
        //1.接收页面传过来的数据:订单号，金额，名称，商品描述  表单中的name值=参数名
        String WIDout_trade_no = memberCourseDto.getMemberId() + memberCourseDto.getCourseId() + System.currentTimeMillis();
        String WIDtotal_amount = memberCourseDto.getCoursePrice();
        String WIDsubject = memberCourseDto.getCourseName();
        String WIDbody = "会员号："+ memberCourseDto.getMemberId() +"，购买《"+ memberCourseDto.getCourseName() +"》课程";
        //2.获得支付的客户端AlipayClient,和配置支付信息的对象AlipayTradePagePayRequest
        //3.设置响应的地址(支付宝返回给商户的响应地址)
        alipayTradePagePayRequest.setNotifyUrl(AppUtil.notify_url);
        alipayTradePagePayRequest.setReturnUrl(AppUtil.return_url);
        //4.设置请求的参数(传递给支付宝的数据)
        alipayTradePagePayRequest.setBizContent(
                "{\"out_trade_no\":\""+ WIDout_trade_no +"\","
                        + "\"total_amount\":\""+ WIDtotal_amount +"\","
                        + "\"subject\":\""+ WIDsubject +"\","
                        + "\"body\":\""+ WIDbody +"\","
                        + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //5.发送请求
        String result = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
        //System.out.println(result);
        //6.将响应结果返回给前端
        return result;
    }


    @RequestMapping("/getreturn")
    public void getreturn(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        Iterator<String> iter = requestParams.keySet().iterator();
        while(iter.hasNext()){
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //RSA2验证
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AppUtil.alipay_public_key, AppUtil.charset, AppUtil.sign_type); //调用SDK验证签名
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //——请在这里编写您的程序（以下代码仅作参考）——
        System.out.println("支付结果"+ signVerified);
        if(signVerified) {
            String out_trade_no = request.getParameter("out_trade_no");
            String memberId = out_trade_no.substring(0,8);
            String courseId = out_trade_no.substring(8,16);
            MemberCourseDto memberCourseDto = new MemberCourseDto();
            memberCourseDto.setCourseId(courseId);
            memberCourseDto.setMemberId(memberId);
            memberCourseService.enrolls(memberCourseDto);
            courseService.updateEnroll(memberCourseDto.getCourseId());
            response.sendRedirect("http://localhost:8081/detail?id=" + courseId);
        }else {
            out.println("验签失败");
        }
    }

    //接收支付宝返回的异步通知的信息
    @PostMapping("/getnotify")
    public void getnotify(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        Iterator<String> iter = requestParams.keySet().iterator();
        while(iter.hasNext()){
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AppUtil.alipay_public_key, AppUtil.charset, AppUtil.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no =request.getParameter("out_trade_no");
            //支付宝交易号
            String trade_no = request.getParameter("trade_no");
            //交易状态
            String trade_status = request.getParameter("trade_status");
            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

            out.println("success");

        }else {//验证失败
            out.println("fail");
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }

    }
}
