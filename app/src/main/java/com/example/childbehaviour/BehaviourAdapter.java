package com.example.childbehaviour;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Switch;
import android.widget.TextView;

import com.aquery.AQuery;

public class BehaviourAdapter extends RecyclerView.Adapter<BehaviourAdapter.ViewHolder> {
    private List<Behaviour> behaviours;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtVwBehaviourCategory;
        public TextView txtVwBehaviourGeneralDescription;
        public TextView txtVwPointsLimit;
        public View behavioursLayout;
        public AQuery aQueryBehaviours;

        public ViewHolder(View v) {
            super(v);
            behavioursLayout = v;
            txtVwBehaviourCategory = (TextView) v.findViewById(R.id.txtVwBehaviourCategory);
            txtVwBehaviourGeneralDescription = (TextView) v.findViewById(R.id.txtVwBehaviourGeneralDescription);
            txtVwPointsLimit = (TextView) v.findViewById(R.id.txtVwBehaviourPointsLimit);
        }
    }

    public void add(int position, Behaviour behaviour) {
        behaviours.add(position, behaviour);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        behaviours.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BehaviourAdapter(List<Behaviour> myBehavioursDataset) {
        behaviours = myBehavioursDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BehaviourAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.behaviours_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Behaviour behaviour = behaviours.get(position);
        holder.txtVwBehaviourCategory.setText(behaviour.getBehaviourCategory());
        holder.txtVwBehaviourGeneralDescription.setText(behaviour.getBehaviourGeneralDescription());
        holder.txtVwPointsLimit.setText(Integer.toString(behaviour.getBehaviourPointLimit()));

//Remove a row from the View Holder.
//This doesn't remove it from the database.
        holder.txtVwPointsLimit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return behaviours.size();
    }

}