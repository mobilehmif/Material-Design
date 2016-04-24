package com.sugoi.materialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sugoi.materialdesign.R;
import com.sugoi.materialdesign.model.NavDrawerItem;

import java.util.Collections;
import java.util.List;

public class NavigationDrawerAdapter
        extends RecyclerView.Adapter<NavigationDrawerAdapter.MDViewHolder> {


    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int posisition) {
        data.remove(posisition);
        notifyItemRemoved(posisition);
    }

    @Override
    public MDViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MDViewHolder holder = new MDViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MDViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MDViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MDViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
