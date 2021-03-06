package wiki.ramyun.www.ingredient;

import java.time.LocalDateTime;

public class IngredientVO {
	private String name;
	private String type;
	private String description;
	private LocalDateTime updatedDate;
	
	public IngredientVO() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Override
	public String toString() {
		return "IngredientVO [name=" + name + ", type=" + type + ", description=" + description + ", updatedDate="
				+ updatedDate + "]";
	}
	
}
