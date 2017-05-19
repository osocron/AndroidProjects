package siscomp.osocron.com.proyectoacelerometro;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class Main extends Service {
	static SharedPreferences prefs;
	static CollMotor collmotor = null;
	private static final String TAG = "CollmotorService";
	static SensorManager mSensorManager1;
	Toast t;
	static int count = 0;
	static SensorView mVista;
	static boolean STARTED = false;
	static boolean STOPED = false;


	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	public static void setCollMotor(CollMotor cm) {
		collmotor = cm;
	}

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		count = 0;
		SensorView.tiempo_inicial = 0;
		SensorView.tiempo_final = 0;
		showMessage("Creating service!!");
		t = new Toast(this);
		mVista = new SensorView();
		
		settingSensors();
	}
	
	public void showMessage(String text){
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}
	@Override
	public void onDestroy() {
		Toast.makeText(this, "Stopping Activity Recognition Sensor", Toast.LENGTH_LONG).show();
		STOPED = true;
		Log.d(TAG, "onDestroy");
	}
	

	@Override
	public void onStart(Intent intent, int startid) {
		Log.d(TAG, "onStart");
		if (!STARTED){
			STARTED = true;
			showMessage( "Starting Activity Recognition Sensor....");				
		}			
	}

	
	public void settingSensors() {
		SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> listSensors = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		Sensor acelerometerSensor = listSensors.get(0);
		mSensorManager.registerListener(mVista, acelerometerSensor, SensorManager.SENSOR_DELAY_UI);
		mSensorManager1 = (SensorManager) getSystemService(SENSOR_SERVICE);
	}
}

