package com.bit2015.network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
   private static final int PORT = 10002;

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      ServerSocket serverSocket = null;
      try {
         // 1.서버 소켓 생성
         serverSocket = new ServerSocket();

         // 2.바인딩
         InetAddress inetAddress = InetAddress.getLocalHost();
         String hostAddress = inetAddress.getHostAddress();
         serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
         System.out.println("[서버] 바인딩   " + hostAddress + ":" + PORT);

         
         // 3.연결 요청 대기
         System.out.println("[서버] 연결 기다림");
         Socket socket = serverSocket.accept();

       

      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (serverSocket != null && serverSocket.isClosed() == false) {
            try {
               serverSocket.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
   }
}