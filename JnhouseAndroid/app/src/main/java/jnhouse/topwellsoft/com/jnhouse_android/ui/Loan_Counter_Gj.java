package jnhouse.topwellsoft.com.jnhouse_android.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import jnhouse.topwellsoft.com.jnhouse_android.R;

public class Loan_Counter_Gj extends Fragment implements View.OnClickListener {
   private EditText bili,year;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.loan_gongjijin,null);
        bili=(EditText)view.findViewById(R.id.bili);
        bili.setOnClickListener(this);
        year=(EditText)view.findViewById(R.id.ynum);
        year.setOnClickListener(this);
        return view;
    }

    public void xuanze(View view){
        final String[] items=new String[]{"1","2","3","4","5","6","7","8","9","10"};
        AlertDialog.Builder buider=new AlertDialog.Builder(getActivity());
        buider.setTitle("首付比例");
        buider.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                bili.setText(items[i]);
            }
        });
        buider.create().show();
    }
    public void year(View view){
        final String[] items=new String[30];
        for (int i=0;i<items.length;i++){
            items[i]=i+1+"年";
        }
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        builder.setTitle("选择贷款年限");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                year.setText(items[i]);
            }
        });
        builder.create().show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bili:
                xuanze(bili);
                break;
            case  R.id.ynum:
                year(year);
                break;
        }
    }

}
