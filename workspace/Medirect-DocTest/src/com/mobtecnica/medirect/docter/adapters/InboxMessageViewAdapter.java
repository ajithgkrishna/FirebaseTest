package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.RecentMessageModel;
import com.pkmmte.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InboxMessageViewAdapter extends BaseAdapter {

	Context context;
	ArrayList<RecentMessageModel> recentMessageModellist;
	TextView patientname;
	TextView datetime;
	/* TextView sender; */
	CircularImageView pic;
	TextView textViewmessagecontent;
	TextView textViewNoofUnreadMessages;

	public InboxMessageViewAdapter(FragmentActivity activity,
			ArrayList<RecentMessageModel> recentMessageModellist) {
		// TODO Auto-generated constructor stub
		context = activity;
		this.recentMessageModellist = recentMessageModellist;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return recentMessageModellist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {

			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.inboxmessageviewadapter,
					null, true);
			patientname = (TextView) convertView.findViewById(R.id.patientname);
			datetime = (TextView) convertView.findViewById(R.id.datetime);
			/* sender = (TextView) convertView.findViewById(R.id.sender); */
			textViewmessagecontent = (TextView) convertView
					.findViewById(R.id.textViewmessagecontent);
			textViewNoofUnreadMessages = (TextView) convertView
					.findViewById(R.id.textViewNoofUnreadMessages);
			pic = (CircularImageView) convertView.findViewById(R.id.pic);

			patientname.setText(recentMessageModellist.get(position).getName());
			datetime.setText(recentMessageModellist.get(position).getDate());
			/* sender.setText("You"); */
			textViewmessagecontent.setText(recentMessageModellist.get(position)
					.getContent());
			Picasso.with(context)
					.load(recentMessageModellist.get(position).getPhoto())
					.placeholder(R.drawable.ic_profile)
					.error(R.drawable.ic_profile).into(pic);

			if (Integer.parseInt(recentMessageModellist.get(position)
					.getNew_messages()) > 0) {
				textViewNoofUnreadMessages.setText(recentMessageModellist.get(
						position).getNew_messages());
				textViewNoofUnreadMessages.setVisibility(View.VISIBLE);
			} else {
				textViewNoofUnreadMessages.setVisibility(View.GONE);
			}

		}

		return convertView;
	}
}
