import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer2 {
	public static void main(String[] args) {
		try {
			ServerSocket ss=new ServerSocket(8001);
			while (true) {
				Socket s=ss.accept();   //accept() ,���������ܵ����׽��ֵ����ӡ�
				System.out.println("����һ���ͻ���");
				new Thread(new Worker(s)).start();;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
