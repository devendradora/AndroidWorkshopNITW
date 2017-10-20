package com.dora.androidnitw;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MathFragment extends Fragment  {
    Button addbtn;
    EditText num1,num2;
    TextView res;
    private FirebaseAnalytics firebaseAnalytics;

    public MathFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAnalytics = FirebaseAnalytics.getInstance(getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      // Inflate the layout for this fragment

         View rootview= inflater.inflate(R.layout.fragment_math, container, false);
         num1 =(EditText) rootview.findViewById(R.id.num1);
         num2 =(EditText)rootview.findViewById(R.id.num2);
         res= (TextView)rootview.findViewById(R.id.resultTv);
        addbtn = (Button)rootview.findViewById(R.id.addbtn);




        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res.setText("sum is "+(Integer.parseInt(num1.getText().toString())+Integer.parseInt(num2.getText().toString())));
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID,"add_btn_click");
                bundle.putString("num1",num1.toString());
                bundle.putString("num2",num2.toString());
                //Logs an app event.
                firebaseAnalytics.logEvent("Math", bundle);

                Random rn = new Random();
               int num =  rn.nextInt(1000 - 10 + 1) + 10;
                firebaseAnalytics.setUserId(String.valueOf(num));

           }
        });
        return rootview;
    }

}
