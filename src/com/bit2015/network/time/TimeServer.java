package com.bit2015.network.time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {

	private static final int PORT = 50000;
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		
		
		DatagramSocket datagramSocket = null;
		
		
		try {
			//1.UDP 서버 소켓 생성
			datagramSocket = new DatagramSocket(PORT);
			while(true){
			//2.수신 대기
			log("수신대기");
			DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
			datagramSocket.receive(receivePacket);
			
			//3.시간 출력
//			String date = new String(receivePacket.getData(),0,
//									 receivePacket.getLength(),"UTF-8");
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss a" );
			String data = format.format( new Date() );
			log("시간: "+data);
			
			//4.데이터 보내기

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
		System.out.println("[Time_Server_error] "+log);	
	}

}
