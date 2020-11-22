package com.xiongmaohaixin;

import com.xiongmaohaixin.wxDeciphering.AesException;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * ClassName:Test01
 * Package:com.xiongmaohaixin
 * Description:请为该功能做描述
 *
 * @Date:2020/11/16 18:06
 * @Author:XHX
 */
public class Test01 {
    public static void main(String[] args) throws AesException {
        String xhx = getSHA1("xhx", "1605519614", "1048708102");
        System.out.println(xhx);
    }

    /**
     * 用SHA1算法生成安全签名
     * @param token 票据
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @param encrypt 密文
     * @return 安全签名
     * @throws AesException
     */
    public static String getSHA1(String token, String timestamp, String nonce) throws AesException
    {
        try {
            String[] array = new String[] { token, timestamp, nonce };
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < 3; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.ComputeSignatureError);
        }
    }
}
