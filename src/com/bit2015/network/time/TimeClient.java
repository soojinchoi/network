package com.bit2015.network.time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class TimeClient {

	private static final int SERVER_PORT = 50000;
	private static final String SERVER_IP = "192.168.1.100";
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		
		DatagramSocket datagramSocket=null;
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(System.in);
			//1.UDP 클라이언트 소켓 생성
			datagramSocket = new DatagramSocket();
			
			while(true){
			//2.데이터 보내기
			System.out.print(">>");	
			String message = scanner.nextLine();
			if("exit".equalsIgnoreCase(message) == true){
				System.out.println("[접속종료!]");
				break;
			}//exit를 입력하면 접속종료
			
			byte[] data = "".getBytes();
		
			DatagramPacket sendPacket = 
					new DatagramPacket(data, data.length, 
					new InetSocketAddress(SERVER_IP,SERVER_PORT));
			datagramSocket.send(sendPacket);
		
			//3.시간 데이터 받기
			DatagramPacket receivePacket =
					new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
			
			datagramSocket.receive(receivePacket);
			
			//4.시간 출력
			String date = new String(receivePacket.getData(), 0 ,
									 receivePacket.getLength(),"UTF-8");
			System.out.println(date);
			}
			
			
		} catch (IOException e) {
			log("error:"+e);
		}finally{datagramSocket.close();}
		
		
		
	}

	private static void log(String log) {
		System.out.println("[Time_Server_error] "+log);	
		
	}

}
