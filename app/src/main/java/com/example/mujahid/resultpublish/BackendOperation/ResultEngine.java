package com.example.mujahid.resultpublish.BackendOperation;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Mujahid on 12/25/2017.
 */

public class ResultEngine {

    private double sub1, sub2, sub3, sub4, sub5, sub6;

    public String getGPAFromMark(int mark) {

        String counter = "";
        if (mark >= 80) {
            counter = "A+";
        } else if (mark < 80 && mark >= 70) {
            counter = "A";
        } else if (mark < 70 && mark >= 60) {
            counter = "A-";
        } else if (mark < 60 && mark >= 50) {
            counter = "B";
        } else if (mark < 50 && mark >= 40) {
            counter = "C";
        } else if (mark < 40 && mark >= 33) {
            counter = "D";
        } else if (mark < 33 && mark >= 0) {
            counter = "F";
        }

        return counter;
    }

    public String getGPAFromPoint(double point) {
        String counter = "";
        if (point >= 5.00) {
            counter = "A+";
        } else if (point < 5.00 && point >= 4.00) {
            counter = "A";
        } else if (point < 4.00 && point >= 3.50) {
            counter = "A-";
        } else if (point < 3.50 && point >= 3.00) {
            counter = "B";
        } else if (point < 3.00 && point >= 2.50) {
            counter = "C";
        } else if (point < 2.50 && point >= 2.00) {
            counter = "D";
        }

        return counter;
    }

    public double getPoint(Context c, HashMap<String, Integer> cursor) {

        if (cursor.get("s1") >= 80) {
            sub1 = 5;
        } else if ((cursor.get("s1") < 80) && (cursor.get("s1") >= 70)) {
            sub1 = 4;
        } else if ((cursor.get("s1") < 70) && (cursor.get("s1") >= 60)) {
            sub1 = 3.5;
        } else if ((cursor.get("s1") < 60) && (cursor.get("s1") >= 50)) {
            sub1 = 3;
        } else if ((cursor.get("s1") < 50) && (cursor.get("s1") >= 40)) {
            sub1 = 2;
        } else if ((cursor.get("s1") < 40) && (cursor.get("s1") >= 33)) {
            sub1 = 1;
        } else {
            sub1 = 0.0;
        }

        if (cursor.get("s2") >= 80) {
            sub2 = 5;
        } else if ((cursor.get("s2") < 80) && (cursor.get("s2") >= 70)) {
            sub2 = 4;
        } else if ((cursor.get("s2") < 70) && (cursor.get("s2") >= 60)) {
            sub2 = 3.5;
        } else if ((cursor.get("s2") < 60) && (cursor.get("s2") >= 50)) {
            sub2 = 3;
        } else if ((cursor.get("s2") < 50) && (cursor.get("s2") >= 40)) {
            sub2 = 2;
        } else if ((cursor.get("s2") < 40) && (cursor.get("s2") >= 33)) {
            sub2 = 1;
        } else {
            sub2 = 0.0;
        }

        if (cursor.get("s3") >= 80) {
            sub3 = 5;
        } else if ((cursor.get("s3") < 80) && (cursor.get("s3") >= 70)) {
            sub3 = 4;
        } else if ((cursor.get("s3") < 70) && (cursor.get("s3") >= 60)) {
            sub3 = 3.5;
        } else if ((cursor.get("s3") < 60) && (cursor.get("s3") >= 50)) {
            sub3 = 3;
        } else if ((cursor.get("s3") < 50) && (cursor.get("s3") >= 40)) {
            sub3 = 2;
        } else if ((cursor.get("s3") < 40) && (cursor.get("s3") >= 33)) {
            sub3 = 1;
        } else {
            sub3 = 0.0;
        }

        if (cursor.get("s4") >= 80) {
            sub4 = 5;
        } else if ((cursor.get("s4") < 80) && (cursor.get("s4") >= 70)) {
            sub4 = 4;
        } else if ((cursor.get("s4") < 70) && (cursor.get("s4") >= 60)) {
            sub4 = 3.5;
        } else if ((cursor.get("s4") < 60) && (cursor.get("s4") >= 50)) {
            sub4 = 3;
        } else if ((cursor.get("s4") < 50) && (cursor.get("s4") >= 40)) {
            sub4 = 2;
        } else if ((cursor.get("s4") < 40) && (cursor.get("s4") >= 33)) {
            sub4 = 1;
        } else {
            sub4 = 0.0;
        }


        if (cursor.get("s5") >= 80) {
            sub5 = 5;
        } else if ((cursor.get("s5") < 80) && (cursor.get("s5") >= 70)) {
            sub5 = 4;
        } else if ((cursor.get("s5") < 70) && (cursor.get("s5") >= 60)) {
            sub5 = 3.5;
        } else if ((cursor.get("s5") < 60) && (cursor.get("s5") >= 50)) {
            sub5 = 3;
        } else if ((cursor.get("s5") < 50) && (cursor.get("s5") >= 40)) {
            sub5 = 2;
        } else if ((cursor.get("s5") < 40) && (cursor.get("s5") >= 33)) {
            sub5 = 1;
        } else {
            sub5 = 0.0;
        }

        if (cursor.get("s6") >= 80) {
            sub6 = 5;
        } else if ((cursor.get("s6") < 80) && (cursor.get("s6") >= 70)) {
            sub6 = 4;
        } else if ((cursor.get("s6") < 70) && (cursor.get("s6") >= 60)) {
            sub6 = 3.5;
        } else if ((cursor.get("s6") < 60) && (cursor.get("s6") >= 50)) {
            sub6 = 3;
        } else if ((cursor.get("s6") < 50) && (cursor.get("s6") >= 40)) {
            sub6 = 2;
        } else if ((cursor.get("s6") < 40) && (cursor.get("s6") >= 33)) {
            sub6 = 1;
        } else {
            sub6 = 0.0;
        }
        return  (sub1+sub2+sub3+sub4+sub5+sub6)/6;
    }

}
