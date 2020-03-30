package com.example.mysensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor accelerometer,proxy,gyroscope,magnetic,light;
    TextView x,y,z,prox,gyx,gyy,gyz,max,may,maz,LUX;
    TextView list;

    private SensorManager senSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proxy = senSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        gyroscope = senSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetic = senSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        light = senSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        senSensorManager.registerListener(this, accelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        senSensorManager.registerListener(this, proxy , SensorManager.SENSOR_DELAY_NORMAL);
        senSensorManager.registerListener(this,gyroscope,senSensorManager.SENSOR_DELAY_NORMAL);
        senSensorManager.registerListener(this,magnetic,senSensorManager.SENSOR_DELAY_NORMAL);
        senSensorManager.registerListener(this,light,senSensorManager.SENSOR_DELAY_NORMAL);



        x = (TextView) findViewById( R.id.xval);
        y = (TextView) findViewById( R.id.yval);
        z = (TextView) findViewById( R.id.zval);
        prox=(TextView) findViewById(R.id.proxval);
        gyx = (TextView)findViewById(R.id.gyrox);
        gyy = (TextView)findViewById(R.id.gyroy);
        gyz = (TextView)findViewById(R.id.gyroz);
        max = (TextView)findViewById(R.id.magx);
        may = (TextView)findViewById(R.id.magy);
        maz = (TextView)findViewById(R.id.magz);
        LUX = (TextView)findViewById(R.id.luxl);
        list= (TextView)findViewById(R.id.tlist);
        List<Sensor> sensorList = senSensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder strBuilder = new StringBuilder();
        for(Sensor s: sensorList){
            strBuilder.append(s.getName()+"\n");
        }
        list.setVisibility(View.VISIBLE);
        list.setText(strBuilder);
    }
    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy)
    {}
    @Override
    public final void onSensorChanged(SensorEvent event)
    {

        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float fx = event.values[0];
            float fy = event.values[1];
            float fz= event.values[2];
            x.setText(String.valueOf(fx));
            y.setText(String.valueOf(fy));
            z.setText(String.valueOf(fz));
        }
        if (mySensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            float gx = event.values[0];
            float gy = event.values[1];
            float gz = event.values[2];
            gyx.setText(String.valueOf(gx));
            gyy.setText(String.valueOf(gy));
            gyz.setText(String.valueOf(gz));
        }
        if (mySensor.getType() == Sensor.TYPE_PROXIMITY) {
prox.setText(String.valueOf(event.values[0]));
        }
        if (mySensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
        {
            float mx = event.values[0];
            float my = event.values[1];
            float mz = event.values[2];
            max.setText(String.valueOf(mx));
            may.setText(String.valueOf(my));
            maz.setText(String.valueOf(mz));
        }
        if (mySensor.getType() == Sensor.TYPE_LIGHT)
        {
            float light = event.values[0];
            LUX.setText(String.valueOf(light));

        }


    }

    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        senSensorManager.registerListener(this, proxy, SensorManager.SENSOR_DELAY_NORMAL);
        senSensorManager.registerListener(this,gyroscope,senSensorManager.SENSOR_DELAY_NORMAL);
        senSensorManager.registerListener(this,magnetic,senSensorManager.SENSOR_DELAY_NORMAL);
        senSensorManager.registerListener(this,light,senSensorManager.SENSOR_DELAY_NORMAL);

    }
}
