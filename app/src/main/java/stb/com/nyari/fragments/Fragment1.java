package stb.com.nyari.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import stb.com.nyari.activities.MapsActivity;
import stb.com.nyari.R;

public class Fragment1 extends Fragment {

	private Button mBtTest;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment1, container, false);
		mBtTest = (Button)rootView.findViewById(R.id.bt_test);

		mBtTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getActivity(), MapsActivity.class);
				startActivity(intent);
			}
		});

		return rootView;
	}

}
