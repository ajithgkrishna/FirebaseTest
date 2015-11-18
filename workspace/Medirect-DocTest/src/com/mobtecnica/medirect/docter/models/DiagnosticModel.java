package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class DiagnosticModel implements Parcelable {

	String id;
	String prescription_id;
	String diagnostic_id;
	String value;
	String diagnostic_type;
	String test_name;
	String sample;
	String result;
	String def_value;

	public DiagnosticModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static Parcelable.Creator<DiagnosticModel> getCreator() {
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
		dest.writeString(prescription_id);
		dest.writeString(diagnostic_id);
		dest.writeString(value);
		dest.writeString(diagnostic_type);
		dest.writeString(test_name);
		dest.writeString(sample);
		dest.writeString(result);
		dest.writeString(def_value);

	}

	public DiagnosticModel(Parcel source) {
		readFromParcel(source);
	}

	public DiagnosticModel(String id, String prescription_id,
			String diagnostic_id, String value, String diagnostic_type,
			String test_name, String sample, String result, String def_value) {
		super();
		this.id = id;
		this.prescription_id = prescription_id;
		this.diagnostic_id = diagnostic_id;
		this.value = value;
		this.diagnostic_type = diagnostic_type;
		this.test_name = test_name;
		this.sample = sample;
		this.result = result;
		this.def_value = def_value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrescription_id() {
		return prescription_id;
	}

	public void setPrescription_id(String prescription_id) {
		this.prescription_id = prescription_id;
	}

	public String getDiagnostic_id() {
		return diagnostic_id;
	}

	public void setDiagnostic_id(String diagnostic_id) {
		this.diagnostic_id = diagnostic_id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDiagnostic_type() {
		return diagnostic_type;
	}

	public void setDiagnostic_type(String diagnostic_type) {
		this.diagnostic_type = diagnostic_type;
	}

	public String getTest_name() {
		return test_name;
	}

	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}

	public String getSample() {
		return sample;
	}

	public void setSample(String sample) {
		this.sample = sample;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDef_value() {
		return def_value;
	}

	public void setDef_value(String def_value) {
		this.def_value = def_value;
	}

	private void readFromParcel(Parcel source) {
		this.id = source.readString();
		this.prescription_id = source.readString();
		this.diagnostic_id = source.readString();
		this.value = source.readString();
		this.diagnostic_type = source.readString();
		this.test_name = source.readString();
		this.sample = source.readString();
		this.result = source.readString();
		this.def_value = source.readString();
		

	}

	public static final Parcelable.Creator<DiagnosticModel> CREATOR = new Creator<DiagnosticModel>() {

		@Override
		public DiagnosticModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DiagnosticModel[size];
		}

		@Override
		public DiagnosticModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new DiagnosticModel(source);
		}
	};

}
