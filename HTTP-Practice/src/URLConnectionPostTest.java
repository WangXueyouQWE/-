import java.io.IOException;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * CookieHandler 对象提供一种回调机制以将 HTTP 状态管理策略实现挂钩到 HTTP 协议处理程序。
 * HTTP 状态管理机制利用 HTTP 请求和响应指定创建有状态的会话的方式。 
 * 可以通过执行 CookieHandler.setDefault(CookieHandler) 来注册 HTTP 协议处理程序要使用的系统级 CookieHandler。
 * 调用 CookieHandler.getDefault() 可以获取当前注册的 CookieHandler。
 * @author Administrator
 *
 */
public class URLConnectionPostTest {
	public static void main(String[] args) throws NumberFormatException, IOException {
		String url="https://tools.usps.com/go/ZipLookupAction.action";
		Object userAgent="HTTPie/0.9.2";
		Object redirects="1";
		
		CookieHandler.setDefault(new CookieManager(null,CookiePolicy.ACCEPT_ALL));
		Map<String, String> params=new HashMap<String, String>();
		params.put("tAddress", "1 Market Street");
		params.put("tCity", "San Francisco");
		params.put("sState", "CA");
		
		String result=dopost(new URL(url),params,userAgent==null?null:userAgent.toString(),
				redirects==null?-1:Integer.parseInt(redirects.toString()));
		System.out.println(result);
		
	}

	private static String dopost(URL url,Map<String, String> nameValueParis,String userAgent,int redirects) throws IOException
	{
		StringBuilder response=new StringBuilder();
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
		if (userAgent !=null) {
			connection.setRequestProperty("User-Agent", userAgent);
			if (redirects>0) {
				connection.setInstanceFollowRedirects(false);
				connection.setDoOutput(true);
				//请求输出的参数,提交给服务器
				PrintWriter out=new PrintWriter(connection.getOutputStream());
				boolean first=true;
				for (Map.Entry<String, String> pair : nameValueParis.entrySet()) {
					//拼接参数，
					if (first) {
						first=false;
					}else {
						out.print('&');
					}
					String name=pair.getKey();
					String value=pair.getValue();
					out.print(name);
					out.print('=');
					out.print(URLEncoder.encode(value,"UTF-8"));//URLEncoder 字符加密，转义
				}
			}
			String encoding=connection.getContentEncoding();
			if (encoding==null) {
				encoding="UTF-8";
			}
			if (redirects>0) {
				int responseCode=connection.getResponseCode(); //得到一些返回码
				System.out.println("responseCode:"+responseCode);
				if (responseCode==HttpURLConnection.HTTP_MOVED_PERM
						|| responseCode == HttpURLConnection.HTTP_MOVED_TEMP
						|| responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
					String location=connection.getHeaderField("location");
					if (location !=null) {
						URL base=connection.getURL();
						connection.disconnect();
						return dopost(new URL(base,location), nameValueParis, userAgent, redirects -1);
					}
				}
			}
			else if (redirects ==0) {
				throw new IOException("Too many redrects");
			}
			//接下来写获取html内容
			
			
			Scanner in=new Scanner(connection.getInputStream(),encoding);
			while (in.hasNextLine()) {
				response.append(in.nextLine());
				response.append("\n");
			}
		}
		return response.toString();
	}
}
