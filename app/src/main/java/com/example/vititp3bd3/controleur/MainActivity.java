package com.example.vititp3bd3.controleur;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vititp3bd3.R;
import com.example.vititp3bd3.modele.Viticulteur;
import com.example.vititp3bd3.utilitaire.AccesHTTP;

public class MainActivity extends Activity {
	private static final String serveur="141.94.222.166:8040/";
	private static final String chemin="apiviticulture/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		AccesHTTP accesDonnees = new AccesHTTP(){
			@Override
			protected void onPostExecute(Long result) {
				ArrayList<Viticulteur> listeViticulteurs = new ArrayList<Viticulteur>();
				long idV;
				String nomV;
				
				Log.d("log","donnees brutes : "+this.ret);
				try {
					JSONArray tabJson = new JSONArray(this.ret);
					for(int i=0;i<tabJson.length();i++){
						idV = Long.parseLong(tabJson.getJSONObject(i).getString("idV"));
						nomV = tabJson.getJSONObject(i).getString("nomV");
						listeViticulteurs.add(new Viticulteur(idV,nomV));
					}
					Log.d("log","Liste d'objets : "+listeViticulteurs.toString());
				}
				catch (JSONException e){
					Log.d("log","pb decodage JSON");
				}
			}
			
		}; 
		accesDonnees.execute("http://"+serveur+chemin+"getViticulteurs.php");
	}

}
