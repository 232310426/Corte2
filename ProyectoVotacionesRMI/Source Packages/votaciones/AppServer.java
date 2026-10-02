import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class AppServer {

    public static void main(String[] args) {

        try {

            LocateRegistry.createRegistry(1099);

            VotacionServer servicio = new VotacionServer();

            Naming.rebind(
                "rmi://localhost/Votacion",
                servicio
            );

            System.out.println(
                "[Servidor] Sistema de votaciones en marcha..."
            );

        } catch (Exception e) {

            System.err.println(
                "[Servidor] Error: " + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}