package com.example.ignocop;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Police_Fragment extends Fragment {

    EditText et_phno;
    EditText et_station_name;
    DB_Controller controller;
    String et_station_name_pat,et_phno_pat;


    public Police_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_police_, container, false);
        et_station_name=(EditText)inflate.findViewById(R.id.editText);
        et_phno=(EditText)inflate.findViewById(R.id.editText3);


        controller=new DB_Controller(getActivity(),"",null,1);

        FloatingActionButton fab = (FloatingActionButton)inflate.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialize();
                if (!validate()) {
                    Toast.makeText(getActivity(), "Failed Adding", Toast.LENGTH_SHORT).show();

                } else {
                    save();
                    et_phno.setText("");
                    et_station_name.setText("");

                }

            }
        });

        FloatingActionButton fab1 = (FloatingActionButton)inflate.findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initialize();
                if (!validate()) {
                    Toast.makeText(getActivity(), "Failed Updating", Toast.LENGTH_SHORT).show();

                } else {
                    btn_upda();
                    et_phno.setText("");
                    et_station_name.setText("");

                }

            }
        });

        FloatingActionButton fab2 = (FloatingActionButton)inflate.findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initialize();
                if (!validate()) {
                    Toast.makeText(getActivity(), "Failed Deleting", Toast.LENGTH_SHORT).show();

                } else {
                    delete();
                    et_phno.setText("");
                    et_station_name.setText("");

                }

            }
        });
        // Inflate the layout for this fragment
        return inflate;
    }

    public void save()
    {
        try{
            controller.police(et_station_name.getText().toString(), et_phno.getText().toString());
            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("Added SUCCESSFULLY");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            dialog.show();
        }catch(SQLiteException e){
            Toast.makeText(getActivity(),"ALREADY EXISTS",Toast.LENGTH_SHORT).show();
        }
    }

    public void initialize()
    {
        et_station_name_pat = et_station_name.getText().toString().trim();
        et_phno_pat=et_phno.getText().toString().trim();



    }

    public void delete()
    {
        controller.del_police(et_station_name.getText().toString(), et_phno.getText().toString());
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Deleted SUCCESSFULLY");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.show();
    }

    public void btn_upda()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
        dialog.setTitle("ENTER NEW Number");

        final EditText et_new_police_num=new EditText(getActivity());
        dialog.setView(et_new_police_num);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                controller.update_police(et_phno.getText().toString(),et_new_police_num.getText().toString());
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Updated SUCCESSFULLY");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                dialog.show();
            }
        });

        dialog.show();
    }

    public boolean validate() {
        boolean valid = true;

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (et_station_name.length() == 0) {
            et_station_name.setError("Please Enter valid address");
            valid = false;
            et_station_name.setText("");
        }
        if(et_phno.length()==0 ||et_phno.length()>10 ||et_phno.length()<10){
            et_phno.setError("Please Enter valid number ");
            valid = false;
            et_phno.setText("");
        }
        return valid;
    }

}
