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
			System.out.println("等待客户端输入");
			Socket s = ss.accept(); // 阻塞，等到有客户端连接上来。
			System.out.println("欢迎来到Java世界");
			InputStream ips = s.getInputStream(); // 有人连接上来，打开输入流
			OutputStream ops = s.getOutputStream();
			ops.write("你好，客户端！".getBytes()); // 输出一句话给客户端
			/**
			 * getBytes() 
          	使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。 
			 */
			BufferedReader br = new BufferedReader(new InputStreamReader(ips));
			// 从客户端读取某句话
			//readLine()是读取流读数据的时候用的，同时会以字符串形式返回这一行的数据，当读取完所有的数据时会返回null。
			System.out.println("客户端说：" + br.readLine());
			
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
