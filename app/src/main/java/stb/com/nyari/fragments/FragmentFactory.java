package stb.com.nyari.fragments;

import android.support.v4.app.Fragment;

public class FragmentFactory {
	
	public Fragment getFragment(int groupPos, int childPos) {
		Fragment fragment = null;
		if (groupPos < 2) {
			switch (groupPos) {
			case 0:
				fragment = new Fragment1();
				break;
			case 1:
				fragment = new Fragment2();
				break;
			default:
				break;
			}
		} else if (groupPos == 2) {
			switch (childPos) {
    		case 0:
    			fragment = new Fragment3();
    			break;
    		case 1:
    			fragment = new Fragment3();
    			break;
    		default:
    			break;
    		}
		}
		return fragment;
	}
}
