package siscomp.osocron.com.proyectoacelerometro;

import android.content.ContentValues;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

class SensorView implements SensorEventListener {

	private float mAccelerometerValues[] = new float[3];
	private float mMagneticValues[] = new float[3];
	static long tiempo_inicial;
	static long tiempo_final;

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}
	
	private static double sumar(double values[]){
		double result = 0;

		for (double value : values) result += value;
		return result;
	}
	
	private static double calcularPromedio(double values[]){
		return sumar(values) / values.length;
	}

	private static double calcularDesviacionEstandar(double promedio, double values[]){

		double desviacion = 0;
		double diferencia;

		for (double value : values) {
			diferencia = (value - promedio);
			desviacion = desviacion + (diferencia * diferencia);
		}
		desviacion = desviacion / values.length;
		desviacion = Math.sqrt(desviacion);
		return desviacion;
	}
	
	//Algoritmo inicial antes de Sensors 
	private String physicalActivityRecognitionV1(double x[], double y[], double z[]){
		String result = "Nothing...";
		double promedioX = calcularPromedio(x);
		double promedioY = calcularPromedio(y);
		double promedioZ = calcularPromedio(z);
	
		double desviacionX = calcularDesviacionEstandar(promedioX, x);
		double desviacionY = calcularDesviacionEstandar(promedioY, y);
		double desviacionZ = calcularDesviacionEstandar(promedioZ, z);
		
		double acelerationSum = 0;
		double acelerationAvg;
		double SMA = 0;
		
		int rowT = x.length;

		//Calcular la sumatoria de cada eje a partir de la matriz generada con las 200 lecturas del acelerometro
		for (int i = 0; i < rowT; i++) {
			SMA += (Math.abs(x[i]) + Math.abs(y[i]) + Math.abs(z[i]));
			acelerationSum += (Math.sqrt((x[i] * x[i]) + (y[i] * y[i]) + (z[i] * z[i])));
		}
		//Calcular promedio de aceleracion
		acelerationAvg = acelerationSum / x.length;
		
		if ((promedioZ > promedioY && SMA > 1400 && SMA < 2300)){
			//&& (Main.collmotor != null && Main.collmotor.txtActividad != null)
			//Celular detenido horizontalmente
			result = "El celular se encuentra detenido horizontalmente...";
		} else if ((promedioY > promedioZ)) {
			if (acelerationAvg < 10) {
				if (desviacionX < 1 && desviacionY < 1 && desviacionZ < 1) {
					//Detenido viendo el celular
					//if (Main.collmotor != null && Main.collmotor.txtActividad != null) 
					result = "Detenido viendo el celular...";
				} else {
					//Caminando 
					//if (Main.collmotor != null && Main.collmotor.txtActividad != null) 
					//Caminando
					result = "Caminando...";
				}
			} else {
				//Corriendo
				//if (Main.collmotor != null && Main.collmotor.txtActividad != null)
				result = "Corriendo...";
			}
		} else {
			if ((promedioY < promedioZ) && acelerationAvg < 10) {
				if (desviacionX < 1 && desviacionY < 1 	&& desviacionZ < 1) {
					// Detenido viendo cel
					//if (Main.collmotor != null && Main.collmotor.txtActividad != null)
					result = "Detenido viendo el celular...";
				} else {
					// Caminando viendo cel
					//if (Main.collmotor != null && Main.collmotor.txtActividad != null)
					result = "Caminando viendo el celular...";
				}
			}
		}
		return result;
	}
	//Algoritmo modificado para Sensors que recibe x,y,z del acelerometro
	/*public String physicalActivityRecognitionV2(double x[], double y[], double z[]){
		String result = "Nothing...";
		
		double promedioX = calcularPromedio(x);
		double promedioY = calcularPromedio(y);
		double promedioZ = calcularPromedio(z);
		
		//////////////////////////////////////////
		//// Valores obtenidos del experimento ////
		//Detenido en superficie plana
		double pX_DetenidoSP = -0.076132697; 
		double dX_DetenidoSP = 0.086181948;
		double pY_DetenidoSP = 0.148521; 
		double dY_DetenidoSP = 0.0630772; 
		double pZ_DetenidoSP = 9.456258185; 
		double dZ_DetenidoSP = 0.108093296; //dZ_DetenidoSP += pZ_DetenidoSP * error;
		///////////////////////////////////////////
		///Caminando viendo celular
		double pX_CaminandoVC = -0.284932199;
		double dX_CaminandoVC = 1.100607196;
		double pY_CaminandoVC = 5.4143533;
		double dY_CaminandoVC = 1.79353269;
		double pZ_CaminandoVC = 8.281029225;
		double dZ_CaminandoVC = 1.641371075;					
		///////////////////////////////////////////
		///De pie detenido viendo celular
		double pX_DepieVC = -0.460863508;
		double dX_DepieVC = 0.153407015;
		double pY_DepieVC = 9.35461244;
		double dY_DepieVC = 0.115577257;
		double pZ_DepieVC = 2.07072312;
		double dZ_DepieVC = 9.596559347;	
		///////////////////////////////////////////
		///Corriendo
		double pX_Corriendo = 5.525507694;
		double dX_Corriendo = 4.328760102;
		double pY_Corriendo = 7.213183246;
		double dY_Corriendo = 4.818112233;
		double pZ_Corriendo = 3.399573235;
		double dZ_Corriendo = 3.326247873;	
		///////////////////////////////////////////
		//// Definicion de limites
		//////////////////////////////////////////
		double limInferiorX_DetenidoSP = pX_DetenidoSP - dX_DetenidoSP;
		double limSuperiorX_DetenidoSP = pX_DetenidoSP + dX_DetenidoSP;
		double limInferiorY_DetenidoSP = pY_DetenidoSP - dY_DetenidoSP;	
		double limSuperiorY_DetenidoSP = pY_DetenidoSP + dY_DetenidoSP;	
		double limInferiorZ_DetenidoSP = pZ_DetenidoSP - dZ_DetenidoSP;
		double limSuperiorZ_DetenidoSP = pZ_DetenidoSP + dZ_DetenidoSP;			
		
		double limInferiorX_CaminandoVC = pX_CaminandoVC - dX_CaminandoVC;
		double limSuperiorX_CaminandoVC = pX_CaminandoVC + dX_CaminandoVC;
		double limInferiorY_CaminandoVC = pY_CaminandoVC - dY_CaminandoVC;
		double limSuperiorY_CaminandoVC = pY_CaminandoVC + dY_CaminandoVC;
		double limInferiorZ_CaminandoVC = pZ_CaminandoVC - dZ_CaminandoVC;
		double limSuperiorZ_CaminandoVC = pZ_CaminandoVC + dZ_CaminandoVC;
	
		double limInferiorX_DepieVC = pX_DepieVC - dX_DepieVC;
		double limSuperiorX_DepieVC = pX_DepieVC + dX_DepieVC;
		double limInferiorY_DepieVC = pY_DepieVC - dY_DepieVC;
		double limSuperiorY_DepieVC = pY_DepieVC + dY_DepieVC;
		double limInferiorZ_DepieVC = pZ_DepieVC - dZ_DepieVC;	
		double limSuperiorZ_DepieVC = pZ_DepieVC + dZ_DepieVC;
	
		double limInferiorX_Corriendo = pX_Corriendo - dX_Corriendo;
		double limSuperiorX_Corriendo = pX_Corriendo + dX_Corriendo;
		double limInferiorY_Corriendo = pY_Corriendo - dY_Corriendo;
		double limSuperiorY_Corriendo = pY_Corriendo + dY_Corriendo;
		double limInferiorZ_Corriendo = pZ_Corriendo - dZ_Corriendo;
		double limSuperiorZ_Corriendo = pZ_Corriendo + dZ_Corriendo;
	
		//Toast.makeText(Main.collmotor, "PromedioX: " + promedioX, Toast.LENGTH_LONG).show();
		//Toast.makeText(Main.collmotor, "Desviacion X: " + calcularDesviacionEstandar(promedioX,x), Toast.LENGTH_LONG).show();
		//Toast.makeText(Main.collmotor, "PromedioY: " + promedioY, Toast.LENGTH_LONG).show();
		//Toast.makeText(Main.collmotor, "Desviacion Y: " + calcularDesviacionEstandar(promedioY,y), Toast.LENGTH_LONG).show();
		//Toast.makeText(Main.collmotor, "PromedioZ: " + promedioY, Toast.LENGTH_LONG).show();
		//Toast.makeText(Main.collmotor, "Desviacion Z: " + calcularDesviacionEstandar(promedioZ,z), Toast.LENGTH_LONG).show();
			
		//Algoritmo version 2 (tomando en cuenta media y desviacion estondar (error))
		if ((limInferiorX_DetenidoSP <= promedioX && limSuperiorX_DetenidoSP >= promedioX) && 
			(limInferiorY_DetenidoSP <= promedioY && limSuperiorY_DetenidoSP >= promedioY) &&
			(limInferiorZ_DetenidoSP <= promedioZ && limSuperiorZ_DetenidoSP >= promedioZ)
			){
			result = "El celular se encuentra detenido horizontalmente";
		}else if 
			((limInferiorX_CaminandoVC <= promedioX && limSuperiorX_CaminandoVC >= promedioX) && 
			(limInferiorY_CaminandoVC <= promedioY && limSuperiorY_CaminandoVC >= promedioY) &&
			(limInferiorZ_CaminandoVC <= promedioZ && limSuperiorZ_CaminandoVC >= promedioZ)
		){
			result = "Caminando viendo el celular";
		}else if 
			((limInferiorX_DepieVC <= promedioX && limSuperiorX_DepieVC >= promedioX) && 
			(limInferiorY_DepieVC <= promedioY && limSuperiorY_DepieVC >= promedioY) &&
			(limInferiorZ_DepieVC <= promedioZ && limSuperiorZ_DepieVC >= promedioZ)
		){
			result = "Detenido viendo el celular";
		}else if 
			((limInferiorX_Corriendo <= promedioX && limSuperiorX_Corriendo >= promedioX) && 
			(limInferiorY_Corriendo <= promedioY && limSuperiorY_Corriendo >= promedioY) &&
			(limInferiorZ_Corriendo <= promedioZ && limSuperiorZ_Corriendo >= promedioZ)
		){
			result = "Corriendo";
		}
		return result;
	}*/
	
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		// Cada sensor puede provocar que un thread pase por aqui, asi que
		// hay que sincronizar el acceso
		synchronized (this) {
			switch (event.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				//La lectora del acelerometro debe llenar la tabla de lecturas
				//La ventana es de 200 lecturas aprox., los valores son colocados en una matriz de 200 filas (lecturas) 3 columnas (x,y,z).
				if (tiempo_inicial == 0) {
					tiempo_inicial = System.currentTimeMillis();
					tiempo_final = System.currentTimeMillis();
				}else
					tiempo_final = System.currentTimeMillis();

				System.arraycopy(event.values, 0, mAccelerometerValues, 0, mAccelerometerValues.length);
				
				//double diffSec = (tiempo_final - tiempo_inicial) * (0.001);
				//diffSec < 9 || 
				//Si las 200 lecturas de la ventana no se cumplen entonces se sigue leyendo la informacion
				if ((CollMotor.row < 200) && mAccelerometerValues.length == 3) {	
						CollMotor.accelerometerX[CollMotor.row] = mAccelerometerValues[0];
						CollMotor.accelerometerY[CollMotor.row] = mAccelerometerValues[1];
						CollMotor.accelerometerZ[CollMotor.row] = mAccelerometerValues[2];
						
						CollMotor.row++;

						boolean sync_ac_local = Main.prefs.getBoolean("sync_ac_local", false);
						
						if (sync_ac_local) {
							ContentValues values = new ContentValues();
							values.put("x", mAccelerometerValues[0]);
							values.put("y", mAccelerometerValues[1]);
							values.put("z", mAccelerometerValues[2]);
							values.put("date", android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date()).toString());
						}

				}else{

					String actividad = physicalActivityRecognitionV1(CollMotor.accelerometerX, CollMotor.accelerometerY, CollMotor.accelerometerZ);
					Main.collmotor.txtActividad.setText(actividad);
					
					
					CollMotor.row = 0;
					tiempo_inicial = System.currentTimeMillis();
					tiempo_final = System.currentTimeMillis();
				}
				break;
			case Sensor.TYPE_MAGNETIC_FIELD:
				System.arraycopy(event.values, 0, mMagneticValues, 0, 3);
				break;
			default:
				
			}
		}
	}
}

