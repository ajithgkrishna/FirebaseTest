package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.ItemsListActivity;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.Inbox_Users_ListAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.RecentMessageModel;
import com.mobtecnica.medirect.docter.utils.Utilities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NewMessageFragment extends Fragment {
	EditText editTextSearchKeyword;

	TextView textViewSearch;
	ListView expandableListViewSearchUsersList;
	EditText message;
	Button send;
	TextView menuname;
	View viewSelected = null;
	public LinearLayout linearList, linearNodataView;
	boolean user_position_clicked = false;
	int last_position_clicked;
	public ArrayList<Profile_Model> prof_serch_resultlist = new ArrayList<Profile_Model>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.new_message_fragment,
				container, false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.NewMessage));

		initializeViews(rootView);
		initializeListeners(rootView);
		buildUI(rootView);
		return rootView;
	}

	private void buildUI(View rootView) {
		// TODO Auto-generated method stub
		if (prof_serch_resultlist.isEmpty()) {
			linearList.setVisibility(View.GONE);
			linearNodataView.setVisibility(View.VISIBLE);
		} else {
			linearList.setVisibility(View.VISIBLE);
			linearNodataView.setVisibility(View.GONE);
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (prof_serch_resultlist.isEmpty()) {
			linearList.setVisibility(View.GONE);
			linearNodataView.setVisibility(View.VISIBLE);

		} else {
			linearList.setVisibility(View.VISIBLE);
			linearNodataView.setVisibility(View.GONE);
			ListView expandableListViewSearchUsersList = (ListView) getView()
					.findViewById(R.id.expandableListViewSearchUsersList);
			Inbox_Users_ListAdapter inboxAdapter = new Inbox_Users_ListAdapter(
					getActivity(), prof_serch_resultlist);
			expandableListViewSearchUsersList.setAdapter(inboxAdapter);
		}
	}

	private void initializeListeners(View rootView) {
		textViewSearch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Utilities.getInstance(getActivity()).isNetAvailable()) {
					HttpRequestHelper.user_search_inbox(getActivity(),
							editTextSearchKeyword.getText().toString().trim());
					editTextSearchKeyword.setText("");
				} else {
					Toast.makeText(getActivity(), R.string.error_internet,
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (user_position_clicked == false) {
					Toast.makeText(getActivity(), R.string.choose_user,
							Toast.LENGTH_SHORT).show();
				} else if (message.getText().toString().trim().length() == 0) {
					Toast.makeText(getActivity(),
							R.string.error_field_required, Toast.LENGTH_SHORT)
							.show();
				} else {
					if (Utilities.getInstance(getActivity()).isNetAvailable()) {
						HttpRequestHelper.sendMessage(
								getActivity(),
								prof_serch_resultlist
										.get(last_position_clicked).getId(),
								getActivity().getSharedPreferences(
										LoginActivity.PREFS_LOGIN_STATUS,
										Context.MODE_PRIVATE).getString(
										LoginActivity.PREFS_USERID, ""),
								message.getText().toString().trim(),
								"from_new_msg");
						message.setText("");
					} else {
						Toast.makeText(getActivity(), R.string.error_internet,
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

		expandableListViewSearchUsersList
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						last_position_clicked = position;
						user_position_clicked = true;
						if (Utilities.getInstance(getActivity())
								.isNetAvailable()) {
							HttpRequestHelper
									.getMessages_item(
											getActivity(),
											new RecentMessageModel(
													prof_serch_resultlist.get(position)
													.getId(),
											prof_serch_resultlist.get(position)
													.getFirst_name()
													+ " "
													+ prof_serch_resultlist
															.get(position)
															.getLast_name(),
													prof_serch_resultlist.get(
															position)
															.getAddress(),
													prof_serch_resultlist.get(
															position).getAge(),
													prof_serch_resultlist.get(
															position)
															.getAccount_no(),
													prof_serch_resultlist.get(
															position)
															.getEmail(),
													prof_serch_resultlist.get(
															position)
															.getPhone(),
													prof_serch_resultlist.get(
															position)
															.getPhoto()),
											getActivity()
													.getSharedPreferences(
															LoginActivity.PREFS_LOGIN_STATUS,
															Context.MODE_PRIVATE)
													.getString(
															LoginActivity.PREFS_USERID,
															""),
											prof_serch_resultlist.get(position)
													.getId(), position);
						} else {
							Toast.makeText(getActivity(),
									R.string.error_internet, Toast.LENGTH_SHORT)
									.show();
						}

					}
				});
		message.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (viewSelected != null) {
					viewSelected.setSelected(true);
				}
			}
		});

	}

	private void initializeViews(View rootView) {
		editTextSearchKeyword = (EditText) rootView
				.findViewById(R.id.editTextSearchKeyword);
		textViewSearch = (TextView) rootView.findViewById(R.id.textViewSearch);
		expandableListViewSearchUsersList = (ListView) rootView
				.findViewById(R.id.expandableListViewSearchUsersList);
		message = (EditText) rootView.findViewById(R.id.message);
		send = (Button) rootView.findViewById(R.id.send);
		linearList = (LinearLayout) rootView.findViewById(R.id.linearlistdata);
		linearNodataView = (LinearLayout) rootView
				.findViewById(R.id.nodatalinear);
	}

}
