
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLConnectionGetTest {
	public static void main(String[] args) throws IOException {
		String urlName="http://www.baidu.com";
		
		try {
			URL rUrl=new URL(urlName);
			URLConnection connection=rUrl.openConnection();
			connection.connect();
			/**
			 * 映射项（键-值对）。Map.entrySet 方法返回映射的 collection 视图，其中的元素属于此类。
			 * 获得映射项引用的唯一 方法是通过此 collection 视图的迭代器来实现。
			 * 这些 Map.Entry 对象仅 在迭代期间有效；更确切地讲，如果在迭代器返回项之后修改了底层映射，则某些映射项的行为是不确定的，除了通过 setValue 在映射项上执行操作之外。 
			 */
			//打印http的头部信息
			Map<String, List<String>> headerMap=connection.getHeaderFields();
			for (Map.Entry<String, List<String>> entry : headerMap.entrySet()) {
				String key=entry.getKey();
				for (String value : entry.getValue()) {
					System.out.println(key+":"+value);
				}
				System.out.println("---------------");
				System.out.println("getContentType:"+connection.getContentType());
				System.out.println("getContentLength:"+connection.getContentLength());
				System.out.println("getContentEncoding:"+connection.getContentEncoding());
				System.out.println("getDate:"+connection.getDate());
				System.out.println("getExpiration:"+connection.getExpiration());
				System.out.println("getLastModifed:"+connection.getLastModified());
				
				BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
				//输出收到的内容
				
				String line="";
				while ((line=br.readLine())!=null) {
					System.out.println(line);
				}
				br.close();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
