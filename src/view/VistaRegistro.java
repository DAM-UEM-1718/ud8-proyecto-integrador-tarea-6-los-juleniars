package view;

import controller.Controlador;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Vista que permite el registro como tutor en la aplicaciÃ³n
 *
 * @author Los Juleniars
 */
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
    private JLabel lblConfirmarContrasea;
    private JPasswordField pswConfirmar;

    public VistaRegistro() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 315);
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
        btnValidar.addActionListener(e -> {
            if (validarMail()) {
                controlador.registrar();
            } else {
                JOptionPane.showMessageDialog(null, "El mail no es válido.");
            }
        });
        btnValidar.setEnabled(false);

        JLabel lblNombreYApellidos = new JLabel("Nombre y Apellidos:");

        JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");

        JLabel lblCorreoElectronico = new JLabel("Correo Electrónico:");

        lblContrasena = new JLabel("Contraseña:");

        lblNif = new JLabel("NIF:");

        lblRegistro = new JLabel("REGISTRO");
        lblRegistro.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 20));

        pswContrasena = new JPasswordField();

        lblConfirmarContrasea = new JLabel("Confirmar");

        pswConfirmar = new JPasswordField();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                                .addComponent(lblContrasena)
                                                                                .addComponent(lblNombreYApellidos)
                                                                                .addComponent(lblNombreDeUsuario))
                                                                        .addGap(10))
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                                .addComponent(lblConfirmarContrasea, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 101, Short.MAX_VALUE)
                                                                                .addComponent(lblCorreoElectronico, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                                                                        .addPreferredGap(ComponentPlacement.RELATED)))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(lblNif)
                                                                .addPreferredGap(ComponentPlacement.RELATED)))
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
                                                        .addComponent(txtUser, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(pswContrasena, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(pswConfirmar, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtMail, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtNIF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(lblRegistro, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(173)
                                                .addComponent(btnValidar)
                                                .addGap(156)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
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
                                                        .addComponent(pswContrasena, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(165)
                                                .addComponent(pswConfirmar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(165)
                                                .addComponent(lblConfirmarContrasea)))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtNIF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNif))
                                .addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addComponent(btnValidar)
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);

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

        txtNombre.getDocument().addDocumentListener(documentListener);
        txtUser.getDocument().addDocumentListener(documentListener);
        pswContrasena.getDocument().addDocumentListener(documentListener);
        pswConfirmar.getDocument().addDocumentListener(documentListener);
        txtMail.getDocument().addDocumentListener(documentListener);
        txtNIF.getDocument().addDocumentListener(documentListener);

    }

    /**
     * Limpia todos los campos del formulario
     */
    public void limpiarCampos() {
        txtMail.setText("");
        txtUser.setText("");
        pswContrasena.setText("");
        txtNIF.setText("");
        txtNombre.setText("");
    }

    /**
     * Activa el botÃ³n de registro en cuanto se han rellenado todos los campos
     */
    private void changed() {
        if (txtNombre.getText().equals("") || txtUser.getText().equals("") || new String(pswContrasena.getPassword()).equals("") || new String(pswContrasena.getPassword()).equals("") || txtMail.getText().equals("") || txtNIF.getText().equals("")) {
            btnValidar.setEnabled(false);
        } else {
            btnValidar.setEnabled(true);
        }
    }

    /**
     * Comprueba el mail introducido
     *
     * @return True si el mail es vÃ¡lido
     */
    private boolean validarMail() {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(txtMail.getText());
        return matcher.matches();
    }

    public void errorUsuario() {
        JOptionPane.showMessageDialog(null, "Ya existe un usuario con el mismo nombre o NIF.");
    }

    public void errorPassword() {
        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
    }

    public void registrado() {
        controlador.registrado();
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public String getTxtNombre() {
        return txtNombre.getText();
    }

    public String getTxtUser() {
        return txtUser.getText();
    }

    public String getTxtMail() {
        return txtMail.getText();
    }

    public String getTxtNIF() {
        return txtNIF.getText();
    }

    public String getPswContrasena() {
        return new String(pswContrasena.getPassword());
    }

    public String getPswConfirmar() {
        return new String(pswConfirmar.getPassword());
    }
}
