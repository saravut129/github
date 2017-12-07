package com.example.fastorder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.fastorder.Model.FoodStoreItem;
import com.example.fastorder.adapter.FoodStoreListAdapter;
import com.example.fastorder.db.PhoneDbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FoodStoreListsActivity extends AppCompatActivity {

    private ListView mListView2;
    private PhoneDbHelper mHelper;
    private SQLiteDatabase mDb;
    private ArrayList<FoodStoreItem> aList2 = new ArrayList<>();
    private FoodStoreListAdapter mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_store_lists);
        mHelper = new PhoneDbHelper(this);
        mDb = mHelper.getReadableDatabase();

        loadDataFromDb();

        mAdapter2 = new FoodStoreListAdapter(
                this,
                R.layout.item2,
                aList2
        );
        mListView2 = (ListView) findViewById(R.id.listView2);
        mListView2.setAdapter(mAdapter2);



        getSupportActionBar().setTitle(
                String.format(
                        Locale.getDefault(),
                        "%s",
                        "General"
                )
        );
        Button bt = findViewById(R.id.button);
        bt.setAdapter(mAdapter2);

        bt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                PhoneItem item = mPhoneItemList.get(position);
                String phoneNumber = item.number;

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        //if (itemId == R.id.action_add)

        switch (itemId) {
            case R.id.action_add:
                addFoodStore();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addFoodStore() {
        Intent intent = new Intent(FoodStoreListsActivity.this, AddFoodStoreActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                loadDataFromDb();
                mAdapter2.notifyDataSetChanged();
            }
        }
    }

    private void loadDataFromDb() {
        Cursor cursor = mDb.query(
                PhoneDbHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        aList2.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(PhoneDbHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(PhoneDbHelper.COL_TITLE));
            String number = cursor.getString(cursor.getColumnIndex(PhoneDbHelper.COL_NUMBER));
            String picture = cursor.getString(cursor.getColumnIndex(PhoneDbHelper.COL_PICTURE));

            FoodStoreItem item = new FoodStoreItem(id, title, number, picture);
            aList2.add(item);
        }
    }
}
