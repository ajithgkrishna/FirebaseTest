package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.models.PromotionModel;
import com.mobtecnica.medirect.docter.models.ViewPromotionModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

public class EducationDetailFragment extends Fragment {

	private static String promotionDetailTag = "promotionDetail";
	private TextView menuname;
	private TextView txtHeading;
	private WebView webContent;
	private ViewPromotionModel promotionModel;
	/****
	 * method for creating instance of class with the bundle values.
	 * 
	 * @param data
	 * @return
	 */

	public static EducationDetailFragment newInstanceEducationFragment(
			ViewPromotionModel data) {
		Bundle bundle = new Bundle();
		bundle.putParcelable(promotionDetailTag, data);
		EducationDetailFragment fragment = new EducationDetailFragment();
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_education_detail,
				container, false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_education));
		initializeViews(rootView);
		initializeListeners(rootView);
		buildUI(rootView);
		return rootView;
	}

	private void initializeViews(View rootView) {
		if (rootView != null) {
			txtHeading = (TextView) rootView.findViewById(R.id.txtHeading);
			webContent = (WebView) rootView.findViewById(R.id.webViewEducation);
		}
	}

	private void initializeListeners(View rootView) {
		// TODO Auto-generated method stub
		
	}

	private void buildUI(View rootView) {
		if (rootView != null) {
			Bundle bun = getArguments();
			if (!bun.isEmpty()) {
				promotionModel = bun.getParcelable(promotionDetailTag);
			}
			if (promotionModel != null) {
				txtHeading.setText(promotionModel.getTitle());
				webContent.loadData(promotionModel.getContent(), "text/html", "UTF-8");
			
			}
		}
	}
}
