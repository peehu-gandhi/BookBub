package com.example.bookbub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserProfileFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView userimg;
    TextView  welcome,booksuploaded,quotesshared;//name,mobno;
    EditText editname,editmobno;
    Button logout,edit;
    public UserProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserProfileFragment newInstance(String param1, String param2) {
        UserProfileFragment fragment = new UserProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_user_profile, container, false);
        userimg=v.findViewById(R.id.userimg);
        welcome=v.findViewById(R.id.welcome);
        booksuploaded=v.findViewById(R.id.booksuploaded);
        quotesshared=v.findViewById(R.id.quotesshared);
        editname=v.findViewById(R.id.name);
        editmobno=v.findViewById(R.id.mobno);
        logout=v.findViewById(R.id.logout);
        loadData();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("simplifiedcodingsharedpref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                getActivity().sendBroadcast(broadcastIntent);
                Intent intent = new Intent(getActivity(), LoginAndRegister.class);
                startActivity(intent);

                // getContext().startActivity(new Intent(getContext(),LoginFragment.class));

            }
        });
        return v;
    }

    private void loadData()
    {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("simplifiedcodingsharedpref", Context.MODE_PRIVATE);
        String name= sharedPreferences.getString("name", null);
        String no= sharedPreferences.getString("no", null);
        int quotes_shared= sharedPreferences.getInt("quotes_shared", 0);
        String passwd= sharedPreferences.getString("passwd", null);
        int books_shared= sharedPreferences.getInt("books_shared", 0);
        welcome.setText(welcome.getText()+name);
        booksuploaded.setText(String.valueOf(books_shared));
        editmobno.setText(no);
        editname.setText(name);
        quotesshared.setText(String.valueOf(quotes_shared));
    }
}