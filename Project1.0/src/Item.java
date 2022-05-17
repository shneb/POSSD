
public class Item {
	
	private int itemId;
	private int quantity = 1;
	private String name;
	private String desc;
	private double price;
	private String PictureId;
	private int categoryId;
	
	
	public Item(int itemId, String name, String desc, double price, String pictureId, int categoryId) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.desc = desc;
		this.price = price;
		PictureId = pictureId;
		this.categoryId = categoryId;
	}
	 
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPictureId() {
		return PictureId;
	}
	public void setPictureId(String pictureId) {
		PictureId = pictureId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public double getTotal() {
		return price * quantity;
	}
	
	
}
