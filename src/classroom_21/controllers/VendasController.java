package classroom_21.controllers;

import classroom_21.models.Veiculo;
import classroom_21.models.Vendas;
import classroom_21.services.BancoDados;
import classroom_21.services.Receber;

import java.sql.*;
import java.util.ArrayList;

public class VendasController {

    public static void addVendas(Vendas venda) {
        Connection conn = BancoDados.conecta();

        String sql = "INSERT INTO vendas (idVeiculo) VALUES(?)";

        if (venda != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, venda.getIdVeiculo());

                int qnt = statement.executeUpdate();

                Veiculo veiculo = VeiculoController.getPorId(venda.getIdVeiculo());
                veiculo.setVendido(true);
                VeiculoController.updateVeiculo(veiculo);

                if (qnt > 0) {
                    System.out.println("\nVenda Realizada com Sucesso\n");
                }

            } catch (Exception e) {
                System.err.println(e);
            }
        } else {
            System.out.println("\nO Veiculo já foi vendido!!!");
        }

        BancoDados.fecha(conn);
    }

    public static Vendas cadastra() {
        Vendas v = new Vendas();
        System.out.print("Qual o id do Veiculo vendido: ");

        int id = Receber.inteiro();

        if (consultarVeiculosVendidos(id)) {
            System.out.println("\nVeiculo não Disponivel pra venda!!!");
        } else {
            v.setIdVeiculo(id);
            return v;

        }

        return null;
    }

    public static ArrayList<Vendas> getAll() {
        Connection conn = BancoDados.conecta();
        ArrayList<Vendas> lista = new ArrayList<Vendas>();
        try {
            String sql = "SELECT * FROM vendas";
            Statement statement = conn.createStatement();
            ResultSet resultados = statement.executeQuery(sql);

            while (resultados.next()) {
                lista.add(new Vendas(
                        resultados.getInt("id"),
                        resultados.getInt("idVeiculo"),
                        resultados.getTimestamp("dataHora")
                ));
            }
            BancoDados.fecha(conn);
            return lista;
        } catch (Exception e) {
            System.out.println(e);

        }
        BancoDados.fecha(conn);
        return null;
    }

    public static ArrayList<Vendas> getPorModelo(String modelo) {
        Connection conn = BancoDados.conecta();
        ArrayList<Vendas> lista = new ArrayList<Vendas>();
        try {
            String sql = "SELECT * FROM vendas Where '%" + modelo + "%'";
            Statement statement = conn.createStatement();
            ResultSet resultados = statement.executeQuery(sql);

            while (resultados.next()) {
                lista.add(new Vendas(
                        resultados.getInt("id"),
                        resultados.getInt("idVeiculo"),
                        resultados.getTimestamp("dataHora")
                ));
            }
            BancoDados.fecha(conn);
            return lista;
        } catch (Exception e) {
            System.out.println(e);

        }
        BancoDados.fecha(conn);
        return null;
    }

    public static ArrayList<Vendas> getVendasPorDia(String data) {
        Connection conn = BancoDados.conecta();
        ArrayList<Vendas> lista = new ArrayList<Vendas>();
        try {
            String sql = "SELECT * FROM vendas WHERE dataHora Like '%" + data + "%';";
            Statement statement = conn.createStatement();
            ResultSet resultados = statement.executeQuery(sql);

            while (resultados.next()) {
                lista.add(new Vendas(
                        resultados.getInt("id"),
                        resultados.getInt("idVeiculo"),
                        resultados.getTimestamp("dataHora")
                ));
            }
            BancoDados.fecha(conn);
            return lista;
        } catch (Exception e) {
            System.out.println(e);

        }
        BancoDados.fecha(conn);
        return null;
    }

    public static void imprime(Vendas v) {
        System.out.println("ID da Venda: " + v.getId());
        System.out.println("ID do Veiculo: " + v.getIdVeiculo());
        System.out.println("Data da Venda: " + v.getDataHora());

    }

    private static boolean consultarVeiculosVendidos(int idVeiculoP) {
        Boolean vendido = false;
        ArrayList<Vendas> listaVendidos = VendasController.getAll();
        for (Vendas v : listaVendidos) {
            if (v.getIdVeiculo() == idVeiculoP) {
                vendido = true;
            }

        }
        return vendido;
    }

}
