package com.lewen.listener.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.util.Log;

/**
 * some http method like get/post 
 * @author Administrator
 *
 */
public class HttpUtil {

	public static String sendPostRequest(HashMap<String, Object> params,
			String strUrl) {
		String result = null;
		BufferedReader br = null;
		HttpURLConnection connection = null;
		DataOutputStream out = null;
		InputStream instream = null;

		try {
			StringBuilder paraUrl = new StringBuilder();
			int index = 0;
			if (params != null && !params.isEmpty()) {
				for (Iterator it = params.entrySet().iterator(); it.hasNext();) {
					Map.Entry e = (Map.Entry) it.next();
					if (index > 0)
						paraUrl.append("&");
					paraUrl.append(e.getKey().toString());
					paraUrl.append("=");
					paraUrl.append(URLEncoder.encode(e.getValue().toString(),
							"utf-8"));
					index++;
				}
			}
			URL url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.connect();
			out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(paraUrl.toString());
			out.flush();

			StringBuilder sBuilder = new StringBuilder();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				instream = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(instream, "utf-8");
				int ichar;
				while ((ichar = isr.read()) != 0) {
					sBuilder.append((char) ichar);
				}
				result = sBuilder.toString();
			}
		} catch (Exception e) {
			Log.d("HTTP", e.getMessage());
		} finally {
			if (null != connection)
				connection.disconnect();
			try {
				if (null != out)
					out.close();
			} catch (IOException ioe) {
				Log.d("HTTP", ioe.getMessage());
			}
		}
		return result;
	}
	
	
	
	public static String sendPost(List<NameValuePair> pairList,String baseURL){
		String result = null;
         try
         {
             HttpEntity requestHttpEntity = new UrlEncodedFormEntity(
                     pairList);
             // URL使用基本URL即可，其中不需要加参数
             HttpPost httpPost = new HttpPost(baseURL);
             // 将请求体内容加入请求中
             httpPost.setEntity(requestHttpEntity);
             // 需要客户端对象来发送请求
             HttpClient httpClient = new DefaultHttpClient();
             // 发送请求
             HttpResponse response = httpClient.execute(httpPost);
             // 显示响应
//             showResponseResult(response);
             if (null!= response)
             {
            	 
            	 HttpEntity httpEntity = response.getEntity();
                 try
                 {
                     InputStream inputStream = httpEntity.getContent();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(
                             inputStream));
                     String line = "";
                     while (null != (line = reader.readLine()))
                     {
                         result += line!=null?line:"";

                     }

                     //{"status":1,"data":{"uid":"7350","salt":"2EuYkz"}}
                     System.out.println(result);
                     JSONObject object = new JSONObject(result);
                     
                 }
                 catch (Exception e)
                 {
                     e.printStackTrace();
                 }
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
		return result;
	}
}
