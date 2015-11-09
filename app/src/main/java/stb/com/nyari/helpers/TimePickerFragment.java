/*
* Copyright (C) Mitrais 2014, Vital Sign Monitoring
*/

package stb.com.nyari.helpers;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import stb.com.nyari.R;

/**
 * Time Picker Fragment will show Time Picker Widget as Dialog Fragment.
 * If exist, this fragment will get initial time from extras value. 
 * Otherwise it will set the initial date from current date.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	
	private TimeListener tListener;
	
	private static TimePickerFragment timePicker;

	public TimePickerFragment(){}
	
	public static TimePickerFragment newInstance(TimeListener listener, Bundle extras){
		if (timePicker == null) {
			timePicker = new TimePickerFragment();
		}
		timePicker.tListener = listener;
		timePicker.setArguments(extras);
		return timePicker;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getDefault());
		int mHour;
		int mMinute;
		String mTitle;
		
		Bundle extras = getArguments();
	    if (extras != null && !extras.isEmpty()) {
	    	mHour = extras.getInt(getString(R.string.extra_date_hour));
	    	mMinute = extras.getInt(getString(R.string.extra_date_minute));
	    	mTitle = extras.getString(getString(R.string.extra_title_dialog));
		}else {
			mHour = c.get(Calendar.HOUR_OF_DAY);
			mMinute = c.get(Calendar.MINUTE);
			mTitle = getString(R.string.dialog_set_time);
		}
	    TimePickerDialog timePicker = new TimePickerDialog(getActivity(), this, mHour, mMinute, true);
	    timePicker.setTitle(mTitle);
//	    timePicker.setIcon(R.drawable.ic_launcher);
	    timePicker.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
		return timePicker;
	}

	/* (non-Javadoc)
	 * @see android.app.TimePickerDialog.OnTimeSetListener#onTimeSet(android.widget.TimePicker, int, int)
	 */
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		tListener.onTimeSet(view, hourOfDay, minute);
	}
	
	/**
	 * Time Listener Interface
	 *
	 */
	public interface TimeListener{
		public void onTimeSet(TimePicker view, int hourOfDay, int minute);
	}
}
