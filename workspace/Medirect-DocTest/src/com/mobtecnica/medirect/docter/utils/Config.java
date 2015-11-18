package com.mobtecnica.medirect.docter.utils;

import android.util.Log;

public class Config {

	private static boolean isRunnable = false;
	
	public static void LogError(String tag,String msg){
		if (!isRunnable) {
			Log.e(tag, msg);
		}
	}
	public static void LogDebug(String tag,String msg){
		if (!isRunnable) {
			Log.d(tag, msg);
		}
	}
	
}
