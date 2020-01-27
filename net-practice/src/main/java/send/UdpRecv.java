package send;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpRecv {
	public static void main(String[] args) throws Exception {
		/**
		 * ������Ϣ
		 * DatagramSocket(int port) 
          �������ݱ��׽��ֲ�����󶨵����������ϵ�ָ���˿ڡ�
		 */
		DatagramSocket ds=new DatagramSocket(3000);
		
		byte[] buf=new byte[1024];
		DatagramPacket dp=new DatagramPacket(buf, 1024);
		System.out.println("UdpRecv:���ڵȴ���Ϣ");
		
		ds.receive(dp);
		System.out.println("UdpRecv:�ҽ��յ���Ϣ");
		
		String strRecv=new String(dp.getData(),0,dp.getLength())+"from"+
		dp.getAddress()  //getAddress()  ����ĳ̨������ IP ��ַ�������ݱ���Ҫ�����û��������ǴӸû������յ��ġ�
		.getHostAddress()+":"+
		dp.getPort();  //getPort() ����ĳ̨Զ�������Ķ˿ںţ������ݱ���Ҫ���������������ǴӸ��������յ��ġ�
		System.out.println(strRecv);
		
		Thread.sleep(1000);
		System.out.println("UdpRecv:��Ҫ������Ϣ");
		
		String str="hello word 123";
		DatagramPacket dp2=new DatagramPacket(str.getBytes(), str.length(),
				InetAddress.getByName("192.168.101.101"),dp.getPort());  //59675
		ds.send(dp2);
		System.out.println("UdpRecv:�ҷ�����Ϣ����");
		ds.close();
	}

}
