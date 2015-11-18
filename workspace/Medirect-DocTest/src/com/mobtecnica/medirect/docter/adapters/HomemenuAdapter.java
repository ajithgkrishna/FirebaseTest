package com.mobtecnica.medirect.docter.adapters;

import java.util.List;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.models.MasterDataItem;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomemenuAdapter extends ArrayAdapter<MasterDataItem> {

	private int resource;
	private LayoutInflater mLayoutInflater;
	Context context;
	// int drawable_menu_icons[] = { R.drawable.home, R.drawable.profile,
	// R.drawable.patients, R.drawable.appoint, R.drawable.prescri,
	// R.drawable.wallet, R.drawable.message, R.drawable.settings };
	//
	int drawable_menu_icons[] = { R.drawable.home, R.drawable.profile,
			R.drawable.appoint, R.drawable.prescri, R.drawable.wallet,
			R.drawable.message,R.drawable.prescri };

	String drawable_menu_lables[] = new String[drawable_menu_icons.length];
	TextView txtmenulable;

	public HomemenuAdapter(Context ctx, int resourceId,
			List<MasterDataItem> objects) {

		super(ctx, resourceId, objects);
		resource = resourceId;
		context = ctx;
		mLayoutInflater = LayoutInflater.from(ctx);
		drawable_menu_lables[0] = ctx.getResources().getString(
				R.string.menu_home);
		drawable_menu_lables[1] = ctx.getResources().getString(
				R.string.menu_profile);
		// drawable_menu_lables[2] = ctx.getResources().getString(
		// R.string.menu_patients);
		drawable_menu_lables[2] = ctx.getResources().getString(
				R.string.menu_appointments);
		drawable_menu_lables[3] = ctx.getResources().getString(
				R.string.menu_patient);
		drawable_menu_lables[4] = ctx.getResources().getString(
				R.string.menu_wallet);
		drawable_menu_lables[5] = ctx.getResources().getString(
				R.string.menu_inbox);
		drawable_menu_lables[6] = ctx.getResources().getString(
				R.string.menu_education);
		// drawable_menu_lables[7] = ctx.getResources().getString(
		// R.string.menu_settings);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = (LinearLayout) mLayoutInflater.inflate(resource, null);

		MasterDataItem item = (MasterDataItem) getItem(position);

		txtmenulable = (TextView) convertView
				.findViewById(R.id.textViewmenutitle);
		if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Drawable img = context.getResources().getDrawable(
					drawable_menu_icons[position]);
			txtmenulable.setCompoundDrawablesWithIntrinsicBounds(img, null,
					null, null);
			txtmenulable.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
		} else {
			Drawable img = context.getResources().getDrawable(
					drawable_menu_icons[position]);
			txtmenulable.setCompoundDrawablesWithIntrinsicBounds(null, img,
					null, null);
			txtmenulable.setGravity(Gravity.CENTER);
		}

		txtmenulable.setText(drawable_menu_lables[position]);
		return convertView;
	}
}