package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobtecnica.medirect.docter.ItemsListActivity;
import com.mobtecnica.medirect.docter.LoginActivity;
import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.adapters.InboxMessageViewAdapter;
import com.mobtecnica.medirect.docter.connection.HttpRequestHelper;
import com.mobtecnica.medirect.docter.models.RecentMessageModel;
import com.mobtecnica.medirect.docter.utils.Utilities;
import com.pkmmte.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class InboxViewMessage extends Fragment {
	ImageButton imageButtonwallet_nav;
	ImageButton imageViewmessage_nav;
	ImageButton imageViewuser_nav;
	TextView menuname, Address, Age, Phone, Name, AccountNo;
	EditText Message;
	Button sendMessage;
	CircularImageView userpic;
	TextView textViewNewMessage;
	ListView listViewInboxList;
	InboxMessageViewAdapter messageadapter;
	ArrayList<RecentMessageModel> RecentMessageItemModelList = new ArrayList<RecentMessageModel>();
	LinearLayout messageListId;
	RecentMessageModel RecentMessageModelProf;
	ScrollView scrollMessage;
	private final String PREFS_LOGIN_STATUS = "LOGIN";

	// ImageButton imageButtonwallet_nav;
	// ImageButton imageViewmessage_nav;

	public InboxViewMessage() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.inboxviewmessage, container,
				false);
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

		// scroll.fullScroll(View.FOCUS_DOWN) ;

		// scroll.scrollTo(0, scroll.getBottom());

		// new Handler().postDelayed(new Runnable() {
		//
		// @Override
		// public void run() {
		// TODO Auto-generated method stub
		scrollMessage.fullScroll(View.FOCUS_DOWN);
		// }
		// }, 2000);
		Bundle bundle = getArguments();
		if (RecentMessageItemModelList != null) {
			RecentMessageItemModelList = bundle
					.getParcelableArrayList("RecentMessageItemModelList");
			RecentMessageModelProf = bundle
					.getParcelable("RecentMessageModelProf");
			Address.setText(RecentMessageModelProf.getAddress());
			Age.setText(RecentMessageModelProf.getAge());

			Phone.setText(RecentMessageModelProf.getPhone());

			Name.setText(RecentMessageModelProf.getName());
			AccountNo.setText(RecentMessageModelProf.getAccountNo());
			Picasso.with(getActivity())
					.load(RecentMessageModelProf.getPhoto())
					.placeholder(R.drawable.ic_profile)
					.error(R.drawable.ic_profile).into(userpic);
		}

		ArrayList<LinearLayout> from_comment = new ArrayList<LinearLayout>();
		ArrayList<LinearLayout> to_comment = new ArrayList<LinearLayout>();
		if (RecentMessageItemModelList != null) {
			if (RecentMessageItemModelList.size() > 0) {

				for (int i = 0; i < RecentMessageItemModelList.size(); i++) {

					if (RecentMessageItemModelList
							.get(i)
							.getFrom_user_id()
							.equalsIgnoreCase(
									getActivity().getSharedPreferences(
											LoginActivity.PREFS_LOGIN_STATUS,
											Context.MODE_PRIVATE).getString(
											LoginActivity.PREFS_USERID, ""))) {
						LinearLayout messageItem = new LinearLayout(
								getActivity());

						messageItem.setBackgroundColor(getResources().getColor(
								R.color.from_col));
						messageItem.setPadding(10, 5, 5, 5);
						messageItem.setId(i);
						messageItem.setOrientation(LinearLayout.VERTICAL);
						LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
								WindowManager.LayoutParams.WRAP_CONTENT,
								WindowManager.LayoutParams.WRAP_CONTENT);
						rlp.gravity = Gravity.RIGHT | Gravity.END;
						rlp.setMargins(50, 10, 10, 10);
						TextView comment = new TextView(getActivity());

						comment.setText(RecentMessageItemModelList.get(i)
								.getContent());
						comment.setPadding(5, 5, 5, 5);
						comment.setGravity(Gravity.RIGHT);
						// Defining the layout parameters of the TextView
						LinearLayout.LayoutParams leftrule = new LinearLayout.LayoutParams(
								RelativeLayout.LayoutParams.WRAP_CONTENT,
								RelativeLayout.LayoutParams.WRAP_CONTENT);
						leftrule.gravity = Gravity.RIGHT;
						leftrule.setMargins(100, 0, 5, 5);

						// Setting the parameters on the TextView
						messageItem.setLayoutParams(leftrule);
						from_comment.add(messageItem);
						messageItem.addView(comment);
						View view = new View(getActivity());
						LinearLayout.LayoutParams viewPar = new LinearLayout.LayoutParams(
								RelativeLayout.LayoutParams.MATCH_PARENT, 1);
						viewPar.gravity = Gravity.RIGHT;
						viewPar.setMargins(5, 0, 5, 5);
						messageItem.addView(view);
						view.setLayoutParams(viewPar);
						view.setBackgroundColor(getActivity().getResources()
								.getColor(R.color.message_devider));
						TextView textDateTime = new TextView(getActivity());
						textDateTime.setGravity(Gravity.RIGHT);
						textDateTime.setText(RecentMessageItemModelList.get(i)
								.getDate());

						messageItem.setLayoutParams(leftrule);
						messageItem.addView(textDateTime);
						messageListId.addView(messageItem);
					} else {

						LinearLayout messageItem = new LinearLayout(
								getActivity());

						messageItem.setBackgroundColor(getResources().getColor(
								R.color.to_col));
						messageItem.setPadding(5, 5, 10, 5);
						messageItem.setId(i);
						messageItem.setOrientation(LinearLayout.VERTICAL);
						LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
								WindowManager.LayoutParams.WRAP_CONTENT,
								WindowManager.LayoutParams.WRAP_CONTENT);
						rlp.gravity = Gravity.RIGHT | Gravity.END;
						rlp.setMargins(10, 10, 100, 10);
						TextView comment = new TextView(getActivity());

						comment.setText(RecentMessageItemModelList.get(i)
								.getContent());
						comment.setPadding(5, 5, 5, 5);
						// Defining the layout parameters of the TextView
						LinearLayout.LayoutParams leftrule = new LinearLayout.LayoutParams(
								RelativeLayout.LayoutParams.WRAP_CONTENT,
								RelativeLayout.LayoutParams.WRAP_CONTENT);
						leftrule.gravity = Gravity.LEFT;
						leftrule.setMargins(5, 0, 5, 5);

						// Setting the parameters on the TextView
						messageItem.setLayoutParams(leftrule);
						from_comment.add(messageItem);
						messageItem.addView(comment);

						View view = new View(getActivity());
						LinearLayout.LayoutParams viewPar = new LinearLayout.LayoutParams(
								RelativeLayout.LayoutParams.MATCH_PARENT, 1);
						viewPar.gravity = Gravity.LEFT;
						viewPar.setMargins(5, 0, 5, 5);
						messageItem.addView(view);
						view.setLayoutParams(viewPar);
						view.setBackgroundColor(getActivity().getResources()
								.getColor(R.color.message_devider));
						TextView textDateTime = new TextView(getActivity());

						textDateTime.setLayoutParams(leftrule);
						messageItem.addView(textDateTime);
						textDateTime.setText(RecentMessageItemModelList.get(i)
								.getDate());

						messageListId.addView(messageItem);

					}

				}

			}
		}
	}

	private void initializeViews(View rootView) {
		// imageButtonwallet_nav = (ImageButton) rootView
		// .findViewById(R.id.imageButtonwallet_nav);
		// imageViewmessage_nav = (ImageButton) rootView
		// .findViewById(R.id.imageViewmessage_nav);
		imageViewuser_nav = (ImageButton) rootView
				.findViewById(R.id.imageViewuser_nav);
		Address = (TextView) rootView.findViewById(R.id.address);
		Age = (TextView) rootView.findViewById(R.id.age);

		Phone = (TextView) rootView.findViewById(R.id.phone);

		Name = (TextView) rootView.findViewById(R.id.name);
		AccountNo = (TextView) rootView.findViewById(R.id.accountno);
		Message = (EditText) rootView.findViewById(R.id.message);
		sendMessage = (Button) rootView.findViewById(R.id.sendMessage);
		userpic = (CircularImageView) rootView.findViewById(R.id.userpic);
		messageListId = (LinearLayout) rootView
				.findViewById(R.id.messageListId);
		scrollMessage = (ScrollView) rootView.findViewById(R.id.scrollmessage);
	}

	private void initializeListeners(View rootView) {
		// imageButtonwallet_nav.setOnClickListener(new
		// View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		// imageViewmessage_nav.setOnClickListener(new
		// View.OnClickListener() {
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
		sendMessage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// sending Message
				System.out.println("RecentMessageModelProf.getId(): "
						+ RecentMessageModelProf.getId());
				RecentMessageModel ss = RecentMessageModelProf;
				if (Message.getText().toString().trim().length() > 0) {
					if (Utilities.getInstance(getActivity()).isNetAvailable()) {

						HttpRequestHelper.sendMessage(
								getActivity(),
								RecentMessageModelProf.getId().toString()
										.trim(),
								getActivity().getSharedPreferences(
										LoginActivity.PREFS_LOGIN_STATUS,
										Context.MODE_PRIVATE).getString(
										LoginActivity.PREFS_USERID, ""),
								Message.getText().toString(),
								"from_inbox_view_msg");
						Message.setText("");
					}
				} else {
					Toast.makeText(getActivity(), R.string.emptymsgwarning,
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

}
