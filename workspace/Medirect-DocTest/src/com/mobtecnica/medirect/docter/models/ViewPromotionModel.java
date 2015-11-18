package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ViewPromotionModel implements Parcelable {

	private String title;
	private String datetime;
	private String content;
	
	public ViewPromotionModel(String title, String datetime, String content) {
		super();
		this.title = title;
		this.datetime = datetime;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(title);
		dest.writeString(datetime);
		dest.writeString(content);


	}

	public ViewPromotionModel(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {

		this.title = source.readString();
		this.datetime = source.readString();
		this.content = source.readString();


	}

	public static final Parcelable.Creator<ViewPromotionModel> CREATOR = new Creator<ViewPromotionModel>() {

		@Override
		public ViewPromotionModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ViewPromotionModel[size];
		}

		@Override
		public ViewPromotionModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new ViewPromotionModel(source);
		}
	};

}
