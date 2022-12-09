package com.example.ignocop;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
public class Trusted_Fragment extends Fragment {

    EditText et_phno;
    EditText et_name;
    String et_name_pat,et_phno_pat;
    DB_Controller controller;

    public Trusted_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_trusted_, container, false);


        et_name=(EditText)inflate.findViewById(R.id.trustname);
        et_phno=(EditText)inflate.findViewById(R.id.trustphone);


        controller=new DB_Controller(getActivity(),"",null,1);

        FloatingActionButton fab = (FloatingActionButton)inflate.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initialize();
                if (!validate()) {
                    Toast.makeText(getActivity(), "Failed Adding", Toast.LENGTH_SHORT).show();

                } else {
                    sa();
                    et_phno.setText("");
                    et_name.setText("");
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
                    btn_updat();
                    et_phno.setText("");
                    et_name.setText("");
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
                    dele();
                    et_phno.setText("");
                    et_name.setText("");
                }


            }
        });
        // Inflate the layout for this fragment
        return inflate;
    }

    public void dele()
    {
        controller.del_Trusted(et_name.getText().toString(), et_phno.getText().toString());
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Deleted SUCCESSFULLY");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.show();
    }

    public void btn_updat()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
        dialog.setTitle("ENTER NEW Number");

        final EditText et_new_num=new EditText(getActivity());
        dialog.setView(et_new_num);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                controller.update(et_phno.getText().toString(),et_new_num.getText().toString());
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

    public void sa()
    {
        try{
            controller.trust(et_name.getText().toString(), et_phno.getText().toString());
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
        et_name_pat = et_name.getText().toString().trim();
        et_phno_pat=et_phno.getText().toString().trim();


    }
    public boolean validate() {
        boolean valid = true;

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (et_name.length() == 0) {
            et_name.setError("Please Enter valid name");
            valid = false;
            et_name.setText("");
        }
        if(et_phno.length()==0 ||et_phno.length()>10 ||et_phno.length()<10){
            et_phno.setError("Please Enter valid phone number");
            valid = false;
            et_phno.setText("");
        }
        return valid;
    }
}
