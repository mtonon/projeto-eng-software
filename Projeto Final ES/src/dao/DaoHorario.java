package dao;

import entidades.Horario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import util.BancoDados;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoHorario {

public boolean cadastrarNovoHorario(Horario horario){
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "insert into Horario (HorarioDiaId, Horario_RotaItinerarioId, HorarioSaida, HorarioChegada, HorarioPreco," +
            		" Horario_MotoristaId, Horario_OnibusId)" +
            		" VALUES ( '"+horario.getHorarioDiaId()+"','"+horario.getHorario_RotaItinerarioId()+
            		"','"+horario.getHorarioSaida()+"','"+horario.getHorarioChegada()+"','"+horario.getHorarioPreco()+
            		"','"+horario.getHorario_MotoristaId()+"','"+horario.getHorario_OnibusId()+"')";
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

    public ArrayList<ArrayList<String>> consultarTodosHorarios(int id) {
        ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();        
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT origem.cidadeNome, destino.cidadeNome, HorarioPreco, HorarioSaida, HorarioChegada "
                    + "FROM Horario "
                    + "INNER JOIN RotaItinerario ON (Horario_RotaItinerarioId = rotaItinerario_rotaId) "
                    + "INNER JOIN Rota ON (rotaId = RotaItinerario_RotaId) "
                    + "INNER JOIN Cidade origem ON (origem.cidadeId = Rota_CidadeOrigem) "
                    + "INNER JOIN Cidade destino ON (destino.cidadeId = Rota_CidadeDestino) "
                    + "WHERE RotaItinerario_ItinerarioId = " + id + " "
                    + "GROUP BY origem.CidadeNome,destino.CidadeNome "
                    + "ORDER BY RotaItinerarioOrdem";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ArrayList<String> array = new ArrayList<String>();
                array.add(rs.getString("origem.cidadeNome"));
                array.add(rs.getString("destino.cidadeNome"));
                array.add(rs.getString("HorarioPreco"));
                array.add(rs.getString("HorarioSaida"));
                array.add(rs.getString("HorarioChegada"));
                matriz.add(array);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();

        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();

        }
        return matriz;
    }
}