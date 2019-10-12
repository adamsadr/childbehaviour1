package com.example.childbehaviour;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Switch;
import android.widget.TextView;

import com.aquery.AQuery;

public class RecordAdapter2 extends RecyclerView.Adapter<RecordAdapter2.ViewHolder> {
    private List<Record> records;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtVwBehaviourCategory;
        public TextView txtVwBehaviourPointsChange;
        public TextView txtVwParent;
        public TextView txtVwBehaviourDetail;
        public TextView txtViewBehaviourDate;
        public TextView txtVwChildPreferredName;
        public View recordsLayout;
        public AQuery aQueryRecords;

        public ViewHolder(View v) {
            super(v);
            recordsLayout = v;
            txtVwBehaviourCategory = (TextView) v.findViewById(R.id.txtVwBehaviourCategory);
            txtVwBehaviourPointsChange = (TextView) v.findViewById(R.id.txtVwBehaviourPointsChange);
            txtVwParent = (TextView) v.findViewById(R.id.txtVwParent);
            txtVwBehaviourDetail = (TextView) v.findViewById(R.id.txtVwBehaviourDetail);
            txtViewBehaviourDate = (TextView) v.findViewById(R.id.txtVwBehaviourDate);
            txtVwChildPreferredName = (TextView) v.findViewById(R.id.txtVwChildPreferredName);
        }
    }

    public void add(int position, Record record) {
        records.add(position, record);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        records.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecordAdapter2(List<Record> myRecordsDataset) {
        records = myRecordsDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecordAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.records_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Record record = records.get(position);
        holder.txtVwBehaviourCategory.setText(record.getBehaviourCategory());
        holder.txtVwBehaviourPointsChange.setText(Integer.toString(record.getPointChange()));
        holder.txtVwBehaviourDetail.setText(record.getBehaviourDetail());
//        holder.txtViewBehaviourDate.setText(record.getBehaviourDate());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        holder.txtViewBehaviourDate.setText(record.getBehaviourDate());

//Remove a row from the View Holder.
//This doesn't remove it from the database.
        holder.txtVwBehaviourCategory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return records.size();
    }

}