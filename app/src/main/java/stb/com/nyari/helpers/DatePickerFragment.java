/*
 * Copyright (C) Mitrais 2014, Vital Sign Monitoring
 */

package stb.com.nyari.helpers;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.CalendarView;
import android.widget.DatePicker;

import stb.com.nyari.R;

/**
 * Date Picker Fragment will show Date Picker as Dialog Fragment. If exist, this
 * fragment will get initial date from extras value and set minimum date if
 * necessary Otherwise it will set the initial date from current date.
 * 
 */
public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {
	private boolean isHasMinDate;
	private DateListener mListener;
	private int mYear;
	private int mMonth;
	private int mDay;

	private static DatePickerFragment datePicker;

	public DatePickerFragment() {

	}

	public static DatePickerFragment newInstance(DateListener listener, Bundle extras) {
		if (datePicker == null) {
			datePicker = new DatePickerFragment();
		}
		datePicker.mListener = listener;
		datePicker.setArguments(extras);
		return datePicker;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.DialogFragment#onCreateDialog(android.os.Bundle)
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		Bundle extras = getArguments();
		mYear = extras.getInt(getString(R.string.extra_date_year),
				c.get(Calendar.YEAR));
		mMonth = extras.getInt(getString(R.string.extra_date_month),
				c.get(Calendar.MONTH));
		mDay = extras.getInt(getString(R.string.extra_date_day),
				c.get(Calendar.DAY_OF_MONTH));
		isHasMinDate = extras.getBoolean(
				getString(R.string.extra_is_has_minimum_date), false);
		DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
				this, mYear, mMonth, mDay);
		// datePickerDialog.setIcon(R.drawable.ic_launcher);
		setMinimumDate(extras, datePickerDialog);
		datePickerDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
		return datePickerDialog;
	}

	/**
	 * Set Minimum Date
	 * 
	 * @param extras
	 * @param dialog
	 */
	private void setMinimumDate(Bundle extras, DatePickerDialog dialog) {
		if (isHasMinDate) {
			int minYear = extras
					.getInt(getString(R.string.extra_min_date_year));
			int minMonth = extras
					.getInt(getString(R.string.extra_min_date_month));
			int minDay = extras.getInt(getString(R.string.extra_min_date_day));
			DatePicker datePicker = dialog.getDatePicker();
			Calendar minDate = new GregorianCalendar(minYear, minMonth, minDay);
			datePicker.setMinDate(minDate.getTimeInMillis());
			fixUpDatePickerCalendarView(datePicker, minDate);
		}
	}

	/**
	 * FixUp Date Picker Calendar View Workaround for CalendarView bug relating
	 * to setMinDate(): https://code.google.com/p/android/issues/detail?id=42750
	 * Set then reset the date on the calendar so that it properly shows today's
	 * date. The choice of 24 months is arbitrary.
	 * 
	 * @param datePicker
	 * @param date
	 */
	private void fixUpDatePickerCalendarView(DatePicker datePicker,
			Calendar date) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			final CalendarView cal = datePicker.getCalendarView();
			if (cal != null) {
				date.add(Calendar.MONTH, 24);
				cal.setDate(date.getTimeInMillis(), false, true);
				date.add(Calendar.MONTH, -24);
				cal.setDate(date.getTimeInMillis(), false, true);
				// set date to last selected date
				Calendar curDate = new GregorianCalendar(mYear, mMonth, mDay);
				cal.setDate(curDate.getTimeInMillis());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.app.DatePickerDialog.OnDateSetListener#onDateSet(android.widget
	 * .DatePicker, int, int, int)
	 */
	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		mListener.onDateSet(view, year, month, day);
	}

	/**
	 * Interface for Date Listener.
	 *
	 */
	public interface DateListener {
		public void onDateSet(DatePicker view, int year, int month, int day);
	}
}