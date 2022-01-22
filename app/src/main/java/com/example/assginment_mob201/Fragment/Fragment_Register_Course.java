package com.example.assginment_mob201.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.assginment_mob201.Adapter.RecyclerViewCourseApdater;
import com.example.assginment_mob201.Adapter.SpinnerListCourseAdapter;
import com.example.assginment_mob201.Custom.CustomSpinner;
import com.example.assginment_mob201.Entity.Course;
import com.example.assginment_mob201.Entity.SpinnerCourse;
import com.example.assginment_mob201.R;
import com.example.assginment_mob201.Until.CheckConnected;
import com.example.assginment_mob201.Until.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class Fragment_Register_Course extends Fragment implements CustomSpinner.OnSpinnerEventsListener {
    private SpinnerListCourseAdapter spinnerListCourseAdapter;
    private List<SpinnerCourse> spinnerCourseList;
    private List<Course> courseList;
    private CustomSpinner customSpinner;
    private RecyclerViewCourseApdater recyclerViewCourseApdater;
    private RecyclerView rvCourse;
    private int positionFirst = 1;

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

        this.initAdapterCourse();

        this.onItemClickSpinner();

        return view;
    }


    private void initSpinnerCourse() {
        this.spinnerCourseList = new ArrayList<>();
        this.customSpinner.setSpinnerEventsListener(this);
        this.spinnerListCourseAdapter = new SpinnerListCourseAdapter(getActivity());
        if (CheckConnected.haveNetworkConnection(getActivity()) == true) {
            getDataSeason();
        } else {
            CheckConnected.showToastCheckConnect(getActivity(), "Chưa kết nối mạng");
        }
        this.customSpinner.setAdapter(spinnerListCourseAdapter);

    }

    private void initViewByID(View view) {
        this.customSpinner = view.findViewById(R.id.spinner_selectCourse);
        this.rvCourse = view.findViewById(R.id.rv_course);
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

    private void initAdapterCourse() {
        this.courseList = new ArrayList<>();
        this.recyclerViewCourseApdater = new RecyclerViewCourseApdater(getContext());
        this.rvCourse.setHasFixedSize(true);
        this.rvCourse.setLayoutManager(new GridLayoutManager(getContext(), 1));
        this.rvCourse.setBackgroundColor(Color.parseColor("#f7f7fe"));
        this.getDataCourseByIDSeason(positionFirst);
        this.rvCourse.setAdapter(recyclerViewCourseApdater);
    }

    private void onItemClickSpinner() {
        this.customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerCourse spinnerCourse = spinnerCourseList.get(position);
                int idSeason = spinnerCourse.getId();
                if (CheckConnected.haveNetworkConnection(getActivity()) == true) {
                    courseList.clear();
                    getDataCourseByIDSeason(idSeason);
                } else {
                    CheckConnected.showToastCheckConnect(getActivity(), "Chưa kết nối mạng");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getDataCourseByIDSeason(int IDSeason) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getUrlCourse(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            int idSeason = jsonObject.getInt("idSeason");
                            String title = jsonObject.getString("title");
                            String url = jsonObject.getString("url");
                            int cost = jsonObject.getInt("cost");
                            String tDate = jsonObject.getString("toDate");
                            String fDate = jsonObject.getString("fromDate");
//                            Date toDate = simpleDateFormat.parse(tDate);
//                            Date fromDate = simpleDateFormat.parse(fDate);
                            Course course = new Course(id, idSeason, title, url, cost, tDate, fDate);
                            courseList.add(course);
                            recyclerViewCourseApdater.setList(courseList);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnected.showToastCheckConnect(getActivity(), String.valueOf(error));
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("IDSeason", String.valueOf(IDSeason));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}