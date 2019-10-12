package com.example.childbehaviour;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.aquery.AQuery;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {
    private List<Child> children;
    Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtVwChildPreferredName;
        public TextView txtVwChildTotalPoints;
        public View layout;
        public AQuery aQuery;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtVwChildPreferredName = (TextView) v.findViewById(R.id.txtVwChildPreferredName);
            txtVwChildTotalPoints = (TextView) v.findViewById(R.id.txtVwChildTotalPoints);
        }
    }

    public void add(int position, Child child) {
        children.add(position, child);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        children.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChildAdapter(List<Child> myDataset, Context context) {
        children = myDataset;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.children_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Child child = children.get(position);

        holder.txtVwChildPreferredName.setText(child.getChildPreferredName());
        holder.txtVwChildTotalPoints.setText(Integer.toString(child.getChildTotalPoints()));

//Remove a row from the View Holder.
//This doesn't remove it from the database.
//        holder.txtVwChildPreferredName.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentViewBehaviours = new Intent(mContext, ActivityAddRecord.class);
//                intentViewBehaviours.putExtra("name",child.getChildPreferredName());
//                intentViewBehaviours.putExtra("id",String.valueOf(child.getChildID()));
//                mContext.startActivity(intentViewBehaviours);

//Open the View Records intent
        holder.txtVwChildPreferredName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentViewRecords = new Intent(mContext, ActivityViewRecords2.class);
                intentViewRecords.putExtra("name",child.getChildPreferredName());
                intentViewRecords.putExtra("id",String.valueOf(child.getChildID()));
                mContext.startActivity(intentViewRecords);

            }
        });

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return children.size();
    }

}