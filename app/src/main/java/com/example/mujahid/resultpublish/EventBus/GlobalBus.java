package com.example.mujahid.resultpublish.EventBus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Mujahid on 12/29/2017.
 */

public class GlobalBus {

    private static EventBus bus;

    public static EventBus getBus(){
        if(bus == null){
            bus = EventBus.getDefault();

        }
        return bus;
    }
}
