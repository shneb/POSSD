
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Handles database access from within your web service
 * @author You, Mainly!
 */
public class DB implements AutoCloseable {
	
	//allows us to easily change the database used
	private static final String JDBC_CONNECTION_STRING = "jdbc:sqlite:./db/db.db";
	
	//allows us to re-use the connection between queries if desired
	private Connection connection = null;
	
	/**
	 * Creates an instance of the DB object and connects to the database
	 */
	public DB() {
		try {
			connection = DriverManager.getConnection(JDBC_CONNECTION_STRING);
		}
		catch (SQLException sqle) {
			error(sqle);
		}
	}
	
	private void error(SQLException sqle) {
		System.err.println("Problem Opening Database! " + sqle.getClass().getName());
		sqle.printStackTrace();
		System.exit(1);
	}
	
	@Override
	public void close() {
		try {
			if ( !connection.isClosed() ) {
				connection.close();
			}
		}
		catch(SQLException sqle) {
			error(sqle);
		}
	}
	
	public ArrayList<Category>  getCategories() {
		
		ArrayList<Category> items = new ArrayList<>();

		try {
			PreparedStatement s = connection.prepareStatement("SELECT * FROM Category;");		
			ResultSet rs = s.executeQuery();
		
			while(rs.next()) {
				;
				items.add(new Category(rs.getInt("categoryId"), rs.getString("name"), rs.getString("description"), rs.getString("pictureId")));
			}

		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		return items;
	}
	
	public ArrayList<Item>  getItems() {
		
		ArrayList<Item> items = new ArrayList<>();

		try {
			PreparedStatement s = connection.prepareStatement("SELECT * FROM Item;");
			ResultSet rs = s.executeQuery();
		
			while(rs.next()) {
				items.add(new Item(rs.getInt("itemId"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"), rs.getString("pictureId"), rs.getInt("categoryId")));
			}

		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		return items;
	}
	public void setOrder(Order order) {
			

		
			int customer = order.getCustomer().getId();
			int employee = order.getEmployee().getId();
			double deliveryCharge = order.getDeliveryCharge();
			double serviceCharge = order.getServiceCharge();
			double total = order.getTotal();
			
		try {
			
			String firstHalf = "INSERT INTO \"OrderItems\" ( ";
			for(int i = 0; i <order.getItems().size(); i++) {

				if(order.getItems().size()-1 == i) {
					firstHalf  += "item" + (i+1);
					
				}else {
					firstHalf  += "item" + (i+1) + ",";
				}
			}
			firstHalf += ")";
			
			String secondHalf = " VALUES(";
			
			for(int i = 0; i <order.getItems().size(); i++) {
				if(order.getItems().size()-1 == i) {
					secondHalf += order.getItems().get(i).getItemId() ;

				}else {
					secondHalf += order.getItems().get(i).getItemId() + ",";

				}
			}
			secondHalf += ");";
			
			PreparedStatement orderItems = connection.prepareStatement( firstHalf + secondHalf);

			
			orderItems.executeUpdate();
			
			PreparedStatement orderItemsId = connection.prepareStatement("SELECT orderItemsId FROM OrderItems ORDER BY orderItemsId DESC LIMIT 1;");
			ResultSet rs = orderItemsId.executeQuery();
			
			PreparedStatement orders = connection.prepareStatement("INSERT INTO \"Order\"\r\n"
					+ "(orderItemsId, customerId, employeeId, deliveryCharge, serviceCharge, totalg)\r\n"
					+ "VALUES\r\n"
					+ "(?, ?, ?, ?,?,?);");
			orders.setInt(1, ((Number) rs.getObject(1)).intValue());
			orders.setInt(2, customer);
			orders.setInt(3, employee);
			orders.setDouble(4, deliveryCharge);
			orders.setDouble(5, serviceCharge);
			orders.setDouble(6, total);
			
			orders.executeUpdate();			

		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
	}
}