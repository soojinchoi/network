package com.bit2015.network.ech;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchClient {
   private static final String SERVER_ADDRESS = "192.168.1.99";
   private static final int SERVER_PORT = 10001;

   public static void main(String[] args) {
      // TODO Auto-generated method stub

      Socket socket = null; 
      BufferedReader reader = null;
      PrintWriter printWriter = null;
//      Scanner scanner = null;

      try {
    	 
//       scanner = new Scanner(System.in);
 
         socket = new Socket();
         socket.connect(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));

         // 쓰고/받기
         
//  	 OutputStream os =null;
//  	 InputStream is =null;
         
        

         while (true) {
//        	os = socket.getOutputStream();
//        	is = socket.getInputStream();
        	
        	reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
            
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); 
            System.out.print(">>");
//          String data = scanner.nextLine();
            
            String data = keyboard.readLine();
            
            if("exit".equalsIgnoreCase(data) == true){
				System.out.println("[접속종료!]");
				break;
			}
            data += "\n";
//            os.write(data.getBytes("UTF-8"));;
            printWriter.println(data);
            printWriter.flush();
            
 //          byte[] buffer = new byte[128];
 //           int readByteCount = is.read(buffer);
 //           data = new String(buffer, 0, readByteCount, "UTF-8");
 //           System.out.print("<<" + data);
         }
         	reader.close();
//         	printWriter.close();
//         	os.close();
//         	is.close();
         if (socket.isClosed() == false) {
            socket.close();
         }

      } catch (IOException e) {
         System.out.println("<<에러: " + e);
      }
   }
}