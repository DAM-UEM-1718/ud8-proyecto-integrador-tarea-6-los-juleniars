package view;

import controller.Controlador;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class VistaAnadirDirector extends JFrame implements Vista {

    private Controlador controlador;

    private JPanel contentPane;
    private JTextField txtMail;

    private JTextField txtUsuario;
    private JTextField txtNombre;
    private JTextField txtNIF;
    private JLabel lblDni;
    private JButton btnAnadir;
    private boolean modificar;

    public VistaAnadirDirector() {
        setTitle("Añadir Director");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAnadirTutor.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JLabel lblMail = new JLabel("Mail");
        txtUsuario = new JTextField();
        txtUsuario.setColumns(10);

        JLabel lblNExpediente = new JLabel("Usuario");
        txtMail = new JTextField();
        txtMail.setColumns(10);


        btnAnadir = new JButton("Añadir");
        btnAnadir.addActionListener(e -> {
            if (!modificar)
                controlador.anadirDirector();
            else
                controlador.modificarDirector();
            controlador.mostrarDirectores();
        });

        txtNombre = new JTextField();
        txtNombre.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre");

        txtNIF = new JTextField();
        txtNIF.setColumns(10);

        lblDni = new JLabel("DNI");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(136)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblDni)
                                                .addGap(18)
                                                .addComponent(txtNIF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblNExpediente)
                                                .addGap(18)
                                                .addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblNombre)
                                                .addGap(18)
                                                .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblMail)
                                                .addGap(18)
                                                .addComponent(txtMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAnadir)
                                                .addGap(23)))
                                .addContainerGap(147, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(42)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNExpediente))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNombre))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblMail)
                                        .addComponent(txtMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblDni)
                                        .addComponent(txtNIF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(13)
                                .addComponent(btnAnadir)
                                .addContainerGap(60, Short.MAX_VALUE))
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
        txtUsuario.getDocument().addDocumentListener(documentListener);
        txtNIF.getDocument().addDocumentListener(documentListener);
        txtNombre.getDocument().addDocumentListener(documentListener);
        txtMail.getDocument().addDocumentListener(documentListener);
    }

    private void changed() {
        if (txtMail.getText().equals("") || txtNombre.getText().equals("") || txtUsuario.getText().equals("") || txtNIF.getText().equals("")) {
            btnAnadir.setEnabled(false);
        } else {
            btnAnadir.setEnabled(true);
        }
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtMail.setText("");
        txtNIF.setText("");
        txtUsuario.setText("");
    }

    public void setAnadir() {
        limpiarCampos();
        modificar = false;
        setTitle("Añadir Director");
        btnAnadir.setText("Añadir");
        txtUsuario.setEditable(true);
    }

    public void setModificar(String nombre, String usuario, String mail, String nif) {
        setTitle("Modificar Director");
        btnAnadir.setText("Modificar");
        modificar = true;
        txtUsuario.setText(usuario);
        txtNombre.setText(nombre);
        txtMail.setText(mail);
        txtNIF.setText(nif);
        txtUsuario.setEditable(false);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public String getMail() {
        return txtMail.getText();
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getNIF() {
        return txtNIF.getText();
    }

    public String getUsuario() {
        return txtUsuario.getText();
    }
}
