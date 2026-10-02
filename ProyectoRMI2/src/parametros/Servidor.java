package parametros;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {

    public static void main(String[] args) {

        try {

            // Crear registry en el puerto 1099
            LocateRegistry.createRegistry(1099);

            // Crear e inscribir el objeto remoto
            MensajeroImpl impl = new MensajeroImpl();

            Naming.rebind("rmi://localhost/Mensajero", impl);

            System.out.println(
                "[Servidor] Mensajero registrado en rmi://localhost/Mensajero"
            );

            System.out.println("[Servidor] Esperando invocaciones...");

        } catch (Exception e) {

            System.err.println("[Servidor] Error: " + e);
            e.printStackTrace();
        }
    }
}