package com.group2.mhci.seefood;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Bob on 3/4/2018 AD.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewPagerAdaptor extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
//    private Integer [] images = {R.drawable.dessert,R.drawable.curry,R.drawable.appetizers};
    private Integer [] images;
    private int categoryid;
    private int itemID;
    private int[]layouts;

    public ViewPagerAdaptor(Context context) {
        this.context = context;
    }

    public ViewPagerAdaptor(Context context,int categoryID, int itemID) {
        this.context = context;
        this.categoryid = categoryID;
        this.itemID = itemID;
        this.images =  food.foodList[categoryID][itemID].images;
    }

    public ViewPagerAdaptor(int[] layouts, Context context) {
        this.layouts = layouts;
        this.context = context;
    }


    @Override
    public int getCount() {
        if (layouts!= null){
            return layouts.length;
        }else{
            return images.length;
        }

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        if (layouts != null){
            view = layoutInflater.inflate(layouts[position], container, false);
        }else {
            view = layoutInflater.inflate(R.layout.custom_layout, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            imageView.setImageResource(images[position]);
        }

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }

//    public static food[][] foodList = {
//            {mangoRice, chaMongKut},
//            {riceChicken, greenCurry, gyuudon},
//            {padThai, udon},
//    };

}