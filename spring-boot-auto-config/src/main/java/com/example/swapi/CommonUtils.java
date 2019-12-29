package com.example.swapi;

import com.example.swapi.data.LengthUnit;

import static com.example.swapi.data.LengthUnit.FOOT;

/**
 * @author Gary Cheng
 */
public class CommonUtils {
    public static Double getLength(Double length, LengthUnit unit) {
        if (unit == FOOT) {
            return length * 3.28084;
        } else {
            return length;
        }
    }
}
