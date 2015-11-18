package com.mobtecnica.medirect.docter.adapters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.models.PromotionModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PromotionListAdapter extends BaseAdapter{

	Context context;
	ArrayList<PromotionModel> promotionList;

	public PromotionListAdapter(Context activity,
			ArrayList<PromotionModel> patModel) {
		// TODO Auto-generated constructor stub
		context = activity;
		this.promotionList = patModel;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return promotionList.size();
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
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = null;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.promotionlist, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.txtName);
			holder.date = (TextView) convertView.findViewById(R.id.txtDate);
			holder.time = (TextView) convertView.findViewById(R.id.txtTime);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();
		if (promotionList.isEmpty()) {
			Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();
		}else {
			final PromotionModel appo_item = promotionList.get(position);
			holder.name.setText(appo_item.getTitle());
			holder.date.setText(appo_item.getDateTime().toString().split(" ")[0]);
			SimpleDateFormat f1 = new SimpleDateFormat("HH:mm:ss");
			String date = appo_item.getDateTime().toString().split(" ")[1];
			try {
				Date d = f1.parse(date);
				SimpleDateFormat f2 = new SimpleDateFormat("h:mma");
				date =  f2.format(d);
			
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			holder.time.setText(date);
		}
		return convertView;
	}

	public static class ViewHolder {

		public TextView name;
		public TextView date;
		public TextView time;


	}
}
