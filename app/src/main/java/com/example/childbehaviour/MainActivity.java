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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AQuery aQuery;
    Intent intent = getIntent();
//    final String totalPoints = intent.getStringExtra("totalPoints");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        aQuery = new AQuery(this);
        final List<Child> input = new ArrayList<>();
        // Get All row from table
        List<Map<String, String>> children = aQuery.sql().table("childrenList").all();
        if(children!=null){
            for (Map<String, String> child: children){
                Child newChild = new Child(Integer.valueOf(child.get("tableId")), child.get("childPreferredName"),child.get("childSurname"), getAllTimePoints(child.get("tableId")),0);
                input.add(newChild);
            }
        }

        // define an adapter
        mAdapter = new ChildAdapter(input,this);
        recyclerView.setAdapter(mAdapter);
    }

    public void addNewChild(View view) {

        Intent intentAddNewChild = new Intent(getApplicationContext(), ActivityAddChild.class);
        startActivity(intentAddNewChild);

    }

    public void viewBehaviours(View view) {

        Intent intentViewBehaviours = new Intent(getApplicationContext(), ActivityViewBehaviours.class);
        startActivity(intentViewBehaviours);

    }

    public void addNewBehaviour(View view) {

        Intent intentAddNewBehaviour = new Intent(getApplicationContext(), ActivityAddBehaviour.class);
        startActivity(intentAddNewBehaviour);

    }

    public void addNewRecord(View view) {

        Intent intentAddNewBehaviour = new Intent(getApplicationContext(), ActivityAddRecord.class);
        startActivity(intentAddNewBehaviour);

    }

    public void viewRecords(View view) {

        Intent intentViewRecords = new Intent(getApplicationContext(), ActivityViewRecords2.class);
        startActivity(intentViewRecords);

    }

    public int getAllTimePoints(String childTableId) {
        AQuery aQueryRecords;
        aQueryRecords = new AQuery(this);
        int allTimePoints = 0;

        // Get All row from table
        List<Map<String, String>> records = aQueryRecords.sql().table("recordsList" + childTableId).all();
            if(records != null) {
                for (Map<String, String> record : records) {

                    allTimePoints = allTimePoints + Integer.valueOf(record.get("pointsChange"));

                }

        }

        return allTimePoints;

    }

}