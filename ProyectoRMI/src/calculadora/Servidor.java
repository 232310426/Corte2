
package calculadora;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String[] args) {
        try {
            // Crear el registro RMI en el puerto 1099
            LocateRegistry.createRegistry(1099);

            // Crear el objeto remoto
            CalculadoraImpl obj = new CalculadoraImpl();

            // Registrar el servicio
            Naming.rebind("rmi://localhost/Calc", obj);

            System.out.println("Servidor RMI listo en rmi://localhost/Calc");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}