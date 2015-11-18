package com.mobtecnica.medirect.docter.fragments;

import java.util.ArrayList;

import com.mobtecnica.medirect.docter.R;
import com.mobtecnica.medirect.docter.R.id;
import com.mobtecnica.medirect.docter.R.layout;
import com.mobtecnica.medirect.docter.adapters.HomemenuAdapter;
import com.mobtecnica.medirect.docter.models.MasterDataItem;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemsListFragment extends Fragment {
	private HomemenuAdapter adapterItems;
	private ListView lvItems;

	private OnItemSelectedListener listener;

	public interface OnItemSelectedListener {
		public void onItemSelected(MasterDataItem i);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnItemSelectedListener) {
			listener = (OnItemSelectedListener) activity;
		} else {
			throw new ClassCastException(
					activity.toString()
							+ " must implement ItemsListFragment.OnItemSelectedListener");
		}

		// Item i = adapterItems.getItem(0);
		// // Fire selected event for item
		// listener.onItemSelected(i);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Create arraylist from item fixtures
		ArrayList<MasterDataItem> items = MasterDataItem.getItems();
		adapterItems = new HomemenuAdapter(getActivity(),
				R.layout.menu_list_item, items);

	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return super.getView();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate view
		View view = inflater.inflate(R.layout.fragment_items_list, container,
				false);
		// Bind adapter to ListView
		lvItems = (ListView) view.findViewById(R.id.lvItems);
		lvItems.performItemClick(lvItems, 1, lvItems.getItemIdAtPosition(1));
		lvItems.setAdapter(adapterItems);

		lvItems.setSelection(0);
		lvItems.setSelected(true);
		
		// Item i = adapterItems.getItem(0);
		// // Fire selected event for item
		// listener.onItemSelected(i);
		
		lvItems.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View item,
					int position, long rowId) {
				// Retrieve item based on position
				MasterDataItem i = adapterItems.getItem(position);
				// Fire selected event for item
				listener.onItemSelected(i);
				item.setPressed(true);
				
			}
			
		});
		return view;
	}

	
	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		lvItems.setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
				: ListView.CHOICE_MODE_NONE);
	}
}
