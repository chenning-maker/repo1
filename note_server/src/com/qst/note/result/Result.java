package com.qst.note.result;

//客户端发送出的请求都需要结果响应，需要一个统一的格式：
//请求结果类：是否请求成功
public class Result {

	public Boolean isSuccess; //是否请求成功？
	public String msg;        //字符串：请求信息(失败原因..)
}
