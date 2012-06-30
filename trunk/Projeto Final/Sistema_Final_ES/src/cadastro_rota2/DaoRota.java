package cadastro_rota2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.BancoDados;


public class DaoRota {
    public ArrayList<Rota> carregaRotas(int id){
    	DaoItinerario daoItinerario = new DaoItinerario(); //para nao replicar cod para consulta do nome da cidade!
        ArrayList<Rota> arrayList = new ArrayList<Rota>();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Rota where rota_cidadeOrigem = " + id +"";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) { // criando arrayList de itinerario
            	arrayList.add(new Rota(rs.getString("rotaId"), rs.getString("rota_cidadeOrigem"), rs.getString("rota_cidadeDestino"), daoItinerario.consultaCidade(rs.getString("rota_cidadeOrigem")),daoItinerario.consultaCidade(rs.getString("rota_cidadeDestino")),rs.getString("rotaDuracao")));
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
       
        return arrayList;
    }
    
    public static void main(String[] args) {
		ArrayList<Rota> rotas = new ArrayList<Rota>();
		DaoRota in = new DaoRota();
		rotas = in.carregaRotas(1);
		
		for (int i = 0; i < rotas.size(); i++) {
			System.out.println(rotas.get(i).getRota_cidadeOrigem());
			System.out.println(rotas.get(i).getRota_cidadeDestino());
			System.out.println(rotas.get(i).getRotaDuracao());
			
		}
	}

}
