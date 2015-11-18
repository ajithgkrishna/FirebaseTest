package com.mobtecnica.medirect.docter.dateSlider.labeler;

import android.content.Context;

import com.mobtecnica.medirect.docter.widgets.dateSlider.timeview.DayTimeLayoutView;
import com.mobtecnica.medirect.docter.widgets.dateSlider.timeview.TimeView;

/**
 * A Labeler that displays days using DayTimeLayoutViews.
 */
public class DayDateLabeler extends DayLabeler {
	/**
	 * The format string that specifies how to display the day. Since this class
	 * uses a DayTimeLayoutView, the format string should consist of two strings
	 * separated by a space.
	 *
	 * @param formatString
	 */
	public DayDateLabeler(String formatString) {
		super(formatString);
	}

	@Override
	public TimeView createView(Context context, boolean isCenterView) {
		return new DayTimeLayoutView(context, isCenterView, 20, 25, 0.8f);
	}
}