package com.mobtecnica.medirect.docter.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;

public  class ShowProgresDialog {
	
	private static ProgressDialog showProgress(Activity activity){
		ProgressDialog progress = ProgressDialog.show(activity, "Loading", "Loading...", true,
				true, new DialogInterface.OnCancelListener() {

					@Override
					public void onCancel(DialogInterface dialog) {

					}
				});
		return progress ;
	}
	public static void dismissProress(ProgressDialog  progress){
		if (progress != null) {
			if (progress.isShowing()) {
				progress.cancel();
				progress.dismiss();
			}
		}
	}
	
}
