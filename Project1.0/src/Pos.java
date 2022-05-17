import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;



public class Pos {
	
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int sWidth = screenSize.width;
	private int sHeight = screenSize.height-40;
	
	private ArrayList<JButton> catButtons = new ArrayList<>();
	private ArrayList<JButton> itemButtons = new ArrayList<>();
	
	private JPanel makingOrderPanel;
	private ArrayList<JPanel> itemsContainer = new ArrayList<>();
	private JPanel calcPanel;
	public static JTable table;
	private JPanel sideBar;
	private JPanel infoContainer;
	private JPanel basketContainer;
	private JPanel toolsContainer;

	private JScrollPane basket;
	
	private JTextField subtotaltextbox;
	private JTextField taxdisplaybox;
	private JTextField totaldisplaybox;
	
	private Icon deleteIcon;
	private Icon addIcon;
	
	public static double orderTotal;
		
	 Order order = new Order();
	public JFrame mainstage  = new JFrame();

	
	public Pos() {

		initialize();
	}
	 
	private void initialize() {
		mainstage.getContentPane().setBackground(new Color(196, 196, 196));
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		mainstage.setBounds(0, 0, sWidth, sHeight);
		
		mainstage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainstage.getContentPane().setLayout(null);
		
		mainstage.setVisible(true);

		makingOrder();
		
		
		//makingOrderPanel.setVisible(false);
	}

	public void payscreen(Boolean visible) {
		Payscreen payscreen = new Payscreen();
		payscreen.chackoutPanel.setVisible(visible);

	}
	public void makingOrder() {
		
		makingOrderPanel = new JPanel();
		makingOrderPanel.setLayout(null);
		makingOrderPanel.setBackground(new Color(196,196,196));
		makingOrderPanel.setBorder(null);
		makingOrderPanel.setBounds(0, 0, sWidth, sHeight);
		mainstage.getContentPane().add(makingOrderPanel);
		sideBar();
		itemsContainer();
		infoContainer();
		basketContainer();
		categories();
		basket();
		items();
		toolsContainer();
		removeItemFromBasket();
		quantityBtn();
		payBtn();
		
	}
	
	
	
	public void sideBar() {
		
		sideBar = new JPanel();
		sideBar.setLayout(new GridLayout(10,1,20,20));
		sideBar.setBackground(new Color(250, 250, 250));
		sideBar.setBorder(null);
		sideBar.setBounds(0, 0, 160, sHeight);
		makingOrderPanel.add(sideBar);
	}
	
	public void itemsContainer() {
	try (DB db = new DB()) {
		    
		for(int i = 0; i < db.getCategories().size(); i++) {
			itemsContainer.add(new JPanel());
			itemsContainer.get(i).setLayout(new GridLayout(3,3,20,20));
			itemsContainer.get(i).setBackground(new Color(250, 250, 250));
			itemsContainer.get(i).setBorder(null);
			itemsContainer.get(i).setBounds(300, 250, 1100, 800);
			itemsContainer.get(i).putClientProperty("id", db.getCategories().get(i).getCategoryId());
			itemsContainer.get(i).setVisible(false);
			makingOrderPanel.add(itemsContainer.get(i));
			}
		itemsContainer.get(0).setVisible(true);
		}
	}
	
	public void infoContainer() {
		
		infoContainer = new JPanel();
		infoContainer.setBackground(new Color(250, 250, 250));
		infoContainer.setBorder(null);
		infoContainer.setBounds(300, 40, 1550 , 140);
		infoContainer.setLayout(null);
		makingOrderPanel.add(infoContainer);
	}
	
	public void basketContainer() {
		
		basketContainer = new JPanel();
		basketContainer.setBackground(new Color(250, 250, 250));
		basketContainer.setBorder(null);
		basketContainer.setBounds(1450, 250, 429 , 865);
		basketContainer.setLayout(null);
		makingOrderPanel.add(basketContainer);
	}
	
	public void toolsContainer() {
		
		toolsContainer = new JPanel();
		toolsContainer.setBackground(new Color(255, 255, 255));
		toolsContainer.setBorder(null);
		toolsContainer.setBounds(0, 550, basketContainer.getWidth(), 90);
		toolsContainer.setLayout(new GridLayout(1,0,20 ,20));
		basketContainer.add(toolsContainer);
	}
	
	public void items() {
		
		try (DB db = new DB()) {
		    
		    for(int i = 0; i < db.getItems().size(); i++) {
		    	Item item = db.getItems().get(i);
		    	itemButtons.add( new JButton(item.getName().toString()));
		    	itemButtons.get(i).setBackground(new Color(255, 153, 0));
		    	itemButtons.get(i).setFont(new Font("MS Reference Sans Serif", Font.BOLD | Font.ITALIC, 8));
		    	itemButtons.get(i).setBackground(Color.green);
		    	itemButtons.get(i).putClientProperty("id", item.getCategoryId());
		    	itemButtons.get(i).addActionListener(new ActionListener() {
		    		public void actionPerformed(ActionEvent e) {
		    			
		    			
		    			order.setCustomer(new Customer(1,"","","","",""));
		    			order.setEmployee(new Employee(1,"","","","",""));
		    			order.setDeliveryCharge(5);
		    			order.setServiceCharge(4);
		    			
		    			order.addItem(item);
		    			addData();
		    			coi();
		    			 
		    		}
				});
			    for(int j = 0; j < db.getCategories().size(); j++) {
			    			
			    	Object property = itemsContainer.get(j).getClientProperty("id");
			    			if(property.equals(item.getCategoryId())){
			    				itemsContainer.get(j).add(itemButtons.get(i));
			    	}
		    	}
		    }
		}
	}
	public void addData() {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
		for(int i = 0; i<order.getItems().size();i++) {
			
			Item item = order.getItems().get(i);
			
			model.addRow(new Object[] {item.getQuantity(), item.getName().toString() ,item.getPrice(), item.getTotal()});
			
		}
	}
	public void categories() {
	
		try (DB db = new DB()) {
		    
		    for(int i = 0; i < db.getCategories().size(); i++) {
		    	Category cat = db.getCategories().get(i);
		    	catButtons.add(new JButton(cat.getName().toString()));
		    	catButtons.get(i).setBackground(new Color(255, 153, 0));
		    	catButtons.get(i).setFont(new Font("MS Reference Sans Serif", Font.BOLD | Font.ITALIC, 8));
		    	catButtons.get(i).setSize(80, 80);
		    	catButtons.get(i).setBackground(Color.green);
		    	catButtons.get(i).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						showItems(cat.getCategoryId());
					}
				});
		    	sideBar.add(catButtons.get(i));
		    }
		}
	}
	
	public void basket() {
	
		basket = new JScrollPane();
		basket.setBounds(0, 0, basketContainer.getWidth(), (int)(basketContainer.getHeight()/1.6));
		basketContainer.add(basket);
		
		table = new JTable();
		table.setShowGrid(false);
		basket.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new Object[] {
					"Qty", "name", "@","Price"
			}
		));
		
		calcPanel = new JPanel();
		calcPanel.setBackground(new Color(255, 255, 255));
		calcPanel.setBounds(0, 650, 350, 128);
		basketContainer.add(calcPanel);
		calcPanel.setLayout(null);
		calcPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel subtotallabel = new JLabel("SubTotal");
		subtotallabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD | Font.ITALIC, 19));
		subtotallabel.setBounds(0, 50, 106, 29);
		calcPanel.add(subtotallabel);
		
		JLabel taxlabel = new JLabel("Tax");
		taxlabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD | Font.ITALIC, 24));
		taxlabel.setBounds(0, 10, 106, 29);
		calcPanel.add(taxlabel);
		
		JLabel totallabel = new JLabel("Total");
		totallabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD | Font.ITALIC, 24));
		totallabel.setBounds(0, 150, 106, 29);
		calcPanel.add(totallabel);
		
		subtotaltextbox = new JTextField();
		subtotaltextbox.setFont(new Font("Tahoma", Font.BOLD, 24));
		subtotaltextbox.setColumns(10);
		subtotaltextbox.setBounds(100, 50, 182, 29);
		calcPanel.add(subtotaltextbox);
		
		taxdisplaybox = new JTextField();
		taxdisplaybox.setFont(new Font("Tahoma", Font.BOLD, 24));
		taxdisplaybox.setColumns(10);
		taxdisplaybox.setBounds(100, 10, 182, 29);
		calcPanel.add(taxdisplaybox);
		
		totaldisplaybox = new JTextField();
		totaldisplaybox.setFont(new Font("Tahoma", Font.BOLD, 24));
		totaldisplaybox.setColumns(10);
		totaldisplaybox.setBounds(100, 150, 182, 29);
		calcPanel.add(totaldisplaybox);
	}
	public void removeItemFromBasket() {
		deleteIcon = new ImageIcon("Btn/deleteBtn.png");
		JButton removebutton = new JButton(deleteIcon);
		removebutton.setBackground(new Color(255, 255, 255));
		removebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				int RemoveItem = table.getSelectedRow();
				
				if(RemoveItem >= 0)
				{
					order.deleteItem(order.getItems().get(RemoveItem));
					addData();
					coi();
				}
				
			}
		});
		//removebutton.setBounds(10, 555, 60, 60);
		toolsContainer.add(removebutton);
	}
	public void quantityBtn() {
		addIcon = new ImageIcon("Icons/addBtn.png");
		JButton quantityBtn = new JButton(addIcon);
		quantityBtn.setBackground(new Color(255, 255, 255));
		quantityBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				int RemoveItem = table.getSelectedRow();
				
				if(RemoveItem >= 0)
				{
					order.getItems().get(RemoveItem).setQuantity(order.getItems().get(RemoveItem).getQuantity() + 1);
					System.out.println(order.checkout() + " " + RemoveItem);
					addData();
				}
				coi();

			}
		});
		//quantityBtn.setBounds(100, 555, 60, 60);
		toolsContainer.add(quantityBtn);
	}


	public void coi()
	{
	
		subtotaltextbox.setText(Double.toString(order.getTotal()));
		double Tot = Double.parseDouble(subtotaltextbox.getText());
	
		double Tax = (Tot * 3.9)/100;
		String TaxTot = String.format("£ %.2f", Tax);
		taxdisplaybox.setText(TaxTot);
	
		String Sub = String.format("£ %.2f", Tot);
		subtotaltextbox.setText(Sub );
	
		String Tot2 = String.format("£ %.2f", Tot + Tax);
		totaldisplaybox.setText(Tot2 );
	}
	
	public void showItems(int catId) {
		for(int i = 0; i < itemsContainer.size(); i++) {
			try (DB db = new DB()) {
				Object property = itemsContainer.get(i).getClientProperty("id");
			
				if(property.equals(catId)){
					itemsContainer.get(i).setVisible(true);
				}else {
					itemsContainer.get(i).setVisible(false);
				}
			}
		}
	}
	public void payBtn() {
		addIcon = new ImageIcon("Btn/payBtn.png");
		JButton payBtn = new JButton(addIcon);
		payBtn.setBackground(new Color(255, 255, 255));
		
		payBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (DB db = new DB()) {
					db.setOrder(order);
				}
				orderTotal = order.getTotal();
				payscreen(true);
			}
		});
		toolsContainer.add(payBtn);
	}
}

