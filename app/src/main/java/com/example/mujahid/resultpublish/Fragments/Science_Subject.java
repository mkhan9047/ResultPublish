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
public class Science_Subject extends Fragment {
    EditText physics;
    EditText chemistry;
    EditText biology;
    EditText bangla;
    EditText english;
    EditText islam;


    public Science_Subject() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GlobalBus.getBus().register(this);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_science__subject, container, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getView() != null) {
            physics = getView().findViewById(R.id.physics);
            chemistry = getView().findViewById(R.id.chmistry);
            biology = getView().findViewById(R.id.biology);
            bangla = getView().findViewById(R.id.Scibangla);
            english = getView().findViewById(R.id.SciEnglish);
            islam = getView().findViewById(R.id.SciIslam);
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

    public void mainCall(){
        sendDatToActivity(physics.getText().toString(),chemistry.getText().toString(),biology.getText().toString(),bangla.getText().toString(),english.getText().toString(),islam.getText().toString());
    }

    @Subscribe
    public void getMessage(Event.ActivityFragmentMessage activityFragmentMessage) {

        Toast.makeText(getActivity(),activityFragmentMessage.getMessage(),Toast.LENGTH_LONG).show();
    }

    public void sendDatToActivity(String physic, String che, String bio, String ban, String eng, String is){
        HashMap<String, Integer> data = new HashMap<>();
        data.put("phy",Integer.valueOf(physic));
        data.put("che",Integer.valueOf(che));
        data.put("bio",Integer.valueOf(bio));
        data.put("ban",Integer.valueOf(ban));
        data.put("eng",Integer.valueOf(eng));
        data.put("is",Integer.valueOf(is));
        Event.ScienceFragmentActivityMessage fragmentActivityMessage  =
                new Event.ScienceFragmentActivityMessage(data);
        GlobalBus.getBus().post(fragmentActivityMessage);

    }
}
