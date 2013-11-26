package com.example.easysound;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Listener implements OnItemClickListener {
	@Override
	public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
		Toast toast = Toast.makeText(null, 'd', Toast.LENGTH_LONG);
		toast.show();
		Log.e("asdfas", "asdfasfdfddffddfasdf");
		Log.e("afd", this.toString());
		Intent intent = new Intent(null, CambiadorDeSonidos.class);
	} 
 
}
