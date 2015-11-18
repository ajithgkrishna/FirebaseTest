package com.mobtecnica.medirect.docter.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MyHistoryModel implements Parcelable {
	String id;
	String user_id;
	String family_history;
	String surgical_history;
	String dm_status;
	String htn_status;
	String thyroid_status;
	String asthma_status;
	String tumor_status;
	String ca_status;
	String added_by;
	String added_on;
	String status;
	String medical_history;
	ArrayList<AllergiMedicineModel> allergies = new ArrayList<AllergiMedicineModel>();

	

	public MyHistoryModel(String id, String user_id, String family_history,
			String surgical_history, String dm_status, String htn_status,
			String thyroid_status, String asthma_status, String tumor_status,
			String ca_status,/* String added_by, String added_on, String status,*/
			String medical_history, ArrayList<AllergiMedicineModel> allergies) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.family_history = family_history;
		this.surgical_history = surgical_history;
		this.dm_status = dm_status;
		this.htn_status = htn_status;
		this.thyroid_status = thyroid_status;
		this.asthma_status = asthma_status;
		this.tumor_status = tumor_status;
		this.ca_status = ca_status;
		/*this.added_by = added_by;
		this.added_on = added_on;
		this.status = status;*/
		this.medical_history = medical_history;
		this.allergies = allergies;
	}
	/****
	 * Model for adding data 
	 * used in AddHistoryFragment
	 * @param id
	 * @param user_id
	 * @param family_history
	 * @param surgical_history
	 * @param dm_status
	 * @param htn_status
	 * @param thyroid_status
	 * @param asthma_status
	 * @param tumor_status
	 * @param ca_status
	 * @param medical_history
	 * @param allergies
	 */
	public MyHistoryModel(String id, String user_id, String family_history,
			String surgical_history, String dm_status, String htn_status,
			String thyroid_status, String asthma_status, String tumor_status,
			String ca_status,/* String added_by, String added_on, String status,*/
			 ArrayList<AllergiMedicineModel> allergies) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.family_history = family_history;
		this.surgical_history = surgical_history;
		this.dm_status = dm_status;
		this.htn_status = htn_status;
		this.thyroid_status = thyroid_status;
		this.asthma_status = asthma_status;
		this.tumor_status = tumor_status;
		this.ca_status = ca_status;
		/*this.added_by = added_by;
		this.added_on = added_on;
		this.status = status;*/
		this.allergies = allergies;
	}

	public MyHistoryModel(Parcel source) {
		readfromparcel(source);
	}

	public String getId() {
		return id;
	}
	public String getMedical_history() {
	return medical_history;
}
public void setMedical_history(String medical_history) {
	this.medical_history = medical_history;
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

	public String getFamily_history() {
		return family_history;
	}

	public void setFamily_history(String family_history) {
		this.family_history = family_history;
	}

	public String getSurgical_history() {
		return surgical_history;
	}

	public void setSurgical_history(String surgical_history) {
		this.surgical_history = surgical_history;
	}

	public String getDm_status() {
		return dm_status;
	}

	public void setDm_status(String dm_status) {
		this.dm_status = dm_status;
	}

	public String getHtn_status() {
		return htn_status;
	}

	public void setHtn_status(String htn_status) {
		this.htn_status = htn_status;
	}

	public String getThyroid_status() {
		return thyroid_status;
	}

	public void setThyroid_status(String thyroid_status) {
		this.thyroid_status = thyroid_status;
	}

	public String getAsthma_status() {
		return asthma_status;
	}

	public void setAsthma_status(String asthma_status) {
		this.asthma_status = asthma_status;
	}

	public String getTumor_status() {
		return tumor_status;
	}

	public void setTumor_status(String tumor_status) {
		this.tumor_status = tumor_status;
	}

	public String getCa_status() {
		return ca_status;
	}

	public void setCa_status(String ca_status) {
		this.ca_status = ca_status;
	}

	public String getAdded_by() {
		return added_by;
	}

	public void setAdded_by(String added_by) {
		this.added_by = added_by;
	}

	public String getAdded_on() {
		return added_on;
	}

	public void setAdded_on(String added_on) {
		this.added_on = added_on;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<AllergiMedicineModel> getAllergies() {
		return allergies;
	}

	public void setAllergies(ArrayList<AllergiMedicineModel> allergies) {
		this.allergies = allergies;
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
		dest.writeString(family_history);
		dest.writeString(surgical_history);
		dest.writeString(dm_status);
		dest.writeString(htn_status);
		dest.writeString(thyroid_status);
		dest.writeString(asthma_status);
		dest.writeString(tumor_status);
		dest.writeString(ca_status);
		dest.writeString(added_by);
		dest.writeString(added_on);
		dest.writeString(status);
		dest.writeString(medical_history);
		dest.writeTypedList(allergies);
		

	}

	public void readfromparcel(Parcel in) {
		this.id = in.readString();
		this.user_id = in.readString();
		this.family_history = in.readString();
		this.surgical_history = in.readString();
		this.dm_status = in.readString();
		this.htn_status = in.readString();
		this.thyroid_status = in.readString();
		this.asthma_status = in.readString();
		this.tumor_status = in.readString();
		this.ca_status = in.readString();
		this.added_by = in.readString();
		this.added_on = in.readString();
		this.status = in.readString();
		this.medical_history = in.readString();
		in.readTypedList(allergies, AllergiMedicineModel.CREATOR);
	}

	public static final Parcelable.Creator<MyHistoryModel> CREATOR = new Creator<MyHistoryModel>() {

		@Override
		public MyHistoryModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new MyHistoryModel[size];
		}

		@Override
		public MyHistoryModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new MyHistoryModel(source);
		}
	};
}
