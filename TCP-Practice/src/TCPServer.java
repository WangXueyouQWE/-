import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8001);
			System.out.println("�ȴ��ͻ�������");
			Socket s = ss.accept(); // �������ȵ��пͻ�������������
			System.out.println("��ӭ����Java����");
			InputStream ips = s.getInputStream(); // ����������������������
			OutputStream ops = s.getOutputStream();
			ops.write("��ã��ͻ��ˣ�".getBytes()); // ���һ�仰���ͻ���
			/**
			 * getBytes() 
          	ʹ��ƽ̨��Ĭ���ַ������� String ����Ϊ byte ���У���������洢��һ���µ� byte �����С� 
			 */
			BufferedReader br = new BufferedReader(new InputStreamReader(ips));
			// �ӿͻ��˶�ȡĳ�仰
			//readLine()�Ƕ�ȡ�������ݵ�ʱ���õģ�ͬʱ�����ַ�����ʽ������һ�е����ݣ�����ȡ�����е�����ʱ�᷵��null��
			System.out.println("�ͻ���˵��" + br.readLine());
			
			ips.close();
			ops.close();
			s.close();
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
