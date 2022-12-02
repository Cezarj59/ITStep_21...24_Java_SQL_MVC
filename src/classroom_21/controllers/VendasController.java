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
            System.out.println(e);
        }
        BancoDados.fecha(conn);
    }

    public static Vendas cadastra() {
        Vendas v = new Vendas();
        System.out.print("Qual o id do Veiculo vendido: ");
        v.setIdVeiculo(Receber.inteiro());

        return v;
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
                        resultados.getDate("dataHora")
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
}
