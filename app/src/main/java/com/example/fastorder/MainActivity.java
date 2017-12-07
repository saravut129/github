package com.example.fastorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fastorder.Model.CategoryItem;
import com.example.fastorder.adapter.CategoryListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private CategoryListAdapter mAdapter;



    private ArrayList<CategoryItem> mCategory = FoodStoreData.foodStoreLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        if (savedInstanceState == null) {
            mCategory.add(new CategoryItem("General"));
            mCategory.add(new CategoryItem("Favorite"));
        }

        mAdapter = new CategoryListAdapter(
                this,
                R.layout.item1,
                mCategory
        );

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CategoryItem ci = mCategory.get(i);
                Toast.makeText(MainActivity.this, ci.name, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, FoodStoreListsActivity.class);
                intent.putExtra("pos", 0);
                startActivity(intent);
            }
        });
    }


}
