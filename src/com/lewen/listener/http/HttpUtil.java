package com.lewen.listener.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import com.lewen.listener.TBApplication;

/**
 * some http method like get/post
 * 
 * @author caixm
 * 
 */
public class HttpUtil {


	public static String sendPost(List<NameValuePair> pairList, String baseURL) {
		String result = "";
		HttpClient httpClient = null;
		try {
			// Create a local instance of cookie store
			CookieStore cookieStore = new BasicCookieStore();

			// Create local HTTP context
			HttpClientContext localContext = HttpClientContext.create();
			// Bind custom cookie store to the local context
			localContext.setCookieStore(cookieStore);

			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList);
			// URL使用基本URL即可，其中不需要加参数
			HttpPost httpPost = new HttpPost(baseURL);
			// 将请求体内容加入请求中
			httpPost.setEntity(requestHttpEntity);
			if (TBApplication.getPreferenceData("session") != null) {
				// add the cookie
				httpPost.addHeader("Pragma", "no-cache");
				httpPost.addHeader("Cache-Control", "no-cache");
				httpPost.addHeader("Cookie", TBApplication.getPreferenceData("session"));// "PHPSESSID=94mqfngr7qlkmoh9nmrgk8bvg1;// TBApplication.getPreferenceData("session")
			}
			// 需要客户端对象来发送请求
			httpClient = new DefaultHttpClient();

			// 发送请求
			HttpResponse response = httpClient.execute(httpPost, localContext);

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			List<Cookie> cookies = cookieStore.getCookies();
			String session = "";
			for (int i = 0; i < cookies.size(); i++) {
				System.out.println("Local cookie: " + cookies.get(i));
				session += cookies.get(i).getName() + "=" + cookies.get(i).getValue();

				if (i != cookies.size() - 1) {
					session += ";";
				}
			}
			if (session.length() > 0) {
				TBApplication.pushPreferenceData("session", session);
			}

			if (null != response) {

				HttpEntity httpEntity = response.getEntity();
				try {
					InputStream inputStream = httpEntity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					String line = "";
					while (null != (line = reader.readLine())) {
						result += line != null ? line : "";

					}
					// {"status":1,"data":{"uid":"7350","salt":"2EuYkz"}}
					result = result.substring(result.indexOf("{"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
