package parametros;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Mensajero extends Remote {

    // El servidor recibe una copia del Mensaje (paso por valor)
    void recibirMensaje(Mensaje m) throws RemoteException;
}