package cadastro_rota2;



import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Horario implements Cloneable{


			private int HorarioId;
			private int HorarioDiaId;
			private int Horario_RotaItinerarioId;
			private String HorarioSaida;
			private String HorarioChegada;
			private double HorarioPreco; //DECIMAL NOT NULL,
			private int Horario_MotoristaId;
			private int Horario_OnibusId;
			

			public Horario(int horarioId, int horarioDiaId,
					int horario_RotaItinerarioId, String horarioSaida,
					String horarioChegada, double horarioPreco,
					int horario_MotoristaId, int horario_OnibusId) {

				HorarioId = horarioId;
				HorarioDiaId = horarioDiaId;
				Horario_RotaItinerarioId = horario_RotaItinerarioId;
				HorarioSaida = horarioSaida;
				HorarioChegada = horarioChegada;
				HorarioPreco = horarioPreco;
				Horario_MotoristaId = horario_MotoristaId;
				Horario_OnibusId = horario_OnibusId;
			}
			public Horario(){
				
			}


			public int getHorarioId() {
				return HorarioId;
			}


			public void setHorarioId(int horarioId) {
				HorarioId = horarioId;
			}


			public int getHorarioDiaId() {
				return HorarioDiaId;
			}


			public void setHorarioDia(int horarioDia) {
				HorarioDiaId = horarioDia;
			}


			public int getHorario_RotaItinerarioId() {
				return Horario_RotaItinerarioId;
			}


			public void setHorario_RotaItinerarioId(int horario_RotaItinerarioId) {
				Horario_RotaItinerarioId = horario_RotaItinerarioId;
			}


			public String getHorarioSaida() {
				return HorarioSaida;
			}


			public void setHorarioSaida(String horarioSaida) {
				HorarioSaida = horarioSaida;
			}


			public String getHorarioChegada() {
				return HorarioChegada;
			}


			public void setHorarioChegada(String horarioChegada) {
				HorarioChegada = horarioChegada;
			}


			public double getHorarioPreco() {
				return HorarioPreco;
			}


			public void setHorarioPreco(double horarioPreco) {
				HorarioPreco = horarioPreco;
			}


			public int getHorario_MotoristaId() {
				return Horario_MotoristaId;
			}


			public void setHorario_MotoristaId(int horario_MotoristaId) {
				Horario_MotoristaId = horario_MotoristaId;
			}


			public int getHorario_OnibusId() {
				return Horario_OnibusId;
			}


			public void setHorario_OnibusId(int horario_OnibusId) {
				Horario_OnibusId = horario_OnibusId;
			}


			public static void main(String[] args) throws ParseException {
				//Horario h = new Horario();
				//Date data = new Date();
				DateFormat sd = DateFormat.getTimeInstance(DateFormat.SHORT);
				//SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
				//NumberFormat nFormat = NumberFormat.getCurrencyInstance();
				//BigDecimal big = new BigDecimal("1.00");
				//System.out.println(nFormat.format(big));
//				Date dateFormat = format.parse("22:00");
//				h.setHorarioChegada(new Time(date.getTime()));
//				System.out.println(format.format(date));
//				System.out.println(h.getHorarioChegada());
//				//h.setHorarioChegada(new Time(date.getTime()));
//				h.getHorarioChegada().setTime(h.getHorarioChegada().getTime()+900000);
//				System.out.println(h.getHorarioChegada());
				
//            	Date timeSaida = dateFormat.parse("22:00"); //pegando horario de saida da rota anterior
//            	String duracao = "00:30"; //pegando duracao da rota anterior
//            	Date timeDuracao = dateFormat.parse(duracao);// passando duracao da rota anterior para dat
//            	Long a = timeDuracao.getTime();
//            	Long b = timeSaida.getTime();
//            	Long Result = a-b;
//            	System.out.println(a);
//            	System.out.println(b);
//            	Date timeSaidaProx = new Date();
//            	
//				Time horaNoBanco01 = Time.valueOf("01:19:30");  
//				Time horaNoBanco02 = Time.valueOf("02:40:31");
//            	
//            	System.out.println(timeSaida);
//            	System.out.println(timeDuracao);
//            	System.out.println("Saida:"+timeSaidaProx);
				Time horaNoBanco01 = Time.valueOf("00:20"+":00");
				
				
				Calendar c1 = Calendar.getInstance();  
				c1.setTime(horaNoBanco01); 
				c1.add(Calendar.MINUTE,20);
				System.out.println(horaNoBanco01.toString());
				Double preco = Double.parseDouble("50.00");
				System.out.println(preco);
//				Time Aux = Time.parse(s)
//				System.out.println(Aux);
				System.out.println(sd.format(c1.getTime()));
			}
}
