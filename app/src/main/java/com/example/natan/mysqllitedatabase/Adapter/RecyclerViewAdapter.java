package com.example.natan.mysqllitedatabase.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.natan.mysqllitedatabase.Data.Contract;
import com.example.natan.mysqllitedatabase.MainActivity;
import com.example.natan.mysqllitedatabase.R;

/**
 * Created by natan on 11/28/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyVIewHolder> {


    private Cursor mCursor;
    private Context mContext;

    public RecyclerViewAdapter(Context context, Cursor cursor) {
        this.mCursor = cursor;
        this.mContext = context;
    }

    @Override
    public MyVIewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new MyVIewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyVIewHolder holder, int position) {

        if (!mCursor.moveToPosition(position))
            return; //

        String name=mCursor.getString(mCursor.getColumnIndex(Contract.Entry.COLUMN_NAME));
        holder.txt.setText(name);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        // COMPLETED (16) Inside, check if the current cursor is not null, and close it if so
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        // COMPLETED (17) Update the local mCursor to be equal to  newCursor
        mCursor = newCursor;
        // COMPLETED (18) Check if the newCursor is not null, and call this.notifyDataSetChanged() if so
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    class MyVIewHolder extends RecyclerView.ViewHolder {

        //
        TextView txt;

        public MyVIewHolder(View itemView) {
            super(itemView);
            txt=itemView.findViewById(R.id.textView);
        }
    }

}
