package cliente;

import java.rmi.Naming;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import modelo.Voto;
import remoto.PonyService;

public class ClienteGUI extends JFrame {

    private PonyService servicio;

    private JTextArea txtResultados;

    public ClienteGUI() {

        setTitle("My Little Pony RMI");
        setSize(500, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        conectarServidor();

        crearInterfaz();
    }

    private void conectarServidor() {

        try {

            servicio = (PonyService) Naming.lookup(
                "rmi://localhost/MyLittlePony"
            );

            System.out.println(
                "Conectado al servidor."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "No se pudo conectar al servidor.\n"
                + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );

            System.exit(1);
        }
    }

    private void crearInterfaz() {

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel(
            "MY LITTLE PONY RMI",
            JLabel.CENTER
        );

        add(titulo, BorderLayout.NORTH);

        JPanelBotones();

        txtResultados = new JTextArea();

        txtResultados.setEditable(false);

        JScrollPane scroll =
            new JScrollPane(txtResultados);

        add(scroll, BorderLayout.CENTER);

        JButton btnResultados =
            new JButton("VER RESULTADOS");

        btnResultados.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    mostrarResultados();
                }
            }
        );

        add(btnResultados, BorderLayout.SOUTH);
    }

    private void JPanelBotones() {

        javax.swing.JPanel panel =
            new javax.swing.JPanel();

        panel.setLayout(new GridLayout(5, 1));

        String[] ponies = {

            "Twilight Sparkle",
            "Rainbow Dash",
            "Pinkie Pie",
            "Fluttershy",
            "Applejack"
        };

        for (String pony : ponies) {

            JButton boton =
                new JButton(pony);

            boton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                        ActionEvent e) {

                        registrarVoto(pony);
                    }
                }
            );

            panel.add(boton);
        }

        add(panel, BorderLayout.WEST);
    }

    private void registrarVoto(String pony) {

        try {

            Voto voto =
                new Voto(pony);

            servicio.registrarVoto(voto);

            txtResultados.append(
                "Votaste por: "
                + pony
                + "\n"
            );

        } catch (Exception e) {

            txtResultados.append(
                "Error al registrar voto: "
                + e.getMessage()
                + "\n"
            );
        }
    }

    private void mostrarResultados() {

        try {

            Map<String, Integer> resultados =
                servicio.obtenerResultados();

            txtResultados.append(
                "\n===== RESULTADOS =====\n"
            );

            for (String pony :
                    resultados.keySet()) {

                txtResultados.append(
                    pony
                    + ": "
                    + resultados.get(pony)
                    + " votos\n"
                );
            }

            txtResultados.append(
                "======================\n"
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

        java.awt.EventQueue.invokeLater(
            new Runnable() {

                @Override
                public void run() {

                    new ClienteGUI().setVisible(true);
                }
            }
        );
    }
}