package com.mtx.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName EncryptionsUtil
 * @Description 加解密工具类，公钥私钥找开发架构问
 * @Author rainbow
 * @Date 2020/11/23 13:47
 * @Version 1.0
 **/
public class EncryptionsUtil {
    private static Map<Integer,String> keyMap=new HashMap<Integer, String>();
    /**
     * @Description: md5加密
     * @Author: rainbow
     * @Date: 2020/11/23 13:53
     * @param data: 待加密字符串
     * @return: java.lang.String
     **/
    public static String md5(String data){
        return DigestUtils.md5Hex(data);
    }

    /**
     * @Description: sha1加密
     * @Author: rainbow
     * @Date: 2020/11/23 14:04
     * @param data: 待加密字符串
     * @return: java.lang.String
     **/
    public static String sha1(String data){
        return DigestUtils.sha1Hex(data);
    }


    /**
     * @Description: sha256加密
     * @Author: rainbow
     * @Date: 2020/11/23 14:05
     * @param data: 待加密字符串
     * @return: java.lang.String
     **/
    public static String sha256(String data) {
        return DigestUtils.sha256Hex(data);
    }

    public static void getKeyPairRSA() throws Exception {
        //KeyPairGenerator类用于生成公钥和密钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        //初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        //生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();//得到私钥
        PublicKey publicKey = keyPair.getPublic();//得到公钥
        //得到公钥字符串
        String publicKeyString=new String(Base64.encodeBase64(publicKey.getEncoded()));
        //得到私钥字符串
        String privateKeyString=new String(Base64.encodeBase64(privateKey.getEncoded()));
        //将公钥和私钥保存到Map
        keyMap.put(0,publicKeyString);//0表示公钥
        keyMap.put(1,privateKeyString);//1表示私钥
    }


    /**
     * @Description: RSA公钥加密
     * @Author: rainbow
     * @Date: 2020/11/23 14:08
     * @param str: 待加密字符串
     * @param publicKey: 公钥
     * @return: java.lang.String 密文
     **/
    public static String encryptRSA(String str,String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey= (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RAS加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,pubKey);
        String outStr=Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }


    /**
     * @Description:   RSA私钥解密
     * @Author: rainbow
     * @Date: 2020/11/23 14:54
     * @param str: 加密字符串
     * @param privateKey:  私钥
     * @return: java.lang.String 明文
     **/
    public static String decryptRSA(String str,String privateKey) throws Exception {
        //Base64解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //Base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        PrivateKey priKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,priKey);
        String outStr=new String(cipher.doFinal(inputByte));
        return outStr;
    }
    

    /**
     * @Description:  aes加密
     * @Author: rainbow
     * @Date: 2020/11/23 14:20
     * @param input: 待加密字符串
     * @param key: 私钥，请找开发询问
     * @return: java.lang.String
     **/
    public static String aesEncode(String input, String key) {
        byte[] crypted = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new String(Base64.encodeBase64(crypted));
    }

    /**
     * @Description:  aes解密
     * @Author: rainbow
     * @Date: 2020/11/23 15:03
     * @param input: 待解密字符串
     * @param key: 私钥，请找开发要
     * @return: java.lang.String
     **/
    public static String aesDecode(String input, String key) {
        byte[] output = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base64.decodeBase64(input));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new String(output);
    }



    public static void main(String[] args) throws Exception {
//      System.out.println(md5("123456"));
//      System.out.println(sha1("123456"));
//    	System.out.println(sha256("123456"));

//        //aes加密
//        String aesEncode = aesEncode("abcdefg", "1234567891234567");
//        System.out.println(aesEncode);
//        //aes解密
//        String aesDecode = aesDecode("89Dw8PpTrUSMdh3oJ7U4Zw==", "1234567891234567");
//        System.out.println(aesDecode);

        //生成公钥和私钥
        getKeyPairRSA();
        System.out.println("随机生成的公钥为："+keyMap.get(0));
        System.out.println("随机生成的私钥为："+keyMap.get(1));

        //rsa加密
        String pwd = "12345678901";
        String pwdEn = encryptRSA(pwd,keyMap.get(0));
        System.out.println(pwd+"\trsa加密："+pwdEn);
        //rsa解密
        String passwordDe=decryptRSA(pwdEn,keyMap.get(1));
        System.out.println("rsa解密："+passwordDe);

    }

}

