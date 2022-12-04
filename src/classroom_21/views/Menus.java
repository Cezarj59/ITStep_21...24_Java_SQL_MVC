package classroom_21.views;

import classroom_21.controllers.VeiculoController;
import classroom_21.controllers.VendasController;
import classroom_21.models.Veiculo;
import classroom_21.models.Vendas;
import classroom_21.services.Receber;

import java.util.ArrayList;

public class Menus {

    public static void inicio() {
        while (0 == 0) {
            System.out.println("\n\n===================================");
            System.out.println("-----CONCESSIONÁRIA CEZAR'S CAR-----");
            System.out.println("===================================");
            System.out.println("(0) #SAIR#");
            System.out.println("(1)Cadastrar");
            System.out.println("(2)Consultar");
            System.out.println("(3)Vender");
            System.out.println("(4)Consultar Vendas");
            System.out.print("\nInforme a opção desejada: ");
            int op = Receber.inteiro();

            switch (op) {
                case 1:
                    cadastar();
                    break;
                case 2:
                    consultar();
                    break;
                case 3:
                    vendeVeiculo();
                    break;
                case 4:
                    consultarVendas();
                    break;
                case 0:
                    System.err.println("SAINDO!");
                    System.exit(0);
                default:
                    System.out.println("\nOpção inválida... só tem de 0 à 2 para escolher!\n");
            }
        }

    }

    private static void vendeVeiculo() {
        System.out.println("\n\n========VENDE VEÍCULOS========\n");
        VendasController.addVendas(VendasController.cadastra());
    }

    private static void cadastar() {
        System.out.println("\n\n++++++++CADASTRAR VEÍCULO++++++++");
        VeiculoController.addVeiculo(VeiculoController.cadastra());
    }

    private static void consultarVendas() {
        System.out.println("\n\n========CONSULTAR VENDAS========\n");

        System.out.println("(1) TODOS");
        System.out.println("(2) Vendas por dia");
        System.out.print("\nInforme a opção desejada: ");
        int op = Receber.inteiro();

        switch (op) {
            case 1: //todos
                ArrayList<Veiculo> listaAllVeiculos = VeiculoController.getAll();
                System.out.println("\n_____TODOS OS VEÍCULOS_____\n");

                ArrayList<Vendas> listaVendas = VendasController.getAll();
                
                for (int i = 0; i < listaVendas.size(); i++) {
                    System.out.println("Venda\n");
                     VendasController.imprime(listaVendas.get(i));
                     System.out.println("\nVeiculo\n");
                       VeiculoController.imprime(listaAllVeiculos.get(i));
                       System.out.println("_____________________\n");
                }
//                for (Vendas v : listaVendas) {
//                   
//                    for(Veiculo c : listaAllVeiculos){
//                  
//                    }
//                    
//                    System.out.println("_____________________");
//                }
                if (listaVendas.isEmpty()) {
                    System.out.println("\n--Não há Vendas Cadastradas--");

                }
                break;
            case 2: //por dia
                System.out.print("\nInforme a data da seguinte forma aaaa-mm-dd: ");
                String dia = Receber.texto();
                ArrayList<Vendas> listaVendasPorDia = VendasController.getVendasPorDia(dia);
                for (Vendas v : listaVendasPorDia) {
                    VendasController.imprime(v);
                    System.out.println("_____________________");

                }
                if (listaVendasPorDia.isEmpty()) {
                    System.out.println("\n--Não há Vendas Cadastradas neste Dia--");

                }
                break;
        }

    }

    private static void consultar() {
        System.out.println("\n\n========CONSULTAR VEÍCULOS========\n");
        System.out.println("(1) TODOS");
        System.out.println("(2) POR FABRICANTE");
        System.out.println("(3) PREÇO MÁXIMO");
        System.out.println("(4) POR COR");
        System.out.println("(5) FAIXA DE PREÇO");
        System.out.println("(6) MODELO");
        System.out.print("\nInforme a opção desejada: ");
        int op = Receber.inteiro();

        switch (op) {
            case 1: //todos
                ArrayList<Veiculo> lista = VeiculoController.getAll();
                System.out.println("\n_____TODOS OS VEÍCULOS_____");

                for (Veiculo v : lista) {
                    VeiculoController.imprime(v);
                    System.out.println("_____________________");
                }

                if (lista.isEmpty()) {
                    System.out.println("\n--Não há Cadastros--");
                }
                break;
            case 2: //por fabricante
                System.out.print("\nInforme o fabricante: ");
                String fabricante = Receber.texto();
                ArrayList<Veiculo> lista2 = VeiculoController.getPorFabricante(fabricante);
                System.out.println("\n_____POR FABRICANTE_____");

                for (Veiculo v : lista2) {
                    VeiculoController.imprime(v);
                    System.out.println("_____________________");
                }

                if (lista2.isEmpty()) {
                    System.out.println("\n--Não há Cadastros com fabricante: " + fabricante + "--");
                }
                break;
            case 3: //por preço máximo
                System.out.print("\nInforme o valor máximo: ");
                double valor = Receber.numeroDecimal();

                ArrayList<Veiculo> lista3 = VeiculoController.getPorPrecoMaximo(valor);
                System.out.println("\n_____PREÇO MÁXIMO: " + valor + "_____");

                for (Veiculo v : lista3) {
                    VeiculoController.imprime(v);
                    System.out.println("_____________________");
                }

                if (lista3.isEmpty()) {
                    System.out.println("\n--Não há Cadastros até R$ " + valor + "--");
                }
                break;
            case 4: //COR
                System.out.print("\nInforme a cor: ");
                String cor = Receber.texto();

                ArrayList<Veiculo> lista4 = VeiculoController.getPorCor(cor);
                System.out.println("\n_____COR: " + cor + "_____");

                for (Veiculo v : lista4) {
                    VeiculoController.imprime(v);
                    System.out.println("_____________________");
                }

                if (lista4.isEmpty()) {
                    System.out.println("\n--Não há Cadastros até R$ " + cor + "--");
                }
                break;
            case 5: //FAIXA DE PREÇO
                System.out.print("\nInforme o menor preço: ");
                double menor = Receber.numeroDecimal();
                System.out.print("\nInforme o maior valor: ");
                double maior = Receber.numeroDecimal();

                ArrayList<Veiculo> lista5 = VeiculoController.getPorFaixaDePreco(menor, maior);
                System.out.println("\n_____PREÇO DE: " + menor + " A " + maior + "_____");

                for (Veiculo v : lista5) {
                    VeiculoController.imprime(v);
                    System.out.println("_____________________");
                }

                if (lista5.isEmpty()) {
                    System.out.println("\n--Não há Cadastros até R$ " + menor + " A " + maior + "--");
                }
                break;
            case 6: //por preço MODELO
                System.out.print("\nInforme o Modelo: ");
                String modelo = Receber.texto();

                ArrayList<Veiculo> lista6 = VeiculoController.getPorModelo(modelo);
                System.out.println("\n_____MODELO: " + modelo + "_____");

                for (Veiculo v : lista6) {
                    VeiculoController.imprime(v);
                    System.out.println("_____________________");
                }

                if (lista6.isEmpty()) {
                    System.out.println("\n--Não há Cadastros até R$ " + modelo + "--");
                }
                break;
        }

    }

}
