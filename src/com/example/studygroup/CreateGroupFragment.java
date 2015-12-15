package com.example.studygroup;

import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CreateGroupFragment extends Fragment {
	DatabaseHelper db;
	public Button bt_login;
	EditText Crs_Code, Crs_Name, Location, Description, Max_Mems;
	String crs_code, crs_name, location, description, max_mem;
	int max_mems;
	private SQLiteDatabase database;
	public CreateGroupFragment(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_groups, container, false);
		db = new DatabaseHelper(getActivity());
		Crs_Code = (EditText) rootView.findViewById(R.id.crs_code);
		Crs_Name = (EditText) rootView.findViewById(R.id.crs_name);
		Location = (EditText) rootView.findViewById(R.id.Location);
		Description = (EditText) rootView.findViewById(R.id.Max_Chars);
		Max_Mems = (EditText) rootView.findViewById(R.id.Max_Box);
		bt_login = (Button) rootView.findViewById(R.id.Create_Group);
		bt_login.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				crs_code = Crs_Code.getText().toString();
				crs_name = Crs_Name.getText().toString();
				location = Location.getText().toString();
				description = Description.getText().toString();				
				max_mem = Max_Mems.getText().toString();
				max_mems = Integer.parseInt(max_mem);
				Groups group = new Groups(crs_code, crs_name, location, description, max_mems);
				startActivity(new Intent(getActivity(), MainActivity.class));				
				db.createGroup(group);
			}
		});
		return rootView;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}


