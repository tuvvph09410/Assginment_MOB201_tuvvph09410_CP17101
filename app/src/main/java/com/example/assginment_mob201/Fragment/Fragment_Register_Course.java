package com.example.assginment_mob201.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.assginment_mob201.Adapter.SpinnerListCourseAdapter;
import com.example.assginment_mob201.Custom.CustomSpinner;
import com.example.assginment_mob201.Entity.SpinnerCourse;
import com.example.assginment_mob201.R;
import com.example.assginment_mob201.Until.CheckConnected;
import com.example.assginment_mob201.Until.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Register_Course extends Fragment implements CustomSpinner.OnSpinnerEventsListener {
    private SpinnerListCourseAdapter spinnerListCourseAdapter;
    private List<SpinnerCourse> spinnerCourseList;
    private CustomSpinner customSpinner;

    public Fragment_Register_Course() {
        // Required empty public constructor
    }


    public static Fragment_Register_Course newInstance() {
        Fragment_Register_Course fragment = new Fragment_Register_Course();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_course, container, false);

        this.initViewByID(view);

        this.initSpinnerCourse();

        return view;
    }


    private void initSpinnerCourse() {
        this.spinnerCourseList = new ArrayList<>();
        this.customSpinner.setSpinnerEventsListener(this);
        this.spinnerListCourseAdapter = new SpinnerListCourseAdapter(getActivity());
        if (CheckConnected.haveNetworkConnection(getActivity()) == true) {
            getDataSeason();
        }
        this.customSpinner.setAdapter(spinnerListCourseAdapter);
        this.customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerCourse spinnerCourse=spinnerCourseList.get(position);
                Toast.makeText(getActivity(), String.valueOf(spinnerCourse.getId()), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initViewByID(View view) {
        this.customSpinner = view.findViewById(R.id.spinner_selectCourse);
    }

    @Override
    public void onPopupWindowOpened(Spinner spinner) {
        this.customSpinner.setBackground(getActivity().getDrawable(R.drawable.desgin_spinner_course_up));
    }

    @Override
    public void onPopupWindowClosed(Spinner spinner) {
        this.customSpinner.setBackground(getActivity().getDrawable(R.drawable.desgin_spinner_course_down));
    }

    private void getDataSeason() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArray = new JsonArrayRequest(Server.getUrlSeason(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            String url = jsonObject.getString("url");
                            String title = jsonObject.getString("title");
                            SpinnerCourse spinnerCourse = new SpinnerCourse(id, url, title);
                            spinnerCourseList.add(spinnerCourse);
                            spinnerListCourseAdapter.setList(spinnerCourseList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnected.showToastCheckConnect(getActivity(), error.toString());
            }
        });
        requestQueue.add(jsonArray);
    }
}