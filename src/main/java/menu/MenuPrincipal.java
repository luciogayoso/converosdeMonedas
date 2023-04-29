package menu;


import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MenuPrincipal {

	public static void main(String[] args) {
String eleccion = (String) elegirConversor();
try {
	if(eleccion == "Conversor de Moneda") {
		
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
	
		System.out.println(eleccion);
		double montoIngresado = tomarMontoAConvertir();
		String monedaDeCamvio =(String) elegirMoneda();
		switch (monedaDeCamvio) {
		case "Convertir a Dolar": {
			double cambio = montoIngresado * Iusd;
			mostrarResultadoConvertido(cambio);
			break;
		}
        case "Convertir a Euro": {
			double cambio = montoIngresado * Ieur;
			mostrarResultadoConvertido(cambio);
			break;
		}
        case "Convertir a Peso Uruguayo": {
			double cambio = montoIngresado * Iuyu;
			mostrarResultadoConvertido(cambio);
			break;
        }
        case "Convertir a Guaranies": {
			double cambio = montoIngresado * Ipyg;
		    mostrarResultadoConvertido(cambio);
			break;
        }
			default:
			throw new IllegalArgumentException("Unexpected value: " + monedaDeCamvio);
		}
	}else {
		System.out.println(eleccion);
		double temperatura = tomarTemperatura();
		String temperaturaConvercion  =(String) elegirTipoTemperatura();
		switch (temperaturaConvercion) {
		case "Grados Fahrenheit": {
			double grados = (temperatura * 1.8)+32;
			mostrarResultadoTemperatura(grados, "Fahreheit");
			break;
		}
        case "Grados Kelvin": {
			double grados = temperatura +273.15;
			mostrarResultadoTemperatura(grados,"Kelvin");
			break;
		}
			default:
			throw new IllegalArgumentException("Unexpected value: " + temperaturaConvercion);
		}
	}
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}

}
	
	
	public static Object elegirConversor () {
		JDialog.setDefaultLookAndFeelDecorated(true);
		Object [] SelectionValues = { "Conversor de Moneda" , "Conversor de Temperatura" };
	    String initialSelection = "Conversor de Moneda" ;
	    Object seleccion = JOptionPane.showInputDialog(null, "¿Qué conversor deseas usar?" ,
	         "Elige uno" , JOptionPane.QUESTION_MESSAGE, null, SelectionValues, initialSelection);
		return seleccion;
	}
	
	
	public static double tomarMontoAConvertir() {
		Double monto =Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa la cantidad de dinero quieres convertir"));
		mostrarResultadoConvertido(monto);
		return monto;
	}
	
    public static void mostrarResultadoConvertido(double moneda) {
    	JOptionPane.showMessageDialog(null, "Su conversion es de "+ moneda);
    }
    
    public static Object elegirMoneda () {
		JDialog.setDefaultLookAndFeelDecorated(true);
		Object [] SelectionValues = { "Convertir a Dolar" , "Convertir a Euro", "Convertir a Peso Uruguayo" , "Convertir a Guaranies" };
	    String initialSelection = "Elegir Moneda" ;
	    Object seleccion = JOptionPane.showInputDialog(null, "¿Qué moneda quiere para la conversion?" ,
	         "Elige uno" , JOptionPane.QUESTION_MESSAGE, null, SelectionValues, initialSelection);
		return seleccion;
	}
    
    
    public static void entraDelValorIncorrecto() {
    	JOptionPane.showMessageDialog(null, "El valor es incorrecto");
    }	
    
    public static double tomarTemperatura() {
		Double monto =Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa la Temperatura en centigrados a convertir"));
		mostrarResultadoConvertido(monto);
		return monto;
	}
    
    public static Object elegirTipoTemperatura () {
		JDialog.setDefaultLookAndFeelDecorated(true);
		Object [] SelectionValues = { "Grados Fahrenheit" , "Grados Kelvin" };
	    String initialSelection = "Elegir Moneda" ;
	    Object seleccion = JOptionPane.showInputDialog(null, "¿Qué tipo de temperatura quiere convertir?" ,
	         "Elige uno" , JOptionPane.QUESTION_MESSAGE, null, SelectionValues, initialSelection);
		return seleccion;
	}
    
    public static void mostrarResultadoTemperatura(double temperatura, String tipoGrado) {
    	JOptionPane.showMessageDialog(null, "Su conversion es de "+ temperatura + " grados");
    }
    
}    
	



