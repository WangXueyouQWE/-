package send;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpRecv {
	public static void main(String[] args) throws Exception {
		/**
		 * 接收信息
		 * DatagramSocket(int port) 
          创建数据报套接字并将其绑定到本地主机上的指定端口。
		 */
		DatagramSocket ds=new DatagramSocket(3000);
		
		byte[] buf=new byte[1024];
		DatagramPacket dp=new DatagramPacket(buf, 1024);
		System.out.println("UdpRecv:我在等待信息");
		
		ds.receive(dp);
		System.out.println("UdpRecv:我接收到信息");
		
		String strRecv=new String(dp.getData(),0,dp.getLength())+"from"+
		dp.getAddress()  //getAddress()  返回某台机器的 IP 地址，此数据报将要发往该机器或者是从该机器接收到的。
		.getHostAddress()+":"+
		dp.getPort();  //getPort() 返回某台远程主机的端口号，此数据报将要发往该主机或者是从该主机接收到的。
		System.out.println(strRecv);
		
		Thread.sleep(1000);
		System.out.println("UdpRecv:我要发送信息");
		
		String str="hello word 123";
		DatagramPacket dp2=new DatagramPacket(str.getBytes(), str.length(),
				InetAddress.getByName("192.168.101.101"),dp.getPort());  //59675
		ds.send(dp2);
		System.out.println("UdpRecv:我发送信息结束");
		ds.close();
	}

}
