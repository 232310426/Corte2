package remoto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import modelo.Voto;

public interface PonyService extends Remote {

    void registrarVoto(Voto voto) throws RemoteException;

    Map<String, Integer> obtenerResultados() throws RemoteException;

    String obtenerMensaje() throws RemoteException;
}