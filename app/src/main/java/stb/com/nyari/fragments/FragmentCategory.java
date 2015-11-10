package stb.com.nyari.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import stb.com.nyari.R;
import stb.com.nyari.activities.MapsActivity;
import stb.com.nyari.helpers.Helper;
import stb.com.nyari.helpers.HttpClient;
import stb.com.nyari.models.Category;
import stb.com.nyari.models.adapters.CategoryAdapter;

public class FragmentCategory extends Fragment {

	private View rootView;
	private ListView categoryListView;
	private CategoryAdapter categoryAdapter;
	private List<Category> categoryData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_category, container, false);
		bindUIComponent();
		initUIComponent();

		Category cat = new Category();
		cat.setCode("a");
		cat.setName("name");
		cat.setAddress("address");
		cat.setPhone("phone");
		Category cat2 = new Category();
		cat2.setCode("b");
		cat2.setName("name");
		cat2.setAddress("address");
		cat2.setPhone("phone");

		categoryData = new ArrayList<Category>();
		categoryData.add(cat);
		categoryData.add(cat2);

		categoryAdapter = new CategoryAdapter(getActivity(), R.layout.list_view_item_category, categoryData);
		categoryListView.setAdapter(categoryAdapter);

		return rootView;
	}

	private void bindUIComponent() {
		categoryListView = (ListView) rootView.findViewById(R.id.lv_category_list);
	}

	private void initUIComponent() {
		categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				getCategoryPosition(categoryData.get(position).getCode());
				Intent intent = new Intent(getActivity().getApplicationContext(), MapsActivity.class);
//				intent = setExtra(intent, icd9);
				startActivity(intent);
			}
		});

	}

	private void getCategoryPosition(String code) {
	}
}
