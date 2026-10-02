package votaciones;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class VotacionServer extends UnicastRemoteObject
        implements IVotacion {

    private Map<String, Integer> resultados;

    public VotacionServer() throws RemoteException {
        super();

        resultados = new HashMap<>();

        resultados.put("Opción A", 0);
        resultados.put("Opción B", 0);
        resultados.put("Opción C", 0);
    }

    @Override
    public synchronized void emitirVoto(Voto voto)
            throws RemoteException {

        String opcion = voto.getOpcion();

        if (resultados.containsKey(opcion)) {
            resultados.put(
                opcion,
                resultados.get(opcion) + 1
            );

            System.out.println(
                "[Servidor] Voto recibido para: " + opcion
            );
        }
    }

    @Override
    public synchronized Map<String, Integer> obtenerResultados()
            throws RemoteException {

        return new HashMap<>(resultados);
    }
}