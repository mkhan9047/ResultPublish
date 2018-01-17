package com.example.mujahid.resultpublish.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mujahid.resultpublish.EventBus.Event;
import com.example.mujahid.resultpublish.EventBus.GlobalBus;
import com.example.mujahid.resultpublish.R;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Arts_Subject extends Fragment {
    EditText histroy;
    EditText math;
    EditText economics;
    EditText bangla;
    EditText english;
    EditText islam;

    public Arts_Subject() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GlobalBus.getBus().register(this);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_arts__subject, container, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getView() != null) {
            histroy = getView().findViewById(R.id.history);
            math = getView().findViewById(R.id.math);
            economics = getView().findViewById(R.id.echonomics);
            bangla = getView().findViewById(R.id.Artbangla);
            english = getView().findViewById(R.id.ArtEnglish);
            islam = getView().findViewById(R.id.ArtiIslam);
        } else {
            Toast.makeText(getActivity(), "Problem is on intilization!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
    }

    @Subscribe
    public void getMessage(Event.ActivityFragmentMessage activityFragmentMessage) {
        Toast.makeText(getActivity(),activityFragmentMessage.getMessage(),Toast.LENGTH_LONG).show();
    }

    public void mainCall(){
        sendDatToActivity(histroy.getText().toString(),economics.getText().toString(),math.getText().toString(),bangla.getText().toString(),english.getText().toString(),islam.getText().toString());
    }

    public void sendDatToActivity(String history, String echonomics, String math, String ban, String eng, String is){

        HashMap<String, Integer> data = new HashMap<>();
        data.put("his",Integer.valueOf(history));
        data.put("echo",Integer.valueOf(echonomics));
        data.put("math",Integer.valueOf(math));
        data.put("ban",Integer.valueOf(ban));
        data.put("eng",Integer.valueOf(eng));
        data.put("is",Integer.valueOf(is));

        Event.ArtFragmentActivityMessage fragmentActivityMessage  =
                new Event.ArtFragmentActivityMessage(data);
        GlobalBus.getBus().post(fragmentActivityMessage);
    }



}
