package com.example.childbehaviour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aquery.AQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityViewBehaviours extends AppCompatActivity {

    private RecyclerView recyclerViewBehaviours;
    private RecyclerView.Adapter behaviourAdapter;
    private RecyclerView.LayoutManager behaviourLayoutManager;
    private AQuery aQueryBehaviours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_behaviours);
        recyclerViewBehaviours = (RecyclerView) findViewById(R.id.recycler_view_behaviours);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerViewBehaviours.setHasFixedSize(true);
        // use a linear layout manager
        behaviourLayoutManager = new LinearLayoutManager(this);
        recyclerViewBehaviours.setLayoutManager(behaviourLayoutManager);
        aQueryBehaviours = new AQuery(this);
        final List<Behaviour> input = new ArrayList<>();
        // Get All row from table
        List<Map<String, String>> behaviours = aQueryBehaviours.sql().table("behavioursList").all();
        if(behaviours!=null){
            for (Map<String, String> behaviour: behaviours){
                Behaviour newBehaviour = new Behaviour(behaviour.get("behaviourCategoryName"),behaviour.get("behaviourGenDesc"),Integer.valueOf(behaviour.get("pointLimit")),behaviour.get("positiveAttribute"));
                input.add(newBehaviour);
            }
        }

        // define an adapter
        behaviourAdapter = new BehaviourAdapter(input);
        recyclerViewBehaviours.setAdapter(behaviourAdapter);
    }

    public void addNewBehaviour(View view) {

        Intent intentAddNewBehaviour = new Intent(getApplicationContext(), ActivityAddBehaviour.class);
        startActivity(intentAddNewBehaviour);

    }
}
