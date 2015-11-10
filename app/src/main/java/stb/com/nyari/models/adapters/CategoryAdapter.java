package stb.com.nyari.models.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import stb.com.nyari.R;
import stb.com.nyari.models.Category;


public class CategoryAdapter extends ArrayAdapter<Category> {
    List<Category> categories;
    int resource;
    Context context;

    public CategoryAdapter(Context context,
                           int resource,
                           List<Category> categories) {
        super(context, resource, categories);
        this.context = context;
        this.resource = resource;
        this.categories = categories;
    }

    private class ViewHolder {
        TextView name;
        TextView address;
        TextView phone;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        String name = "";
        String address = "";
        String phone = "";

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.tv_cat_name);
            holder.address = (TextView) convertView.findViewById(R.id.tv_cat_address);
            holder.phone = (TextView) convertView.findViewById((R.id.tv_cat_phone));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Set ListView item color
        convertView.setBackgroundResource(
                position % 2 == 0 ? R.drawable.bg_list_item_very_light_purple : R.drawable.bg_list_item_white);

        if (isNotEmptyData(categories.get(position).getName())) name = categories.get(position).getName();
        if (isNotEmptyData(categories.get(position).getAddress())) address = categories.get(position).getAddress();
        if (isNotEmptyData(categories.get(position).getPhone())) phone = categories.get(position).getPhone();

        holder.name.setText("[" + name + "]");
        holder.address.setText(address);
        holder.phone.setText(" : " + phone);

        return convertView;
    }

    /**
     * validate data is empty
     *
     * @param data
     * @return
     */
    private boolean isNotEmptyData(String data) {
        boolean result = true;
        if (data == null) result = false;
        return result;
    }
}
