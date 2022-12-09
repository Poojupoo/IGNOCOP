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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Devices_Fragment extends Fragment {

    EditText device_id, password;
    DB_Controller controller;
    String emailpat, idpat;
    String namepat;
    String phone_numberpat, passwordpat, con_passpat, addresspat;
    RadioButton master, user;
    Button reg;
    //static String valid;


    public Devices_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View inflate = inflater.inflate(R.layout.fragment_devices_, container, false);
        device_id = (EditText)inflate.findViewById(R.id.devid);

        password = (EditText)inflate.findViewById(R.id.devpass);

        controller = new DB_Controller(getActivity(), "", null, 1);
        FloatingActionButton fab = (FloatingActionButton)inflate.findViewById(R.id.fabadd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initialize();
                if (!validate()) {
                    Toast.makeText(getActivity(), "Failed Adding", Toast.LENGTH_SHORT).show();

                } else {
                    register();
                    device_id.setText("");
                    password.setText("");
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
                    device_id.setText("");
                    password.setText("");

                }


            }
        });


        // Inflate the layout for this fragment
        return inflate;
    }

    public void delete()
    {
        controller.del_device(device_id.getText().toString(), password.getText().toString());
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Deleted SUCCESSFULLY");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.show();
    }

    public void register() {



        try {
            controller.insert(device_id.getText().toString(), password.getText().toString());
            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("REGISTERED SUCCESSFULLY");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.show();

        } catch (SQLiteException e) {
            Toast.makeText(getActivity(), "ALREADY EXISTS", Toast.LENGTH_SHORT).show();
        }



        password.setText("");
        device_id.setText("");

    }


    public void initialize() {

        passwordpat = password.toString().trim();


    }


    public boolean validate() {
        boolean valid = true;

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (device_id.length() == 0) {
            device_id.setError("Please Enter valid ID");
            valid=false;
            device_id.setText("");
        }

        if (password.length() == 0) {
            password.setError("Please Enter valid Password");
            valid=false;
            password.setText("");
        }

        return valid;
    }

}
