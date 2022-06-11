package com.example.bookbub;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText edno, edpasswd;
    Button btn_login;


    public LoginFragment() {
        // Required empty public constructor
    }
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View v=inflater.inflate(R.layout.fragment_login, container, false);
        if (SharedPrefManager.getInstance(getContext()).isLoggedIn()) {
            getActivity().finish();
            startActivity(new Intent(getActivity(), MainActivity.class));
        }
        edno = v.findViewById(R.id.edno);
        edpasswd = v.findViewById(R.id.edpasswd);
        btn_login=v.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();

            }
        });
        return v;

    }

    private void userLogin() {
        final String no = edno.getText().toString();
        final String passwd = edpasswd.getText().toString();
        //validating inputs
        if (TextUtils.isEmpty(no)) {
            edno.setError("Please enter your Mobile no");
            edno.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(passwd)) {
            edpasswd.setError("Please enter your password");
            edpasswd.requestFocus();
            return;
        }
        login(no,passwd);
    }
    private void login(String no,String passwd)
    {
        String urllogin="https://myimon.000webhostapp.com/login_book_user.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urllogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");
                                //creating a new user object
                                User user = new User(
                                        userJson.getString("no"),
                                        userJson.getString("passwd"),
                                        userJson.getString("name"),
                                        userJson.getInt("books_pub"),
                                        userJson.getInt("quotes_shared")
                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getContext()).userLogin(user);
                                //starting the profile activity
                                getActivity().finish();


                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivityForResult(intent,1);

                            } else {
                                Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            System.out.println("error=="+e.getMessage());
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("no", no);
                params.put("passwd", passwd);
                return params;
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
}