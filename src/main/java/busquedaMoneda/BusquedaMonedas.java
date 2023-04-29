package busquedaMoneda;

import java.io.*;
import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BusquedaMonedas {
	
	public double[] monedas() {
		try {
		OkHttpClient client = new OkHttpClient().newBuilder().build();

		    Request request = new Request.Builder()
		      .url("https://api.apilayer.com/currency_data/live?source=ARS&currencies=USD%2CEUR%2CCLS%2CUYU%2CPYG")
		      .addHeader("apikey", "IjaVFPw7dGmCwRCsxmRM6Oznha0tIgC7")
		      .method("GET", null)
		      .build();
		    Response response = client.newCall(request).execute();
				Gson gson = new Gson();
				String monedasCambio = gson.toJson(response.body().string());
				String[] cambioa = monedasCambio.split(":");
				String[] usd = cambioa[5].split(",");
				String[] eur = cambioa[6].split(",");
				String[] uyu = cambioa[7].split(",");
				char[] pyg = cambioa[8].toCharArray();
				String Spyg = "";
				for (int i = 0; i < 9; i++) {
					 Spyg = Spyg + pyg[i]; 
				}
				
				
				System.out.println(usd[0]);
				System.out.println(eur[0]);
				System.out.println(uyu[0]);
				System.out.println(Spyg);
				
				double Iusd =Double.parseDouble(usd[0]);
				double Ieur =Double.parseDouble(eur[0]);
				double Iuyu =Double.parseDouble(uyu[0]);
				double Ipyg =Double.parseDouble(Spyg);
				
				double[] resultado = new double[4];
				resultado[0] = Iusd;
				resultado[1] = Ieur;
				resultado[2] = Iuyu;
				resultado[3] = Ipyg;
				
				return resultado;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
}
