package com.example.ignocop;


import android.content.Intent;
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
public class Connect_Fragment extends Fragment {

    EditText username;
    EditText password;
    DB_Controller db;


    public Connect_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        View inflate = inflater.inflate(R.layout.fragment_connect_, container, false);
        db=new DB_Controller(getActivity(),"",null,1);
        username=(EditText)inflate.findViewById(R.id.editTextlog);
        password=(EditText)inflate.findViewById(R.id.editText3pass);
        FloatingActionButton fab = (FloatingActionButton)inflate.findViewById(R.id.fabconnect);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String device_id = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                Boolean res = db.checkUser(device_id,pass);
                if(res == true)
                {
                    Toast.makeText(getActivity(),"Successfully Logged IN",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(), Bluetooth_Connect.class);
                    startActivity(intent);
                    username.setText("");
                    password.setText("");
                }
                else
                {
                    Toast.makeText(getActivity(),"Wrong Username or Password",Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                }

            }
        });
        // Inflate the layout for this fragment
        return inflate;
    }

}
