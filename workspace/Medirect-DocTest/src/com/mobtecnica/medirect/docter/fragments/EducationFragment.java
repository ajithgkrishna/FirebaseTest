package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.PromotionListAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.PromotionModel;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.Utilities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class EducationFragment extends Fragment {

	private static String promotionsTag = "promotions";
	TextView menuname;
	private ListView lstEducationList;
	private ArrayList<PromotionModel> promotionModelList;

	public EducationFragment() {
		// TODO Auto-generated constructor stub
	}

	/****
	 * method for creating instance of class with the bundle values.
	 * 
	 * @param data
	 * @return
	 */

	public static EducationFragment newInstanceEducationFragment(
			ArrayList<PromotionModel> data) {
		Bundle bundle = new Bundle();
		bundle.putParcelableArrayList(promotionsTag, data);
		EducationFragment fragment = new EducationFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_education,
				container, false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_education));
		initializeViews(rootView);
		initializeListeners(rootView);
		buildUI(rootView);
		return rootView;
	}

	private void buildUI(View rootView) {
		if (rootView != null) {
			Bundle bun = getArguments();
			if (!bun.isEmpty()) {
				promotionModelList = bun.getParcelableArrayList(promotionsTag);
			}
			/***
			 * condition for adding data to listview
			 */
			if (!promotionModelList.isEmpty()) {
				PromotionListAdapter adapter = new PromotionListAdapter(
						getActivity(), promotionModelList);
				lstEducationList.setAdapter(adapter);
			}
		}

	}

	private void initializeListeners(View rootView) {
		if (rootView != null) {
			lstEducationList.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Config.LogError("Promotion ID",  promotionModelList.get(position).getId());
					HttpRequestHelper.getViewPromotion(getActivity(), getActivity().getSharedPreferences(
										LoginActivity.PREFS_LOGIN_STATUS,
										Context.MODE_PRIVATE).getString(
										LoginActivity.PREFS_USERID, ""), promotionModelList.get(position).getId());
				}
			});
		}
	}

	private void initializeViews(View rootView) {
		if (rootView != null) {
			lstEducationList = (ListView) rootView
					.findViewById(R.id.lstEducationList);
		}

	}

}
