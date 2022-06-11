package com.example.bookbub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonArray;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;


public class AboutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param3";
    private static final String ARG_PARAM2 = "param4";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Ads> images;
    SliderView sliderView;

    public AboutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
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
        if (container != null) {
            container.removeAllViews();
        }
        View v= inflater.inflate(R.layout.fragment_about, container, false);
        sliderView = v.findViewById(R.id.image_slider);

        uploadImages();

        return v;
    }

    private void uploadImages() {
        images = new ArrayList<>() ;

        String urllogin="https://myimon.000webhostapp.com/ads.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urllogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONArray obj = new JSONArray(response);
                            for(int i=0;i<obj.length();i++)
                            {
                                JSONObject o=obj.getJSONObject(i);
                                images.add(new Ads(o.getString("ad")));
                            }
                            SliderAdapter sliderAdapter = new SliderAdapter(images,getContext());
                            sliderView.setSliderAdapter(sliderAdapter);
                            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
                            sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
                            sliderView.startAutoCycle();
                        }
                        catch (JSONException e) {
                            System.out.println("error=="+e.getMessage());
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);


    }
}