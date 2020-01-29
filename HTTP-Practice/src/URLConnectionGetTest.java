
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
			 * ӳ�����-ֵ�ԣ���Map.entrySet ��������ӳ��� collection ��ͼ�����е�Ԫ�����ڴ��ࡣ
			 * ���ӳ�������õ�Ψһ ������ͨ���� collection ��ͼ�ĵ�������ʵ�֡�
			 * ��Щ Map.Entry ����� �ڵ����ڼ���Ч����ȷ�еؽ�������ڵ�����������֮���޸��˵ײ�ӳ�䣬��ĳЩӳ�������Ϊ�ǲ�ȷ���ģ�����ͨ�� setValue ��ӳ������ִ�в���֮�⡣ 
			 */
			//��ӡhttp��ͷ����Ϣ
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
				//����յ�������
				
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
