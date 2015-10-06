package com.bit2015.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {		
		
		try {
			Scanner scan = new Scanner(System.in);
			
			while(true){
		
				System.out.print(">");
				
				String host = scan.nextLine();
				
				if("exit".equals(host)==true){
					break;
				}
				
				InetAddress[] inetAddress = InetAddress.getAllByName(host);
				for (int i = 0; i < inetAddress.length; i++) {
					System.out.println(inetAddress[i].getHostName()+":"+inetAddress[i].getHostAddress());
				}
				
			}scan.close();
			} catch (UnknownHostException e) {
				System.out.println("IP를 가져올 수 없습니다.");
		}
	}
}
