package parametros;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MensajeroImpl extends UnicastRemoteObject implements Mensajero {

    public MensajeroImpl() throws RemoteException {
        super();
    }

    @Override
    public void recibirMensaje(Mensaje m) throws RemoteException {

        System.out.println("[Servidor] Mensaje recibido: " + m);

        // Modificamos la copia recibida
        m.setTexto("TEXTO MODIFICADO EN EL SERVIDOR");

        System.out.println("[Servidor] Mensaje modificado localmente: " + m);
    }
}