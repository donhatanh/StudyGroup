package com.example.studygroup;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Registration extends Activity implements OnClickListener, OnItemSelectedListener {

	private Button mSubmit;
	private EditText mUsername;
	private EditText mPassword;
	private EditText mEmail;

	protected DatabaseHelper DB = new DatabaseHelper(Registration.this); 

	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_signup);

		//Assignment of UI fields to the variables
		mSubmit = (Button)findViewById(R.id.buttonCreateAccount);
		mSubmit.setOnClickListener(this);


		mUsername = (EditText)findViewById(R.id.username);
		mPassword = (EditText)findViewById(R.id.password);
		mEmail = (EditText)findViewById(R.id.email); 
	}

	public void onClick(View v) 
	{

		String uname = mUsername.getText().toString();
		String pass = mPassword.getText().toString();
		String email = mEmail.getText().toString();

		if(uname.equals(""))
		{
			Toast.makeText(getApplicationContext(), "Please enter your Username", Toast.LENGTH_SHORT).show();
		}


		else if(pass.equals(""))
			{
				Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();

			}
			else 
				if(email.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please enter your Email ID", Toast.LENGTH_SHORT).show();
				}
		addEntry(uname, pass, email);

	}


	public void onDestroy()
	{
		   super.onDestroy();
		    if(null !=DB)
		       DB.close();
	}

	private void addEntry(String uname, String pass, String email) 
	{
		SQLiteDatabase db = DB.getWritableDatabase();
System.out.println("username:" + uname + "password: " +pass + "email :" +email);
		ContentValues values = new ContentValues();
		values.put("username", uname);
		values.put("password", pass);
		values.put("email", email);
		
		try
		{			
			db.insert(DatabaseHelper.TABLE_STUDENT, null, values);
			
			Toast.makeText(getApplicationContext(), "your details submitted Successfully...", Toast.LENGTH_SHORT).show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
}
