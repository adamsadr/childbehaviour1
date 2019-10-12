package com.example.childbehaviour;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aquery.AQuery;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityViewRecords2 extends AppCompatActivity {

    private RecyclerView recyclerViewRecords;
    private RecyclerView.Adapter recordAdapter;
    private RecyclerView.LayoutManager recordLayoutManager;
    private AQuery aQueryRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final String childName = intent.getStringExtra("name");
        final String childTableId = intent.getStringExtra("id");

        setContentView(R.layout.activity_view_records);
        recyclerViewRecords = (RecyclerView) findViewById(R.id.recycler_view_records);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerViewRecords.setHasFixedSize(true);
        // use a linear layout manager
        recordLayoutManager = new LinearLayoutManager(this);
        recyclerViewRecords.setLayoutManager(recordLayoutManager);
        aQueryRecords = new AQuery(this);
        final List<Record> input = new ArrayList<>();
        Integer allTimePoints = 0;
        // Get All row from table
        List<Map<String, String>> records = aQueryRecords.sql().table("recordsList" + childTableId).all();
        if(records!=null){
            for (Map<String, String> record: records){
                Log.i("ID", record.get("tableId"));
                Log.i("ID", record.get("childPreferredName"));
                Log.i("ID", record.get("behaviourCategory"));
                Log.i("ID", record.get("behaviourDetail"));
                Log.i("ID", record.get("pointsChange"));
                Log.i("ID", record.get("parentName"));
                Log.i("ID", record.get("behaviourDate"));
                Log.i("ID", record.get("lastRecordUpdate"));
                Record newRecord = new Record(Integer.valueOf(record.get("tableId")),record.get("childPreferredName"),record.get("behaviourCategory"),record.get("behaviourDetail"),Integer.valueOf(record.get("pointsChange")),"Parent", record.get("behaviourDate"),record.get("lastRecordUpdate"));
                input.add(newRecord);

                allTimePoints = allTimePoints + Integer.valueOf(record.get("pointsChange"));

            }



        }

//        for(Map<String, String> i: records){
//            Log.i("NNNNAME", i.get("childPreferredName"));
//        }

        // define an adapter
        recordAdapter = new RecordAdapter2(input);
        recyclerViewRecords.setAdapter(recordAdapter);
    }

    public void addNewRecord(View view) {

        Intent intent = getIntent();
        final String childName = intent.getStringExtra("name");
        final String childTableId = intent.getStringExtra("id");

        Intent intentAddNewRecord = new Intent(getApplicationContext(), ActivityAddRecord.class);
        intentAddNewRecord.putExtra("name",childName);
        intentAddNewRecord.putExtra("id",childTableId);
        startActivity(intentAddNewRecord);

    }
}
