package com.group2.mhci.seefood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Mickey on 2/24/2018.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public int categoryID;

    public ImageAdapter(Context c, int catID) {
        mContext = c;
        categoryID = catID;

    }

    public int getCount() {
        if(mContext.getClass().getName().toString().equals("com.group2.mhci.seefood.FoodListActivity")) {
            return food.foodList[categoryID].length;

        }
        else if(mContext.getClass().getName().toString().equals("com.group2.mhci.seefood.CategoryActivity")){
            return food.categoryThumb.length;
        }
        else {
            return 0;
        }
    }
    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder
    {
        public ImageView imgViewFlag;
        public TextView txtViewTitle;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

            if (convertView == null) {

            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.gridview_row, null);

            view.txtViewTitle = (TextView) convertView.findViewById(R.id.textView1);
            view.imgViewFlag = (ImageView) convertView.findViewById(R.id.imageView1);

            convertView.setTag(view);

        } else {
            view = (ViewHolder) convertView.getTag();
        }
        if(mContext.getClass().getName().toString().equals("com.group2.mhci.seefood.FoodListActivity")) {
            view.txtViewTitle.setTextSize(14);


            view.imgViewFlag.setImageResource(food.foodList[categoryID][position].images[0]);
            view.txtViewTitle.setText(food.foodList[categoryID][position].EN);

        }
        else if(mContext.getClass().getName().toString().equals("com.group2.mhci.seefood.CategoryActivity")){
            view.txtViewTitle.setText(food.categoryNames[position]);
            view.imgViewFlag.setImageResource(food.categoryThumb[position]);
        }

        return convertView;
    }


}
