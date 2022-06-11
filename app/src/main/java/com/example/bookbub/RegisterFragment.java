package com.example.bookbub;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    Button btn_register;
    EditText mobno,edname,edpasswd;
    String name,no,passwd;
    ViewGroup viewGroup;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    char[] otp;

    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View v=inflater.inflate(R.layout.fragment_register,container,false);
        btn_register=v.findViewById(R.id.btn_register);
        mobno=v.findViewById(R.id.mobno);
        edname=v.findViewById(R.id.edname);
        edpasswd=v.findViewById(R.id.edpasswd);

        viewGroup = v.findViewById(android.R.id.content);
        btn_register.setOnClickListener(this);
        return v;
        //return inflater.inflate(R.layout.fragment_register, container, false);
    }
    @Override
    public void onClick(View view) {

    registerUser();
































        //OTP
//        Random random = new Random();
//        otp = new char[4];
//        for (int i=0; i<4; i++)
//        {
//            otp[i]= (char)(random.nextInt(10)+48);
//        }
//
////        Toast.makeText(getApplicationContext(), String.valueOf(otp), Toast.LENGTH_SHORT).show();
//
//        String number  = mobno.getText().toString();
//
//        if(!(mobno.getText().toString().equals(""))) {
//            Toast.makeText(getContext(), "un1", Toast.LENGTH_SHORT).show();
//            new MyAsyncTask(view).execute("https://api.msg91.com/api/sendhttp.php?route=4&sender=TESTIN&message=OTP for your OTP Verification App is : "+String.valueOf(otp)+"&country=91&flash=&unicode=&mobiles="+number+"&authkey=297116AFCGQdLuvdm25d96f3f7");
//        }else{
//            Toast.makeText(getContext(), "un2", Toast.LENGTH_SHORT).show();
//
//            mobno.setError("Please Enter your mobile number");
//        }

    }

    private void registerUser() {
        name=edname.getText().toString();
        no=mobno.getText().toString();
        passwd=edpasswd.getText().toString();
        String regurl="https://myimon.000webhostapp.com/register_book_user.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, regurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                       // progressBar.setVisibility(View.GONE);
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
                                        userJson.getString("name"),0,0
                                );
                                if(response=="1") {
                                    Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent(getActivity(),NewActivity.class);
                                    startActivity(i);
                                }
                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getContext()).userLogin(user);

                                //starting the profile activity
                                getActivity().finish();
                                startActivity(new Intent(getContext(), MainActivity.class));
                            }
                        } catch (Exception e)
                        {
                            System.out.println("error---"+e.getMessage());
                           // Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("no", no);
                params.put("passwd", passwd);
                return params;
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
    class MyAsyncTask extends AsyncTask<String, String, String>
    {
        View v;
        URL Url;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream;
        MyAsyncTask(View v){this.v=v;}
        protected String doInBackground(String... urls)
        {
            try {
                Url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection)Url.openConnection();
                inputStream = httpURLConnection.getInputStream();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            openDialog(v);
        }
        }
    private void openDialog(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_layout, viewGroup, false);
        builder.setView(dialogView);
        Button verify=dialogView.findViewById(R.id.verify);
        EditText otp_text=dialogView.findViewById(R.id.otp_text);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        boolean checkOtpNumber = TextUtils.isEmpty(otp_text.getText().toString());
        if(checkOtpNumber == false){
            if(otp_text.equals(otp_text.getText().toString())){
                otp_text.setVisibility(View.INVISIBLE);
                verify.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Verification Successful!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "OTP does not matches!", Toast.LENGTH_SHORT).show();
            }
        }else{
            otp_text.setError("Please Enter OTP First!");
        }
    }
}