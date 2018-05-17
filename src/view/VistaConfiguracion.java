package view;

import controller.Controlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaConfiguracion extends JFrame implements Vista {

    private Controlador controlador;

    private JPanel contentPane;
    private JPasswordField pswContrasena;
    private JPasswordField pswConfirmarContrasena;
    private JLabel lblCambiarContrasea;
    private JButton btnCambiar;
    private JButton btnAceptar;
    private JButton btnCambiarAvatar;

    public VistaConfiguracion() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaConfiguracion.class.getResource("/img/uem.png")));
        setTitle("Configuración de Usuario");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 325, 367);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        pswContrasena = new JPasswordField();

        lblCambiarContrasea = new JLabel("Cambiar contraseña:");

        pswConfirmarContrasena = new JPasswordField();

        btnCambiar = new JButton("Cambiar");
        btnCambiar.setEnabled(false);
        btnCambiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (new String(pswContrasena.getPassword()).equals(new String(pswConfirmarContrasena.getPassword()))) {
                    controlador.cambiarContrasena(new String(pswContrasena.getPassword()));
                } else {
                    error("Las contraseñas no coinciden.");
                }
            }
        });

        pswContrasena.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

        });

        pswConfirmarContrasena.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

        });


        JLabel lblConfiguracin = new JLabel("Configuración de Usuario");
        lblConfiguracin.setFont(new Font("Tahoma", Font.BOLD, 12));

        JLabel lblNmeroDeExpediente = new JLabel("Número de expediente: 000000");

        JLabel lblNombrePedro = new JLabel("Nombre: Pedro Jesús");

        JLabel lblApellidoCamacho = new JLabel("Apellido: Camacho");

        btnCambiarAvatar = new JButton("Cambiar Avatar");
        btnCambiarAvatar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.cerrarConfiguracion();
            }
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblConfiguracin)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblNmeroDeExpediente)
                                                .addGap(18)
                                                .addComponent(btnCambiarAvatar))
                                        .addComponent(lblNombrePedro)
                                        .addComponent(lblApellidoCamacho)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(lblCambiarContrasea)
                                                .addComponent(pswContrasena, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                                .addComponent(pswConfirmarContrasena))
                                        .addComponent(btnCambiar))
                                .addContainerGap(12, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addContainerGap(200, Short.MAX_VALUE)
                                .addComponent(btnAceptar)
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblConfiguracin)
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNmeroDeExpediente)
                                        .addComponent(btnCambiarAvatar))
                                .addGap(18)
                                .addComponent(lblNombrePedro)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(lblApellidoCamacho)
                                .addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addComponent(lblCambiarContrasea)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(pswContrasena, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(pswConfirmarContrasena, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnCambiar)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnAceptar)
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }

    private void changed() {
        if (new String(pswConfirmarContrasena.getPassword()).equals("") || new String(pswConfirmarContrasena.getPassword()).equals("")) {
            btnCambiar.setEnabled(false);
        } else {
            btnCambiar.setEnabled(true);
        }

    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void error(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
