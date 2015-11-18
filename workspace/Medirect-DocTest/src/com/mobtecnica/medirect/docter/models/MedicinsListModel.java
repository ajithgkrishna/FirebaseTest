package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MedicinsListModel implements Parcelable {

	String id;
	String name;
	String generic_name;
	String brand_name;
	String company_name;
	String chemical_name;
	String introduction;
	String indication;
	String contra_indication;
	String special_precaution;
	String side_effects;
	String drug_interaction;
	String prescription_required;
	String dosage;
	String measurement;
	String perunit;
	String medicine_unit_id;

	public MedicinsListModel(String id, String name, String medicine_unit_id,
			String medicine_unit_name) {
		super();
		this.id = id;
		this.name = name;
		this.medicine_unit_id = medicine_unit_id;
		this.medicine_unit_name = medicine_unit_name;
	}

	String medicine_unit_name;
	String original_price;
	String discounted_price;
	String image;
	String stock_quantity;
	String status;
	String doctor_commission;
	String medicine_category_id;

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getMedicine_unit_id() {
		return medicine_unit_id;
	}

	public void setMedicine_unit_id(String medicine_unit_id) {
		this.medicine_unit_id = medicine_unit_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGeneric_name() {
		return generic_name;
	}

	public void setGeneric_name(String generic_name) {
		this.generic_name = generic_name;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getChemical_name() {
		return chemical_name;
	}

	public void setChemical_name(String chemical_name) {
		this.chemical_name = chemical_name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getContra_indication() {
		return contra_indication;
	}

	public void setContra_indication(String contra_indication) {
		this.contra_indication = contra_indication;
	}

	public String getSpecial_precaution() {
		return special_precaution;
	}

	public void setSpecial_precaution(String special_precaution) {
		this.special_precaution = special_precaution;
	}

	public String getSide_effects() {
		return side_effects;
	}

	public void setSide_effects(String side_effects) {
		this.side_effects = side_effects;
	}

	public String getDrug_interaction() {
		return drug_interaction;
	}

	public void setDrug_interaction(String drug_interaction) {
		this.drug_interaction = drug_interaction;
	}

	public String getPrescription_required() {
		return prescription_required;
	}

	public void setPrescription_required(String prescription_required) {
		this.prescription_required = prescription_required;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getPerunit() {
		return perunit;
	}

	public void setPerunit(String perunit) {
		this.perunit = perunit;
	}

	public String getMedicine_unit_name() {
		return medicine_unit_name;
	}

	public void setMedicine_unit_name(String medicine_unit_name) {
		this.medicine_unit_name = medicine_unit_name;
	}

	public String getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(String original_price) {
		this.original_price = original_price;
	}

	public String getDiscounted_price() {
		return discounted_price;
	}

	public void setDiscounted_price(String discounted_price) {
		this.discounted_price = discounted_price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(String stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDoctor_commission() {
		return doctor_commission;
	}

	public void setDoctor_commission(String doctor_commission) {
		this.doctor_commission = doctor_commission;
	}

	public String getMedicine_category_id() {
		return medicine_category_id;
	}

	public void setMedicine_category_id(String medicine_category_id) {
		this.medicine_category_id = medicine_category_id;
	}

	public MedicinsListModel(String id, String name, String generic_name,
			String brand_name, String company_name, String chemical_name,
			String introduction, String indication, String contra_indication,
			String special_precaution, String side_effects,
			String drug_interaction, String prescription_required,
			String dosage, String measurement, String perunit,
			String medicine_unit_name, String original_price,
			String discounted_price, String image, String stock_quantity,
			String status, String doctor_commission, String medicine_category_id,String medicine_unit_id) {
		super();
		this.id = id;
		this.name = name;
		this.generic_name = generic_name;
		this.brand_name = brand_name;
		this.company_name = company_name;
		this.chemical_name = chemical_name;
		this.introduction = introduction;
		this.indication = indication;
		this.contra_indication = contra_indication;
		this.special_precaution = special_precaution;
		this.side_effects = side_effects;
		this.drug_interaction = drug_interaction;
		this.prescription_required = prescription_required;
		this.dosage = dosage;
		this.measurement = measurement;
		this.perunit = perunit;
		this.medicine_unit_name = medicine_unit_name;
		this.original_price = original_price;
		this.discounted_price = discounted_price;
		this.image = image;
		this.stock_quantity = stock_quantity;
		this.status = status;
		this.doctor_commission = doctor_commission;
		this.medicine_category_id = medicine_category_id;
		this.medicine_unit_id = medicine_unit_id;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(introduction);
		dest.writeString(name);
		dest.writeString(generic_name);
		dest.writeString(brand_name);
		dest.writeString(indication);
		dest.writeString(contra_indication);
		dest.writeString(special_precaution);
		dest.writeString(side_effects);
		dest.writeString(drug_interaction);
		dest.writeString(prescription_required);
		dest.writeString(dosage);
		dest.writeString(measurement);
		dest.writeString(perunit);
		dest.writeString(medicine_unit_name);
		dest.writeString(original_price);
		dest.writeString(discounted_price);
		dest.writeString(image);
		dest.writeString(stock_quantity);
		dest.writeString(status);
		dest.writeString(doctor_commission);
		dest.writeString(doctor_commission);
		dest.writeString(medicine_category_id);
		dest.writeString(medicine_unit_id);

	}

	public MedicinsListModel(Parcel source) {
		super();
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {
		this.id = source.readString();
		this.name = source.readString();
		this.generic_name = source.readString();
		this.brand_name = source.readString();
		this.company_name = source.readString();
		this.chemical_name = source.readString();
		this.introduction = source.readString();
		this.indication = source.readString();
		this.contra_indication = source.readString();
		this.special_precaution = source.readString();
		this.side_effects = source.readString();
		this.drug_interaction = source.readString();
		this.prescription_required = source.readString();
		this.dosage = source.readString();
		this.measurement = source.readString();
		this.perunit = source.readString();
		this.medicine_unit_name = source.readString();
		this.original_price = source.readString();
		this.discounted_price = source.readString();
		this.image = source.readString();
		this.stock_quantity = source.readString();
		this.status = source.readString();
		this.doctor_commission = source.readString();
		this.medicine_category_id = source.readString();
		this.medicine_unit_id = source.readString();
	}

	public static final Parcelable.Creator<MedicinsListModel> CREATOR = new Creator<MedicinsListModel>() {

		@Override
		public MedicinsListModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new MedicinsListModel[size];
		}

		@Override
		public MedicinsListModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new MedicinsListModel(source);
		}
	};
}
