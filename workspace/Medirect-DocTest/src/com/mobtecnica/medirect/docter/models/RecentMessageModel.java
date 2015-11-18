package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RecentMessageModel implements Parcelable {
	String id;
	String primary_message_id;
	String message_type_id;
	String date;
	String content;
	String from_user_id;
	String to_user_id;
	String message_status_id;
	String new_messages;
	String Name;
	String Address;
	String Age;
	String AccountNo;
	String email;
	String phone;
	String photo;

	/**
	 * default constructor
	 */
	public RecentMessageModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAccountNo() {
		return AccountNo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAddress() {
		return Address;
	}

	public String getAge() {
		return Age;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public void setAge(String age) {
		Age = age;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrimary_message_id() {
		return primary_message_id;
	}

	public void setPrimary_message_id(String primary_message_id) {
		this.primary_message_id = primary_message_id;
	}

	public String getMessage_type_id() {
		return message_type_id;
	}

	public void setMessage_type_id(String message_type_id) {
		this.message_type_id = message_type_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFrom_user_id() {
		return from_user_id;
	}

	public void setFrom_user_id(String from_user_id) {
		this.from_user_id = from_user_id;
	}

	public String getTo_user_id() {
		return to_user_id;
	}

	public void setTo_user_id(String to_user_id) {
		this.to_user_id = to_user_id;
	}

	public String getMessage_status_id() {
		return message_status_id;
	}

	public void setMessage_status_id(String message_status_id) {
		this.message_status_id = message_status_id;
	}

	public String getNew_messages() {
		return new_messages;
	}

	public void setNew_messages(String new_messages) {
		this.new_messages = new_messages;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public static Parcelable.Creator<RecentMessageModel> getCreator() {
		return CREATOR;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void readFromParcel(Parcel in) {

		// this.propertyId = in.readString();
		this.id = in.readString();
		this.primary_message_id = in.readString();
		this.message_type_id = in.readString();
		this.content = in.readString();
		this.from_user_id = in.readString();
		this.to_user_id = in.readString();
		this.message_status_id = in.readString();
		this.new_messages = in.readString();
		this.Name = in.readString();
		this.Address = in.readString();
		this.Age = in.readString();
		this.AccountNo = in.readString();
		this.email = in.readString();
		this.phone = in.readString();
		this.photo = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(primary_message_id);
		dest.writeString(message_type_id);
		dest.writeString(date);
		dest.writeString(content);
		dest.writeString(from_user_id);
		dest.writeString(to_user_id);
		dest.writeString(message_status_id);
		dest.writeString(new_messages);
		dest.writeString(Name);
		dest.writeString(Address);
		dest.writeString(Age);
		dest.writeString(AccountNo);
		dest.writeString(email);
		dest.writeString(phone);
	}

	public RecentMessageModel(Parcel source) {
		super();
		readFromParcel(source);
	}

	public RecentMessageModel(String id, String primary_message_id,
			String message_type_id, String date, String content,
			String from_user_id, String to_user_id, String message_status_id,
			String new_messages, String name) {
		super();
		this.id = id;
		this.primary_message_id = primary_message_id;
		this.message_type_id = message_type_id;
		this.date = date;
		this.content = content;
		this.from_user_id = from_user_id;
		this.to_user_id = to_user_id;
		this.message_status_id = message_status_id;
		this.new_messages = new_messages;
		this.Name = name;
	}

	public RecentMessageModel(String id, String name, String address,
			String age, String accountNo, String email, String phone,
			String photo) {
		super();
		this.id = id;
		this.Name = name;
		this.Address = address;
		this.Age = age;
		this.AccountNo = accountNo;
		this.email = email;
		this.phone = phone;
		this.photo = photo;
	}

	public RecentMessageModel(String id, String primary_message_id,
			String message_type_id, String date, String content,
			String from_user_id, String to_user_id, String message_status_id,
			String test) {
		super();
		this.id = id;
		this.primary_message_id = primary_message_id;
		this.message_type_id = message_type_id;
		this.date = date;
		this.content = content;
		this.from_user_id = from_user_id;
		this.to_user_id = to_user_id;
		this.message_status_id = message_status_id;

	}

	public RecentMessageModel(String id, String primary_message_id,
			String message_type_id, String date, String content,
			String from_user_id, String to_user_id, String message_status_id,
			String new_messages, String name, String address, String age,
			String accountNo, String email, String phone, String photo) {
		super();
		this.id = id;
		this.primary_message_id = primary_message_id;
		this.message_type_id = message_type_id;
		this.date = date;
		this.content = content;
		this.from_user_id = from_user_id;
		this.to_user_id = to_user_id;
		this.message_status_id = message_status_id;
		this.new_messages = new_messages;
		this.Name = name;
		this.Address = address;
		this.Age = age;
		this.AccountNo = accountNo;
		this.email = email;
		this.phone = phone;
		this.photo = photo;
	}

	public static final Parcelable.Creator<RecentMessageModel> CREATOR = new Creator<RecentMessageModel>() {

		@Override
		public RecentMessageModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new RecentMessageModel[size];
		}

		@Override
		public RecentMessageModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new RecentMessageModel(source);
		}
	};
}
