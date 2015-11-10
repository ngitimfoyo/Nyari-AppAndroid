package stb.com.nyari.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import stb.com.nyari.R;
import stb.com.nyari.models.adapters.CustomExpandAdapter;
import stb.com.nyari.fragments.FragmentFactory;
import stb.com.nyari.models.LeftMenuModel;

public class MainActivity extends FragmentActivity {

	// Variables
	private DrawerLayout mDrawerLayout;
	private ExpandableListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CustomExpandAdapter mMenuAdapter;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private List<LeftMenuModel> listParent, listChild;
	private HashMap<String, List<LeftMenuModel>> listDataChild;

	// private int selectedPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_main);
		mTitle = mDrawerTitle = getTitle();

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ExpandableListView) findViewById(R.id.listview_drawer);

		initializeLeftMenu();
		mDrawerList.setAdapter(mMenuAdapter);
		mDrawerList.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);

		if (mDrawerLayout != null) {
			// Set a custom shadow that overlays the main content when the
			// drawer opens
			mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
					GravityCompat.START);
			// Enable ActionBar app icon to behave as action to toggle nav
			// drawer
			getActionBar().setHomeButtonEnabled(true);
			getActionBar().setDisplayHomeAsUpEnabled(true);
			setTogleDrawer();
			
			mDrawerLayout.setDrawerListener(mDrawerToggle);
		}
		
		if (savedInstanceState == null) {
			setDisplayView(0);
		}
	}

	private void setTogleDrawer() {
		// ActionBarDrawerToggle ties together the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				invalidateOptionsMenu();
				super.onDrawerClosed(view);

			}

			public void onDrawerOpened(View drawerView) {
				// Set the title on the action when drawer open
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
				super.onDrawerOpened(drawerView);
			}
		};

	}

	private void initializeLeftMenu() {
		listParent = new ArrayList<LeftMenuModel>();
		listChild = new ArrayList<LeftMenuModel>();
		listDataChild = new HashMap<String, List<LeftMenuModel>>();
		// Navigation Drawer of Flight starts
		listParent.add(new LeftMenuModel(getString(R.string.sun),
				R.drawable.sun));
		listParent.add(new LeftMenuModel(getString(R.string.moon),
				R.drawable.moon));
		listParent.add(new LeftMenuModel(getString(R.string.Planets),
				R.drawable.solar_system));

		listChild.add(new LeftMenuModel(getString(R.string.sun),
				R.drawable.solar_system));
		listChild.add(new LeftMenuModel(getString(R.string.moon),
				R.drawable.solar_system));
		listDataChild.put(getString(R.string.sun),
				new ArrayList<LeftMenuModel>());
		listDataChild.put(getString(R.string.moon),
				new ArrayList<LeftMenuModel>());
		listDataChild.put(getString(R.string.Planets), listChild);

		mMenuAdapter = new CustomExpandAdapter(this, listParent, listDataChild);

	}

	@Override
	protected void onResume() {
		super.onResume();
		setGroupClickListener();
		setChildClickListener();
	}

	private void setChildClickListener() {
		mDrawerList.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				Log.d("CHECK", "child click");

				int index = parent.getFlatListPosition(ExpandableListView
						.getPackedPositionForChild(groupPosition, childPosition));
				parent.setItemChecked(index, true);

				setSelectedItem(groupPosition, childPosition);

				return false;
			}
		});

	}

	private void setGroupClickListener() {
		mDrawerList.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				int index = parent.getFlatListPosition(ExpandableListView
						.getPackedPositionForGroup(groupPosition));
				parent.setItemChecked(index, true);
				if (index < 2) {
					setDisplayView(groupPosition);
					if (mDrawerLayout != null) {
						mDrawerLayout.closeDrawer(mDrawerList);
					}
				}
				return false;
			}
		});

	}

	private void setSelectedItem(int groupPos, int childPos) {
		FragmentFactory fragmentFactory = new FragmentFactory();
		// selectedPosition = childPos;
		if (mDrawerLayout != null) {
			mDrawerLayout.closeDrawer(mDrawerList);
		}

		Fragment fragment = null;
		fragment = fragmentFactory.getFragment(groupPos, childPos);
		if (fragment != null) {
			replaceFragment(fragment, childPos);
		}

	}

	private void setDisplayView(int groupPos) {
		FragmentFactory fragmentFactory = new FragmentFactory();
		// update the main content with called Fragment
		Fragment fragment = null;
		fragment = fragmentFactory.getFragment(groupPos, 0);
		if (fragment != null) {
			replaceFragment(fragment, groupPos);
			mDrawerList.setItemChecked(groupPos, true);
			mDrawerList.setSelection(groupPos);
		}

	}

	private void replaceFragment(Fragment fragment, int position) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.setCustomAnimations(R.anim.slide_left_in,
						R.anim.slide_left_out)
				.replace(R.id.content_frame, fragment).commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			if (mDrawerLayout != null) {
				if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
					mDrawerLayout.closeDrawer(mDrawerList);
				} else {
					mDrawerLayout.openDrawer(mDrawerList);
				}
			}
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		if (mDrawerLayout != null) {
			mDrawerToggle.syncState();
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (mDrawerLayout != null) {
			// Pass any configuration change to the drawer toggles
			mDrawerToggle.onConfigurationChanged(newConfig);
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

}
