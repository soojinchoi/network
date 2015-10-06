package com.bit2015.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			System.out.println(inetAddress.getHostName());
			System.out.println(inetAddress.getHostAddress());
			
			byte[] addresses = inetAddress.getAddress();
			
			for (int i = 0; i < addresses.length; i++) {
				int address = addresses[i] & 0xff; //바이트 사용시 부호를 없애주고 int형으로 변환
				System.out.println(address);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
	
}
