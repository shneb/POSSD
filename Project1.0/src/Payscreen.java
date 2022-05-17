import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;


public class Payscreen{
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int sWidth = screenSize.width;
	private int sHeight = screenSize.height-40;
	
	public JPanel chackoutPanel;
	private JPanel numberpadpanel;

	private JButton cashpadbutton8;
	private JButton cashpadbutton9;
	private JButton cashpadbutton4;
	private JButton cashpadbutton5;
	private JButton cashpadbutton6;
	private JButton cashpadbutton1;
	private JButton cashpadbutton2;
	private JButton cashpadbutton3;
	private JButton chapadbutton0;
	private JButton cashpadbuttonfullstop;
	private JButton cashpadbuttondelete;
	private final JTextField cashdisplaybox = new JTextField();
	private JTextField textField_2;
	private JTextField totaltextbox;
	
	 private JFrame mainstage  = new JFrame();

	//private Order order;

	public Payscreen() {
		super();
		initialize();
	}

	private void initialize() {
		chackoutPanel();
		

	}
	
	public void chackoutPanel() {
		
		mainstage.getContentPane().setBackground(new Color(196, 196, 196));
		mainstage.setBounds(0, 0, sWidth, sHeight);
		mainstage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainstage.getContentPane().setLayout(null);
		mainstage.setVisible(true);
		
		chackoutPanel = new JPanel();
		chackoutPanel.setLayout(null);
		chackoutPanel.setBackground(new Color(196,196,196));
		chackoutPanel.setBorder(null);
		chackoutPanel.setBounds(0, 0, sWidth, sHeight);
		chackoutPanel.setVisible(false);
		mainstage.getContentPane().add(chackoutPanel);
		numberPadPanel();
		initializeOld();
		numberPad();
	}
	public void numberPadPanel() {
		numberpadpanel = new JPanel();
		numberpadpanel.setBackground(new Color(169, 169, 169));
		numberpadpanel.setBorder(null);
		numberpadpanel.setBounds(sWidth/3, sHeight/3, 674, 458);
		numberpadpanel.setLayout(null);
		chackoutPanel.add(numberpadpanel);
	}

		private void initializeOld() {


			cashdisplaybox.setBounds(435, 140, 182, 29);
			numberpadpanel.add(cashdisplaybox);
			cashdisplaybox.setFont(new Font("Tahoma", Font.BOLD, 24));
			cashdisplaybox.setColumns(10);
			
			textField_2 = new JTextField();
			textField_2.setFont(new Font("Tahoma", Font.BOLD, 24));
			textField_2.setColumns(10);
			textField_2.setBounds(435, 194, 182, 29);
			numberpadpanel.add(textField_2);
			
			JLabel Cahslabel = new JLabel("Cash:");
			Cahslabel.setBounds(346, 140, 79, 29);
			numberpadpanel.add(Cahslabel);
			
			JLabel changelabel = new JLabel("Change:");
			changelabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			changelabel.setBounds(333, 198, 92, 29);
			numberpadpanel.add(changelabel);
			
			JButton paybutton2 = new JButton("Pay!");
			paybutton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changecal();
				}
			});
			paybutton2.setBounds(376, 291, 89, 23);
			numberpadpanel.add(paybutton2);
			
			JButton exitbutton = new JButton("Finished");
			exitbutton.setBounds(503, 291, 89, 23);
			numberpadpanel.add(exitbutton);
			
			totaltextbox = new JTextField();
			totaltextbox.setText(Double.toString(Pos.orderTotal));
			totaltextbox.setBounds(435, 85, 182, 29);
			numberpadpanel.add(totaltextbox);
			totaltextbox.setColumns(10);
			
			JLabel totallabel = new JLabel("Total:");
			totallabel.setBounds(321, 91, 46, 14);
			numberpadpanel.add(totallabel);
			exitbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			ImageIcon addIcon = new ImageIcon("Icons/printer.png");
			JButton printbutton = new JButton(addIcon);
			printbutton.setBackground(new Color(255, 255, 255));
			printbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					MessageFormat header = new MessageFormat("POSSD Pizza & Burger \n 10 Downing Street \nPS6 9SM \n" );

					MessageFormat footer = new MessageFormat("Total: " + Pos.orderTotal);
					 
					try
					{
						Pos.table.print(JTable.PrintMode.NORMAL,header,footer);
					}
					
					catch(java.awt.print.PrinterException e1)
					{
						System.err.format("No Printer found", e1.getMessage() );
					}
					
				}
			});
			printbutton.setFont(new Font("Tahoma", Font.BOLD, 24));
			printbutton.setBounds(numberpadpanel.getWidth()-50, numberpadpanel.getHeight()-50, 50, 50);
			numberpadpanel.add(printbutton);
		}

		
		public void changecal() 
		{

			double cash = Double.parseDouble(cashdisplaybox.getText());
			
			double Change = (cash - Pos.orderTotal);
			
			String Changeneeded = String.format("£ %.2f", Change);
			textField_2.setText("");
			textField_2.setText(Changeneeded);
		}
		public void numberPad() {
			JButton chapadbutton7 = new JButton("7");
			chapadbutton7.setBackground(new Color(255, 153, 102));
			chapadbutton7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Enternumber=cashdisplaybox.getText();
					
					if (Enternumber == "")
					{
						cashdisplaybox.setText(chapadbutton7.getText());
					}
					else 
					{
						Enternumber = cashdisplaybox.getText() + chapadbutton7.getText();
						cashdisplaybox.setText(Enternumber);
					}
				}
			});
			chapadbutton7.setFont(new Font("Tahoma", Font.BOLD, 36));
			chapadbutton7.setBounds(10, 11, 79, 94);
			numberpadpanel.add(chapadbutton7);
			
			cashpadbutton8 = new JButton("8");
			cashpadbutton8.setBackground(new Color(255, 153, 102));
			cashpadbutton8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Enternumber=cashdisplaybox.getText();
					
					if (Enternumber == "")
					{
						cashdisplaybox.setText(cashpadbutton8.getText());
					}
					else 
					{
						Enternumber = cashdisplaybox.getText() + cashpadbutton8.getText();
						cashdisplaybox.setText(Enternumber);
					}
				}
			});
			cashpadbutton8.setFont(new Font("Tahoma", Font.BOLD, 36));
			cashpadbutton8.setBounds(99, 11, 79, 94);
			numberpadpanel.add(cashpadbutton8);
			
			cashpadbutton9 = new JButton("9");
			cashpadbutton9.setBackground(new Color(255, 153, 102));
			cashpadbutton9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Enternumber=cashdisplaybox.getText();
					
					if (Enternumber == "")
					{
						cashdisplaybox.setText(cashpadbutton9.getText());
					}
					else 
					{
						Enternumber = cashdisplaybox.getText() + cashpadbutton9.getText();
						cashdisplaybox.setText(Enternumber);
					}
				}
			});
			cashpadbutton9.setFont(new Font("Tahoma", Font.BOLD, 36));
			cashpadbutton9.setBounds(188, 11, 79, 94);
			numberpadpanel.add(cashpadbutton9);
			
			cashpadbutton4 = new JButton("4");
			cashpadbutton4.setBackground(new Color(255, 153, 102));
			cashpadbutton4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Enternumber=cashdisplaybox.getText();
					
					if (Enternumber == "")
					{
						cashdisplaybox.setText(cashpadbutton4.getText());
					}
					else 
					{
						Enternumber = cashdisplaybox.getText() + cashpadbutton4.getText();
						cashdisplaybox.setText(Enternumber);
					}
				}
			});
			cashpadbutton4.setFont(new Font("Tahoma", Font.BOLD, 36));
			cashpadbutton4.setBounds(10, 115, 79, 94);
			numberpadpanel.add(cashpadbutton4);
			
			cashpadbutton5 = new JButton("5");
			cashpadbutton5.setBackground(new Color(255, 153, 102));
			cashpadbutton5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Enternumber=cashdisplaybox.getText();
					
					if (Enternumber == "")
					{
						cashdisplaybox.setText(cashpadbutton5.getText());
					}
					else 
					{
						Enternumber = cashdisplaybox.getText() + cashpadbutton5.getText();
						cashdisplaybox.setText(Enternumber);
					}
				}
			});
			cashpadbutton5.setFont(new Font("Tahoma", Font.BOLD, 36));
			cashpadbutton5.setBounds(99, 116, 79, 94);
			numberpadpanel.add(cashpadbutton5);
			
			cashpadbutton6 = new JButton("6");
			cashpadbutton6.setBackground(new Color(255, 153, 102));
			cashpadbutton6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Enternumber=cashdisplaybox.getText();
					
					if (Enternumber == "")
					{
						cashdisplaybox.setText(cashpadbutton6.getText());
					}
					else 
					{
						Enternumber = cashdisplaybox.getText() + cashpadbutton6.getText();
						cashdisplaybox.setText(Enternumber);
					}
				}
			});
			cashpadbutton6.setFont(new Font("Tahoma", Font.BOLD, 36));
			cashpadbutton6.setBounds(188, 115, 79, 94);
			numberpadpanel.add(cashpadbutton6);
			
			cashpadbutton1 = new JButton("1");
			cashpadbutton1.setBackground(new Color(255, 153, 102));
			cashpadbutton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Enternumber=cashdisplaybox.getText();
					
					if (Enternumber == "")
					{
						cashdisplaybox.setText(cashpadbutton1.getText());
					}
					else 
					{
						Enternumber = cashdisplaybox.getText() + cashpadbutton1.getText();
						cashdisplaybox.setText(Enternumber);
					}
				}
			});  
			cashpadbutton1.setFont(new Font("Tahoma", Font.BOLD, 36));
			cashpadbutton1.setBounds(10, 220, 79, 94);
			numberpadpanel.add(cashpadbutton1);
			
			cashpadbutton2 = new JButton("2");
			cashpadbutton2.setBackground(new Color(255, 153, 102));
			cashpadbutton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Enternumber=cashdisplaybox.getText();
					
					if (Enternumber == "")
					{
						cashdisplaybox.setText(cashpadbutton2.getText());
					}
					else 
					{
						Enternumber = cashdisplaybox.getText() + cashpadbutton2.getText();
						cashdisplaybox.setText(Enternumber);
					}
				}
			});
			cashpadbutton2.setFont(new Font("Tahoma", Font.BOLD, 36));
			cashpadbutton2.setBounds(99, 220, 79, 94);
			numberpadpanel.add(cashpadbutton2);
			
			cashpadbutton3 = new JButton("3");
			cashpadbutton3.setBackground(new Color(255, 153, 102));
			cashpadbutton3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Enternumber=cashdisplaybox.getText();
					
					if (Enternumber == "")
					{
						cashdisplaybox.setText(cashpadbutton3.getText());
					}
					else 
					{
						Enternumber = cashdisplaybox.getText() + cashpadbutton3.getText();
						cashdisplaybox.setText(Enternumber);
					}
				}
			});
			cashpadbutton3.setFont(new Font("Tahoma", Font.BOLD, 36));
			cashpadbutton3.setBounds(188, 220, 79, 94);
			numberpadpanel.add(cashpadbutton3);
			
			chapadbutton0 = new JButton("0");
			chapadbutton0.setBackground(new Color(255, 153, 102));
			chapadbutton0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String Enternumber=cashdisplaybox.getText();
					
					if (Enternumber == "")
					{
						cashdisplaybox.setText(chapadbutton0.getText());
					}
					else 
					{
						Enternumber = cashdisplaybox.getText() + chapadbutton0.getText();
						cashdisplaybox.setText(Enternumber);
					}
				}
			});
			chapadbutton0.setFont(new Font("Tahoma", Font.BOLD, 36));
			chapadbutton0.setBounds(10, 325, 79, 94);
			numberpadpanel.add(chapadbutton0);
			
			cashpadbuttonfullstop = new JButton(".");
			cashpadbuttonfullstop.setBackground(new Color(255, 153, 102));
			cashpadbuttonfullstop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (! cashdisplaybox.getText().contains("."))
					{
						cashdisplaybox.setText(cashdisplaybox.getText() + cashpadbuttonfullstop.getText());
					}
				}
			});
			cashpadbuttonfullstop.setFont(new Font("Tahoma", Font.BOLD, 36));
			cashpadbuttonfullstop.setBounds(99, 325, 79, 94);
			numberpadpanel.add(cashpadbuttonfullstop);
			
			cashpadbuttondelete = new JButton("c");
			cashpadbuttondelete.setBackground(new Color(255, 153, 102));
			cashpadbuttondelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cashdisplaybox.setText("");
					cashdisplaybox.setText("");
				}
			});
			cashpadbuttondelete.setFont(new Font("Tahoma", Font.BOLD, 36));
			cashpadbuttondelete.setBounds(188, 325, 79, 94);
			numberpadpanel.add(cashpadbuttondelete);
			cashdisplaybox.setBounds(435, 140, 182, 29);
			numberpadpanel.add(cashdisplaybox);
			cashdisplaybox.setFont(new Font("Tahoma", Font.BOLD, 24));
			cashdisplaybox.setColumns(10);
			cashpadbuttondelete.setFont(new Font("Tahoma", Font.BOLD, 36));
			cashpadbuttondelete.setBounds(188, 325, 79, 94);
			numberpadpanel.add(cashpadbuttondelete);
			
		}
	}

