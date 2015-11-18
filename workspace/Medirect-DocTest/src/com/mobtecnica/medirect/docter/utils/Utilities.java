package com.mobtecnica.medirect.docter.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mobtecnica.medirect.docter.R;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Utilities {

	private static Utilities mUtilities;
	private static Context mContext;

	/**
	 * Constructor
	 */
	public Utilities() {
		super();
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	public static Utilities getInstance(Context context) {
		mContext = context;
		if (mUtilities == null) {
			mUtilities = new Utilities();

		}
		return mUtilities;
	}

	/**
	 * Checking the Internet Connectivity
	 * 
	 * @return the status of the Internet connection
	 */
	public Boolean isNetAvailable() {

		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) mContext
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo wifiInfo = connectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			NetworkInfo mobileInfo = connectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

			if (wifiInfo != null) {

				if (wifiInfo.isConnected()) {

					return true;
				}
			}

			if (mobileInfo != null) {
				if (mobileInfo.isConnected()) {
					return true;

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkCondition(int value) {

		if (value % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}
	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
			return;

		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(),
				MeasureSpec.UNSPECIFIED);
		int totalHeight = 0;
		View view = null;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			view = listAdapter.getView(i, view, listView);
			if (i == 0)
				view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth,
						LayoutParams.WRAP_CONTENT));

			view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
			totalHeight += view.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	public void changeCurrentFragment(Fragment fragment, String tag,
			FragmentActivity act) {
		if (fragment != null) {

			FragmentManager fragmentManager = act.getSupportFragmentManager();
			clearBackStack(fragmentManager, act);
			fragmentManager.beginTransaction()
					.replace(R.id.flDetailContainer, fragment, tag).commit();

		} else {
			// error in creating fragment
			Log.e("HomeActivity", "Error in creating fragment");
		}
	}
/**
 * 
 * @param fm
 * @param act
 */
	public void clearBackStack(FragmentManager fm, FragmentActivity act) {
		int backStackCount = act.getSupportFragmentManager()
				.getBackStackEntryCount();
		for (int i = 0; i < backStackCount; i++) {

			// Get the back stack fragment id.
			int backStackId = act.getSupportFragmentManager()
					.getBackStackEntryAt(i).getId();

			// Log.e("popBackStack", getSupportFragmentManager()
			// .getBackStackEntryAt(i).getName());

			fm.popBackStack(backStackId,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);

		}
	}
/**
 * 
 * @param fragment
 * @param tag
 * @param act
 */
	public void changeChildFragment(Fragment fragment, String tag,
			FragmentActivity act) {
		if (fragment != null) {

			FragmentManager fragmentManager = act.getSupportFragmentManager();
			fragmentManager.beginTransaction().addToBackStack(null);
			fragmentManager.beginTransaction()
					.replace(R.id.flDetailContainer, fragment, tag)
					.addToBackStack(tag).commit();

		} else {
			// error in creating fragment
			Log.e("HomeActivity@changeChildFragment",
					"Error in creating fragment");
		}
	}
	
	public void changeFragmentWithoutAddingBack(Fragment fragment, String tag,
			FragmentActivity act) {
		if (fragment != null) {

			FragmentManager fragmentManager = act.getSupportFragmentManager();
			fragmentManager.beginTransaction().addToBackStack(null);
			fragmentManager.beginTransaction()
					.replace(R.id.flDetailContainer, fragment, tag)
					.addToBackStack(null).commit();

		} else {
			// error in creating fragment
			Log.e("HomeActivity@changeChildFragment",
					"Error in creating fragment");
		}
	}

	public int getAge(int _year, int _month, int _day) {

		GregorianCalendar cal = new GregorianCalendar();
		int y, m, d, a;

		y = cal.get(Calendar.YEAR);
		m = cal.get(Calendar.MONTH);
		d = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(_year, _month, _day);
		a = y - cal.get(Calendar.YEAR);
		if ((m < cal.get(Calendar.MONTH))
				|| ((m == cal.get(Calendar.MONTH)) && (d < cal
						.get(Calendar.DAY_OF_MONTH)))) {
			--a;
		}

		return a;
	}
	/*****
	 * Code For email validation
	 * 
	 * @param email 
	 * @return if valid email type it returns true
	 */
	public boolean isValidMail(String email) 
	{
	    boolean check;
	    Pattern p;
	    Matcher m;

	    String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	    p = Pattern.compile(EMAIL_STRING);

	    m = p.matcher(email);
	    check = m.matches();
	    return check;
	}
	/*****
	 * This function returns true if the mobile number contains only 10 digits.
	 * @param phone2
	 * @return 
	 */
	
	public boolean isValidMobile(String phone) 
	{
	    boolean check = false;
	    if(phone.length() == 10)
	    {
	        check = true;
	    }
	    
	    return check;
	}
	/*****
	 * This function is used to check the length of the pinCode.
	 * @param pinCode
	 * @return returns true if the pinCode length is between 6 and 10 only.
	 */
	public boolean isValidPinCode(String pinCode) 
	{
	    boolean check = false;
	    if(pinCode.length() == 6)
	    {
	    	
	    		check = true;
			
	    }
	   
	    return check;
	}
	/*****
	 * This function is used to check the length of the number in edittext.
	 * @param pinCode
	 * @return returns true if the pinCode length is between 6 and 10 only.
	 */
	public boolean isValidEditTextNumber(String number) 
	{
	    boolean check = false;
	    
	    int a = Integer.parseInt(number);
	    if (a >= 1 && a <= 100) {
	    	check = true;
		}
	   
	    return check;
	}

}
