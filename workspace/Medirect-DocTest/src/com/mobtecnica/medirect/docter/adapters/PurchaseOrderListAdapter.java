package com.mobtecnica.medirect.docter.adapters;

import java.util.ArrayList;

import org.apache.http.util.TextUtils;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.ConfirmPrescriptionAdapter.ViewHolder;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.PurchaseOrderModel;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.pkmmte.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PurchaseOrderListAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<PurchaseOrderModel> orderModelList;
	private quantityChangedListener listener;

	public PurchaseOrderListAdapter(Activity activity,
			ArrayList<PurchaseOrderModel> orderModelList,
			quantityChangedListener listner) {
		this.activity = activity;
		this.orderModelList = orderModelList;
		this.listener = listner;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return orderModelList.size();
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
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = null;
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.purchase_order_list, null);
			holder = new ViewHolder();
			holder.imgPic = (CircularImageView) convertView
					.findViewById(R.id.medicineImage);
			holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
			holder.txtDescription = (TextView) convertView
					.findViewById(R.id.txtDescription);
			holder.txtUnitPrice = (TextView) convertView
					.findViewById(R.id.txtUnitPrice);
			holder.txtSubtotal = (TextView) convertView
					.findViewById(R.id.txtSubTotal);
			holder.edtQuantity = (EditText) convertView
					.findViewById(R.id.edtQuantity);
			holder.imgDelete = (ImageView) convertView
					.findViewById(R.id.imgDelete);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (orderModelList.size() <= 0) {
			// ToDo Add no data view
		} else {
			final PurchaseOrderModel orderModel = orderModelList.get(position);

			Picasso.with(activity)
					.load(orderModel.getImage())
					.placeholder(R.drawable.ic_profile)
					.error(R.drawable.ic_profile).into(holder.imgPic);
			Config.LogError("image path", orderModel.getImage());
			holder.txtName.setText(orderModel.getName());
			holder.txtDescription.setText(orderModel.getIntroduction());
			holder.txtUnitPrice.setText(orderModel.getUnit_price());
			holder.txtSubtotal.setText(String.valueOf(calculateSubTotal(
					orderModel.getQuantity(), orderModel.getUnit_price())));
			holder.edtQuantity.setText(orderModel.getQuantity());

			holder.edtQuantity.addTextChangedListener(new TextWatcher() {
				String text;

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					String changedText = holder.edtQuantity.getText()
							.toString();
					if (!TextUtils.isEmpty(changedText)
							&& Utilities.getInstance(activity)
									.isValidEditTextNumber(changedText)) {
						orderModel.setQuantity(changedText);
						holder.txtSubtotal.setText(String
								.valueOf(calculateSubTotal(changedText,
										orderModel.getUnit_price())));
						listener.quantityChanged(orderModelList);
					}

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				@Override
				public void afterTextChanged(Editable s) {
					text = holder.edtQuantity.getText().toString();
					if (!TextUtils.isEmpty(text)) {
						if (!Utilities.getInstance(activity)
								.isValidEditTextNumber(text)) {
							holder.edtQuantity.setText(orderModel.getQuantity());
						}

					}
				}
			});
			holder.imgDelete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					orderModelList.remove(orderModel);
					listener.itemDeleted(orderModelList);
				}
			});

		}
		return convertView;
	}

	public static class ViewHolder {

		public CircularImageView imgPic;
		public TextView txtName;
		public TextView txtDescription;
		public TextView txtUnitPrice;
		public TextView txtSubtotal;
		public EditText edtQuantity;
		public ImageView imgDelete;

	}
	/****
	 * method for calculating the sub total
	 * @param changedNo represents quantity
	 * @param unitPrice
	 * @return
	 */
	private Double calculateSubTotal(String changedNo, String unitPrice) {
		Double a =Double.valueOf(changedNo);
		Double b = Double.valueOf(unitPrice);
		Double c = a * b;
		return c;
	}
	/****
	 * Interface used for implementing changes to the parent fragment
	 * @author MOBTECNICA DEV #114
	 *
	 */
	public interface quantityChangedListener {
		void quantityChanged(ArrayList<PurchaseOrderModel> orderList);
		void itemDeleted(ArrayList<PurchaseOrderModel> orderList);
	}
}
