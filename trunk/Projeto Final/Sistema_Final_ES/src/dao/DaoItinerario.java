package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import entidades.Itinerario;
import util.BancoDados;

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

    public ArrayList<Itinerario> consultarTodosItinerarios() {
        ArrayList<Itinerario> arrayList = new ArrayList<Itinerario>();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Itinerario";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) { // criando arrayList de itinerario
                Itinerario itinerario = new Itinerario();
                itinerario.setId(rs.getString("itinerarioId"));
                itinerario.setItinerario_cidadeOrigemId(rs.getString("itinerario_cidadeOrigem"));
                itinerario.setItinerario_cidadeDestinoId(rs.getString("itinerario_cidadeDestino"));
                itinerario.setItinerario_cidadeOrigem(consultaCidade(rs.getString("itinerario_cidadeOrigem")));
                itinerario.setItinerario_cidadeDestino(consultaCidade(rs.getString("itinerario_cidadeDestino")));
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

    public ArrayList<Itinerario> consultarTodosItinerarios2() {
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
