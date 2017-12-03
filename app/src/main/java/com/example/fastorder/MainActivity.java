package com.example.fastorder;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fastorder.Model.FoodStoreItem;
import com.example.fastorder.adapter.FoodStoreListAdapter;
import com.example.fastorder.db.PhoneDbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private FoodStoreListAdapter mAdapter;

    private PhoneDbHelper mHelper;
    private SQLiteDatabase mDb;

    private ArrayList<FoodStoreItem> mFoodStoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        if (savedInstanceState == null) {
            mFoodStoreList.add(new FoodStoreItem("Pa-Jar(23 Hours)", "ร้านข้าวมันไก่ป้าจ๋า 23 ชม.", R.drawable.pajar, getString(R.string.details_pajar)));
            mFoodStoreList.add(new FoodStoreItem("Lan Kra-Tib", "ล้านกระติ๊บ", R.drawable.lankratib, getString(R.string.details_lankratib)));
        }

        mAdapter = new FoodStoreListAdapter(
                this,
                R.layout.item,
                mFoodStoreList
        );

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FoodStoreItem foodStoreItem = mFoodStoreList.get(i);

                Intent intent = new Intent(MainActivity.this, FoodStoreDetailsActivity.class);
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
