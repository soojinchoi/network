package com.bit2015.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class ChatServerProcessThread extends Thread {
	
	private static final String PROTOCOOL_DRIVER=":";
	private String nickname;
	private Socket socket;
	private List<PrintWriter> listPrintWriters;
	
	public ChatServerProcessThread(Socket socket, List<PrintWriter> listPrintWriters) {
		this.socket = socket;
		this.listPrintWriters = listPrintWriters;
	}

	@Override
	public void run() {
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;
		
		try{
			
			//1.스트림 얻기
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			printWriter = new PrintWriter(socket.getOutputStream());
			
			//2.Remote host 정보 얻기
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			int remoteHostPort = inetSocketAddress.getPort();
			ChatServer.log("연결됨 from" + inetSocketAddress.getHostName() + ":" + remoteHostPort);
			
			//3.요청처리
			while(true){
				String request = bufferedReader.readLine();
				if(request == null){
					ChatServer.log("클라이언트로 부터 연결 끊김");
					break;
				}
				
				String [] tokens = request.split(PROTOCOOL_DRIVER);
				if("join".equals(tokens[0])){
					doJoin(printWriter,tokens[1]);
				}else{
					ChatServer.log("error:알 수 없는 요청명령("+tokens[0]+")");
				}
			}
			//4.자원정리
			bufferedReader.close();
			printWriter.close();
			if(socket.isClosed() == false){
				socket.close();
			}
		}catch(IOException ex){
			ChatServer.log("error:"+ex);
		}
	}
	
	private void doJoin(PrintWriter printWriter, String nickname){
		//1.닉네임 저장
		this.nickname = nickname;
		//2.메세지 브로드캐스팅
		String message = nickname +"님이 입장했습니다.";
		broadcast(message);
		//3.
		addPrintWriter(printWriter);
	}
	private void addPrintWriter(PrintWriter printWriter){
		synchronized (listPrintWriters) {	
			listPrintWriters.add(printWriter);		
		}
	}
	private void broadcast(String data){
		
//		for(PrintWriter printWriter : listPrintWriters){
//			printWriter.println(data);}
	
		int count = listPrintWriters.size();
		for (int i = 0; i < count; i++) {
			PrintWriter printWriter = listPrintWriters.get(i);
			printWriter.println(data);
			printWriter.flush();
		}
	}
}
