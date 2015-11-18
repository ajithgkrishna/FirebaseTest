package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PurchaseOrderModel implements Parcelable {

	private String id;
	private String name;
	private String image;
	private String introduction;
	private String unit_price;
	private String quantity;
	
	
	/*****
	 * Constructor for adding data from database
	 * @param id
	 * @param name
	 * @param image
	 * @param introduction
	 * @param unit_price
	 */
	public PurchaseOrderModel(String id, String name, String image,
			String introduction, String unit_price ,String quantity) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.introduction = introduction;
		this.unit_price = unit_price;
		this.quantity = quantity;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(image);
		dest.writeString(introduction);
		dest.writeString(unit_price);
	}

	public PurchaseOrderModel(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {
		this.id = source.readString();
		this.name = source.readString();
		this.image = source.readString();
		this.introduction = source.readString();
		this.unit_price = source.readString();
		
	}
	
	public static final Parcelable.Creator<PurchaseOrderModel> CREATOR = new Creator<PurchaseOrderModel>() {

		@Override
		public PurchaseOrderModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new PurchaseOrderModel[size];
		}

		@Override
		public PurchaseOrderModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new PurchaseOrderModel(source);
		}
	};
}
