package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.PurchaseOrderListAdapter;
import com.mobtecnica.medirect.docter.adapters.PurchaseOrderListAdapter.quantityChangedListener;
import com.mobtecnica.medirect.docter.models.PurchaseOrderModel;
import com.mobtecnica.medirect.docter.models.PurchaseOrderPatientDetails;
import com.mobtecnica.medirect.docter.utils.Utilities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PurchaseOrderFragment extends Fragment implements quantityChangedListener {
	TextView menuname;
	private static String prescriptionIdTag = "prescriptionId";
	private static String patientAddressTag = "patientAddress";
	private static String purchaseOrderTag = "purchaseOrder";
	private String prescriptionId;
	private ArrayList<PurchaseOrderModel> purchaseOrderModelList;
	private PurchaseOrderPatientDetails patientDetails;
	private ListView lstvPurchaseOrder;
	private PurchaseOrderListAdapter purchaseOrderAdapter;
	private TextView txtSubTotal,txtProceed;

	public static PurchaseOrderFragment PurchaseOrderFragmentNewInstance(
			ArrayList<PurchaseOrderModel> data, String pres_id,PurchaseOrderPatientDetails patientDetails) {
		Bundle bundle = new Bundle();
		bundle.putString(prescriptionIdTag, pres_id);
		bundle.putParcelableArrayList(purchaseOrderTag, data);
		bundle.putParcelable(patientAddressTag, patientDetails);
		PurchaseOrderFragment fragment=new PurchaseOrderFragment();
		fragment.setArguments(bundle);
		
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_purchase_order,
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
				prescriptionId = bun.getString(prescriptionIdTag);
				purchaseOrderModelList = bun.getParcelableArrayList(purchaseOrderTag);
				patientDetails = bun.getParcelable(patientAddressTag);
			}
			if (!purchaseOrderModelList.isEmpty()) {
				purchaseOrderAdapter = new PurchaseOrderListAdapter(getActivity(), purchaseOrderModelList,this);
				lstvPurchaseOrder.setAdapter(purchaseOrderAdapter);
				Utilities.getInstance(getActivity()).setListViewHeightBasedOnChildren(lstvPurchaseOrder);
			}
			txtSubTotal.setText("Total Rs."+String.valueOf(calculateSubtotal(purchaseOrderModelList)));
		}
	}

	private void initializeListeners(View rootView) {
		txtProceed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!purchaseOrderModelList.isEmpty()) {
					Bundle bundle = new Bundle();
					bundle.putString("prescriptionId", prescriptionId);
					bundle.putParcelableArrayList(purchaseOrderTag, purchaseOrderModelList);
					bundle.putParcelable(patientAddressTag, patientDetails);
					DeliveryAddressFragment fragmentItemDetail = new DeliveryAddressFragment();
					fragmentItemDetail.setArguments(bundle);
					Utilities.getInstance(getActivity().getApplicationContext()).changeChildFragment(
							fragmentItemDetail, "DeliveryAddressFragment", getActivity());
				}else {
					Toast.makeText(getActivity(), "Please add atleast one item", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void initializeViews(View rootView) {
		if (rootView != null) {
			lstvPurchaseOrder  = (ListView) rootView.findViewById(R.id.listPurchaseOrder);
			txtSubTotal = (TextView) rootView.findViewById(R.id.txtSubTotal);
			txtProceed = (TextView) rootView.findViewById(R.id.txtProceed);
		}
	}
	/****
	 * Method for calculating the sub total
	 * @param orderList
	 * @return
	 */
	private Double calculateSubtotal(ArrayList<PurchaseOrderModel> orderList){
		Double a,b,c,total = 0.0;
		if (!orderList.isEmpty()) {
			
			for (int i = 0; i < orderList.size(); i++) {
				a = Double.valueOf(orderList.get(i).getUnit_price());
				b = Double.valueOf(orderList.get(i).getQuantity());
				c = a * b;
				total = c + total ;
			}
		}
		return total;
	}

	@Override
	public void quantityChanged(ArrayList<PurchaseOrderModel> orderList) {
		// TODO Auto-generated method stub
		txtSubTotal.setText("Total Rs."+String.valueOf(calculateSubtotal(orderList)));
		
	}

	@Override
	public void itemDeleted(ArrayList<PurchaseOrderModel> orderList) {
		purchaseOrderModelList = orderList;
		purchaseOrderAdapter.notifyDataSetChanged();
		Utilities.getInstance(getActivity()).setListViewHeightBasedOnChildren(lstvPurchaseOrder);
	}
}
