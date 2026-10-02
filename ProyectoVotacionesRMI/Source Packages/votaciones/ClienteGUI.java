package votaciones;

import javax.swing.*;
import java.awt.*;
import java.rmi.Naming;
import java.util.Map;

public class ClienteGUI extends JFrame {

    private IVotacion votacion;

    private JButton btnOpcionA;
    private JButton btnOpcionB;
    private JButton btnOpcionC;
    private JButton btnResultados;

    private JTextArea txtResultados;

    public ClienteGUI() {

        setTitle("Sistema de Votaciones RMI");

        setSize(500, 450);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        crearInterfaz();

        conectarServidor();
    }

    private void crearInterfaz() {

        JPanel panelPrincipal = new JPanel();

        panelPrincipal.setLayout(
                new BorderLayout(10, 10)
        );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        JLabel titulo = new JLabel(
                "SISTEMA DE VOTACIONES",
                SwingConstants.CENTER
        );

        titulo.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        panelPrincipal.add(
                titulo,
                BorderLayout.NORTH
        );

        JPanel panelBotones = new JPanel();

        panelBotones.setLayout(
                new GridLayout(2, 2, 10, 10)
        );

        btnOpcionA = new JButton(
                "Votar Opción A"
        );

        btnOpcionB = new JButton(
                "Votar Opción B"
        );

        btnOpcionC = new JButton(
                "Votar Opción C"
        );

        btnResultados = new JButton(
                "Ver Resultados"
        );

        panelBotones.add(btnOpcionA);
        panelBotones.add(btnOpcionB);
        panelBotones.add(btnOpcionC);
        panelBotones.add(btnResultados);

        panelPrincipal.add(
                panelBotones,
                BorderLayout.CENTER
        );

        txtResultados = new JTextArea();

        txtResultados.setEditable(false);

        txtResultados.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        JScrollPane scroll = new JScrollPane(
                txtResultados
        );

        panelPrincipal.add(
                scroll,
                BorderLayout.SOUTH
        );

        add(panelPrincipal);

        btnOpcionA.addActionListener(e -> {
            emitir("Opción A");
        });

        btnOpcionB.addActionListener(e -> {
            emitir("Opción B");
        });

        btnOpcionC.addActionListener(e -> {
            emitir("Opción C");
        });

        btnResultados.addActionListener(e -> {
            mostrarResultados();
        });
    }

    private void conectarServidor() {

        try {

            votacion = (IVotacion)
                    Naming.lookup(
                            "rmi://localhost/Votacion"
                    );

            txtResultados.append(
                    "Conectado al servidor.\n"
            );

            txtResultados.append(
                    "Ya puedes votar.\n\n"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo conectar con el servidor.\n\n"
                    + e.getMessage(),
                    "Error de conexión",
                    JOptionPane.ERROR_MESSAGE
            );

            System.exit(1);
        }
    }

    private void emitir(String opcion) {

        try {

            votacion.emitirVoto(
                    new Voto(opcion)
            );

            txtResultados.append(
                    "Has votado por: "
                    + opcion
                    + "\n"
            );

        } catch (Exception e) {

            txtResultados.append(
                    "Error al votar: "
                    + e.getMessage()
                    + "\n"
            );
        }
    }

    private void mostrarResultados() {

        try {

            Map<String, Integer> resultados =
                    votacion.obtenerResultados();

            txtResultados.append(
                    "\n--- RESULTADOS ---\n"
            );

            for (String opcion : resultados.keySet()) {

                txtResultados.append(
                        opcion
                        + ": "
                        + resultados.get(opcion)
                        + " votos\n"
                );
            }

            txtResultados.append(
                    "------------------\n\n"
            );

        } catch (Exception e) {

            txtResultados.append(
                    "Error al obtener resultados: "
                    + e.getMessage()
                    + "\n"
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ClienteGUI ventana =
                    new ClienteGUI();

            ventana.setVisible(true);
        });
    }
}