package com.example.admin.prova;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final String user_name,mail;
        Intent intent = getIntent();
        user_name = intent.getStringExtra("username");
        mail = intent.getStringExtra("mail");

        TextView txtUser_name = (TextView) findViewById(R.id.txtUser_name);
        ListView list_profile = (ListView) findViewById(R.id.list_Profile);
        ListView list_account = (ListView) findViewById(R.id.list_Account);

        txtUser_name.setText(user_name);

        String [] Profile = {"EDIT PROFILE","CHANGE PASSWORD"};
        String [] Account = {"DELETE ACCOUNT","SIGN OUT"};

        ListSettingsAdapter adapterProfile = new ListSettingsAdapter(this,Profile);
        list_profile.setAdapter(adapterProfile);

        ListSettingsAdapter adapterAccount = new ListSettingsAdapter(this, Account);
        list_account.setAdapter(adapterAccount);


        list_profile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i)
                {
                    case 0:
                            Intent profile = new Intent(SettingsActivity.this, ProfileActivity.class);
                            profile.putExtra("username",user_name);
                            profile.putExtra("mail",mail);
                            startActivity(profile);
                        break;
                    case 1:
                        Intent passwords = new Intent(SettingsActivity.this, PasswordActivity.class);
                        passwords.putExtra("username",user_name);
                        startActivity(passwords);
                        break;


                }
            }
        });

        list_account.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:

                        break;
                    case 1:


                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

                        builder.setTitle("Log out");
                        builder.setMessage("Are you sure you want to exit?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                // Do nothing but close the dialog

                                dialog.dismiss();
                                Intent logOut = new Intent(SettingsActivity.this, MainActivity.class);
                                Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
                                startActivity(logOut);

                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // Do nothing
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();


                        break;

                }
            }
        });
    }
}