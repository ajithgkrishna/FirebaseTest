package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MedicinsModel implements Parcelable {
	/****
	 * The constructor used in AddMedicineFragment
	 * @param id
	 * @param medicines_per_dose
	 * @param medicine_unit
	 * @param dose_per_day
	 * @param total_day
	 * @param refill_number
	 * @param notes
	 * @param after_food
	 * @param mornig
	 * @param afterNoon
	 * @param evening
	 * @param night
	 */
	public MedicinsModel(String medicine_id,String medicineName, String medicines_per_dose,
			String medicine_unit,  String total_day,
			String refill_number, String notes, String after_food,
			String mornig, String afterNoon, String evening, String night,String medicineFrequencyId,String frequencyName) {
		super();
		this.medicine_id = medicine_id;
		this.medicine_name = medicineName;
		this.medicines_per_dose = medicines_per_dose;
		this.medicine_unit = medicine_unit;
		this.total_day = total_day;
		this.refill_number = refill_number;
		this.notes = notes;
		this.after_food = after_food;
		this.mornig = mornig;
		this.afterNoon = afterNoon;
		this.evening = evening;
		this.night = night;
		this.frequencyId = medicineFrequencyId;
		this.frequencyName = frequencyName;
	}

	public String id;
	public String medicine_id;
	public String medicine_name;
	public String prescription_id;
	public String medicines_per_dose;
	public String medicine_unit;
	public String medicine_unit_id;
	public String dose_per_day;
	public String total_day;
	public String refill_number;
	public String notes;
	public String consumption_mode;
	public String after_food;
	public String after_foodId;
	public String frequencyId;
	public String frequencyName;
	public String before_food;
	public String before_foodId;

	public String mornig;
	public String mornigId;
	public String afterNoon;
	public String afterNoonId;
	public String evening;

	public String getAfter_foodId() {
		return after_foodId;
	}

	public void setAfter_foodId(String after_foodId) {
		this.after_foodId = after_foodId;
	}

	public String getBefore_foodId() {
		return before_foodId;
	}

	public void setBefore_foodId(String before_foodId) {
		this.before_foodId = before_foodId;
	}

	public String getMornigId() {
		return mornigId;
	}

	public void setMornigId(String mornigId) {
		this.mornigId = mornigId;
	}

	public String getAfterNoonId() {
		return afterNoonId;
	}

	public void setAfterNoonId(String afterNoonId) {
		this.afterNoonId = afterNoonId;
	}

	public String getEveningId() {
		return eveningId;
	}

	public void setEveningId(String eveningId) {
		this.eveningId = eveningId;
	}

	public String getNightId() {
		return nightId;
	}

	public void setNightId(String nightId) {
		this.nightId = nightId;
	}

	public void setMornig(String mornig) {
		this.mornig = mornig;
	}

	public String eveningId;
	public String night;
	public String nightId;

	public MedicinsModel(String id, String medicine_id, String medicine_name,
			String prescription_id, String medicines_per_dose,
			String medicine_unit, String medicine_unit_id, String dose_per_day,
			String total_day, String refill_number, String notes,
			String consumption_mode, String after_food, String frequencyId,
			String frequencyName, String initial_dispatch_quantity) {
		super();
		this.id = id;
		this.medicine_id = medicine_id;
		this.medicine_name = medicine_name;
		this.prescription_id = prescription_id;
		this.medicines_per_dose = medicines_per_dose;
		this.medicine_unit = medicine_unit;
		this.medicine_unit_id = medicine_unit_id;
		this.dose_per_day = dose_per_day;
		this.total_day = total_day;
		this.refill_number = refill_number;
		this.notes = notes;
		this.consumption_mode = consumption_mode;
		this.after_food = after_food;
		this.frequencyId = frequencyId;
		this.frequencyName = frequencyName;
		this.initial_dispatch_quantity = initial_dispatch_quantity;
	}

	public String getBefore_food() {
		return before_food;
	}

	public void setBefore_food(String before_food) {
		this.before_food = before_food;
	}

	public String getMornig() {
		return mornig;
	}

	public void setIsMornig(String mornig) {
		this.mornig = mornig;
	}

	public String getAfterNoon() {
		return afterNoon;
	}

	public void setAfterNoon(String afterNoon) {
		this.afterNoon = afterNoon;
	}

	public String getEvening() {
		return evening;
	}

	public void setEvening(String evening) {
		this.evening = evening;
	}

	public String getNight() {
		return night;
	}

	public void setNight(String night) {
		this.night = night;
	}

	public String getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}

	public String getFrequencyName() {
		return frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}

	public String initial_dispatch_quantity;

	public String getMedicine_unit_id() {
		return medicine_unit_id;
	}

	public void setMedicine_unit_id(String medicine_unit_id) {
		this.medicine_unit_id = medicine_unit_id;
	}

	public String getMedicine_name() {
		return medicine_name;
	}

	public void setMedicine_name(String medicine_name) {
		this.medicine_name = medicine_name;
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static final Parcelable.Creator<MedicinsModel> CREATOR = new Creator<MedicinsModel>() {

		@Override
		public MedicinsModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new MedicinsModel[size];
		}

		@Override
		public MedicinsModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new MedicinsModel(source);
		}

	};

	@Override
	public String toString() {
		return "MedicinsModel [id=" + id + ", medicine_id=" + medicine_id
				+ ", medicine_name=" + medicine_name + ", prescription_id="
				+ prescription_id + ", medicines_per_dose="
				+ medicines_per_dose + ", medicine_unit=" + medicine_unit
				+ ", dose_per_day=" + dose_per_day + ", total_day=" + total_day
				+ ", refill_number=" + refill_number + ", notes=" + notes
				+ ", consumption_mode=" + consumption_mode + ", after_food="
				+ after_food + ", initial_dispatch_quantity="
				+ initial_dispatch_quantity + "]";
	}

	public MedicinsModel(String id, String medicine_id, String medicine_name,
			String prescription_id, String medicines_per_dose,
			String medicine_unit, String dose_per_day, String total_day,
			String refill_number, String notes, String consumption_mode,
			String after_food, String initial_dispatch_quantity) {
		super();
		this.id = id;
		this.medicine_id = medicine_id;
		this.medicine_name = medicine_name;
		this.prescription_id = prescription_id;
		this.medicines_per_dose = medicines_per_dose;
		this.medicine_unit = medicine_unit;
		this.dose_per_day = dose_per_day;
		this.total_day = total_day;
		this.refill_number = refill_number;
		this.notes = notes;
		this.consumption_mode = consumption_mode;
		this.after_food = after_food;
		this.initial_dispatch_quantity = initial_dispatch_quantity;
	}

	/*******
	 * Model used for showing data in the dialog in add prescription list
	 * 
	 * @param medicine_id
	 * @param medicine_name
	 * @param medicines_per_dose
	 * @param medicine_unit
	 * @param medicine_unit_id
	 * @param dose_per_day
	 * @param no_of_days
	 * @param refill_number
	 * @param notes
	 * @param frequencyId
	 * @param frequencyName
	 * @param AfterFood
	 * @param BeforeFood
	 * @param Morning
	 * @param AfterNoon
	 * @param Evening
	 * @param Night
	 */
	public MedicinsModel(String medicine_id, String medicine_name,
			String medicines_per_dose, String medicine_unit,
			String medicine_unit_id, String dose_per_day, String no_of_days,
			String refill_number, String notes, String frequencyId,
			String frequencyName, String afterFood, String afterFoodId,  String morning,String morningId,
			String afterNoon,String afterNoonId, String evening,String eveningId, String night,String nightId) {
		super();
		this.medicine_name = medicine_name;
		this.medicine_id = medicine_id;
		this.medicines_per_dose = medicines_per_dose;
		this.medicine_unit = medicine_unit;
		this.medicine_unit_id = medicine_unit_id;
		this.dose_per_day = dose_per_day;
		this.total_day = no_of_days;
		this.refill_number = refill_number;
		this.notes = notes;
		this.frequencyId = frequencyId;
		this.frequencyName = frequencyName;
		this.after_food = afterFood;
		this.after_foodId = afterFoodId;
		this.mornig = morning;
		this.mornigId = morningId;
		this.afterNoon = afterNoon;
		this.afterNoonId = afterNoonId;
		this.evening = evening;
		this.eveningId = eveningId;
		this.night = night;
		this.nightId = nightId;

	}

	public void readFromParcel(Parcel in) {
		this.id = in.readString();
		this.medicine_id = in.readString();
		this.medicine_name = in.readString();
		this.prescription_id = in.readString();
		this.medicines_per_dose = in.readString();
		this.medicine_unit = in.readString();
		this.medicine_unit_id = in.readString();
		this.dose_per_day = in.readString();
		this.total_day = in.readString();
		this.refill_number = in.readString();
		this.notes = in.readString();
		this.consumption_mode = in.readString();
		this.after_food = in.readString();
		this.initial_dispatch_quantity = in.readString();
		this.frequencyId = in.readString();
		this.frequencyName = in.readString();
	}

	public MedicinsModel(Parcel source) {
		super();
		readFromParcel(source);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(id);
		dest.writeString(medicine_id);
		dest.writeString(medicine_name);
		dest.writeString(prescription_id);
		dest.writeString(medicines_per_dose);
		dest.writeString(medicine_unit);
		dest.writeString(medicine_unit_id);
		dest.writeString(dose_per_day);
		dest.writeString(total_day);
		dest.writeString(refill_number);
		dest.writeString(notes);
		dest.writeString(consumption_mode);
		dest.writeString(after_food);
		dest.writeString(initial_dispatch_quantity);
		dest.writeString(frequencyId);
		dest.writeString(frequencyName);
	}
}
