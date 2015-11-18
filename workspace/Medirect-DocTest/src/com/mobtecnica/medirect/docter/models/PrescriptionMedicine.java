package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PrescriptionMedicine implements Parcelable{

	public String Id;
	public String medicine_name;
	public String prescription_id;
	public String medicines_per_dose;
	public String medicine_unit;
	public String dose_per_day;
	public String total_day;
	public String refill_number;
	public String notes;
	public String consuption_mode;
	public String after_food;
	public String initial_dispatch_quantity;
	
	/**
	 * Default Constructor
	 */
	public PrescriptionMedicine(){
		super();
	}
	

	/*****
	 * 
	 * @param medicine_name
	 * @param medicines_per_dose
	 * @param dose_per_day
	 * @param no_of_days
	 * @param refill_number
	 * @param notes
	 */
	public PrescriptionMedicine(String medicine_name,String medicines_per_dose,
			String dose_per_day,String no_of_days,String refill_number,String notes){
		super();
		this.medicine_name = medicine_name;
		this.medicines_per_dose = medicines_per_dose;
		this.dose_per_day = dose_per_day;
		this.total_day = no_of_days;
		this.refill_number = refill_number;
		this.notes = notes;
	}
	
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getMedicine_name() {
		return medicine_name;
	}
	public void setMedicine_name(String medicine_name) {
		this.medicine_name = medicine_name;
	}
	public String getPrescription_id() {
		return prescription_id;
	}
	public void setPrescription_id(String prescription_id) {
		this.prescription_id = prescription_id;
	}
	public String getMedicines_per_dose() {
		return medicines_per_dose;
	}
	public void setMedicines_per_dose(String medicines_per_dose) {
		this.medicines_per_dose = medicines_per_dose;
	}
	public String getMedicine_unit() {
		return medicine_unit;
	}
	public void setMedicine_unit(String medicine_unit) {
		this.medicine_unit = medicine_unit;
	}
	public String getDose_per_day() {
		return dose_per_day;
	}
	public void setDose_per_day(String dose_per_day) {
		this.dose_per_day = dose_per_day;
	}
	public String getTotal_day() {
		return total_day;
	}
	public void setTotal_day(String total_day) {
		this.total_day = total_day;
	}
	public String getRefill_number() {
		return refill_number;
	}
	public void setRefill_number(String refill_number) {
		this.refill_number = refill_number;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getConsuption_mode() {
		return consuption_mode;
	}
	public void setConsuption_mode(String consuption_mode) {
		this.consuption_mode = consuption_mode;
	}
	public String getAfter_food() {
		return after_food;
	}
	public void setAfter_food(String after_food) {
		this.after_food = after_food;
	}
	public String getInitial_dispatch_quantity() {
		return initial_dispatch_quantity;
	}
	public void setInitial_dispatch_quantity(String initial_dispatch_quantity) {
		this.initial_dispatch_quantity = initial_dispatch_quantity;
	}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
	
	
}
