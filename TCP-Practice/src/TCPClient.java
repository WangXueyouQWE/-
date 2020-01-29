import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	public static void main(String[] args) {
		try {
			Socket s=new Socket(InetAddress.getByName("192.168.101.101"),8001);
			
			InputStream ip=s.getInputStream();
			BufferedReader brNet=new BufferedReader(new InputStreamReader(ip));
			
			OutputStream op=s.getOutputStream();
			DataOutputStream dos=new DataOutputStream(op);
			
			BufferedReader brKey=new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				String strWord=brKey.readLine();
				if (strWord.equalsIgnoreCase("quit")) {
					System.out.println("�ر�����");
					break;
				}else {
					System.out.println("����Ҫ���ͣ�"+strWord);
					dos.writeBytes(strWord+System.getProperty("line.separator"));
					//getProperty(String key) ��ȡָ����ָʾ��ϵͳ���ԡ�
			          
					System.out.println("������˵��"+brNet.readLine());
				}
			}
			dos.close();
			brNet.close();
			brKey.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
