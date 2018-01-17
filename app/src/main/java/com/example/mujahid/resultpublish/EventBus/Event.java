package com.example.mujahid.resultpublish.EventBus;

import java.util.HashMap;

/**
 * Created by Mujahid on 12/29/2017.
 */

public class Event {

    public static class ScienceFragmentActivityMessage {

       HashMap<String, Integer> message;

        public ScienceFragmentActivityMessage(HashMap<String, Integer> m) {

            message = m;
        }

        public HashMap<String, Integer> getMessage() {

            return message;
        }
    }
    public static class CommerceFragmentActivityMessage {

        HashMap<String, Integer> message;

        public CommerceFragmentActivityMessage(HashMap<String, Integer> m) {

            message = m;
        }

        public HashMap<String, Integer> getMessage() {

            return message;
        }
    }

    public static class ArtFragmentActivityMessage {

        HashMap<String, Integer>  message;

        public ArtFragmentActivityMessage(HashMap<String, Integer>  m) {

            message = m;
        }

        public HashMap<String, Integer>  getMessage() {

            return message;
        }
    }

    public static class ActivityFragmentMessage {

        private String message;

        public ActivityFragmentMessage(String message) {

            this.message = message;
        }
        public String getMessage() {

            return message;
        }
    }


}