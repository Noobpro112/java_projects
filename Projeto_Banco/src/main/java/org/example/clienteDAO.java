package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class clienteDAO {

    private final Connection con;  //

    public clienteDAO() {  // Construtor deve ter o mesmo nome da classe
        con = new ConnectionFactory().getConnection();
    }

    public String verificarLogin(String usuario, String senha) {
        String sql = "SELECT * FROM usuarios WHERE user = ? AND senha = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario);  
            stmt.setString(2, senha);     
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("user");  

                
                return id + "," + nome;  
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "0";
    }

    public String verificarsaldo(int id) {
        String sql = "SELECT saldo FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(id));  
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int saldo = rs.getInt("saldo");
                return ("Seu saldo é: " + String.valueOf(saldo));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "ERRO!";
    }

    public String transferir(int id, int id_recebedor, double valor) {
        
        String sql = "SELECT saldo FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next() && rs.getDouble("saldo") >= valor) {
                double saldoRemetente = rs.getDouble("saldo");
                
          
                String sql_update = "UPDATE usuarios SET saldo = ? WHERE id = ?";
                try (PreparedStatement stmt1 = con.prepareStatement(sql_update)) {
                    stmt1.setDouble(1, saldoRemetente - valor);
                    stmt1.setInt(2, id);
                    stmt1.executeUpdate();
                    
                   
                    String sql_receiver = "SELECT saldo FROM usuarios WHERE id = ?";
                    try (PreparedStatement stmt2 = con.prepareStatement(sql_receiver)) {
                        stmt2.setInt(1, id_recebedor);
                        ResultSet rs2 = stmt2.executeQuery();
                        
                        if (rs2.next()) {
                            double saldoDestinatario = rs2.getDouble("saldo");
                            
                           
                            String sql_update2 = "UPDATE usuarios SET saldo = ? WHERE id = ?";
                            try (PreparedStatement stmt3 = con.prepareStatement(sql_update2)) {
                                stmt3.setDouble(1, saldoDestinatario + valor);
                                stmt3.setInt(2, id_recebedor);
                                stmt3.executeUpdate();
                                
                                return "Transferência Concluída com Sucesso!";
                            }
                        }
                    }
                }
            } else {
                return "Saldo insuficiente para realizar a transferência.";
            }
        } catch (SQLException e) {
            System.out.println(e);
            return "Erro ao realizar a transferência: " + e.getMessage();
        }
        return "Não foi possível realizar a transferência.";
    }

    }
