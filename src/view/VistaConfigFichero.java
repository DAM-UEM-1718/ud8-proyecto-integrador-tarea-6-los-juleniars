package view;

import controller.Controlador;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class VistaConfigFichero extends JFrame implements Vista {

    private Controlador controlador;
    private JPanel contentPane;
    private JTextField txtUser;
    private JTextField txtHost;
    private JPasswordField pswContrasena;
    private JButton btnAceptar;

    public VistaConfigFichero() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaConfigFichero.class.getResource("/img/uem.png")));
        setTitle("Configuración Base de Datos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 327, 306);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JLabel lblUsuario = new JLabel("Usuario");

        JLabel lblContrasea = new JLabel("Contraseña");

        JLabel lblHostMysql = new JLabel("Host MySQL");

        txtUser = new JTextField();
        txtUser.setColumns(10);

        txtHost = new JTextField();
        txtHost.setColumns(10);

        pswContrasena = new JPasswordField();

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setEnabled(false);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(37)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblUsuario)
                                                        .addComponent(lblContrasea)
                                                        .addComponent(lblHostMysql))
                                                .addGap(62)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(txtHost, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                                                        .addComponent(pswContrasena, Alignment.LEADING)
                                                                        .addComponent(txtUser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                                                                .addGap(107))))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(106)
                                                .addComponent(btnAceptar)))
                                .addContainerGap(23, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(38, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblUsuario)
                                        .addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblContrasea)
                                        .addComponent(pswContrasena, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblHostMysql)
                                        .addComponent(txtHost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addComponent(btnAceptar)
                                .addGap(57))
        );
        contentPane.setLayout(gl_contentPane);

        btnAceptar.addActionListener(e -> controlador.escribirFichero());

        DocumentListener documentListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

        };
        txtUser.getDocument().addDocumentListener(documentListener);
        pswContrasena.getDocument().addDocumentListener(documentListener);
        txtHost.getDocument().addDocumentListener(documentListener);
    }

    public JTextField getTxtUser() {
        return txtUser;
    }

    public JTextField getTxtHost() {
        return txtHost;
    }

    public JPasswordField getPswContrasena() {
        return pswContrasena;
    }

    public void limpiarCampos() {
        txtHost.setText("");
        txtUser.setText("");
        pswContrasena.setText("");
    }

    private void changed() {
        if (txtUser.getText().equals("") || txtHost.getText().equals("") || new String(pswContrasena.getPassword()).equals("")) {
            btnAceptar.setEnabled(false);
        } else {
            btnAceptar.setEnabled(true);
        }
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
