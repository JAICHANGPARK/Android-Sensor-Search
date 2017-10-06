package org.dreamwalker.sensorfinder;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener,StepListener {
    private static final String TAG = "MainActivity";

    SensorManager mSensorManager;

    private Sensor mAccelerometer;
    private Sensor mGyroscope;
    private Sensor mLight;
    private Sensor mGravity;
    private Sensor mStepCounter;
    private Sensor mStepDetecter;

    private StepDetector simpleStepDetector;

    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;

    private TextView TvSteps;
    private Button BtnStart,BtnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO : 1. 디바이스에서 사용 가능한 센서 정보 확인
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // TODO: 10/6/17   모든 센서 값을 받아 리스트로 반환한다
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL); // getSensorList 함수는 반환값이 List 이다.

        // TODO: 10/6/17 개별 센서 인스턴스 생성.
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetecter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        TvSteps = (TextView) findViewById(R.id.tv_steps);
        BtnStart = (Button) findViewById(R.id.btn_start);
        BtnStop = (Button) findViewById(R.id.btn_stop);

        for (int i = 0; i < deviceSensors.size(); i++) {
            // TODO: 10/6/17  디바이스 센서변수(리스트)에 들어있는 i번째 센서 를 가져온다.
            Sensor sensor = deviceSensors.get(i);

            Log.e(TAG, sensor.getName() + "[ " + sensor.getType() + " ]");

            if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                Log.e(TAG, sensor.getName() + " [" + sensor.getType() + "]");
                Log.e(TAG, "-- Power: " + sensor.getPower());
                Log.e(TAG, "-- Vendor: " + sensor.getVendor());
                Log.e(TAG, "-- Version: " + sensor.getVersion());
                Log.e(TAG, "-- Resolution: " + sensor.getResolution());
                Log.e(TAG, "-- Maximum Range: " + sensor.getMaximumRange());
                Log.e(TAG, "-- Minimum Delay: " + sensor.getMinDelay());
                //Log.e(TAG, "-- Maximum Delay: " + sen);
            } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                Log.d(TAG, sensor.getName() + " [" + sensor.getType() + "]");
                Log.d(TAG, "-- Power: " + sensor.getPower());
                Log.d(TAG, "-- Vendor: " + sensor.getVendor());
                Log.d(TAG, "-- Version: " + sensor.getVersion());
                Log.d(TAG, "-- Resolution: " + sensor.getResolution());
                Log.d(TAG, "-- Maximum Range: " + sensor.getMaximumRange());
                Log.d(TAG, "-- Minimum Delay: " + sensor.getMinDelay());
                //Log.d(TAG, "-- Maximum Delay: " + sensor.getMaxDelay());
            } else if (sensor.getType() == Sensor.TYPE_LIGHT) {
                Log.d(TAG, sensor.getName() + " [" + sensor.getType() + "]");
                Log.d(TAG, "-- Power: " + sensor.getPower());
                Log.d(TAG, "-- Vendor: " + sensor.getVendor());
                Log.d(TAG, "-- Version: " + sensor.getVersion());
                Log.d(TAG, "-- Resolution: " + sensor.getResolution());
                Log.d(TAG, "-- Maximum Range: " + sensor.getMaximumRange());
                Log.d(TAG, "-- Minimum Delay: " + sensor.getMinDelay());
                //Log.d(TAG, "-- Maximum Delay: " + sensor.getMaxDelay());
            } else if (sensor.getType() == Sensor.TYPE_GRAVITY) {
                Log.d(TAG, sensor.getName() + " [" + sensor.getType() + "]");
                Log.d(TAG, "-- Power: " + sensor.getPower());
                Log.d(TAG, "-- Vendor: " + sensor.getVendor());
                Log.d(TAG, "-- Version: " + sensor.getVersion());
                Log.d(TAG, "-- Resolution: " + sensor.getResolution());
                Log.d(TAG, "-- Maximum Range: " + sensor.getMaximumRange());
                Log.d(TAG, "-- Minimum Delay: " + sensor.getMinDelay());
                //Log.d(TAG, "-- Maximum Delay: " + sensor.getMaxDelay());
            } else if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
                Log.d(TAG, sensor.getName() + " [" + sensor.getType() + "]");
                Log.d(TAG, "-- Power: " + sensor.getPower());
                Log.d(TAG, "-- Vendor: " + sensor.getVendor());
                Log.d(TAG, "-- Version: " + sensor.getVersion());
                Log.d(TAG, "-- Resolution: " + sensor.getResolution());
                Log.d(TAG, "-- Maximum Range: " + sensor.getMaximumRange());
                Log.d(TAG, "-- Minimum Delay: " + sensor.getMinDelay());
                //Log.d(TAG, "-- Maximum Delay: " + sensor.getMaxDelay());
            } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
                Log.d(TAG, sensor.getName() + " [" + sensor.getType() + "]");
                Log.d(TAG, "-- Power: " + sensor.getPower());
                Log.d(TAG, "-- Vendor: " + sensor.getVendor());
                Log.d(TAG, "-- Version: " + sensor.getVersion());
                Log.d(TAG, "-- Resolution: " + sensor.getResolution());
                Log.d(TAG, "-- Maximum Range: " + sensor.getMaximumRange());
                Log.d(TAG, "-- Minimum Delay: " + sensor.getMinDelay());
                //Log.d(TAG, "-- Maximum Delay: " + sensor.getMaxDelay());
            }
        }

        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                numSteps = 0;
                mSensorManager.registerListener(MainActivity.this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });


        BtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                mSensorManager.unregisterListener(MainActivity.this);

            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(sensorEvent.timestamp, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
        }

       /* synchronized (this) {
            // TODO: 10/6/17  4. Sensor row data 수신
            float var0, var1, var2;

            switch (sensorEvent.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    var0 = sensorEvent.values[0];
                    var1 = sensorEvent.values[1];
                    var2 = sensorEvent.values[2];
                    Log.d(TAG, "Accelerometer: " + "x = " + var0 + ", y = " + var1 + " , z = " + var2);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    var0 = sensorEvent.values[0];
                    var1 = sensorEvent.values[1];
                    var2 = sensorEvent.values[2];
                    Log.d(TAG, "Gyroscope: " + "x = " + var0 + ", y = " + var1 + " , z = " + var2);
                    break;
                case Sensor.TYPE_LIGHT:
                    var0 = sensorEvent.values[0];
                    Log.d(TAG, "Light: " + "lux = " + var0);
                    break;
                case Sensor.TYPE_GRAVITY:
                    var0 = sensorEvent.values[0];
                    var1 = sensorEvent.values[1];
                    var2 = sensorEvent.values[2];
                    Log.d(TAG, "Gravity: " + "x = " + var0 + ", y = " + var1 + " , z = " + var2);
                    break;
                case Sensor.TYPE_STEP_COUNTER:
                    var0 = sensorEvent.values[0];
                    Log.d(TAG, "Step Counter: " + var0);
                    break;
                case Sensor.TYPE_STEP_DETECTOR:
                    var0 = sensorEvent.values[0];
                    Log.d(TAG, "Step Detector: " + var0);
                    break;
                default:
                    break;
            }
        }*/

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d(TAG, "onAccuracyChanged()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: 10/6/17  3. 이벤트 리스너 설정
        //SENSOR_DELAY_NORMAL --> 0.2 sec
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);

        if (mStepDetecter != null) {
            mSensorManager.registerListener(this, mStepDetecter, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void step(long timeNs) {

        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);

    }
}
