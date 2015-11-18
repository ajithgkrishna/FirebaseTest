package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;

import org.w3c.dom.Text;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.fragments.ViewPrescriptionFragment;
import com.mobtecnica.medirect.docter.models.PatientModel;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.pkmmte.circularimageview.CircularImageView;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UsersListAdapter extends BaseExpandableListAdapter {

	Context context;
	ArrayList<Profile_Model> patModel;
	Button buttonViewPrescriptions;
	Button newprescriptions;
	ImageView imgArrows;
	FragmentActivity fractivity;

	TextView textViewPatientName;
	TextView textViewaccountNumId;
	CircularImageView profileImage;
	TextView patientaddressValue;
	TextView emailValue;
	TextView phoneValue;

	public UsersListAdapter(Context activity,
			ArrayList<Profile_Model> patModel, FragmentActivity fractivity) {
		// TODO Auto-generated constructor stub
		this.context = activity;
		this.fractivity = fractivity;
		this.patModel = patModel;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {

			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.prescription_list_item,
					null, true);

		}

		childviewInitializations(convertView);
		childviewClickListeners(groupPosition, childPosition);
		buildChildUI(convertView, groupPosition);
		return convertView;
	}

	private void buildChildUI(View convertView, int childPosition) {
		// profileImage
		patientaddressValue.setText(patModel.get(childPosition).getAddress());
		emailValue.setText(patModel.get(childPosition).getEmail());
		phoneValue.setText(patModel.get(childPosition).getPhone());

	}

	private void childviewInitializations(View convertView) {

		buttonViewPrescriptions = (Button) convertView
				.findViewById(R.id.buttonViewPrescriptions);
		newprescriptions = (Button) convertView
				.findViewById(R.id.newprescriptions);
		profileImage = (CircularImageView) convertView
				.findViewById(R.id.profileImage);
		patientaddressValue = (TextView) convertView
				.findViewById(R.id.patientaddressValue);
		emailValue = (TextView) convertView.findViewById(R.id.emailValue);
		phoneValue = (TextView) convertView.findViewById(R.id.phoneValue);

	}

	private void childviewClickListeners(final int groupPosition,
			int childPosition) {

		buttonViewPrescriptions.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Utilities.getInstance(context).isNetAvailable()) {
					HttpRequestHelper.viewSinglePatientPrescriptionList(
							fractivity, patModel.get(groupPosition).getId(),
							patModel.get(groupPosition));
				} else {
					Toast.makeText(context, R.string.error_internet,
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		newprescriptions.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Utilities.getInstance(context).isNetAvailable()) {
					/*HttpRequestHelper.getAllFrequencies(fractivity,
							patModel.get(groupPosition));*/
				} else {
					Toast.makeText(context, R.string.error_internet,
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return patModel.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {

			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(
					R.layout.exp_prescription_list_group, null, true);

		}

		groupviewInitializations(convertView);

		buildGroupUI(convertView, groupPosition);
		if (isExpanded) {
			imgArrows.setBackgroundResource(R.drawable.up_arrow);
		} else {
			imgArrows.setBackgroundResource(R.drawable.down_arrow);
		}
		/*
		 * TextView medicinename = (TextView) convertView
		 * .findViewById(R.id.patientaddress);
		 * 
		 * medicinename.setText("hhhhhhh");
		 */
		return convertView;
	}

	private void buildGroupUI(View convertView, int groupPosition) {
		textViewPatientName.setText(patModel.get(groupPosition).getFirst_name()
				+ " " + patModel.get(groupPosition).getLast_name());
		textViewaccountNumId.setText(patModel.get(groupPosition)
				.getAccount_no());

	}

	private void groupviewInitializations(View convertView) {
		imgArrows = (ImageView) convertView.findViewById(R.id.imageViewdownUp);
		textViewPatientName = (TextView) convertView
				.findViewById(R.id.textViewPatientName);
		textViewaccountNumId = (TextView) convertView
				.findViewById(R.id.textViewaccountNumId);
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
