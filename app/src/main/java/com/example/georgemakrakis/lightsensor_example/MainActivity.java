package com.example.georgemakrakis.lightsensor_example;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checking which  sensors are supported in our device
        SensorManager sMgr = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = sMgr.getSensorList(Sensor.TYPE_ALL);
        Log.d("Sensors supported",list.toString());

        //choosing the light sensor if supported
        Sensor lightSensor = sMgr.getDefaultSensor(Sensor.TYPE_LIGHT);
        sMgr.registerListener(lightSensorEventListener, lightSensor, 2000000);//2 sec sampling - delay

    }

    SensorEventListener lightSensorEventListener = new SensorEventListener()
    {
        public void onSensorChanged(SensorEvent event)
        {
            TextView text = (TextView) findViewById(R.id.backround);
            if (event.sensor.getType() == Sensor.TYPE_LIGHT)
            {
                text.setText(""+event.values[0]);//the value of sensor
                if(event.values[0]<=30.0)
                {
                    text.setBackgroundColor(Color.GREEN);//set green color
                }
                else if(event.values[0]>=40.0)
                {
                    text.setBackgroundColor(Color.RED);//set red color
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {

        }
    };
}
