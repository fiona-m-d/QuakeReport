package com.example.android.quakereport;

/**
 * Created by fiona on 09/06/2018.
 */

public class Earthquake {

    /**
     * Value of magnitude
     */
    private double mEarthquakeMagnitude;

    /**
     * String for earthquake location
     */
    private String mEarthquakeLocation;

    /** Time of the earthquake */
    private long mTimeInMilliseconds;

    /** Url link */
    private String mEarthquakeUrl;

    /**
     * Create a new Earthquake object.
     */
    public Earthquake(double earthquakeMagnitude, String earthquakeLocation, long timeInMilliseconds, String earthquakeUrl) {
        mEarthquakeMagnitude = earthquakeMagnitude;
        mEarthquakeLocation = earthquakeLocation;
        mTimeInMilliseconds =  timeInMilliseconds;
        mEarthquakeUrl =  earthquakeUrl;
    }

    /**
     * Get the magnitude
     */
    public double getItemMagnitude() {

        return mEarthquakeMagnitude;
    }

    /**
     * Get the location
     */
    public String getItemLocation() {

        return mEarthquakeLocation;
    }

    /**
     * Get the date
     */
    public long getItemTime() {

        return mTimeInMilliseconds;
    }

    /**
     * Get the url
     */
    public String getItemUrl() {

        return mEarthquakeUrl;
    }

}
