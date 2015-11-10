package stb.com.nyari.models.adapters;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import stb.com.nyari.R;
import stb.com.nyari.models.DisabledChildMenuItem;
import stb.com.nyari.models.LeftMenuModel;

@SuppressLint("InflateParams")
public class CustomExpandAdapter extends BaseExpandableListAdapter {

    private List<LeftMenuModel> parentRecord;
    private HashMap<String, List<LeftMenuModel>> childRecord;
    private List<DisabledChildMenuItem> disableChildMenuList;
    private LayoutInflater inflater = null;
    private Activity mContext;
    boolean found;

    public CustomExpandAdapter(Activity context, List<LeftMenuModel> parentList, HashMap<String, List<LeftMenuModel>> childList) {
        this.parentRecord = parentList;
        this.childRecord = childList;
        found	=	true;
        mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    public CustomExpandAdapter(Activity context, List<LeftMenuModel> parentList, HashMap<String, List<LeftMenuModel>> childList, List<DisabledChildMenuItem> disableChildMenuList) {
    	this.parentRecord = parentList;
        this.childRecord = childList;
        this.disableChildMenuList	=	disableChildMenuList;
        found	=	false;
        mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public LeftMenuModel getChild(int groupPosition, int childPosition) {
        return this.childRecord.get(((LeftMenuModel) getGroup(groupPosition)).getTitle()).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

    	LeftMenuModel childConfig = getChild(groupPosition, childPosition);
        Log.v("pos", groupPosition + " " + childPosition + " " + childConfig.getTitle());
        
        if(!found) {
	        for (DisabledChildMenuItem item : disableChildMenuList) {
				if (groupPosition == item.getGroupPosition() && childPosition == item.getChildPosition()
						&& childConfig.getTitle().equals(item.getTitle())) {
					found	=	true;
					break;
	//				Log.v("pos", groupPosition + " " + childPosition);
				}
			}
	        Log.v("found status", found + " ditemukan ");
        }

        ViewHolder holder;
        try {
            if (convertView == null) {
                holder = new ViewHolder();
                
                Log.v("found status", found + "");
                convertView = inflater.inflate(R.layout.custom_list_view_child, null);
//                if (found) {
//					convertView	=	inflater.inflate(R.layout.custom_list_view_child_disabled, null);
//				} else {
//					convertView = inflater.inflate(R.layout.custom_list_view_child, null);
//				}
                
                holder.childTitle = (TextView) convertView.findViewById(R.id.childTitle);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.childTitle.setText(childConfig.getTitle());

        } catch (Exception e) {
        }
        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        LeftMenuModel parentSampleTo = parentRecord.get(groupPosition);

        ViewHolder holder;
        try {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.custom_list_view, null);
                holder = new ViewHolder();

                holder.parentTitle = (TextView) convertView.findViewById(R.id.parentTitle);
                holder.parentIcon = (ImageView) convertView.findViewById(R.id.parentIcon);
                holder.parentNavIcon = (ImageView) convertView.findViewById(R.id.parentNavIcon);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.parentTitle.setText(parentSampleTo.getTitle());
            holder.parentIcon.setBackgroundResource(parentSampleTo.getIcon());
            holder.parentNavIcon.setBackgroundResource(parentSampleTo.getNavIcon());
        } catch (Exception e) {
        }
        return convertView;
    }

    public static class ViewHolder {

        private TextView childTitle;
        // Content
        private TextView parentTitle;
        private ImageView parentIcon;
        private ImageView parentNavIcon;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childRecord.get(((LeftMenuModel) getGroup(groupPosition)).getTitle()).size();
    }

    @Override
    public LeftMenuModel getGroup(int groupPosition) {
        return this.parentRecord.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.parentRecord.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
    	if (!found) {
        	for (DisabledChildMenuItem item : disableChildMenuList) {
    			if (groupPosition == item.getGroupPosition() && childPosition == item.getChildPosition()) {
    				return false;
    			}
    		}
		}

    	Log.v("group pos", groupPosition + " " + childPosition);
        return true;
    }

}
