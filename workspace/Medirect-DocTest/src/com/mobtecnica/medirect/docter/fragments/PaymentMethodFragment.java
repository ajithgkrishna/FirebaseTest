package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.util.TextUtils;

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.PaymentMethodModels;
import com.mobtecnica.medirect.docter.models.PurchaseOrderModel;
import com.mobtecnica.medirect.docter.utils.Config;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentMethodFragment extends Fragment {
	TextView menuname;
	private ListView lstPayment;
	private String addressId, prescriptionId,itemSelectedID = "";
	private ArrayList<PurchaseOrderModel> purchaseOrderList;
	public ArrayList<PaymentMethodModels> datalist = new ArrayList<PaymentMethodModels>();
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_payment_method,
				container, false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_confirm_order));
		initializeViews(rootView);
		initializeListeners(rootView);
		buildUI(rootView);

		return rootView;
	}

	

	private void buildUI(View rootView) {
		if (rootView != null) {
			Bundle bun = getArguments();
			if (!bun.isEmpty()) {
				prescriptionId = bun.getString("prescriptionId");
				addressId = bun.getString("addressId");
				purchaseOrderList = bun.getParcelableArrayList("purchaseOrder");
				datalist = bun.getParcelableArrayList("paymentMethods");
			}
			loadDataFromServer();
		}
	}

	private void initializeListeners(View rootView) {
		if (rootView != null) {
			lstPayment.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					itemSelectedID = datalist.get(position).getId();
					
				}
			});
			
			((TextView)rootView.findViewById(R.id.txtConfirm)).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (checkItemSelectedOrNot()) {
						HashMap<String, String> idSets = new HashMap<String, String>();
						idSets.put(
								"userId",
								getActivity().getSharedPreferences(
										LoginActivity.PREFS_LOGIN_STATUS,
										Context.MODE_PRIVATE).getString(
										LoginActivity.PREFS_USERID, ""));
						idSets.put("prescription_id", prescriptionId);
						String paymentId = null;
						int pos  = lstPayment.getSelectedItemPosition() + 1;
						paymentId = datalist.get(pos).getId();
						idSets.put("payment_method_id",paymentId);
						idSets.put("shipping_id", addressId);
						
				
						Config.LogError("prescription_id", prescriptionId);
						Config.LogError("paymentId", paymentId);
						Config.LogError("shipping_id", addressId);
						HttpRequestHelper.placeOrder(getActivity(), idSets,purchaseOrderList);
					}else {
						Toast.makeText(getActivity(), "Please select one option", Toast.LENGTH_SHORT).show();
					}
					
				}
			});
		}
	}
	
	private boolean checkItemSelectedOrNot(){
		if (TextUtils.isEmpty(itemSelectedID)) {
			return false;
		}else {
			return true;
		}
		
	}
	private void initializeViews(View rootView) {
		// TODO Auto-generated method stub
		if (rootView != null) {
			lstPayment = (ListView) rootView
					.findViewById(R.id.lstPaymentMethodList);
		}

	}
	
	private void loadDataFromServer() {
		// TODO Auto-generated method stub
		if (datalist.isEmpty()) {
			HashMap<String, String> idSets = new HashMap<String, String>();
			idSets.put(
					"userId",
					getActivity().getSharedPreferences(
							LoginActivity.PREFS_LOGIN_STATUS,
							Context.MODE_PRIVATE).getString(
							LoginActivity.PREFS_USERID, ""));
			HttpRequestHelper.getPaymentMethods(getActivity(), idSets);
		}else {
			loadDataToListView();
		}
	}

	public void loadDataToListView() {
		if (!datalist.isEmpty()) {
			ArrayList<String> paymentNamelist = new ArrayList<String>();
			for (int i = 0; i < datalist.size(); i++) {
				paymentNamelist.add(datalist.get(i).getName());
			}
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity()
					.getApplicationContext(),
					R.layout.checkbox_list_item,
					paymentNamelist);
			lstPayment.setAdapter(arrayAdapter);
			/*lstPayment.setSelected(true);*/
			
		}
	}

}
