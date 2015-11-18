package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class DiagnosticsTestModel implements Parcelable {
	private String id;
	private String type;
	private String test;
	private String testname;
	private String unit;
	private String default_value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}

	public String getDefault_value() {
		return default_value;
	}

	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}

	public DiagnosticsTestModel(String id, String type, String test,
			String testname, String unit, String default_value) {
		super();
		this.id = id;
		this.type = type;
		this.test = test;
		this.testname = testname;
		this.unit = unit;
		this.default_value = default_value;
	}

	public DiagnosticsTestModel(Parcel source) {
		// TODO Auto-generated constructor stub
		super();
		readFromParcel(source);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void readFromParcel(Parcel in) {
		this.id = in.readString();
		this.type = in.readString();
		this.test = in.readString();
		this.testname = in.readString();
		this.unit = in.readString();
		this.default_value = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(id);
		dest.writeString(type);
		dest.writeString(test);
		dest.writeString(testname);
		dest.writeString(unit);
		dest.writeString(default_value);
	}

	@Override
	public String toString() {
		return "Diagnostic_Model [type=" + type + ", test=" + test
				+ ", testname=" + testname + ", value=" + unit
				+ ", default_value=" + default_value + "]";
	}

	public static final Parcelable.Creator<DiagnosticsTestModel> CREATOR = new Creator<DiagnosticsTestModel>() {

		@Override
		public DiagnosticsTestModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DiagnosticsTestModel[size];
		}

		@Override
		public DiagnosticsTestModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new DiagnosticsTestModel(source);
		}

	};
}
