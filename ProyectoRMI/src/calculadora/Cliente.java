
package calculadora;

import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Calculadora calc = (Calculadora) Naming.lookup(
                "rmi://localhost/Calc"
            );

            Scanner sc = new Scanner(System.in);

            System.out.println("Cliente RMI - Calculadora");
            System.out.println("Operaciones: suma, resta, mult, div, potencia, modulo");
            System.out.print("Operacion: ");
            String op = sc.nextLine().trim().toLowerCase();

            System.out.print("a = ");
            double a = Double.parseDouble(sc.nextLine());

            System.out.print("b = ");
            double b = Double.parseDouble(sc.nextLine());

            double resultado;

            switch (op) {
                case "suma":
                    resultado = calc.sumar(a, b);
                    break;

                case "resta":
                    resultado = calc.restar(a, b);
                    break;

                case "mult":
                case "multiplicar":
                    resultado = calc.multiplicar(a, b);
                    break;

                case "div":
                case "dividir":
                    resultado = calc.dividir(a, b);
                    break;

                case "potencia":
                    resultado = calc.potencia(a, b);
                    break;

                case "modulo":
                case "mod":
                    resultado = calc.modulo(a, b);
                    break;

                default:
                    System.out.println("Operacion no reconocida.");
                    sc.close();
                    return;
            }

            System.out.println("Resultado: " + resultado);
            sc.close();

        } catch (ArithmeticException e) {
            System.err.println("Error aritmetico: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}