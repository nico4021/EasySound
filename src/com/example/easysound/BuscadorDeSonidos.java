/** App creada por Nicolás Boné
 * 
 * Dudas o recomendaciones a nico-bone@hotmail.com
 * 
 * Proyecto Programacion 2013
 * 
 * 
 * */

package com.example.easysound;
// Importamos librerias para utilizarlas en la clase
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.AudioColumns;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

/**
 *Esta clase representa la actividad "BuscadorDeSonidos" *(En la aplicacion, es la lista
 * con las canciones). 
 * @author nico
 *
 */
public class BuscadorDeSonidos extends Activity {

	private ListView listsong;
	public Uri asd;
	public Uri newUri = SGI.getNewUri();
	public Context context = SGI.getContext();
	private HashMap<String, String> createEmployee(String name, String number) {
		HashMap<String, String> employeeNameNo = new HashMap<String, String>();
		employeeNameNo.put(name, number);
		return employeeNameNo;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscador_de_sonidos);
        //Generamos la lista de canciones. 
		String[] STAR = {"*"};
		Uri kiwi = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
		Cursor cursor;
		cursor = getContentResolver().query(kiwi, STAR, selection, null, null);
		Cancionero.chargeList(cursor);
		int size = Cancionero.getSongs().size();
		ArrayList<String> mp3list = new ArrayList<String>();
		for (int i = 0 ; i<size; i++){
			Cancionero.setActualSongId(i);
			String nombre = Cancionero.getNombre();
			mp3list.add(nombre);
		}
		//seteamos los elementos en un ListView "lsvSongs"
		listsong = (ListView)findViewById(R.id.lsvSongs);
        
        List<Map<String, String>> employeeList = new ArrayList<Map<String, String>>();
        
        Iterator<String> iteratormusica = mp3list.iterator();
        while (iteratormusica.hasNext()){
        	String cancion = iteratormusica.next();
        	employeeList.add(createEmployee("employees", cancion));
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, employeeList,
				android.R.layout.simple_list_item_1,
				new String[] { "employees" }, new int[] { android.R.id.text1 });
		listsong.setAdapter(simpleAdapter);
        
        listsong.setOnItemClickListener(new OnItemClickListener() {
        	
        	
        	/**
        	 * El onItemClick lo que hace es captar cuando alguien presiona uno de los
        	 *elementos de la lista, cambia de vuelta a la actividad "CambiadorDeSonidos"
        	 *, toma la ruta del archivo, y la guarda en el uri "asd" y pone el nombre 
        	 *de la cancion en el TextView "txtfSelectedSong"
        	 */
            @Override       
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
                setContentView(R.layout.activity_cambiador_de_sonidos);
                Cancionero.setActualSongId((int)id);
                asd = Uri.parse(Cancionero.getDireccion());
                
                String txtvContent = ((TextView)view).getText().toString();
                TextView txtfSelectedSong = (TextView) findViewById(R.id.txtfSelectedSong);
                txtfSelectedSong.setText(txtvContent);
            }     
        	});
        
        
        
        
    }
	/** "aplicar" como dice, aplica la cancion o tono utilizando RingtoneManager, el
	 * tono es seteado como TYPE_NOTIFICATION lo que nos da a saber que cuando nos
	 * llegue un SMS, este es el tono que sonara
	 * 
	 * @param v es el objeto que disparo la accion (aplicar)
	 */
	public void aplicar (View v){	
		try{
		Uri ringtoneUri = asd;
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, ringtoneUri.toString());
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
        values.put(AudioColumns.IS_RINGTONE, true);
        values.put(AudioColumns.IS_NOTIFICATION, true);
        values.put(AudioColumns.IS_ALARM, false);
        values.put(AudioColumns.IS_MUSIC, false);
        Uri uri = MediaStore.Audio.Media.getContentUriForPath(ringtoneUri.toString());
        Uri newUri = getContentResolver().insert(uri, values);
		RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION, newUri);		
		Toast.makeText(this, "Seteado como tono de SMS :3", Toast.LENGTH_LONG).show();
		}
		catch (Exception e){
			Toast.makeText(this, "Porfavor, seleccione una cancion.", Toast.LENGTH_LONG).show();
		}
	}
	
	
	/** "aplicarLlamada" como dice, aplica la cancion o tono utilizando RingtoneManager, el
	 * tono es seteado como TYPE_RINGTONE lo que nos da a saber que cuando nos
	 * llegue una llamada, este es el tono que sonara.
	 * 
	 * @param v es el objeto que disparo la accion (aplicarLlamada)
	 */
	public void aplicarLlamada (View v){
		Uri ringtoneUri = asd;
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, ringtoneUri.toString());
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
        values.put(AudioColumns.IS_RINGTONE, true);
        values.put(AudioColumns.IS_NOTIFICATION, true);
        values.put(AudioColumns.IS_ALARM, false);
        values.put(AudioColumns.IS_MUSIC, false);
        Uri uri = MediaStore.Audio.Media.getContentUriForPath(ringtoneUri.toString());
        Uri newUri = getContentResolver().insert(uri, values);
		RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_RINGTONE, newUri);		
		Toast.makeText(this, "Seteado como tono de llamada :3", Toast.LENGTH_LONG).show();
	}
	
	/** "cambio" es lo que cambia de actividad entre esta y "CambiadorDeSonidos"
	 * 
	 * @param v es el objeto que disparo la accion (cambio)
	 */
	public void cambio(View v){
		Intent strawberry = new Intent(this, CambiadorDeSonidos.class);
		startActivity(strawberry);
		
		super.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buscador_de_sonidos, menu);
		return true;
	}
	

}
