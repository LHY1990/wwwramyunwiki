package wiki.ramyun.www.manufactory;

import java.time.LocalDateTime;

public class ManufactoryVO {
	private String factoryId;
	private String factoryName;
	private	String itemReportNumber;
	private	String adress;
	private	String identifyLetter;
	private	String corporateName;
	private String description;
	private LocalDateTime updatedDate;
	
	
	
	public ManufactoryVO() {
		super();
	}
	
	public String getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getItemReportNumber() {
		return itemReportNumber;
	}
	public void setItemReportNumber(String itemReportNumber) {
		this.itemReportNumber = itemReportNumber;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getIdentifyLetter() {
		return identifyLetter;
	}
	public void setIdentifyLetter(String identifyLetter) {
		this.identifyLetter = identifyLetter;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
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
		return "ManufactoryVO [factoryId=" + factoryId + ", factoryName=" + factoryName + ", itemReportNumber="
				+ itemReportNumber + ", adress=" + adress + ", identifyLetter=" + identifyLetter + ", corporateName="
				+ corporateName + ", description=" + description + ", updatedDate=" + updatedDate + "]";
	}
	
	
	
	
}
