package com.cy.tpes.entity.yeyentity;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class YeAlipayConfig
{
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101900723321";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCeuLNnKwwT5bLotIcPFubICTtaeNpVHPlf7wH58j/PH20WhWojj7PCiWyPjPqMvHc0C8d+4sGcsq9dFB8YcwNjL3H1UCk15t1xrpkR6NgP13ptsks1Q3PSXeXIVXi35STGgAT8LfCdm83xiPOChoSTnXIAGrHix/O8abZ3quF2+bvCuZs1eUvjLTTDpQm2ruhb5s2x7QgasWys6WfYkwxalT99aNw5wcrafXIy1+SqwaYvgRmrNY14NWBG0VhR/gLBL7ptPXxTsvAzHcV1f/xiLANpTfck4pOfOUlFMhiU9NoKo4ZzB5jz2xtKdT3QD9l3Rna01rPQe/SGCpy3MidBAgMBAAECggEAD5Rvv8PA9r8IVcFKtJd55c7KLbJJNCuY6urKFd/KlakvCxPjfEQh0AmvR1P+UAw74zb4ySK3AIulnQxZlQvNHFJO7FaHBQx5VixeSBzEAKIVGWlz6nGJj++5m3WQWPS8LGPqmVplEQN3LqtTw88yek7tHseACJO6ABecWpjCcuwe4uKWL+bNCaQ5AePKT08tVaXYMHzX0bUW5jOJmZH4M2SY+lAdjU33QwXomP18KDbUjnFm/uDDYl3jEzsOwn9re/+W8Q0HXG10RQKwtTPU29ssP6XbpaVC+vM0+jUrLeKO2UmeNKMbQpGLF4/0ZaL/25KjtpX52D/jzUTI6AMXRQKBgQDbrHCReWTauLiIjK6rsD3s1oImTxSApAmwN5o6hKaP4rb/rwRaU5bRnnSXeVcnX05xZWyqhlf57NYOFltUH96HPqO0blNGwnld+XoogSZ5S2oZccOzhq6B+RQTFFvsEZj4LcPLbrMRnPDrKEWpM6z4U8xUihbgCF7dDx8ut35DBwKBgQC49/FeACtTlKhhUP8I3D1Nn+3fcZvAEdGdr26fdH45Uel05WPqdpAHpAL619LALAM7lVp0n+GBbr/EHf2tYZdOKsG1gVLaa0SbPXYE+33DQCSPo869QCwBZ4A5rRwoVSC3zkpcahzmTYfyudeOVu20L/4fGASx6EdFqPVKtXxJdwKBgQDTAkj+9ASUtGtNfxUphhl1rndGJSAkkW1c/4v+Of1kqPnvkEY2HaT5paOI/N1izIGlGy/OfU10nLLgxUjk/dfbQaguYOtNRPEf+8wDrEswp+g6mLTLdcuavY8y5JOHpz+w5QT2HFeVy5L/z6daaHD1+EiFzD884817ulLRpSB7cQKBgQCjlKtCbbXjgfnzrrUNufEBQGoXjRqoH1oZMTgNDvI+cSGc/u2BQn+YMkVsYAiDaakmPay0rVy6oh44LCnX9xHf0FJl2cxTXEBdZlqEh4GudoCfA8hneGQyjb1JxL29MaPwyiDbYADU8EZFtHNc76XjD/qWtyAX0ouNoKHmA2C6ewKBgApoUJuLCBLAY2/2OPlL8mXUF88IxiwaNh/xmTtvH5WM2emu4Tqsr5nJgh0gcL8cLbtCUhKIB8E4D5CIOJnH2Qf0CK1Xwzbc4mhBwjFLuLoHwhxll2hRrt0rUqFeBmO7bfU3z1gKHieIBILhMtbZY6+vMpUNnZU7ubICWcIxsdKj";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAunrmUeFth/mLNwG/Vgvvr62Tw0QbUSq8A6T+/NC7ioyrfQuIocTP3kfooaVmv91rDs++bsWZKYzGdos6r6WmhVneWUKQQpmopesDjfRK5Qb7D1f4jw1/cmV+kBVyoGhrvD6Xr/4Psg7PPPbPlBrxBi/1OifkZ/5MJRzUE2tBxs/w8gH98CPazZrkt9hBnxlSowebT1c+dUuPncllpDy2Q+CxJH11AV/Jp1+Pym7fvS8X70KC1TFeF/699eDP1NyxQM4vATBFsnWv8yVu/HwVy3TJe2wplCwbCo6Djumt24m3yzR3UzcuFBmQDLztgMD4pFy+tj4wO/CIxG6+FaNNZQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8300/tpes/alipayNotifyNotice";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8300/tpes/alipayReturnNotice";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "https://openapi.alipaydev.com/gateway.do";


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

