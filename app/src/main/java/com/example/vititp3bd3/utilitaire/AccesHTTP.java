package com.example.vititp3bd3.utilitaire;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class AccesHTTP extends AsyncTask<String, Integer, Long> {
	public String ret="";
	//private ArrayList<NameValuePair> parametres;
	private HashMap<String, String> params;
	
	public AccesHTTP(){
		params = new HashMap<String, String>();
		//parametres = new ArrayList<NameValuePair>();
	}
	
	public void addParam(String nom, String valeur){
		params.put(nom, valeur);
		//parametres.add(new BasicNameValuePair(nom,valeur));
	}
	
	@Override
	protected Long doInBackground(String... urls) {
		// TODO Auto-generated method stub
		// Récupération des paramètres pour les envoyer dans la requête HTTP
		StringBuilder sbParams = new StringBuilder();
		int i = 0;
		for (String key : params.keySet()) {
			try {
				if (i != 0){
					sbParams.append("&");
				}
				sbParams.append(key).append("=")
						.append(URLEncoder.encode(params.get(key), "UTF-8"));

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			i++;
		}
		HttpURLConnection conn;
		// Création de la connexion HTTP et envoi des données
		try{
			URL urlObj = new URL(urls[0]);
			conn = (HttpURLConnection) urlObj.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept-Charset", "UTF-8");

			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);

			conn.connect();

			String paramsString = sbParams.toString();

			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(paramsString);
			wr.flush();
			wr.close();

			// traitement du retour de la requête (réponse)
			try {
				InputStream in = new BufferedInputStream(conn.getInputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuilder result = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}

				this.ret = result.toString();
				Log.d("test", "result from server: " + result.toString());

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					conn.disconnect();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}



/*
		HttpClient cnxHttp = new DefaultHttpClient();
		HttpPost paramCnx = new HttpPost(urls[0]);
		Log.d("log","urls[0] : "+urls[0]);
		
		try {
		    paramCnx.setEntity(new UrlEncodedFormEntity(parametres));
		    Log.d("log","parametres : "+parametres);
		    HttpResponse reponse = cnxHttp.execute(paramCnx);
		    
		    ret = EntityUtils.toString(reponse.getEntity());
		    Log.d("log","ret : "+ret);
		
		} catch (ClientProtocolException e) {
		    // TODO Auto-generated catch block
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		}
		*/

		return null;
	}

	@Override
	protected void onPostExecute(Long result) {
		// preciser le code dans la classe qui l'appelle en surchargeant la methode
    }
	
}
