import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Worker implements Runnable{
	Socket s;
	public Worker(Socket s) {
		this.s=s;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("服务人员已启动");
		try {
			InputStream is=s.getInputStream();
			OutputStream os=s.getOutputStream();
			
			BufferedReader bf=new BufferedReader(new InputStreamReader(is));
			DataOutputStream dos=new DataOutputStream(os);
			while (true) {
				String strWord=bf.readLine();
				System.out.println("客户端说:"+strWord+":"+strWord.length());
				if (strWord.equalsIgnoreCase("quit")) {
					System.out.println("服务停止");
					break;
				}else {
					String strEcho=strWord+"777";
					System.out.println("服务器说："+strWord+"----->"+strEcho);
					dos.writeBytes(strWord+"---->"+strEcho+System.getProperty("line.seperator"));
				}
				bf.close();
				dos.close();
				is.close();
				s.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
