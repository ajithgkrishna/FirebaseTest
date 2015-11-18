package com.mobtecnica.medirect.docter.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class DiagnosticsModelForAddPrescription implements Parcelable  {
	private String diagnosticsTestId;
	private String diagnosticsTestName;
	private String diagnosticsType;
	private String sample;

	
	
	public DiagnosticsModelForAddPrescription() {
		super();
	}

	public DiagnosticsModelForAddPrescription(String diagnosticsTestId,
			String diagnosticsTestName, String diagnosticsType, String sample) {
		super();
		this.diagnosticsTestId = diagnosticsTestId;
		this.diagnosticsTestName = diagnosticsTestName;
		this.diagnosticsType = diagnosticsType;
		this.sample = sample;
	}

	public String getDiagnosticsTestId() {
		return diagnosticsTestId;
	}

	public void setDiagnosticsTestId(String diagnosticsTestId) {
		this.diagnosticsTestId = diagnosticsTestId;
	}

	public String getDiagnosticsTestName() {
		return diagnosticsTestName;
	}

	public void setDiagnosticsTestName(String diagnosticsTestName) {
		this.diagnosticsTestName = diagnosticsTestName;
	}

	public String getDiagnosticsType() {
		return diagnosticsType;
	}

	public void setDiagnosticsType(String diagnosticsType) {
		this.diagnosticsType = diagnosticsType;
	}

	public String getSample() {
		return sample;
	}

	public void setSample(String sample) {
		this.sample = sample;
	}

	public DiagnosticsModelForAddPrescription(Parcel source) {
		// TODO Auto-generated constructor stub
		super();
		readFromParcel(source);
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		// TODO Auto-generated method stub
		dest.writeString(diagnosticsTestId);
		dest.writeString(diagnosticsTestName);
		dest.writeString(diagnosticsType);
		dest.writeString(sample);
	}


	public void readFromParcel(Parcel in) {
		this.diagnosticsTestId = in.readString();
		this.diagnosticsTestName = in.readString();
		this.diagnosticsType = in.readString();
		this.sample = in.readString();

	}
	public static final Parcelable.Creator<DiagnosticsModelForAddPrescription> CREATOR = new Creator<DiagnosticsModelForAddPrescription>() {

		@Override
		public DiagnosticsModelForAddPrescription[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DiagnosticsModelForAddPrescription[size];
		}

		@Override
		public DiagnosticsModelForAddPrescription createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new DiagnosticsModelForAddPrescription(source);
		}

	};
}
