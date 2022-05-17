
public class Category {
	private int categoryId;
	private String name;
	private String description;
	private String pictureId;
	

	
	public Category(int categoryId, String name, String description, String pictureId) {
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.pictureId = pictureId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPictureId() {
		return pictureId;
	}
	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}
	


}
