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
            String sql = "SELECT vendas.id AS idVenda,"
                    + " vendas.idVeiculo AS idVeiculo,"
                    + " vendas.dataHora AS dataHora,"
                    + " veiculo.modelo AS modelo,"
                    + " veiculo.fabricante AS fabricante,"
                    + " veiculo.cor AS cor,"
                    + " veiculo.ano AS ano,"
                    + " veiculo.preco AS preco,"
                    + " veiculo.vendido AS vendido"
                    + " FROM vendas INNER JOIN veiculo"
                    + " ON veiculo.id = vendas.idVeiculo";

            Statement statement = conn.createStatement();
            ResultSet resultados = statement.executeQuery(sql);

            while (resultados.next()) {
                lista.add(new Vendas(
                        resultados.getInt("idVenda"),
                        resultados.getInt("idVeiculo"),
                        resultados.getDate("dataHora"),
                        new Veiculo(
                                resultados.getInt("idVeiculo"),
                                resultados.getString("modelo"),
                                resultados.getString("fabricante"),
                                resultados.getString("cor"),
                                resultados.getInt("ano"),
                                resultados.getDouble("preco"),
                                resultados.getBoolean("vendido")
                        )
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
            
            String sql = "SELECT vendas.id AS idVenda,"
                    + " vendas.idVeiculo AS idVeiculo,"
                    + " vendas.dataHora AS dataHora,"
                    + " veiculo.modelo AS modelo,"
                    + " veiculo.fabricante AS fabricante,"
                    + " veiculo.cor AS cor,"
                    + " veiculo.ano AS ano,"
                    + " veiculo.preco AS preco,"
                    + " veiculo.vendido AS vendido"
                    + " FROM vendas INNER JOIN veiculo"
                    + " ON veiculo.id = vendas.idVeiculo"
                    + " WHERE dataHora LIKE '%" + data + "%'";

            Statement statement = conn.createStatement();
            ResultSet resultados = statement.executeQuery(sql);

            while (resultados.next()) {
                lista.add(new Vendas(
                        resultados.getInt("idVenda"),
                        resultados.getInt("idVeiculo"),
                        resultados.getDate("dataHora"),
                        new Veiculo(
                                resultados.getInt("idVeiculo"),
                                resultados.getString("modelo"),
                                resultados.getString("fabricante"),
                                resultados.getString("cor"),
                                resultados.getInt("ano"),
                                resultados.getDouble("preco"),
                                resultados.getBoolean("vendido")
                        )
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
            String sql = "SELECT vendas.id AS idVenda,"
                    + " vendas.idVeiculo AS idVeiculo,"
                    + " vendas.dataHora AS dataHora,"
                    + " veiculo.modelo AS modelo,"
                    + " veiculo.fabricante AS fabricante,"
                    + " veiculo.cor AS cor,"
                    + " veiculo.ano AS ano,"
                    + " veiculo.preco AS preco,"
                    + " veiculo.vendido AS vendido"
                    + " FROM vendas INNER JOIN veiculo"
                    + " ON veiculo.id = vendas.idVeiculo"
                    + " WHERE modelo LIKE '%" + modelo + "%'";

            Statement statement = conn.createStatement();
            ResultSet resultados = statement.executeQuery(sql);

            while (resultados.next()) {
                lista.add(new Vendas(
                        resultados.getInt("idVenda"),
                        resultados.getInt("idVeiculo"),
                        resultados.getDate("dataHora"),
                        new Veiculo(
                                resultados.getInt("idVeiculo"),
                                resultados.getString("modelo"),
                                resultados.getString("fabricante"),
                                resultados.getString("cor"),
                                resultados.getInt("ano"),
                                resultados.getDouble("preco"),
                                resultados.getBoolean("vendido")
                        )
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

  public static ArrayList<Vendas> getPorFabricante(String fabricante) {
        Connection conn = BancoDados.conecta();

        try {
            String sql = "SELECT vendas.id AS idVenda,"
                    + " vendas.idVeiculo AS idVeiculo,"
                    + " vendas.dataHora AS dataHora,"
                    + " veiculo.modelo AS modelo,"
                    + " veiculo.fabricante AS fabricante,"
                    + " veiculo.cor AS cor,"
                    + " veiculo.ano AS ano,"
                    + " veiculo.preco AS preco,"
                    + " veiculo.vendido AS vendido"
                    + " FROM vendas INNER JOIN veiculo"
                    + " ON veiculo.id = vendas.idVeiculo"
                    + " WHERE fabricante LIKE '%" + fabricante + "%'";
            
            Statement statement = conn.createStatement();

            ResultSet resultados = statement.executeQuery(sql);

            ArrayList<Vendas> lista = new ArrayList<Vendas>();

             while (resultados.next()) {
                lista.add(new Vendas(
                        resultados.getInt("idVenda"),
                        resultados.getInt("idVeiculo"),
                        resultados.getDate("dataHora"),
                        new Veiculo(
                                resultados.getInt("idVeiculo"),
                                resultados.getString("modelo"),
                                resultados.getString("fabricante"),
                                resultados.getString("cor"),
                                resultados.getInt("ano"),
                                resultados.getDouble("preco"),
                                resultados.getBoolean("vendido")
                        )
                ));
            }
            BancoDados.fecha(conn);
            return lista;
        } catch (SQLException e) {
            System.err.println(e);
        }

        BancoDados.fecha(conn);
        return null;
    }
  
    public static ArrayList<Vendas> getPorPrecoMaximo(double preco) {
        Connection conn = BancoDados.conecta();

        try {
             String sql = "SELECT vendas.id AS idVenda,"
                    + " vendas.idVeiculo AS idVeiculo,"
                    + " vendas.dataHora AS dataHora,"
                    + " veiculo.modelo AS modelo,"
                    + " veiculo.fabricante AS fabricante,"
                    + " veiculo.cor AS cor,"
                    + " veiculo.ano AS ano,"
                    + " veiculo.preco AS preco,"
                    + " veiculo.vendido AS vendido"
                    + " FROM vendas INNER JOIN veiculo"
                    + " ON veiculo.id = vendas.idVeiculo"
                    + " WHERE preco <= " + preco;
            Statement statement = conn.createStatement();

            ResultSet resultados = statement.executeQuery(sql);


            ArrayList<Vendas> lista = new ArrayList<Vendas>();

             while (resultados.next()) {
                lista.add(new Vendas(
                        resultados.getInt("idVenda"),
                        resultados.getInt("idVeiculo"),
                        resultados.getDate("dataHora"),
                        new Veiculo(
                                resultados.getInt("idVeiculo"),
                                resultados.getString("modelo"),
                                resultados.getString("fabricante"),
                                resultados.getString("cor"),
                                resultados.getInt("ano"),
                                resultados.getDouble("preco"),
                                resultados.getBoolean("vendido")
                        )
                ));
            }
            BancoDados.fecha(conn);
            return lista;
        } catch (SQLException e) {
            System.err.println(e);
        }
        BancoDados.fecha(conn);
        return null;
    }

    public static ArrayList<Vendas> getPorCor(String cor) {
        Connection conn = BancoDados.conecta();

        try {
            String sql = "SELECT vendas.id AS idVenda,"
                    + " vendas.idVeiculo AS idVeiculo,"
                    + " vendas.dataHora AS dataHora,"
                    + " veiculo.modelo AS modelo,"
                    + " veiculo.fabricante AS fabricante,"
                    + " veiculo.cor AS cor,"
                    + " veiculo.ano AS ano,"
                    + " veiculo.preco AS preco,"
                    + " veiculo.vendido AS vendido"
                    + " FROM vendas INNER JOIN veiculo"
                    + " ON veiculo.id = vendas.idVeiculo"
                    + " WHERE cor LIKE '%" + cor + "%'";
            Statement statement = conn.createStatement();

            ResultSet resultados = statement.executeQuery(sql);


           ArrayList<Vendas> lista = new ArrayList<Vendas>();

             while (resultados.next()) {
                lista.add(new Vendas(
                        resultados.getInt("idVenda"),
                        resultados.getInt("idVeiculo"),
                        resultados.getDate("dataHora"),
                        new Veiculo(
                                resultados.getInt("idVeiculo"),
                                resultados.getString("modelo"),
                                resultados.getString("fabricante"),
                                resultados.getString("cor"),
                                resultados.getInt("ano"),
                                resultados.getDouble("preco"),
                                resultados.getBoolean("vendido")
                        )
                ));
            }
            BancoDados.fecha(conn);
            return lista;
        } catch (SQLException e) {
            System.err.println(e);
        }

        BancoDados.fecha(conn);
        return null;
    }

    public static ArrayList<Vendas> getPorFaixaDePreco(double menor, double maior) {
        Connection conn = BancoDados.conecta();

        try {
             String sql = "SELECT vendas.id AS idVenda,"
                    + " vendas.idVeiculo AS idVeiculo,"
                    + " vendas.dataHora AS dataHora,"
                    + " veiculo.modelo AS modelo,"
                    + " veiculo.fabricante AS fabricante,"
                    + " veiculo.cor AS cor,"
                    + " veiculo.ano AS ano,"
                    + " veiculo.preco AS preco,"
                    + " veiculo.vendido AS vendido"
                    + " FROM vendas INNER JOIN veiculo"
                    + " ON veiculo.id = vendas.idVeiculo"
                    + " WHERE preco >= " + menor + "AND preco <= " + maior;
            Statement statement = conn.createStatement();

            ResultSet resultados = statement.executeQuery(sql);


           ArrayList<Vendas> lista = new ArrayList<Vendas>();

             while (resultados.next()) {
                lista.add(new Vendas(
                        resultados.getInt("idVenda"),
                        resultados.getInt("idVeiculo"),
                        resultados.getDate("dataHora"),
                        new Veiculo(
                                resultados.getInt("idVeiculo"),
                                resultados.getString("modelo"),
                                resultados.getString("fabricante"),
                                resultados.getString("cor"),
                                resultados.getInt("ano"),
                                resultados.getDouble("preco"),
                                resultados.getBoolean("vendido")
                        )
                ));
            }
            BancoDados.fecha(conn);
            return lista;
        } catch (SQLException e) {
            System.err.println(e);
        }

        BancoDados.fecha(conn);
        return null;
    }

    public static void imprime(Vendas v) {
        System.out.println("ID da Venda: " + v.getId());
        System.out.println("Data da Venda: " + v.getDataHora());
        VeiculoController.imprime(v.getVeiculo());
        System.out.println("--------------------------");

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
