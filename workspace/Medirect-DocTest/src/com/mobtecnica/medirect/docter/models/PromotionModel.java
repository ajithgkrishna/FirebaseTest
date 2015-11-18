package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PromotionModel implements Parcelable {

	private String id;
	private String title;
	private String dateTime;
	private String readStatus;

	public PromotionModel(String id, String title, String dateTime,
			String readStatus) {
		super();
		this.id = id;
		this.title = title;
		this.dateTime = dateTime;
		this.readStatus = readStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(id);
		dest.writeString(title);
		dest.writeString(dateTime);
		dest.writeString(readStatus);

	}

	public PromotionModel(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {

		this.id = source.readString();
		this.title = source.readString();
		this.dateTime = source.readString();
		this.readStatus = source.readString();

	}

	public static final Parcelable.Creator<PromotionModel> CREATOR = new Creator<PromotionModel>() {

		@Override
		public PromotionModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new PromotionModel[size];
		}

		@Override
		public PromotionModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new PromotionModel(source);
		}
	};

}
