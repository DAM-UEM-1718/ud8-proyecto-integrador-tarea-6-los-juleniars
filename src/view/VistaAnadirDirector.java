package view;

import controller.Controlador;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaAnadirDirector extends JFrame implements Vista {

    private Controlador controlador;

    private JPanel contentPane;
    private JTextField txtMail;

    private JTextField txtUsuario;
    private JTextField txtNombre;
    private JTextField txtNIF;
    private JLabel lblDni;

    private JButton btnAadir;
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


        btnAadir = new JButton("Añadir");
        btnAadir.addActionListener(e -> {
            controlador.anadirAdministrativo();
            controlador.mostrarTutores();
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
                                                .addComponent(btnAadir)
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
                                .addComponent(btnAadir)
                                .addContainerGap(60, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }

    private void changed() {
        if (txtMail.getText().equals("") || txtNombre.getText().equals("") || txtUsuario.getText().equals("") || txtNIF.getText().equals("")) {
            btnAadir.setEnabled(false);
        } else {
            btnAadir.setEnabled(true);
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
        btnAadir.setText("Añadir");
        txtUsuario.setEditable(true);
    }

    public void setModificar(int numMat, String nombre, String apellido1, String apellido2, String dni) {
        setTitle("Modificar Director");
        btnAadir.setText("Modificar");
        modificar = true;
        txtUsuario.setText(Integer.toString(numMat));
        txtNombre.setText(nombre);
        txtMail.setText(apellido1);
        txtNIF.setText(apellido2);
        txtUsuario.setEditable(false);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public String getTxtMail() {
        return txtMail.getText();
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getNIF() {
        return txtNIF.getText();
    }

    public String getTxtExpediente() {
        return txtUsuario.getText();
    }
}
