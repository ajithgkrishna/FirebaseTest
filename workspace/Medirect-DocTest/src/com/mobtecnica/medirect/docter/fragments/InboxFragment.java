package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.ItemsListActivity;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.InboxMessageViewAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.Profile_Model;
import com.mobtecnica.medirect.docter.models.RecentMessageModel;
import com.mobtecnica.medirect.docter.utils.Config;
import com.mobtecnica.medirect.docter.utils.Utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InboxFragment extends Fragment {
	TextView menuname;
	// ImageButton imageButtonwallet_nav;
	// ImageButton imageViewmessage_nav;
	ImageButton imageViewuser_nav;
	TextView textViewNewMessage;
	ListView listViewInboxList;
	Fragment fragmentItemDetail;

	InboxMessageViewAdapter messageadapter;
	ArrayList<RecentMessageModel> recentMessageModelList = new ArrayList<RecentMessageModel>();
	private final String PREFS_LOGIN_STATUS = "LOGIN";

	public InboxFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_inbox_fragment,
				container, false);
		menuname = (TextView) rootView.findViewById(R.id.menuname);
		menuname.setText(getActivity().getResources().getString(
				R.string.menu_inbox));
		initializeViews(rootView);
		initializeListeners(rootView);
		buildUI(rootView);
		return rootView;
	}

	private void buildUI(View rootView) {
		// TODO Auto-generated method stub
		Bundle extras = getArguments();
		if (extras != null) {
			recentMessageModelList = extras
					.getParcelableArrayList("recentMessageModellist");
		}
		messageadapter = new InboxMessageViewAdapter(getActivity(),
				recentMessageModelList);
		Config.LogError("kkk", "ui");
		/* Log.e("kkk", "ui"); */

		listViewInboxList.setAdapter(messageadapter);

		Config.LogError("kkk", "list completed");
		/* Log.e("kkk", "list completed"); */

	}

	private void initializeViews(View rootView) {

		// imageButtonwallet_nav = (ImageButton) rootView
		// .findViewById(R.id.imageButtonwallet_nav);
		// imageViewmessage_nav = (ImageButton) rootView
		// .findViewById(R.id.imageViewmessage_nav);
		imageViewuser_nav = (ImageButton) rootView
				.findViewById(R.id.imageViewuser_nav);
		textViewNewMessage = (TextView) rootView
				.findViewById(R.id.textViewNewMessage);
		listViewInboxList = (ListView) rootView
				.findViewById(R.id.listViewInboxList);
	}

	private void initializeListeners(View rootView) {

		listViewInboxList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String fromOrtoId;
				if (Utilities.getInstance(getActivity()).isNetAvailable()) {
					if (getActivity()
							.getSharedPreferences(
									LoginActivity.PREFS_LOGIN_STATUS,
									Context.MODE_PRIVATE)
							.getString(LoginActivity.PREFS_USERID, "")
							.equalsIgnoreCase(
									recentMessageModelList.get(position)
											.getTo_user_id())) {
						fromOrtoId = recentMessageModelList.get(position)
								.getFrom_user_id();
					} else {
						fromOrtoId = recentMessageModelList.get(position)
								.getTo_user_id();
					}
					HttpRequestHelper.getMessages_item(
							getActivity(),
							new RecentMessageModel(recentMessageModelList
									.get(position).getTo_user_id(), recentMessageModelList
									.get(position).getName(),
									recentMessageModelList.get(position)
											.getAddress(),
									recentMessageModelList.get(position)
											.getAge(), recentMessageModelList
											.get(position).getAccountNo(),
									recentMessageModelList.get(position)
											.getEmail(), recentMessageModelList
											.get(position).getPhone(),
									recentMessageModelList.get(position)
											.getPhoto()),
							getActivity().getSharedPreferences(
									LoginActivity.PREFS_LOGIN_STATUS,
									Context.MODE_PRIVATE).getString(
									LoginActivity.PREFS_USERID, ""),
							fromOrtoId, position);
				} else {
					Toast.makeText(getActivity(), R.string.error_internet,
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		// imageButtonwallet_nav.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		// imageViewmessage_nav.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		imageViewuser_nav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor = getActivity()
						.getSharedPreferences(PREFS_LOGIN_STATUS,
								Context.MODE_PRIVATE).edit();
				editor.clear();
				editor.commit();
				Toast.makeText(getActivity(),
						"You have been Successfully Logged out !",
						Toast.LENGTH_SHORT).show();
				Intent dashBoardIntent = new Intent(getActivity(),
						LoginActivity.class);
				startActivity(dashBoardIntent);
				getActivity().finish();

			}
		});
		textViewNewMessage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment newMessageFragment = new NewMessageFragment();
				Bundle pDataBundle = new Bundle();

				newMessageFragment.setArguments(pDataBundle);
				Utilities.getInstance(getActivity())
						.changeFragmentWithoutAddingBack(newMessageFragment,
								"NewMessageFragment", getActivity());

			}
		});
	}
}