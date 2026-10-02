package servidor;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {

    public static void main(String[] args) {

        try {

            LocateRegistry.createRegistry(1099);

            PonyServiceImpl servicio = new PonyServiceImpl();

            Naming.rebind(
                "rmi://localhost/MyLittlePony",
                servicio
            );

            System.out.println(
                "================================"
            );

            System.out.println(
                " SERVIDOR MY LITTLE PONY RMI"
            );

            System.out.println(
                "================================"
            );

            System.out.println(
                "Servidor iniciado correctamente."
            );

            System.out.println(
                "Esperando clientes..."
            );

        } catch (Exception e) {

            System.out.println(
                "Error en el servidor: "
                + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}
