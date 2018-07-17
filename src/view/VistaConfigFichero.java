package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Vista de la aplicaciÃ³n que permite editar la configuraciÃ³n de conexiÃ³n
 * a la base de datos
 *
 * @author Los Juleniars
 */
public class VistaConfigFichero extends JFrame implements Vista {

    private Controlador controlador;
    private Modelo modelo;

    private JPanel contentPane;
    private JTextField txtUser;
    private JTextField txtUrl;
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

        JLabel lblHostMysql = new JLabel("URL MySQL");

        txtUser = new JTextField();
        txtUser.setColumns(10);

        txtUrl = new JTextField();
        txtUrl.setColumns(10);

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
                                                        .addComponent(txtUrl, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(txtUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
        txtUrl.getDocument().addDocumentListener(documentListener);
    }

    public void setConfiguracion() {
        txtUser.setText(modelo.getUSER());
        pswContrasena.setText(modelo.getPASSWORD());
        txtUrl.setText(modelo.getURL());
    }

    public String getTxtUser() {
        return txtUser.getText();
    }

    public String getTxtUrl() {
        return txtUrl.getText();
    }

    public String getPswContrasena() {
        return new String(pswContrasena.getPassword());
    }


    private void changed() {
        if (txtUser.getText().equals("") || txtUrl.getText().equals("") || new String(pswContrasena.getPassword()).equals("")) {
            btnAceptar.setEnabled(false);
        } else {
            btnAceptar.setEnabled(true);
        }
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
