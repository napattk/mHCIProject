package com.group2.mhci.seefood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class FoodListActivity extends AppCompatActivity {

    private Toolbar mTopToolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            Toast.makeText(FoodListActivity.this, "Function under implementation.", Toast.LENGTH_LONG).show();
            return true;
        }

        else if (id == R.id.action_search) {
            Toast.makeText(FoodListActivity.this, "Function under implementation.", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mTopToolbar.setTitle("Select an item");
        setSupportActionBar(mTopToolbar);

        final String categoryID = getIntent().getStringExtra("CategoryID");


        GridView gridview = (GridView) findViewById(R.id.grid_view);
        gridview.setAdapter(new ImageAdapter(this, Integer.parseInt(categoryID)));
        gridview.setNumColumns(2);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //Toast.makeText(FoodListActivity.this, "Clicked on category " +  categoryID + " item " + position,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                intent.putExtra("CategoryID", categoryID);
                intent.putExtra("ItemID", Integer.toString(position));
                startActivity(intent);

            }
        });
    }

}
