package com.tech.technionevents;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aiman.ay.28 on 2/1/15.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {
    List<String> mNames;
    Context mContext;

    DrawerAdapter(Context context, List<String> names){
        mNames = names;
        mContext = context;
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.drawer_row, viewGroup, false);
        return new DrawerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DrawerViewHolder drawerViewHolder, int i) {
        drawerViewHolder.name.setText(mNames.get(i));
        drawerViewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mContext).onCategorySelected(drawerViewHolder.name.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    class DrawerViewHolder extends  RecyclerView.ViewHolder{
        TextView name;
        public DrawerViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.categoryName);
        }
    }
}
