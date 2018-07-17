package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;

/**
 * Created by fiona on 09/06/2018.
 */


public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    String date;
    String time;

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> pEarthquakes) {
        //we call the super class (ArrayAdaptor's) constructor
        super(context, 0, pEarthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        // get magnitude for current earthquake
        double magnitude = currentEarthquake.getItemMagnitude();

        // Find the TextView with view ID magnitude
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_text_view);

        // set desired decimal format
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(magnitude);

        // Display the magnitude of the current earthquake in that TextView
        magnitudeTextView.setText(output);

// Set the proper background color on the magnitude circle.
// Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getItemMagnitude());
// Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        // get location for current earthquake
        String string = currentEarthquake.getItemLocation();

        // split up primary location and location offset
        if (string.contains("of")) {
            String[] parts = string.split("of");
            offset = parts[0];
            primary = parts[1];

            // Handle location entries with no offset
        } else {
            offset = "Near the ";
            primary = string;
        }

        TextView locationPrimaryTextView = (TextView) listItemView.findViewById(R.id.primary_location_text_view);
        locationPrimaryTextView.setText(primary);

        TextView locationOffsetTextView = (TextView) listItemView.findViewById(R.id.offset_location_text_view);
        locationOffsetTextView.setText(offset);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getItemTime());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date_text_view);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time_text_view);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }



    }