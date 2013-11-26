/** App creada por Nicolás Boné
 * 
 * Dudas o recomendaciones a nico-bone@hotmail.com
 * 
 * Proyecto Programacion 2013
 * 
 * 
 * */
package com.example.easysound;

import android.content.Context;
import android.net.Uri;

/** SGI es una clase abstracta con variables que pueden ser utilizadas en todas
 * las otras clases del proyecto. Basicamente lo que hace es tomar o estableccecr un 
 * Uri referido a la cancion, un newUri (uri nuevo) que es lo que se termina
 * seteando como ringtone o notification y un contexto.
 * */

public abstract class SGI {
private static Uri uricancion;
private static Uri newUri;
private static Context context  = null;
public static Uri getUricancion() {
	return uricancion;
}

public static void setUricancion(Uri uricancion) {
	SGI.uricancion = uricancion;
}


public static Uri getNewUri() {
	return newUri;
}

public static void setNewUri(Uri newUri) {
	SGI.newUri = newUri ;
	
}

public static Context getContext() {
	return context;
}

public static void setUricancion(Context context) {
	SGI.context = context;
}
}
