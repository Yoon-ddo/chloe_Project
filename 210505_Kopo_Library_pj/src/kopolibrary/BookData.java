package kopolibrary;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookData{
	Scanner sc = new Scanner(System.in);
	static Map<String, ArrayList> EnrollUser = new HashMap<>();
	static Map<String, ArrayList> EnrollBooks = new HashMap<>();
	static ArrayList<String> notice = new ArrayList<String>();
	
	static boolean userloginfo=false;
	static boolean adminloginfo=false;
	
	BookData(){	
	}
	
	
	
	public int getNum() {
		int num = sc.nextInt();
		return num;
	}
	public int getNum(String msg) {
		System.out.print(msg);
		int num = sc.nextInt();
		return num;
	}
	
	public String getStr() {
		String str = sc.next();
		return str;
	}
	public String getStr(String msg) {
		System.out.print(msg);
		String str = sc.next();
		return str;
	}
	
	
	
	
	
	
	

}
