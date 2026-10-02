package parametros;

import java.rmi.Naming;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteGUI extends JFrame {

    private Mensajero mensajero;

    private JTextField txtMensaje;
    private JTextArea txtLog;
    private JButton btnEnviar;

    public ClienteGUI() {

        setTitle("Cliente RMI - Paso por Valor");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarInterfaz();

        conectarServidor();
    }

    private void inicializarInterfaz() {

        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));

        JLabel lblMensaje = new JLabel("Mensaje:");

        txtMensaje = new JTextField();

        btnEnviar = new JButton("Enviar");

        panelSuperior.add(lblMensaje, BorderLayout.WEST);
        panelSuperior.add(txtMensaje, BorderLayout.CENTER);
        panelSuperior.add(btnEnviar, BorderLayout.EAST);

        txtLog = new JTextArea();
        txtLog.setEditable(false);

        JScrollPane scroll = new JScrollPane(txtLog);

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnEnviar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });
    }

    private void conectarServidor() {

        try {

            mensajero = (Mensajero)
                    Naming.lookup("rmi://localhost/Mensajero");

            log("[Cliente] Conectado al servidor RMI.");

        } catch (Exception ex) {

            log("[Cliente] Error de conexión: " + ex.getMessage());
        }
    }

    private void log(String texto) {

        txtLog.append(texto + "\n");
    }

    private void enviarMensaje() {

        try {

            if (txtMensaje.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Escribe un mensaje."
                );

                return;
            }

            // Crear el objeto local
            Mensaje mensajeCliente =
                    new Mensaje(txtMensaje.getText());

            log("[Cliente] Antes de enviar: " + mensajeCliente);

            // Invocar el método remoto
            mensajero.recibirMensaje(mensajeCliente);

            // Mostrar el objeto local después de la llamada
            log("[Cliente] Después de la llamada remota: "
                    + mensajeCliente);

            txtMensaje.setText("");

        } catch (Exception ex) {

            log("[Cliente] Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ClienteGUI ventana = new ClienteGUI();

            ventana.setVisible(true);
        });
    }
}