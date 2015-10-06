package com.bit2015.network.ech;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPEcoServer {

	private static final int PORT =20000;
	private static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		DatagramSocket datagramSocket = null;
		try {
			//1. UDP 서버 소켓 생성
			datagramSocket = new DatagramSocket(PORT);
			
			//2. 수신대기
			while(true){
			log("packet 수신 대기");	
			DatagramPacket receivePacket = 
				new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
			
			datagramSocket.receive(receivePacket);
			
			//3. 수신 데이터 출력
			String message = 
			new String(	receivePacket.getData(), 0 ,
						receivePacket.getLength(),"UTF-8");//(데이터바이트를, 0번부터 인코딩해라, 데이터바이트의 길이만큼,UTF-8로~)
			log("packet 수신:"+message);
			
			//4. 데이터 보내기(답장)
			DatagramPacket sendPacket = 
				new DatagramPacket( receivePacket.getData(), 
									receivePacket.getLength(),
									receivePacket.getAddress(), 
									receivePacket.getPort());
			
			datagramSocket.send(sendPacket);
			}
		} catch (IOException e) {
			log("error:"+e);
		} finally{datagramSocket.close();}
	}	
	private static void log(String log) {
		System.out.println("[udp-echo-server] "+log);	
	}

}
