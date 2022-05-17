
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Pos();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}
	
	

}
