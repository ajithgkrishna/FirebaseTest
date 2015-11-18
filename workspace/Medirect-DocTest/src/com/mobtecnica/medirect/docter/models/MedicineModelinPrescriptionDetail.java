package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MedicineModelinPrescriptionDetail implements Parcelable {

	String id;
	String medicine_id;
	String prescription_id;
	String medicines_per_dose;
	String medicine_unit;
	String medicine_frequency_id;
	String morning_dose;
	String afternoon_dose;
	String evening_dose;
	String night_dose;
	String total_day;
	String refill_number;
	String notes;
	String consumption_mode;
	String after_food;
	String initial_dispatch_quantity;
	String medicine_name;
	String medicine_unit_name;
	String timings;
	String food_preference;

	public MedicineModelinPrescriptionDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFood_preference() {
		return food_preference;
	}

	public String getTimings() {
		return timings;
	}

	public void setFood_preference(String food_preference) {
		this.food_preference = food_preference;
	}

	public void setTimings(String timings) {
		this.timings = timings;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMedicine_id() {
		return medicine_id;
	}

	public void setMedicine_id(String medicine_id) {
		this.medicine_id = medicine_id;
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

	public String getMedicine_frequency_id() {
		return medicine_frequency_id;
	}

	public void setMedicine_frequency_id(String medicine_frequency_id) {
		this.medicine_frequency_id = medicine_frequency_id;
	}

	public String getMorning_dose() {
		return morning_dose;
	}

	public void setMorning_dose(String morning_dose) {
		this.morning_dose = morning_dose;
	}

	public String getAfternoon_dose() {
		return afternoon_dose;
	}

	public void setAfternoon_dose(String afternoon_dose) {
		this.afternoon_dose = afternoon_dose;
	}

	public String getEvening_dose() {
		return evening_dose;
	}

	public void setEvening_dose(String evening_dose) {
		this.evening_dose = evening_dose;
	}

	public String getNight_dose() {
		return night_dose;
	}

	public void setNight_dose(String night_dose) {
		this.night_dose = night_dose;
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

	public String getConsumption_mode() {
		return consumption_mode;
	}

	public void setConsumption_mode(String consumption_mode) {
		this.consumption_mode = consumption_mode;
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

	public String getMedicine_name() {
		return medicine_name;
	}

	public void setMedicine_name(String medicine_name) {
		this.medicine_name = medicine_name;
	}

	public String getMedicine_unit_name() {
		return medicine_unit_name;
	}

	public void setMedicine_unit_name(String medicine_unit_name) {
		this.medicine_unit_name = medicine_unit_name;
	}

	public MedicineModelinPrescriptionDetail(String id, String medicine_id,
			String prescription_id, String medicines_per_dose,
			String medicine_unit, String medicine_frequency_id,
			String morning_dose, String afternoon_dose, String evening_dose,
			String night_dose, String total_day, String refill_number,
			String notes, String consumption_mode, String after_food,
			String initial_dispatch_quantity, String medicine_name,
			String medicine_unit_name, String timings, String food_preference) {
		super();
		this.id = id;
		this.medicine_id = medicine_id;
		this.prescription_id = prescription_id;
		this.medicines_per_dose = medicines_per_dose;
		this.medicine_unit = medicine_unit;
		this.medicine_frequency_id = medicine_frequency_id;
		this.morning_dose = morning_dose;
		this.afternoon_dose = afternoon_dose;
		this.evening_dose = evening_dose;
		this.night_dose = night_dose;
		this.total_day = total_day;
		this.refill_number = refill_number;
		this.notes = notes;
		this.consumption_mode = consumption_mode;
		this.after_food = after_food;
		this.initial_dispatch_quantity = initial_dispatch_quantity;
		this.medicine_name = medicine_name;
		this.medicine_unit_name = medicine_unit_name;
		this.timings = timings;
		this.food_preference = food_preference;
	}

	public static Parcelable.Creator<MedicineModelinPrescriptionDetail> getCreator() {
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
		dest.writeString(medicine_id);
		dest.writeString(prescription_id);
		dest.writeString(medicines_per_dose);
		dest.writeString(medicine_name);
		dest.writeString(medicine_unit);
		dest.writeString(medicine_frequency_id);
		dest.writeString(afternoon_dose);
		dest.writeString(evening_dose);
		dest.writeString(total_day);
		dest.writeString(refill_number);
		dest.writeString(medicine_name);
		dest.writeString(notes);
		dest.writeString(consumption_mode);
		dest.writeString(after_food);
		dest.writeString(initial_dispatch_quantity);
		dest.writeString(medicine_name);
		dest.writeString(medicine_unit_name);
		dest.writeString(timings);
		dest.writeString(food_preference);

	}

	public MedicineModelinPrescriptionDetail(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {

		this.id = source.readString();
		this.medicine_id = source.readString();
		this.prescription_id = source.readString();
		this.medicines_per_dose = source.readString();
		this.medicine_name = source.readString();
		this.medicine_unit = source.readString();
		this.medicine_frequency_id = source.readString();
		this.afternoon_dose = source.readString();
		this.evening_dose = source.readString();
		this.total_day = source.readString();
		this.refill_number = source.readString();
		this.medicine_name = source.readString();
		this.notes = source.readString();
		this.consumption_mode = source.readString();
		this.after_food = source.readString();
		this.initial_dispatch_quantity = source.readString();
		this.medicine_name = source.readString();
		this.medicine_unit_name = source.readString();
		this.timings = source.readString();
		this.food_preference = source.readString();

	}

	public static final Parcelable.Creator<MedicineModelinPrescriptionDetail> CREATOR = new Creator<MedicineModelinPrescriptionDetail>() {

		@Override
		public MedicineModelinPrescriptionDetail[] newArray(int size) {
			// TODO Auto-generated method stub
			return new MedicineModelinPrescriptionDetail[size];
		}

		@Override
		public MedicineModelinPrescriptionDetail createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new MedicineModelinPrescriptionDetail(source);
		}
	};

}
