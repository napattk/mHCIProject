package com.group2.mhci.seefood;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    private Toolbar mTopToolbar;
    private ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_favorite).setVisible(true);
        menu.findItem(R.id.action_search).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            Toast.makeText(DetailActivity.this, "Function under implementation.", Toast.LENGTH_LONG).show();
            return true;
        }

        else if (id == R.id.action_search) {
            Toast.makeText(DetailActivity.this, "Function under implementation.", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);


        final String categoryID = getIntent().getStringExtra("CategoryID");
        final String itemID = getIntent().getStringExtra("ItemID");

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdaptor viewPagerAdapter = new ViewPagerAdaptor(this,Integer.parseInt(categoryID),Integer.parseInt(itemID));
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(DetailActivity.this, "Clicked on category " +  categoryID + " item " + itemID ,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getBaseContext(), TranslateActivity.class);
                intent.putExtra("CategoryID", categoryID);
                intent.putExtra("ItemID", itemID);
                startActivity(intent);
            }

        });

        String englishName = food.foodList[Integer.parseInt(categoryID)][Integer.parseInt(itemID)].EN;
        String localName = food.foodList[Integer.parseInt(categoryID)][Integer.parseInt(itemID)].TH;
        String romanName = food.foodList[Integer.parseInt(categoryID)][Integer.parseInt(itemID)].Roman;
        String desc =  food.foodList[Integer.parseInt(categoryID)][Integer.parseInt(itemID)].desc;

        TextView foodTitleEng = (TextView) findViewById(R.id.foodTitleEng);
        foodTitleEng.setText(englishName);

        TextView foodTitleLocal = (TextView) findViewById(R.id.foodTitleLocal);
        foodTitleLocal.setText(localName + " / " + romanName);

        TextView foodDesc = (TextView) findViewById(R.id.foodDesc);
        foodDesc.setText(desc);
        foodDesc.setMovementMethod(new ScrollingMovementMethod());



    }
}
