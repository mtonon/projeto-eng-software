package cadastro_rota2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import classe.Itinerario;
import dao.BancoDados;

public class DaoItinerario {

    public int cadastrarItinerario(Itinerario itinerario) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Itinerario WHERE Itinerario_CidadeOrigem = " + itinerario.getItinerario_cidadeOrigemId() + " AND Itinerario_CidadeDestino = " + itinerario.getItinerario_cidadeDestinoId();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return 1;
            }
            if (itinerario.getItinerario_cidadeOrigemId() != itinerario.getItinerario_cidadeDestinoId()) {
                sql = "INSERT INTO Itinerario VALUES (0, " + itinerario.getItinerario_cidadeOrigemId() + ", " + itinerario.getItinerario_cidadeDestinoId() + ")";
                stmt.executeUpdate(sql);
            } else {
                return 2;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return 0;
    }

    public int alterarItinerario(Itinerario itinerario) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Itinerario WHERE Itinerario_CidadeOrigem = " + itinerario.getItinerario_cidadeOrigemId() + " AND Itinerario_CidadeDestino = " + itinerario.getItinerario_cidadeDestinoId();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return 1;
            }
            sql = "SELECT * FROM RotaItinerario WHERE RotaItinerario_ItinerarioId = " + itinerario.getId();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return 2;
            }
            if (itinerario.getItinerario_cidadeOrigemId() != itinerario.getItinerario_cidadeDestinoId()) {
                sql = "UPDATE Itinerario SET Itinerario_CidadeOrigem = " + itinerario.getItinerario_cidadeOrigemId() + ", Itinerario_CidadeDestino = " + itinerario.getItinerario_cidadeDestinoId() + " WHERE ItinerarioId = " + itinerario.getId();
                stmt.executeUpdate(sql);
            } else {
                return 3;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean removerItinerario(Itinerario itinerario) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM RotaItinerario WHERE RotaItinerario_ItinerarioId = " + itinerario.getId();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return false;
            }
            sql = "DELETE FROM Itinerario WHERE ItinerarioId = " + itinerario.getId();
            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
            return false;
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public Itinerario consultaItinerario(Itinerario itinerario) {
        Itinerario dadosRota = new Itinerario();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Itinerario WHERE ItinerarioId = " + itinerario.getId() + "";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            dadosRota.setItinerario_cidadeOrigemId(rs.getInt("Itinerario_CidadeOrigem"));
            dadosRota.setItinerario_cidadeDestinoId(rs.getInt("Itinerario_CidadeDestino"));
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return dadosRota;
    }
		
    public ArrayList<Itinerario> consultarTodosItinerarios(){
        ArrayList<Itinerario> arrayList = new ArrayList<Itinerario>();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Itinerario";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) { // criando arrayList de itinerario
            	arrayList.add(new Itinerario(rs.getString("itinerarioId"), rs.getString("itinerario_cidadeOrigem"), rs.getString("itinerario_cidadeDestino"), consultaCidade(rs.getString("itinerario_cidadeOrigem")),consultaCidade(rs.getString("itinerario_cidadeDestino"))));
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
		ArrayList<Itinerario> itinerarios = new ArrayList<Itinerario>();
		DaoItinerario in = new DaoItinerario();
		itinerarios = in.consultarTodosItinerarios();
		
		for (int i = 0; i < itinerarios.size(); i++) {
			System.out.println(itinerarios.get(i).getItinerario_cidadeOrigem());
			System.out.println(itinerarios.get(i).getItinerario_cidadeDestino());
			
		}
	}
	
	
	

}
