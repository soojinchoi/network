package com.bit2015.network.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

	private static final int PORT = 30000;
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		List<PrintWriter> listPrintWriters = new ArrayList<PrintWriter>();
		try{
			//1.서버소켓생성
			serverSocket = new ServerSocket();
			
			//2.바인딩
			String hostAddress = InetAddress.getLocalHost().getHostAddress(); //IP를 지정하지 않고, 해당하는 PC의 IP를 자동으로 받아올 수 있다.  
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			
			log("연결 기다림"+ hostAddress + ":" + PORT);
			
			
			//3.연결요청 기다림
			while (true){
				Socket socket = serverSocket.accept();
				
				Thread thread = new ChatServerProcessThread(socket,listPrintWriters);
				thread.start();
			}
		}catch(IOException ex){
			log( "error:"+ ex );
		}finally{
			if(serverSocket != null && serverSocket.isClosed() == false){
				try {
					serverSocket.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	public static void log( String log ){
		System.out.println("[chat-server]"+log);
	}

}
