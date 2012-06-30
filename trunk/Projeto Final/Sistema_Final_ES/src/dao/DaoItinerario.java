package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classe.Itinerario;

public class DaoItinerario {

    public ArrayList<Itinerario> consultarTodosItinerarios() {
        ArrayList<Itinerario> arrayList = new ArrayList<Itinerario>();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT ItinerarioId, origem.CidadeNome, origem.CidadeId, destino.CidadeNome, destino.CidadeId "
                    + "FROM Itinerario "
                    + "INNER JOIN Cidade AS origem ON (Itinerario_CidadeOrigem = origem.CidadeId)"
                    + "INNER JOIN Cidade AS destino ON(destino.CidadeId = Itinerario_CidadeDestino) order by origem.CidadeNome, destino.CidadeNome";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Itinerario Itinerario = new Itinerario();
                Itinerario.setId(rs.getInt("ItinerarioId"));
                Itinerario.setItinerario_cidadeOrigemId(rs.getInt("origem.CidadeId"));
                Itinerario.setItinerario_cidadeDestinoId(rs.getInt("destino.CidadeId"));
                Itinerario.setItinerario_cidadeOrigem(rs.getString("origem.CidadeNome"));
                Itinerario.setItinerario_cidadeDestino(rs.getString("destino.CidadeNome"));
                arrayList.add(Itinerario);
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
}
