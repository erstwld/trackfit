package com.example.trackfit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.lang.Math;

import java.util.ArrayList;
import java.util.Objects;

// Jiaxin added packages about volley to get the weather
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class WorkoutFragment extends Fragment implements View.OnClickListener {
    private ArrayList<String>quotes = new ArrayList<String>();
    private TextView quotesTextView;
    private TextView weatherTextView;
    private RequestQueue queue;
    public WorkoutFragment() {
        this.quotes.add("The Pain You Feel Today, Will Be The Strength You Feel Tomorrow");
        this.quotes.add("You Don't Have To Be Extreme, Just Consistent");
        quotes.add("All Progress Takes Place Outside Your Comfort Zone");
        quotes.add("Later = Never. Do It Now");
        quotes.add("A Little Progress Each Day Adds Up To Big Results");
        quotes.add("Be Somebody Nobody Thought You Could Be");
        quotes.add("When You Feel Like Quitting, Think About Why You Started");
        quotes.add("Find Your Fire");
        quotes.add("Push Through The Pain Every Single Day");
        quotes.add("Turn The Pain Into Power");
        quotes.add("If It's Easy You Are Doing It Wrong");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        Button startWorkout = (Button) view.findViewById(R.id.startWorkoutButton);
        quotesTextView = (TextView) view.findViewById(R.id.quoteTextView) ;
        String toDisplayQuote ="";
        int randQuote = (int)(Math.random() * 9);
        toDisplayQuote = quotes.get(randQuote);
        quotesTextView.setText(toDisplayQuote);
        startWorkout.setOnClickListener(this);

        // Jiaxin added: about weather
        weatherTextView = (TextView) view.findViewById(R.id.weatherTextView) ;
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="https://api.weatherbit.io/v2.0/current?lat=33&lon=33&key=0815ce13ffff4a0bbc264061dbe23ffd";
        //api.openweathermap.org/data/2.5/weather?id=524901&appid=YOUR_API_KEY
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        weatherTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                weatherTextView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startWorkoutButton:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new CurrentWorkoutFragment()).commit();
                break;
        }
    }

    public void getTemp(){
//        weatherTextView = (TextView) view.findViewById(R.id.quoteTextView) ;
    }



}