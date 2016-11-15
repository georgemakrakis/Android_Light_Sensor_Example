package com.example.georgemakrakis.lightsensor_example;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
        SensorManager sMgr = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        List<Sensor> list = sMgr.getSensorList(Sensor.TYPE_ALL);
        Log.d("Sensors supported",list.toString());

        SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        float min =  lightSensor.getMaximumRange();
        sensorManager.registerListener(lightSensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

        /*SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(lightSensor==null)
        {
            TextView text = (TextView)findViewById(R.id.backround);
            text.setBackgroundColor(0xFFFF0000);
        }*/
    }
    SensorEventListener lightSensorEventListener = new SensorEventListener()
    {
        public void onSensorChanged(SensorEvent event)
        {
            TextView text = (TextView) findViewById(R.id.backround);
            if (event.sensor.getType() == Sensor.TYPE_LIGHT)
            {
                text.setText(""+event.values[0]);
                if(event.values[0]<=30.0)
                {
                    text.setBackgroundColor(Color.GREEN);//green
                }
                else if(event.values[0]>=40.0)
                {
                    text.setBackgroundColor(Color.RED);//set red color
                }
                /*for (int i = 1; i < event.values.length; i++)
                {
                    if (event.values[i] == event.values[i - 1])
                    {

                    }
                    else
                    {
                        t
                    }

                }*/
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {

        }
    };
}
