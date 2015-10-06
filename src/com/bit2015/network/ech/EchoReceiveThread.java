package com.bit2015.network.ech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoReceiveThread extends Thread {

	private Socket socket;
	
	public EchoReceiveThread(Socket socket){
		this.socket = socket ;
	}
	
	//run 구현
	@Override
	public void run() {
		  // 4. 데이터 읽고 쓰기
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socket
              .getRemoteSocketAddress();
        System.out.println("[서버] 연결됨 from: "
              + inetSocketAddress.getHostName() + " : "
              + inetSocketAddress.getPort());
        
        BufferedReader reader = null;
        PrintWriter printWriter = null;
   
        try {
 
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
            
           while (true) {
              
              String data = reader.readLine();
              if(data == null){
            	  break;
              }
              System.out.println("[서버] 데이터 수신: "+data);

              printWriter.println( data );
              printWriter.flush();
           }
           
           reader.close();
           printWriter.close();
           
           if(socket.isClosed()==false){
              socket.close();
           }
        } catch (IOException e) {
           System.out.println("[서버] 애러: " + e);
        }
	}
	
}
