package stb.com.nyari.models.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class BaseListAdapters.
 *
 * @param <T> the generic type
 */
public class BaseListAdapters<T> extends BaseAdapter {

    /** The context. */
    protected final Context context;
    
    /** The list. */
    protected List<T> list = new ArrayList<T>();

    /**
     * Instantiates a new base list adapters.
     *
     * @param context the context
     */
    public BaseListAdapters(Context context){
        this.context = context;
    }

    /**
     * Update list.
     *
     * @param list the list
     */
    public void updateList(List<T> list){
        ThreadPreconditions.checkOnMainThread();
        this.list = list;
        notifyDataSetChanged();
    }


    /* (non-Javadoc)
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public T getItem(int pos) {
        return list.get(pos);
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        }

        return view;
    }
}

