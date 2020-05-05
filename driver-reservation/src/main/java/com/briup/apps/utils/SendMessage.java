package com.briup.apps.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMessage {
	public static void send(String smsMob,String time) throws Exception {
		HttpClient client = new HttpClient(); 
	    PostMethod post = new PostMethod("http://gbk.api.smschinese.cn"); 
	    post.addRequestHeader("Content-Type", 
	        "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码 
	    NameValuePair[] data = { new NameValuePair("Uid", "随便玩玩"),//中国网建sms平台注册的用户名 
	        new NameValuePair("Key", "d41d8cd98f00b204e980"),//中国网建sms平台注册的用户密钥 
	        new NameValuePair("smsMob", smsMob),//将要发送到的手机号码 
	        new NameValuePair("smsText", "骐达驾校提醒您,请你于"+time+"到驾校练车") };//要发送的短信内容 
	    post.setRequestBody(data); 
	  
	    client.executeMethod(post); 
	    Header[] headers = post.getResponseHeaders(); 
	    int statusCode = post.getStatusCode(); 
	    System.out.println("statusCode:" + statusCode); 
	    for (Header h : headers) { 
	      System.out.println(h.toString()); 
	    } 
	    String result = new String(post.getResponseBodyAsString().getBytes( 
	        "gbk")); 
	    System.out.println(result); // 打印返回消息状态 
	  
	    post.releaseConnection(); 
	}
}
