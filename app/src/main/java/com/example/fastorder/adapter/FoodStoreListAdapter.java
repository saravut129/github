package com.example.fastorder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fastorder.Model.FoodStoreItem;
import com.example.fastorder.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Admin on 2/12/2560.
 */

public class FoodStoreListAdapter extends ArrayAdapter<FoodStoreItem> {

    private Context mContext;
    private int mLayoutResId;
    private ArrayList<FoodStoreItem> mFoodStoreList;

    public FoodStoreListAdapter(Context context, int layoutResId, ArrayList<FoodStoreItem>  FoodStoreItemList){
        super(context, layoutResId, FoodStoreItemList);

        this.mContext = context;
        this.mLayoutResId = layoutResId;
        this.mFoodStoreList = FoodStoreItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View v = convertView;
        if(v==null){
            v = inflater.inflate(mLayoutResId, null);
        }

        ImageView iv = v.findViewById(R.id.imageView);
        TextView tv1 = v.findViewById(R.id.textView);
        TextView tv2 = v.findViewById(R.id.textView2);

        FoodStoreItem foodStoreItem = mFoodStoreList.get(position);

        iv.setImageResource(foodStoreItem.picture);
        tv1.setText(
                String.format(
                        Locale.getDefault(),
                        "%s (%s)",
                        foodStoreItem.thaiName,
                        foodStoreItem.englishName
                )
        );

        tv2.setText(
                String.format(
                        Locale.getDefault(),
                        "%s",
                        foodStoreItem.details
                )
        );
        return v;
    }
}
