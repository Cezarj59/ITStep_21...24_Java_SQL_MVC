package classroom_21.services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Receber {
    public static String texto(){
        Scanner leia = new Scanner(System.in);

        return leia.nextLine();
    }

    public static int inteiro(){
       
        int valor = 0;

        while(true){
            try{
                 Scanner leia = new Scanner(System.in);
                valor = leia.nextInt();
                break;
            } catch (InputMismatchException e){
                System.out.println("Tente novamente, apenas com números!");
                System.out.print("Informe novamente o valor: ");
            }
        }

        return valor;
    }

    public static double numeroDecimal(){
        
        double valor = 0;

        while(true){
            try{
                Scanner leia = new Scanner(System.in);
                valor = leia.nextDouble();
                break;
            } catch (InputMismatchException e){
                System.out.println("Tente novamente, apenas com números!");
                System.out.print("Informe novamente o valor: ");
            }
        }

        return valor;
    }
}