package cadastro_rota2;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;

public class teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
		Time timeSaida1 = Time.valueOf("07:30:00"); //pegando horario de saida do TXT na primeira vez
		Time timeSaida2 = Time.valueOf("06:30:00");
		Calendar calAux = Calendar.getInstance();
		Calendar calAux1 = Calendar.getInstance();
    	calAux.setTime(timeSaida1);
    	calAux1.setTime(timeSaida2);
    	System.out.println(calAux.compareTo(calAux1));
    	//calAux.add(Calendar.MINUTE, 30); //somando a duração (em minutos)para obter horario de chegada
    	String horarioChegada = dateFormat.format(calAux.getTime());
    	System.out.println(horarioChegada);

	}

}
