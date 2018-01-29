package com.xavier.common;

import lombok.Data;

/**
 * Created by xavier on 2018/1/22.
 */
@Data
public class BaseResponse<T> {

	private T data;

	private String message;

	private boolean success;

	private int code;

	public static BaseResponse success() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setSuccess(true);
		baseResponse.setMessage("OK");
		baseResponse.setCode(200);
		return baseResponse;
	}

	public static BaseResponse fail() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setSuccess(false);
		baseResponse.setCode(0);
		baseResponse.setMessage("Something Wrong");
		return baseResponse;
	}

	public static void main(String[] args){

	}
}
