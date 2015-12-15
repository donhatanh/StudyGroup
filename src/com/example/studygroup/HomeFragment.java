package com.example.studygroup;

import java.util.Arrays;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.studygroup.R;
import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.SessionState;
import com.facebook.Session;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.OnErrorListener;
import com.facebook.widget.ProfilePictureView;
import com.facebook.Response;
public class HomeFragment extends Fragment {
	
	public HomeFragment(){}
	private static final String TAG = "MainActivity";
	private UiLifecycleHelper uiHelper;
	LoginButton authButton;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        uiHelper = new UiLifecycleHelper(this.getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {				 

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
       authButton = (LoginButton) rootView.findViewById(R.id.authButton);

       /* Facebook Authentication  */
       LoginButton authButton = (LoginButton) rootView.findViewById(R.id.authButton);
       authButton.setOnErrorListener(new OnErrorListener() {

           @Override
           public void onError(FacebookException error) {
               Log.i(TAG, "Error " + error.getMessage());
           }
       });
       // set permission list, Don't forget to add email
       authButton.setReadPermissions(Arrays.asList("user_friends","public_profile", "email"));

       // session state call back event
       authButton.setSessionStatusCallback(new Session.StatusCallback() {
           @Override
           public void call(Session session, SessionState state,
                            Exception exception) {

               Log.i(TAG, session.toString());
               if (session.isOpened()) {
                   // not accessed
                   Log.i(TAG, "Access Token" + session.getAccessToken());
                   Request.newMeRequest(session, new Request.GraphUserCallback() {
                       @Override
                       public void onCompleted(GraphUser user, Response response) {
                           if (user != null) {
                               Log.d("Social", user.getId());
                               // not accessed
                           }
                       }
                   }).executeAsync();
               }
           }
       }); // Facebook Auth end
        return rootView;
    }
//	 @Override
//	    public void onViewCreated(View view, Bundle savedInstanceState) {
//	        super.onViewCreated(view, savedInstanceState);
//
//	        profilePictureView = (ProfilePictureView) view.findViewById(R.id.profile_pic);
//	       // defaultProfilePic = (ImageView) view.findViewById(R.id.default_user_pic);
//            profilePictureView.setVisibility(View.VISIBLE);
//          //  defaultProfilePic.setVisibility(View.GONE);
//	    }
	 

	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) {
	        Log.i(TAG, "Logged in...");
	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	    }
	}

	@SuppressWarnings("unused")
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
}
