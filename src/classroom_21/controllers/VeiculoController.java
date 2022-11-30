package classroom_21.controllers;


import classroom_21.models.Veiculo;
import classroom_21.services.BancoDados;
import classroom_21.services.Receber;


import java.sql.*;
import java.util.ArrayList;

public class VeiculoController {
    public static void imprime(Veiculo v){
        System.out.println("ID: " + v.getId());
        System.out.println("Modelo: " + v.getModelo());
        System.out.println("Fabricante: " + v.getFabricante());
        System.out.println("Cor: " + v.getCor());
        System.out.println("Ano: " + v.getAno());
        System.out.println("Preço: R$ " + v.getPreco());
    }

    public static Veiculo cadastra(){
        Veiculo v = new Veiculo();

        System.out.print("Modelo: ");
        v.setModelo(Receber.texto());

        System.out.print("Fabricante: ");
        v.setFabricante(Receber.texto());

        System.out.print("Cor: ");
        v.setCor(Receber.texto());

        System.out.print("Ano: ");
        v.setAno(Receber.inteiro());

        System.out.print("Preço: R$ ");
        v.setPreco(Receber.numeroDecimal());

        return v;
    }

    public static void addVeiculo(Veiculo v){
        Connection conn = BancoDados.conecta();
        try{
            String sql = "INSERT INTO veiculo (modelo, fabricante, ano, cor, preco) VALUES (?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1,v.getModelo());
            statement.setString(2,v.getFabricante());
            statement.setInt(3,v.getAno());
            statement.setString(4,v.getCor());
            statement.setDouble(5,v.getPreco());

            int linhas = statement.executeUpdate();

            if(linhas > 0){
                System.out.println("\n\n===============================");
                System.out.println("Veículo Cadastrado com Sucesso!");
                System.out.println("===============================\n\n");
            } else {
                System.out.println("Erro ao cadastrar!");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        BancoDados.fecha(conn);
    }

    public static ArrayList<Veiculo> getAll(){
        Connection conn = BancoDados.conecta();

        try{
            String sql = "SELECT * FROM veiculo";
            Statement statement = conn.createStatement();

            ResultSet resultados = statement.executeQuery(sql);

            ArrayList<Veiculo> veiculosList = new ArrayList<Veiculo>();

            while(resultados.next()){
               veiculosList.add(new Veiculo(
                        resultados.getInt("id"),
                        resultados.getString("modelo"),
                        resultados.getString("fabricante"),
                        resultados.getString("cor"),
                        resultados.getInt("ano"),
                        resultados.getDouble("preco")
                ));
            }
            BancoDados.fecha(conn);
            return veiculosList;
        } catch (SQLException e) {
            System.err.println(e);
        }

        BancoDados.fecha(conn);
        return null;
    }

    public static ArrayList<Veiculo> getPorFabricante(String fabricante){
        Connection conn = BancoDados.conecta();

        try{
            String sql = "SELECT * FROM veiculo WHERE fabricante LIKE '%" + fabricante +"%'";
            Statement statement = conn.createStatement();

            ResultSet resultados = statement.executeQuery(sql);

            ArrayList<Veiculo> veiculosList = new ArrayList<Veiculo>();

            while(resultados.next()){
                veiculosList.add(new Veiculo(
                        resultados.getInt("id"),
                        resultados.getString("modelo"),
                        resultados.getString("fabricante"),
                        resultados.getString("cor"),
                        resultados.getInt("ano"),
                        resultados.getDouble("preco")
                ));
            }
            BancoDados.fecha(conn);
            return veiculosList;
        } catch (SQLException e) {
            System.err.println(e);
        }

        BancoDados.fecha(conn);
        return null;
    }

    public static ArrayList<Veiculo> getPorPrecoMaximo(double preco){
        Connection conn = BancoDados.conecta();

        try{
            String sql = "SELECT * FROM veiculo WHERE preco <= " + preco +"";
            Statement statement = conn.createStatement();

            ResultSet resultados = statement.executeQuery(sql);

            ArrayList<Veiculo> veiculosList = new ArrayList<Veiculo>();

            while(resultados.next()){
                veiculosList.add(new Veiculo(
                        resultados.getInt("id"),
                        resultados.getString("modelo"),
                        resultados.getString("fabricante"),
                        resultados.getString("cor"),
                        resultados.getInt("ano"),
                        resultados.getDouble("preco")
                ));
            }
            BancoDados.fecha(conn);
            return veiculosList;
        } catch (SQLException e) {
            System.err.println(e);
        }

        BancoDados.fecha(conn);
        return null;
    }
    
     public static ArrayList<Veiculo> getPorCor(String cor){
        Connection conn = BancoDados.conecta();

        try{
           String sql = "SELECT * FROM veiculo WHERE cor LIKE '%" + cor +"%'";
            Statement statement = conn.createStatement();

            ResultSet resultados = statement.executeQuery(sql);

            ArrayList<Veiculo> veiculosList = new ArrayList<Veiculo>();

            while(resultados.next()){
                veiculosList.add(new Veiculo(
                        resultados.getInt("id"),
                        resultados.getString("modelo"),
                        resultados.getString("fabricante"),
                        resultados.getString("cor"),
                        resultados.getInt("ano"),
                        resultados.getDouble("preco")
                ));
            }
            BancoDados.fecha(conn);
            return veiculosList;
        } catch (SQLException e) {
            System.err.println(e);
        }

        BancoDados.fecha(conn);
        return null;
    }
     
     public static ArrayList<Veiculo> getPorFaixaDePreco(double menor,double maior){
        Connection conn = BancoDados.conecta();

        try{
            String sql = "SELECT * FROM veiculo WHERE preco >= " + menor +"AND preco <= " + maior;
            Statement statement = conn.createStatement();

            ResultSet resultados = statement.executeQuery(sql);

            ArrayList<Veiculo> veiculosList = new ArrayList<Veiculo>();

            while(resultados.next()){
                veiculosList.add(new Veiculo(
                        resultados.getInt("id"),
                        resultados.getString("modelo"),
                        resultados.getString("fabricante"),
                        resultados.getString("cor"),
                        resultados.getInt("ano"),
                        resultados.getDouble("preco")
                ));
            }
            BancoDados.fecha(conn);
            return veiculosList;
        } catch (SQLException e) {
            System.err.println(e);
        }

        BancoDados.fecha(conn);
        return null;
    }
     
      public static ArrayList<Veiculo> getPorModelo(String modelo){
        Connection conn = BancoDados.conecta();

        try{
            String sql = "SELECT * FROM veiculo WHERE modelo LIKE '%" + modelo +"%'";
            Statement statement = conn.createStatement();

            ResultSet resultados = statement.executeQuery(sql);

            ArrayList<Veiculo> veiculosList = new ArrayList<Veiculo>();

            while(resultados.next()){
                veiculosList.add(new Veiculo(
                        resultados.getInt("id"),
                        resultados.getString("modelo"),
                        resultados.getString("fabricante"),
                        resultados.getString("cor"),
                        resultados.getInt("ano"),
                        resultados.getDouble("preco")
                ));
            }
            BancoDados.fecha(conn);
            return veiculosList;
        } catch (SQLException e) {
            System.err.println(e);
        }

        BancoDados.fecha(conn);
        return null;
    }
      
      
      
}