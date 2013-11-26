/** App creada por Nicolás Boné
 * 
 * Dudas o recomendaciones a nico-bone@hotmail.com
 * 
 * Proyecto Programacion 2013
 * 
 * 
 * */

package com.example.easysound;
//importamos las librerias que vamos a utilizar
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

/**
 * Esta es la clase principal con toda la parte grafica, es la primer actividad que
 * se ejecuta en el programa
 * @author nico
 *
 */
public class CambiadorDeSonidos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cambiador_de_sonidos);
		Toast.makeText(this, "IMPORTANTE: SELECCIONAR SIEMPRE UNA CANCION", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cambiador_de_sonidos, menu);
		return true;
	}	
	/** "cambio" es lo que cambia de actividad entre esta y "BuscadorDeSonidos"
	 * al tocar el boton "Seleccionar Cancion"
	 * 
	 * @param v es el objeto que disparo la accion (cambio)
	 */
	public void cambio (View v){
		Intent strawberry = new Intent(this, BuscadorDeSonidos.class);
		startActivity(strawberry);
		
		finish();
	}
	

}
