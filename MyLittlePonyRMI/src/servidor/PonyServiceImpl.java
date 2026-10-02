package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import modelo.Voto;
import remoto.PonyService;

public class PonyServiceImpl extends UnicastRemoteObject
        implements PonyService {

    private Map<String, Integer> resultados;

    public PonyServiceImpl() throws RemoteException {
        super();

        resultados = new HashMap<>();

        resultados.put("Twilight Sparkle", 0);
        resultados.put("Rainbow Dash", 0);
        resultados.put("Pinkie Pie", 0);
        resultados.put("Fluttershy", 0);
        resultados.put("Applejack", 0);
    }

    @Override
    public synchronized void registrarVoto(Voto voto)
            throws RemoteException {

        String pony = voto.getPony();

        if (resultados.containsKey(pony)) {

            int cantidad = resultados.get(pony);

            resultados.put(pony, cantidad + 1);

            System.out.println(
                "[Servidor] Nuevo voto para: " + pony
            );
        }
    }

    @Override
    public synchronized Map<String, Integer> obtenerResultados()
            throws RemoteException {

        return new HashMap<>(resultados);
    }

    @Override
    public String obtenerMensaje() throws RemoteException {

        return "¡Bienvenido al servidor de My Little Pony RMI!";
    }
}