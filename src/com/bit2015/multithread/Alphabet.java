package com.bit2015.multithread;

public class Alphabet {
	public void print(){
		
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.print( " "+c );
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		
	}
	
}
