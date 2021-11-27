package wiki.ramyun.www.ramyun;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class RamyunVO {
	private String brandNameKor;          //라면 브랜드 명
	private String brandNameEng;          //라면 브랜드 영어 이름
	@DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime updatedDate;    //정보갱신 날짜 
	private String corporateName;         //업체명
	private String developedDate;         //개발년도
	private String weight ;               //무게
	private String calorie;               //열량
	private String scovilleUnit;          //스코빌유닛
	private String foodCategory;          //식품유형
	private String recipe;                //조리법
	private String barcode;               //바코드 넘버
	private String noodleShape;           //면모양
	private String soupComposition;       //스프구성
	private String discontinuance;        //단종여부
	private String relatedRamyun;         //연관 라면
	private String waterCapacityByNumber; //갯수별 물량
	private String materialList;          // 원재료명
	private String itemReportNumber;      //품목보고번호
	private String expirationDate;        //유통기한
	private String soupPosition;          //스프 위치 
	private String natrium;               //나트륨
	private String carbohydrate;          //탄수화물
	private String sugars;                //당류
	private String fat;                   //지방
	private String transfat;              //트랜스지방
	private String saturatedfat;          //포화지방
	private String cholesterol;           //콜레스테롤
	private String protein;               //단백질
	private String calcium;               //칼슘
	private String image;                 //이미지 위치
	private String userEditedContents;    //유저 작성 내용
	
	
	
	public RamyunVO() {
		super();
	}
	
	
	public String getbrandNameKor() {
		return brandNameKor;
	}
	public void setbrandNameKor(String brandNameKor) {
		this.brandNameKor = brandNameKor;
	}
	public String getbrandNameEng() {
		return brandNameEng;
	}
	public void setbrandNameEng(String brandNameEng) {
		this.brandNameEng = brandNameEng;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	public String getDevelopedDate() {
		return developedDate;
	}
	public void setDevelopedDate(String developedDate) {
		this.developedDate = developedDate;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getCalorie() {
		return calorie;
	}
	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}
	public String getScovilleUnit() {
		return scovilleUnit;
	}
	public void setScovilleUnit(String scovilleUnit) {
		this.scovilleUnit = scovilleUnit;
	}
	public String getFoodCategory() {
		return foodCategory;
	}
	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}
	public String getRecipe() {
		return recipe;
	}
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getNoodleShape() {
		return noodleShape;
	}
	public void setNoodleShape(String noodleShape) {
		this.noodleShape = noodleShape;
	}
	public String getSoupComposition() {
		return soupComposition;
	}
	public void setSoupComposition(String soupComposition) {
		this.soupComposition = soupComposition;
	}
	public String getDiscontinuance() {
		return discontinuance;
	}
	public void setDiscontinuance(String discontinuance) {
		this.discontinuance = discontinuance;
	}
	public String getRelatedRamyun() {
		return relatedRamyun;
	}
	public void setRelatedRamyun(String relatedRamyun) {
		this.relatedRamyun = relatedRamyun;
	}
	public String getWaterCapacityByNumber() {
		return waterCapacityByNumber;
	}
	public void setWaterCapacityByNumber(String waterCapacityByNumber) {
		this.waterCapacityByNumber = waterCapacityByNumber;
	}
	public String getMaterialList() {
		return materialList;
	}
	public void setMaterialList(String materialList) {
		this.materialList = materialList;
	}
	public String getItemReportNumber() {
		return itemReportNumber;
	}
	public void setItemReportNumber(String itemReportNumber) {
		this.itemReportNumber = itemReportNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getSoupPosition() {
		return soupPosition;
	}
	public void setSoupPosition(String soupPosition) {
		this.soupPosition = soupPosition;
	}
	public String getNatrium() {
		return natrium;
	}
	public void setNatrium(String natrium) {
		this.natrium = natrium;
	}
	public String getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(String carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public String getSugars() {
		return sugars;
	}
	public void setSugars(String sugars) {
		this.sugars = sugars;
	}
	public String getFat() {
		return fat;
	}
	public void setFat(String fat) {
		this.fat = fat;
	}
	public String getTransfat() {
		return transfat;
	}
	public void setTransfat(String transfat) {
		this.transfat = transfat;
	}
	public String getSaturatedfat() {
		return saturatedfat;
	}
	public void setSaturatedfat(String saturatedfat) {
		this.saturatedfat = saturatedfat;
	}
	public String getCholesterol() {
		return cholesterol;
	}
	public void setCholesterol(String cholesterol) {
		this.cholesterol = cholesterol;
	}
	public String getProtein() {
		return protein;
	}
	public void setProtein(String protein) {
		this.protein = protein;
	}
	public String getCalcium() {
		return calcium;
	}
	public void setCalcium(String calcium) {
		this.calcium = calcium;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUserEditedContents() {
		return userEditedContents;
	}
	public void setUserEditedContents(String userEditedContents) {
		this.userEditedContents = userEditedContents;
	}
	
	
	@Override
	public String toString() {
		return "RamyunVO [brandNameKor=" + brandNameKor+"]";
	}
	
	
}
