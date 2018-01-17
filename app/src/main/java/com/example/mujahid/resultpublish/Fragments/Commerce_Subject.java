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
public class Commerce_Subject extends Fragment {
    EditText accounting;
    EditText entireprenure;
    EditText business;
    EditText bangla;
    EditText english;
    EditText islam;


    public Commerce_Subject() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GlobalBus.getBus().register(this);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_commerce__subject, container, false);
    }

    public void onActivityCreated(Bundle bundle) {

        super.onActivityCreated(bundle);
        if (getView() != null) {

            accounting = getView().findViewById(R.id.accounting);
            entireprenure = getView().findViewById(R.id.entireprenure);
            business = getView().findViewById(R.id.business);
            bangla = getView().findViewById(R.id.Combangla);
            english = getView().findViewById(R.id.ComEnglish);
            islam = getView().findViewById(R.id.ComiIslam);
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
        sendDatToActivity(business.getText().toString(),accounting.getText().toString(),entireprenure.getText().toString(),bangla.getText().toString(),english.getText().toString(),islam.getText().toString());
    }

    public void sendDatToActivity(String business, String accounting, String entireprenure, String ban, String eng, String is){
        HashMap<String, Integer> data = new HashMap<>();
        data.put("bus",Integer.valueOf(business));
        data.put("acc",Integer.valueOf(accounting));
        data.put("entire",Integer.valueOf(entireprenure));
        data.put("ban",Integer.valueOf(ban));
        data.put("eng",Integer.valueOf(eng));
        data.put("is",Integer.valueOf(is));
        Event.CommerceFragmentActivityMessage fragmentActivityMessage  =
                new Event.CommerceFragmentActivityMessage(data);


        GlobalBus.getBus().post(fragmentActivityMessage);

    }

}
