package com.djcps.wms.outorder.model.outorderresult;
/**
 * 返回页面的参数
 * @author xzzx
 *
 */
public class OrderDetailInfoVO {
	/**
	 * 订单编号
	 */
	private String childOrderId;
	/**
	 * 产品名称
	 */
	private String groupGoodName;
	/**
	 * 下料规格
	 */
	private String material;
	/**
	 * 纸箱规格
	 */
	private String box;
	/**
	 * 数量
	 */
	private Integer amount;
	/**
	 * 单价
	 */
	private Double unitPrice;
	/**
	 * 金额
	 */
	private Double amountPrice; 
	/**
	 * 单位
	 */
	private String unit;
	
	public OrderDetailInfoVO(){
		this.unit = "片";
	}

	public String getChildOrderId() {
		return childOrderId;
	}

	public void setChildOrderId(String childOrderId) {
		this.childOrderId = childOrderId;
	}

	public String getGroupGoodName() {
		return groupGoodName;
	}

	public void setGroupGoodName(String groupGoodName) {
		this.groupGoodName = groupGoodName;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getAmountPrice() {
		return amountPrice;
	}

	public void setAmountPrice(Double amountPrice) {
		this.amountPrice = amountPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "OrderDetailInfoBO [childOrderId=" + childOrderId + ", groupGoodName=" + groupGoodName + ", material="
				+ material + ", box=" + box + ", amount=" + amount + ", unitPrice=" + unitPrice + ", amountPrice="
				+ amountPrice + ", unit=" + unit + "]";
	}
	
	
}
