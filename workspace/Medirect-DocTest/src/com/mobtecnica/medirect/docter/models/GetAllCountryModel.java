package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GetAllCountryModel implements Parcelable {
	String country_id;
	String country_name;
	String name_english;
	String show_in_list;
	String show_order_engine;
	String country_code;
	String code;

	public String getCountry_id() {
		return country_id;
	}

	public String getName_english() {
		return name_english;
	}

	public void setName_english(String name_english) {
		this.name_english = name_english;
	}

	public GetAllCountryModel(String country_id, String country_name,
			String name_english, String show_in_list, String show_order_engine,
			String country_code, String code) {
		super();
		this.country_id = country_id;
		this.country_name = country_name;
		this.name_english = name_english;
		this.show_in_list = show_in_list;
		this.show_order_engine = show_order_engine;
		this.country_code = country_code;
		this.code = code;
	}

	public String getShow_in_list() {
		return show_in_list;
	}

	public void setShow_in_list(String show_in_list) {
		this.show_in_list = show_in_list;
	}

	public String getShow_order_engine() {
		return show_order_engine;
	}

	public void setShow_order_engine(String show_order_engine) {
		this.show_order_engine = show_order_engine;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	@Override
	public String toString() {
		return "GetAllCountryModel [country_id=" + country_id
				+ ", country_name=" + country_name + ", name_english="
				+ name_english + ", show_in_list=" + show_in_list
				+ ", show_order_engine=" + show_order_engine
				+ ", country_code=" + country_code + ", code=" + code + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(country_id);
		dest.writeString(country_name);
		dest.writeString(name_english);
		dest.writeString(show_in_list);
		dest.writeString(show_order_engine);
		dest.writeString(country_code);
		dest.writeString(code);

	}

	public GetAllCountryModel(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {

		this.country_id = source.readString();
		this.country_name = source.readString();
		this.name_english = source.readString();
		this.show_in_list = source.readString();
		this.show_order_engine = source.readString();
		this.country_code = source.readString();
		this.code = source.readString();

	}

	public static final Parcelable.Creator<GetAllCountryModel> CREATOR = new Creator<GetAllCountryModel>() {

		@Override
		public GetAllCountryModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new GetAllCountryModel[size];
		}

		@Override
		public GetAllCountryModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new GetAllCountryModel(source);
		}
	};
}
