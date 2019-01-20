package com.crossedproductions.nucleus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crossedproductions.nucleus.ListActivity;
import com.crossedproductions.nucleus.R;
import com.crossedproductions.nucleus.model.Drugs;

import java.util.List;


public class ListDrugsAdapter extends BaseAdapter {
    private Context mContext;
    private List<Drugs> mDrugsList;

    EditText text;
    Button button;

    public ListDrugsAdapter(Context mContext, List<Drugs> mDrugsList) {
        this.mContext = mContext;
        this.mDrugsList = mDrugsList;


    }

    @Override
    public int getCount() {
        return mDrugsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDrugsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mDrugsList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.item_listview, null);
        TextView tvName = (TextView)v.findViewById(R.id.tv_name);
        TextView tvSciName = (TextView)v.findViewById(R.id.tv_sciname);
        TextView tvCdosage = (TextView)v.findViewById(R.id.tv_cdosage);
        //TextView tvAdosage = (TextView)v.findViewById(R.id.tv_adosage);
        TextView tvMaxdosage = (TextView)v.findViewById(R.id.tv_maxdosage);
        //TextView tvNote = (TextView)v.findViewById(R.id.tv_note);

        tvName.setText(mDrugsList.get(position).getName());
        tvSciName.setText(mDrugsList.get(position).getSciname());
        tvCdosage.setText(String.valueOf(mDrugsList.get(position).getCdosage()) + " mg");
        //tvAdosage.setText(String.valueOf(mDrugsList.get(position).getAdosage()) + " mg");
        tvMaxdosage.setText(mDrugsList.get(position).getMaxdosage());
        //tvNote.setText(mDrugsList.get(position).getNote());

        return v;








    }
}