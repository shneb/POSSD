
	import java.util.ArrayList;


	public class Order {
		private int orderId;
		private ArrayList<Item> items = new ArrayList<>();
		private Customer customer;
		private Employee employee;
		private double deliveryCharge;
		private double serviceCharge;
		private double total;

		public ArrayList<Item> getItems() {
			return items;
		}
		
		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}


		public void setTotal(double total) {
			this.total = total;
		}

		public void setItems(ArrayList<Item> items) {
			this.items = items;
		}

		public double getDeliveryCharge() {
			return deliveryCharge;
		}

		public void setDeliveryCharge(double deliveryCharge) {
			this.deliveryCharge = deliveryCharge;
		}

		public double getServiceCharge() {
			return serviceCharge;
		}


		public void setServiceCharge(double serviceCharge) {
			this.serviceCharge = serviceCharge;
		}

		public boolean itemListEmpty() {
			if(items.size() <= 0) {
				return true;
			}else {
				return false;
			}
		}

		public double getTotal() {
			double  total = 0;
			for(int i = 0; i < items.size(); i++) {
				total += items.get(i).getPrice() * items.get(i).getQuantity();
			}
			total += deliveryCharge + serviceCharge;
		return total;
		}
		
		public void deleteItem(Item item) {
			items.remove(item);
		}
		public void addItem(Item item) {
			items.add(item);
		}
		public String checkout() {
			String itemList = "";
			String buffer;
			
			for(int i = 0; i < items.size(); i++) {
				itemList += items.get(i).getQuantity() + " " +items.get(i).getName() + " @" + items.get(i).getPrice() +  " ";
			}
			buffer = orderId + " " + customer.getName() + " " + employee.getName() + " " + itemList + " " + getTotal();
			
			return buffer;
		}
	}

