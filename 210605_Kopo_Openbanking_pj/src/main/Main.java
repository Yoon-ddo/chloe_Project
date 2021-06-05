package main;

import UI.BankSysUI;

public class Main {
	public static void main(String[] args) {
		BankSysUI ui = new BankSysUI();
		try {
			ui.execute();
		} catch (Exception e) {
			//여기서 예외처리 수행해줌.
			e.printStackTrace();
		} 
		
		
	}


		
}

