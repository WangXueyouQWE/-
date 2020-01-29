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
 * CookieHandler �����ṩһ�ֻص������Խ� HTTP ״̬�������ʵ�ֹҹ��� HTTP Э�鴦�����
 * HTTP ״̬����������� HTTP �������Ӧָ��������״̬�ĻỰ�ķ�ʽ�� 
 * ����ͨ��ִ�� CookieHandler.setDefault(CookieHandler) ��ע�� HTTP Э�鴦�����Ҫʹ�õ�ϵͳ�� CookieHandler��
 * ���� CookieHandler.getDefault() ���Ի�ȡ��ǰע��� CookieHandler��
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
				//��������Ĳ���,�ύ��������
				PrintWriter out=new PrintWriter(connection.getOutputStream());
				boolean first=true;
				for (Map.Entry<String, String> pair : nameValueParis.entrySet()) {
					//ƴ�Ӳ�����
					if (first) {
						first=false;
					}else {
						out.print('&');
					}
					String name=pair.getKey();
					String value=pair.getValue();
					out.print(name);
					out.print('=');
					out.print(URLEncoder.encode(value,"UTF-8"));//URLEncoder �ַ����ܣ�ת��
				}
			}
			String encoding=connection.getContentEncoding();
			if (encoding==null) {
				encoding="UTF-8";
			}
			if (redirects>0) {
				int responseCode=connection.getResponseCode(); //�õ�һЩ������
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
			//������д��ȡhtml����
			
			
			Scanner in=new Scanner(connection.getInputStream(),encoding);
			while (in.hasNextLine()) {
				response.append(in.nextLine());
				response.append("\n");
			}
		}
		return response.toString();
	}
}
