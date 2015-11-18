package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.PatientModel;
import com.mobtecnica.medirect.docter.models.PrescriptionDetailModel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPrescriptionAdapter extends BaseExpandableListAdapter {

	Context context;
	ArrayList<PrescriptionDetailModel> presDetModel;
	ImageView imgArrows;
	FragmentActivity fractivity;
	TextView textViewDocterNameValue;
	TextView textViewdateValue;
	TextView textViewMedicinname;
	TextView textViewdoss;
	TextView textViewperdayDoss;
	TextView textViewnoofdays;
	TextView textViewRefillno;
	TextView textViewNotes;

	public ViewPrescriptionAdapter(Context activity,
			ArrayList<PrescriptionDetailModel> presDetModel,
			FragmentActivity fractivity) {
		// TODO Auto-generated constructor stub
		context = activity;
		this.presDetModel = presDetModel;
		this.fractivity = fractivity;

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
			convertView = inflater.inflate(R.layout.prescription_view_item,
					null, true);

		}
		childviewInitializations(convertView);
		childviewClickListeners(groupPosition, childPosition);
		buildChildUI(convertView, childPosition, groupPosition);
		return convertView;
	}

	private void buildChildUI(View convertView, int childPosition,
			int groupPosition) {
		textViewMedicinname.setText(presDetModel.get(groupPosition)
				.getMedicinsModel().get(childPosition).getMedicine_name());
		textViewdoss.setText(presDetModel.get(groupPosition).getMedicinsModel()
				.get(childPosition).getMedicines_per_dose()
				+ " "
				+ presDetModel.get(groupPosition).getMedicinsModel()
						.get(childPosition).getMedicine_unit());
		textViewperdayDoss.setText(presDetModel.get(groupPosition)
				.getMedicinsModel().get(childPosition).getDose_per_day());
		textViewnoofdays.setText(presDetModel.get(groupPosition)
				.getMedicinsModel().get(childPosition).getTotal_day());
		textViewRefillno.setText(presDetModel.get(groupPosition)
				.getMedicinsModel().get(childPosition).getRefill_number());
		/*
		 * textViewNotes.setText(context.getResources().getString(R.string.Notes)
		 * );
		 */
		textViewNotes.setText(presDetModel.get(groupPosition)
				.getMedicinsModel().get(childPosition).getNotes());

	}

	private void childviewInitializations(View convertView) {
		textViewMedicinname = (TextView) convertView
				.findViewById(R.id.textViewMedicinname);
		textViewdoss = (TextView) convertView.findViewById(R.id.textViewdoss);
		textViewperdayDoss = (TextView) convertView
				.findViewById(R.id.textViewperdayDoss);
		textViewnoofdays = (TextView) convertView
				.findViewById(R.id.textViewnoofdays);
		textViewRefillno = (TextView) convertView
				.findViewById(R.id.textViewRefillno);
		textViewNotes = (TextView) convertView.findViewById(R.id.textViewNotes);
	}

	private void childviewClickListeners(final int groupPosition,
			final int childPosition) {
		/*
		 * textViewNotes.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { setNoteDialog(groupPosition,
		 * childPosition); }
		 * 
		 * });
		 */
	}

	/*
	 * private void setNoteDialog(int groupPosition, int childPosition) { final
	 * Dialog dialog = new Dialog(context);
	 * dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	 * dialog.setContentView(R.layout.dialog_note_pop); dialog.getWindow()
	 * .setBackgroundDrawable(new ColorDrawable(Color.WHITE)); dialog.show();
	 * TextView textViewMedicinName = (TextView) dialog
	 * .findViewById(R.id.textViewMedicinName); TextView textViewMedicinNote =
	 * (TextView) dialog .findViewById(R.id.textViewMedicinNote);
	 * textViewMedicinName.setText(presDetModel.get(groupPosition)
	 * .getMedicinsModel().get(childPosition).getMedicine_name());
	 * textViewMedicinNote.setText(presDetModel.get(groupPosition)
	 * .getMedicinsModel().get(childPosition).getNotes()); Button
	 * buttonClosedialog = (Button) dialog
	 * .findViewById(R.id.buttonClosedialog);
	 * buttonClosedialog.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { dialog.dismiss();
	 * 
	 * } });
	 * 
	 * }
	 */

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return presDetModel.get(groupPosition).getMedicinsModel().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return presDetModel.size();
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
					R.layout.exp_view_prescription_list_group, null, true);

		}
		groupviewInitializations(convertView);
		buildGroupUI(convertView, groupPosition);

		if (isExpanded) {
			imgArrows.setBackgroundResource(R.drawable.up_arrow);
		} else {

			imgArrows.setBackgroundResource(R.drawable.down_arrow);
		}
		imgArrows.setVisibility(View.GONE);

		/*
		 * TextView medicinename = (TextView) convertView
		 * .findViewById(R.id.patientaddress);
		 * 
		 * medicinename.setText("hhhhhhh");
		 */
		return convertView;
	}

	private void buildGroupUI(View convertView, int groupPosition) {

		textViewDocterNameValue.setText(presDetModel.get(groupPosition)
				.getDoctor_name());
		textViewdateValue
				.setText(presDetModel.get(groupPosition).getAdded_on());
	}

	private void groupviewInitializations(View convertView) {
		imgArrows = (ImageView) convertView.findViewById(R.id.imageViewdownUp);
		textViewDocterNameValue = (TextView) convertView
				.findViewById(R.id.textViewDocterNameValue);
		textViewdateValue = (TextView) convertView
				.findViewById(R.id.textViewdateValue);
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
