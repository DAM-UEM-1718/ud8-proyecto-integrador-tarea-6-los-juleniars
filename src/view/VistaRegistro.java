package view;

import controller.Controlador;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class VistaRegistro extends JFrame implements Vista {

    private JPanel contentPane;
    private Controlador controlador;
    private JTextField txtNombre;
    private JTextField txtUser;
    private JTextField txtMail;
    private JTextField txtNIF;
    private JLabel lblContrasena;
    private JLabel lblNif;
    private JLabel lblRegistro;
    private JPasswordField pswContrasena;
    private JButton btnValidar;

    public VistaRegistro() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setTitle("Registro");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAlumnos.class.getResource("/img/uem.png")));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);

        txtUser = new JTextField();
        txtUser.setColumns(10);

        txtMail = new JTextField();
        txtMail.setColumns(10);

        txtNIF = new JTextField();
        txtNIF.setColumns(10);

        btnValidar = new JButton("VALIDAR");
        btnValidar.addActionListener(e -> controlador.registrar());
        btnValidar.setEnabled(false);

        JLabel lblNombreYApellidos = new JLabel("Nombre y Apellidos:");

        JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");

        JLabel lblCorreoElectronico = new JLabel("Correo Electronico:");

        lblContrasena = new JLabel("Contraseña:");

        lblNif = new JLabel("NIF:");

        lblRegistro = new JLabel("REGISTRO");
        lblRegistro.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 20));

        pswContrasena = new JPasswordField();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                                        .addComponent(lblContrasena)
                                                                                        .addComponent(lblNombreYApellidos)
                                                                                        .addComponent(lblNif)
                                                                                        .addComponent(lblNombreDeUsuario))
                                                                                .addGap(10))
                                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                                .addComponent(lblCorreoElectronico, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)))
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(txtMail, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
                                                                        .addComponent(txtNIF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtUser, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(pswContrasena, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(lblRegistro, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(173)
                                                .addComponent(btnValidar)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblRegistro, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNombreYApellidos))
                                .addGap(7)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNombreDeUsuario))
                                .addGap(9)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblCorreoElectronico))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblContrasena)
                                        .addComponent(pswContrasena, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtNIF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNif))
                                .addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(btnValidar)
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);

        txtUser.getDocument().addDocumentListener(new DocumentListener() {
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
        txtMail.getDocument().addDocumentListener(new DocumentListener() {
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
        txtNIF.getDocument().addDocumentListener(new DocumentListener() {
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

    }

    public void limpiarCampos() {
        txtMail.setText("");
        txtUser.setText("");
        pswContrasena.setText("");
        txtNIF.setText("");
        txtNombre.setText("");
    }

    private void changed() {
        if (txtUser.getText().equals("") || new String(pswContrasena.getPassword()).equals("") || txtMail.getText().equals("") || txtNIF.getText().equals("")) {
            btnValidar.setEnabled(false);
        } else {
            btnValidar.setEnabled(true);
        }
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtUser() {
        return txtUser;
    }

    public JTextField getTxtMail() {
        return txtMail;
    }

    public JTextField getTxtNIF() {
        return txtNIF;
    }

    public JPasswordField getPswContrasena() {
        return pswContrasena;
    }
}
