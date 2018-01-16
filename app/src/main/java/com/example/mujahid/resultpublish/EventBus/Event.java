package com.example.mujahid.resultpublish.EventBus;

/**
 * Created by Mujahid on 12/29/2017.
 */

public class Event {

    public static class ScienceFragmentActivityMessage {

        String[] message;

        public ScienceFragmentActivityMessage(String[] m) {

            message = m;
        }

        public String[] getMessage() {

            return message;
        }
    }
    public static class CommerceFragmentActivityMessage {

        String[] message;

        public CommerceFragmentActivityMessage(String[] m) {

            message = m;
        }

        public String[] getMessage() {

            return message;
        }
    }

    public static class ArtFragmentActivityMessage {

        String[] message;

        public ArtFragmentActivityMessage(String[] m) {

            message = m;
        }

        public String[] getMessage() {

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