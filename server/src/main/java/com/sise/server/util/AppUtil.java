package com.sise.server.util;

import java.io.FileWriter;
import java.io.IOException;

public class AppUtil {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016103100781229";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCSOLvVBv3pbgpyDUVK+WPyAgRzCJ6KRZgtm0o1VaXc/UbF4Vr9wV4hIxyEPDRdQ3TJGf0xMnxHf5D3hSD4CCrZhpIRGmjC7H1YaGcYY2dQQoDaFx0XPl+J9yFmNb15xA8kAi5cqe76R/5JGUd2/UoaWVxAHFcSnpa8UufTplz99e7Fop/txkJG0qZMDfluEtREW+YyNxWR5j0nIYIW7x/OQUpvhdCV4pMsPB6sT5H4RIdiudv9saZsijTEkE+39MGsq/GNeEBtU25zrMFGieKuWGwebH5xfk7cDu9kXb97u+OfpRfLDGkDOdtO+2e5D+ufGs/SvvV5dmL2P7+NgWG3AgMBAAECggEAXDFLNLiQ1WIdUeXOH8R4YLkm5wSzOUlB9tdnhZM33csPmWpKPZ5y4//IVhPDjF4gvz8UpbVIKKhk1btpmxlDMx2m8QOus9/59Aj3n6flFvSnW1qha+tWv51CwLf3fFPCnBiH9MLsO3+y85HtvjdbRpu7D20O9V1qetjm9Ep7bV9vp+KQkqmWt3rSBk+DwjYOjJwLXekA/8Y+bWZ6ymBSCYYi86GLBONKvTjU1oAmZ6WfmkCNmDzBXuAab0qrPxGVbueVL+HwhnHCyQNqfsbQWASk93SW5neU8OSMBLv7sFaM1oVZiyMF9bg6bO6kN00A3fTTq6wd/Yud8oU8EnPMEQKBgQDn/aLZi7/gZNo8Fs4c859K4O5Yjvrrm7O+IU9BZc2Fd1QCpU6GsvQn0Uxx1cl6XulGUpki2vFylp3xbUe1keWKlivJIhQq3k2HQMv3EBw9KtbxSpd5Sm6Qjn15RMFBUSymbqksNXurPBwgXVYQitJ6rO2vKnbbV8cR03Ke1qinrwKBgQChWrrC9i4/VJ56t8oRVtKecUIyshhxGtXXv80vBgaua3hArD7q1YEEZAcMzXjvCf6rKPIUOMX0SRk9Xbt8ewSZb9HOdAhiNKx+9NYM1J0L+WXneCYwpNf53//lGZIefrQ0tAWQ6vP6hOFGUIi93qPjIjRz7TTt7fSosdO8Dz/geQKBgCyylrTZky+V3uJi9nVnjxJrQiD382p/xmLkohOUnMORWTNlTDrxsc8WKWdpbHdSYBm3gl/P1WNhl67TglR+dr577pXDJiWYTVG471N73CUA4wb8ezDYYXMisRQzZhTCohqzCY9GArzcSQMHWLS5OpJ+TRFJ2jqqr+VEWzo1W6trAoGAcSILdu1jidYL9GFvFe85aAxjbOZAivVU4JxEAmeIQb1qqHXc+4zNlEWKR+i9lWT9ogbojVMtQNYME2HoNiSPGgI0KjxjMHBcTfvfJUnh/2ZTlBNDO7IpGLN9WaYvNKCEO7vJd0212X5X8ZQ//FjhsjzmaC32gajpdWqR2fcbQ4kCgYBF5DUtQKm9w06rqFqg/Jt1vZqrql9Dtz+cXq6ZevaPE72QdlvBifGLyuwfH162IHdQS2xG92kIlqk/rFp+8sGOrAaX6ogKWuGRLvYGAwNNx+Zf97fgY6yTOYh6kD47idyNKg5tisYN+0MFQACo7v7duxxJ61PGYGP3uyNIDCzxnQ==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjZ1g/ecMRMwMXZDVkHUmFD9oOS+RxDzwYbVQAq7q9RV2C1v6hWfYr6RY4CMOWg8V61bPLg3tg8u+5LuoICvU4l53qFiPFAQVf5ioEDj2+vdCgF94Xv4k7WCb4XlGGm8ujae3FU7h2XTw9FG6D+W49LaVflpSeeGXBEUZxoCkMwj9JfUU7MbPeFuIhcsceflX6BFE8QSw/hLsk9dxymlckPTmxQOI0qNSiaWMS9YV+pba1+av8fPn7Q+0SngKnyDs49fluGf6T+mFNRm5sMNEqlgv8FPwT3r8T75p35PW1RFoOqhCyu+LZoRx7Vkk9melT4q5EVmPETDQW5t2yGknTQIDAQAB";

    // 服务器异步通知页面路径
    //需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://mvzdze.natappfree.cc/business/web/pay/getnotify";

    // 页面跳转同步通知页面路径
    //需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://mvzdze.natappfree.cc/business/web/pay/getreturn";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关,注意这些使用的是沙箱的支付宝网关，与正常网关的区别是多了dev
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
