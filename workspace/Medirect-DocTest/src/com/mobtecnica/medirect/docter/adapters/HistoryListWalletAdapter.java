package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.fragments.WalletsFragment;
import com.mobtecnica.medirect.docter.models.PatientModel;
import com.mobtecnica.medirect.docter.models.WalletHistory;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HistoryListWalletAdapter extends BaseAdapter {

	private Activity activity;

	ArrayList<WalletHistory> wallet_model;
	int count = 0;

	public HistoryListWalletAdapter(Activity activity,
			ArrayList<WalletHistory> wallet_model) {
		super();
		this.activity = activity;
		this.wallet_model = wallet_model;
		// inflater = (LayoutInflater) activity
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return wallet_model.size();
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

	/**
	 * getView
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		// create views
		if (convertView == null) {
			LayoutInflater inflater = null;
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.wallet_list_item, null);
			holder = new ViewHolder();

			holder.textViewDate = (TextView) convertView
					.findViewById(R.id.textViewDate); // title

			holder.textViewTime = (TextView) convertView
					.findViewById(R.id.textViewTime);
			holder.textViewDebitCredit = (TextView) convertView
					.findViewById(R.id.textViewDebitCredit);
			holder.textViewBalance = (TextView) convertView
					.findViewById(R.id.textViewBalance);
			// holder.condition = (TextView) convertView
			// .findViewById(R.id.textViewConditionofRecentPatient);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (wallet_model.size() <= 0) {
			holder.textViewDate.setText("No History Found ");

		} else {

			holder.textViewDate.setText(wallet_model.get(position)
					.getDatetime().split(" ")[0]);
			holder.textViewTime.setText(wallet_model.get(position)
					.getDatetime().split(" ")[1]);
			holder.textViewDebitCredit.setText(wallet_model.get(position)
					.getTransaction_type_name());
			holder.textViewBalance.setText(wallet_model.get(position)
					.getBalance());
		}

		return convertView;
	}

	public static class ViewHolder {

		public TextView textViewDate;
		public TextView textViewTime;
		public TextView textViewDebitCredit;
		public TextView textViewBalance;

	}
}