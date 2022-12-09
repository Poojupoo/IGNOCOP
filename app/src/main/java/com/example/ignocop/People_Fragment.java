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
public class People_Fragment extends Fragment {
    EditText et_id;
    EditText et_name;
    EditText et_phno;
    DB_Controller controller;
    String et_name_pat,et_phno_pat,et_id_pat;

    public People_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_people_, container, false);
        et_id=(EditText)inflate.findViewById(R.id.editText);
        et_name=(EditText)inflate.findViewById(R.id.editText3);
        et_phno=(EditText)inflate.findViewById(R.id.editText4);


        controller=new DB_Controller(getActivity(),"",null,1);

        FloatingActionButton fab = (FloatingActionButton)inflate.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initialize();
                if (!validate()) {
                    Toast.makeText(getActivity(), "Failed Adding", Toast.LENGTH_SHORT).show();

                } else {
                    ad();
                    et_phno.setText("");
                    et_name.setText("");
                    et_id.setText("");
                }

            }
        });

        FloatingActionButton fab1 = (FloatingActionButton)inflate.findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialize();
                if (!validate()) {
                    Toast.makeText(getActivity(), "Failed Update", Toast.LENGTH_SHORT).show();
                } else {
                    up();
                    et_phno.setText("");
                    et_name.setText("");
                    et_id.setText("");
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
                    de();
                    et_phno.setText("");
                    et_name.setText("");
                    et_id.setText("");
                }

            }
        });
        // Inflate the layout for this fragment
        return inflate;
    }

    public void ad()
    {
        try{
            controller.insert_ppl(et_id.getText().toString(), et_name.getText().toString(), et_phno.getText().toString());
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

    public void de()
    {
        controller.del_ppl(et_id.getText().toString(), et_name.getText().toString(), et_phno.getText().toString());
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Deleted SUCCESSFULLY");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.show();
    }

    public void initialize()
    {
        et_name_pat = et_name.getText().toString().trim();
        et_phno_pat=et_phno.getText().toString().trim();
        et_id_pat=et_id.getText().toString().trim();


    }

    public boolean validate() {
        boolean valid = true;

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (et_name.length() == 0) {
            et_name.setError("Please Enter valid name");
            valid = false;
            et_name.setText("");
        }
        if (et_id.length() == 0) {
            et_id.setError("Please Enter valid Id");
            valid = false;
            et_id.setText("");
        }
        if(et_phno.length()==0 ||et_phno.length()>10 ||et_phno.length()<10){
            et_phno.setError("Please Enter valid phone number ");
            valid = false;
            et_phno.setText("");
        }
        return valid;
    }

    public void up()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
        dialog.setTitle("ENTER NEW Number");

        final EditText et_new_user_num=new EditText(getActivity());
        dialog.setView(et_new_user_num);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                controller.update_ppl(et_name.getText().toString(),et_new_user_num.getText().toString());
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

}
