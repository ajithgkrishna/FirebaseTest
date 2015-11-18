package com.mobtecnica.medirect.docter.models;

import java.io.Serializable;
import java.util.ArrayList;

public class MasterDataItem implements Serializable {
	private static final long serialVersionUID = -6099312954099962806L;
	private String title;
	private String body;

	public MasterDataItem(String title, String body) {
		this.title = title;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public static ArrayList<MasterDataItem> getItems() {
		ArrayList<MasterDataItem> items = new ArrayList<MasterDataItem>();
		items.add(new MasterDataItem("Home", "Home"));
		items.add(new MasterDataItem("Profile", "Profile"));
		// items.add(new MasterDataItem("Patients", "Patients"));
		items.add(new MasterDataItem("Appointments", "Appointments"));
		items.add(new MasterDataItem("Patients", "Patients"));
		items.add(new MasterDataItem("Wallet", "Wallet"));
		items.add(new MasterDataItem("Inbox", "Inbox"));
		items.add(new MasterDataItem("Education", "Education"));

		return items;
	}

	@Override
	public String toString() {
		return getTitle();
	}

}
