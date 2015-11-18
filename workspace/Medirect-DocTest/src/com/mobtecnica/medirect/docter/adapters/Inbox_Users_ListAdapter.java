package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.pkmmte.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Inbox_Users_ListAdapter extends BaseAdapter {
	Context ctx;
	LayoutInflater lInflater;
	ArrayList<Profile_Model> data;
	CircularImageView pic;

	public Inbox_Users_ListAdapter(Context context,
			ArrayList<Profile_Model> data) {
		this.ctx = context;
		this.data = data;
		lInflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	} 

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater.inflate(R.layout.single_choice_items, parent,
					false);
			
		}
		pic = (CircularImageView) view.findViewById(R.id.pic);

		((TextView) view.findViewById(R.id.textViewPatientName)).setText(data
				.get(position).getFirst_name()
				+ " "
				+ data.get(position).getLast_name());
		((TextView) view.findViewById(R.id.textViewaccountNumId)).setText(data
				.get(position).getAccount_no());
		Picasso.with(ctx)
				.load(data.get(position).getPhoto())
				.placeholder(R.drawable.ic_profile)
				.error(R.drawable.ic_profile).into(pic);
		return view;
	}
}