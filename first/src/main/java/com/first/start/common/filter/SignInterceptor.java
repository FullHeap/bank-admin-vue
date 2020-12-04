package com.first.start.common.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.first.start.common.util.crypt.DESUtil;
import com.first.start.common.util.crypt.RSAUtil;

/**
 * @ClassName: SignInterceptor
 * @Description: 签名验证请求拦截器
 * @author 忙碌的菠萝
 * @date 2020年11月30日 下午1:45:02
 *
 */
@Component
public class SignInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		System.out.println(request.getRequestURI());
//		System.out.println(request.getHeader("sign"));
//		System.out.println(request.getHeader("envlop"));
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder("");
		try {
			br = request.getReader();
			String str;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(sb.toString());
		
		// 获得签名
		String sign = request.getHeader("sign");
		boolean result = RSAUtil.verify(sb.toString(), sign, RSAUtil.DEFAULT_PK);
		if(!result) {
			System.out.println(result);
			System.out.println("签名验证失败");
		}

		//登录时校验密码
		if ("/systemLogin".equals(request.getRequestURI())) {
			String key = RSAUtil.decrypt(request.getHeader("envlop"), RSAUtil.DEFAULT_PRIK, "utf-8",true);
			System.out.println(key);
			JSONObject js = JSON.parseObject((sb.toString()));
			System.out.println(js.getString("password"));
			String pw = (String) js.get("password");
			System.out.println(DESUtil.decrypt(pw,key));
		}
		// 其他url 需校验token
		else {
			String token = request.getHeader("token");
			System.out.println("token:"+token);
		}
		
		
		
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
