package com.bit2015.network.ech;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;


public class UDPEchoClient {
	
	private static final String SERVER_ADDRESS = "192.168.1.100";
	private static final int SERVER_PORT = 20000;
	private static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		
		DatagramSocket datagramSocket=null;
		Scanner scanner = null;
		
		try {
			
			//1.UDP 클라이언트 소켓 생성
			scanner = new Scanner(System.in);
			datagramSocket = new DatagramSocket();
			
			while(true){
			
			//2.데이터 보내기
			System.out.print(">>");	
			String message = scanner.nextLine();
			
			if("exit".equalsIgnoreCase(message) == true){
				System.out.println("[접속종료!]");
				break;
			}//exit를 입력하면 접속종료
			
			byte[] sendData = message.getBytes();
			
			DatagramPacket sendPacket = 
				new DatagramPacket(sendData, sendData.length, 
				new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));
			
			datagramSocket.send(sendPacket);
			
			//3.데이터 받기
			DatagramPacket receivePacket =
				new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
			datagramSocket.receive(receivePacket);
			
			//4.데이터 출력
			message = new String(	receivePacket.getData(),0,
									receivePacket.getLength(),"UTF-8");
			System.out.println("<< "+message);
			
			}
			//5.자원정리
			datagramSocket.close();
			scanner.close();
			
		} catch (IOException e) {
			log("error:"+e);
		}
	}

	private static void log(String log) {
		System.out.println("[udp-echo-client] "+log);		
	}
}
