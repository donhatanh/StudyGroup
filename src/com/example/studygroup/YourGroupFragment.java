package com.example.studygroup;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.studygroup.R;

public class YourGroupFragment extends Fragment {
	
	public YourGroupFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_groups, container, false);
         
        return rootView;
    }
}
