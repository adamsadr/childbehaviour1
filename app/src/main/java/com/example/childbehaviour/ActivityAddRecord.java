package com.example.childbehaviour;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aquery.AQuery;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ActivityAddRecord extends AppCompatActivity {

    private static final String TAG = "ActivityAddRecord";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();

    private AQuery aQuery;
    private AQuery aQueryBehaviours;
    int childID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);


        aQuery = new AQuery(this);
//        aQuery.sql().clear();

        final TextView childPreferredName = (TextView) findViewById(R.id.txtVwChildPreferredName);
        final TextView behaviourCategory = (TextView) findViewById(R.id.txtInBehaviourCategory);
        final TextView behaviourDetail = (TextView) findViewById(R.id.txtInBehaviourDetail);
        final SeekBar pointChange = (SeekBar) findViewById(R.id.skBrPointChange);
        final TextView pointChangeText = (TextView) findViewById(R.id.txtVwPointChangeText);
        final TextView parentName = (TextView) findViewById(R.id.txtInParentName);
//        final EditText behaviourDate = (EditText) findViewById(R.id.txtInBehaviourDate);
        final TextView lastRecordUpdate = (TextView) findViewById(R.id.txtVwRecordUpdate);
        Button btnAddRecord = (Button) findViewById(R.id.btnAddRecord);
        Intent intent = getIntent();
        final String childName = intent.getStringExtra("name");
        final String childTableId = intent.getStringExtra("id");
        final Calendar myCalendar = Calendar.getInstance();
        final Context mContext = null;

        final EditText behaviourDate = (EditText) findViewById(R.id.txtInBehaviourDate);
//        final Date behaviourDater = Calendar.getInstance().getTime();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                behaviourDate.setText(sdf.format(myCalendar.getTime()));
            }

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        behaviourDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ActivityAddRecord.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


//Put today's date in Last Update textview box
        String todaysDate = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        lastRecordUpdate.setText(todaysDate);
        behaviourDate.setText(todaysDate);


        childPreferredName.setText(childName);

        pointChangeText.setText(Integer.toString(pointChange.getProgress()));

        pointChange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 5;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pointChangeText.setText(Integer.toString(pval));
            }
        });






        btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<Child> input = new ArrayList<>();
                // Get All row from table
                List<Map<String, String>> children = aQuery.sql().table("childrenList").all();
                if(children!=null){
                    for (Map<String, String> child: children){
                        Child newChild = new Child(Integer.valueOf(child.get("tableId")), child.get("childPreferredName"),child.get("childSurname"),0,0);
                        input.add(newChild);


                    }
                }

                String childIDNum = childTableId;
                String childPrefName = childName;
//                String childPrefName = childPreferredName.getText().toString();
                String behaviourCategoryName = behaviourCategory.getText().toString();
                String behaviourDetailText = behaviourDetail.getText().toString();
                String pointChange = pointChangeText.getText().toString();
                String parent = parentName.getText().toString();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
                String behaviourDated = String.valueOf(behaviourDate.getText());
                String lastUpdate = String.valueOf(lastRecordUpdate.getText());
//                String behaviourDated = sdf.format(behaviourDate.getText());
//                String behaviourDaters = sdf.format(behaviourDater);
//                String lastUpdate = sdf.format(lastRecordUpdate);
                int totalPoints = 0;
                totalPoints = totalPoints + Integer.valueOf(pointChange);
//                final Integer allTimePoints = null;
//                final Integer thisWeekPoints = null;
//                final Integer thisMonthPoints = null;

                Log.i("BEHAVIOUR DATE: ", String.valueOf(behaviourDate.getText()));




                Map<String, Object> recordData = new HashMap<>();
                recordData.put("childPreferredName",childPrefName);
                recordData.put("behaviourCategory",behaviourCategoryName);
                recordData.put("behaviourDetail",behaviourDetailText);
                recordData.put("pointsChange",pointChange);
                recordData.put("parentName",parent);
                recordData.put("behaviourDate",behaviourDated);
                recordData.put("lastRecordUpdate",lastUpdate);
//                recordData.put("thisWkPoints",thisWeekPoints);
//                recordData.put("thisMthPoints",thisMonthPoints);
//                recordData.put("allTmPoints",allTimePoints);
                aQuery.sql().table("recordsList" + childIDNum).insert(recordData);

                Toast.makeText(ActivityAddRecord.this,"New record added for " + childPrefName,Toast.LENGTH_SHORT).show();




                List<Map<String, String>> recordsList = aQuery.sql().table("recordsList"+childIDNum).all();
                for (Map<String, String> child: recordsList){
                    Log.i("CHILD NAME",child.get("childPreferredName"));
//                    Log.i("POINTS CHANGE",child.get("pointChange"));
                    Log.i("xxxx","recordsList" + childIDNum + " record created.");




                Intent intentMainActivity = new Intent(mContext, MainActivity.class);
                intentMainActivity.putExtra("totalPoints",String.valueOf(totalPoints));
//                intentViewRecords.putExtra("id", String.valueOf(recordData.get(childIDNum)));
                mContext.startActivity(intentMainActivity);

//                int allTimePoints = Integer.parseInt(pointChange);

//                allTimePoints = Integer.valueOf(pointChange);

            }

            }
        });

        getImages();

    }


    private void getBehaviours() {
        aQueryBehaviours = new AQuery(this);
        final List<Behaviour> input = new ArrayList<>();
        // Get All row from table
        List<Map<String, String>> behaviours = aQueryBehaviours.sql().table("behavioursList").all();
        if (behaviours != null) {
            for (Map<String, String> behaviour : behaviours) {
                Behaviour newBehaviour = new Behaviour(behaviour.get("behaviourCategoryName"), behaviour.get("behaviourGenDesc"), Integer.valueOf(behaviour.get("pointLimit")), behaviour.get("positiveAttribute"));
                input.add(newBehaviour);
            }
        }

        initRecyclerView();
    }



    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Kindness");

        mImages.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Finished homework");

        mImages.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Creativity");

        mImages.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Naughty");


        mImages.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Got ready without a fuss");

        mImages.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("In bed by 830PM");


        mImages.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("Using naughty words");

        mImages.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Failure to STOP after being asked");

        mImages.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Violence");

        initRecyclerView();

    }
    
    

    private  void initRecyclerView(){


        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_record_behaviours);
        recyclerView.setLayoutManager(layoutManager);
        AdapterBehavioursInAddRecord adapterBehavioursInAddRecord = new AdapterBehavioursInAddRecord(mNames,mImages,this);
        recyclerView.setAdapter(adapterBehavioursInAddRecord);

    }

}
