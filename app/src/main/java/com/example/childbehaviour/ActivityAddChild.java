package com.example.childbehaviour;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aquery.AQuery;

import java.util.HashMap;
import java.util.Map;

public class ActivityAddChild extends AppCompatActivity {

    private AQuery aQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        aQuery = new AQuery(this);

        final TextView preferredName = (TextView) findViewById(R.id.txtInChildPreferredName);
        final TextView surname = (TextView) findViewById(R.id.txtInChildSurname);
        Button btnAddChild = (Button) findViewById(R.id.btnAddChild);
        Button btnGoToBehaviour = (Button) findViewById(R.id.btnGoToBehaviour);


        btnAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String childPreferredName = preferredName.getText().toString();
                String childSurname = surname.getText().toString();

                Map<String, Object> data = new HashMap<>();
                data.put("childPreferredName",childPreferredName);
                data.put("childSurname",childSurname);
                aQuery.sql().table("childrenList").insert(data);

                Toast.makeText(ActivityAddChild.this,childPreferredName + " added.",Toast.LENGTH_SHORT).show();
            }
        });



    }

}
