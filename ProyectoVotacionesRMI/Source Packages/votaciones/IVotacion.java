package votaciones;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface IVotacion extends Remote {

    void emitirVoto(Voto voto) throws RemoteException;

    Map<String, Integer> obtenerResultados() throws RemoteException;
}