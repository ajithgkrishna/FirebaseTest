package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class WalletHistory implements Parcelable {
	String id;
	String user_id;
	String transaction_type_id;
	String datetime;
	String amount;
	String balance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTransaction_type_id() {
		return transaction_type_id;
	}

	public void setTransaction_type_id(String transaction_type_id) {
		this.transaction_type_id = transaction_type_id;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTransaction_type_name() {
		return transaction_type_name;
	}

	public void setTransaction_type_name(String transaction_type_name) {
		this.transaction_type_name = transaction_type_name;
	}

	public WalletHistory(String id, String user_id, String transaction_type_id,
			String datetime, String amount, String balance,
			String transaction_type_name) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.transaction_type_id = transaction_type_id;
		this.datetime = datetime;
		this.amount = amount;
		this.balance = balance;
		this.transaction_type_name = transaction_type_name;
	}

	String transaction_type_name;

	public WalletHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static Parcelable.Creator<WalletHistory> getCreator() {
		return CREATOR;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(user_id);
		dest.writeString(transaction_type_id);
		dest.writeString(datetime);
		dest.writeString(amount);
		dest.writeString(balance);
		dest.writeString(transaction_type_name);

	}

	public WalletHistory(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel in) {

		this.id = in.readString();
		this.user_id = in.readString();
		this.transaction_type_id = in.readString();
		this.datetime = in.readString();
		this.amount = in.readString();
		this.balance = in.readString();
		this.transaction_type_name = in.readString();

	}

	public static final Parcelable.Creator<WalletHistory> CREATOR = new Creator<WalletHistory>() {

		@Override
		public WalletHistory[] newArray(int size) {
			// TODO Auto-generated method stub
			return new WalletHistory[size];
		}

		@Override
		public WalletHistory createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new WalletHistory(source);
		}
	};

	@Override
	public String toString() {
		return "WalletHistory [id=" + id + ", user_id=" + user_id
				+ ", transaction_type_id=" + transaction_type_id
				+ ", datetime=" + datetime + ", amount=" + amount
				+ ", balance=" + balance + ", transaction_type_name="
				+ transaction_type_name + "]";
	}
}
