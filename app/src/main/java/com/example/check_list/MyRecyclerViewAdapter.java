package com.example.check_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private final List<String> mData;
    private final LayoutInflater mInflater;
    Context mContext;

    public MyRecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int index) {
        String item = mData.get(index);
        TextView name = holder.itemView.findViewById(R.id.productName);
        name.setText(String.format("%s. %s", index + 1, item));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.productName);
            itemView.setOnClickListener(this);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
