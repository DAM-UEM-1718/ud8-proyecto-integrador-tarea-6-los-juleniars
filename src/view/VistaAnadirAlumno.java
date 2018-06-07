package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Vista de la aplicación que permite añadir alumnos
 *
 * @author Los Juleniars
 */
public class VistaAnadirAlumno extends JFrame implements Vista {

    private Modelo modelo;
    private Controlador controlador;

    private JPanel contentPane;
    private JTextField txtNumMat;
    private JTextField txtNombre;
    private JTextField txtApellido1;
    private JTextField txtDNI;
    private JTextField txtApellido2;

    private boolean modificar;
    private JButton btnAnadir;
    private JLabel lblNombre;
    private JLabel lblNMatrcula;
    private JLabel lblPrimerApellido;
    private JLabel lblSegundoApellido;
    private JLabel lblDni;

    public VistaAnadirAlumno() {

        setTitle("Añadir Alumno");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAnadirTutor.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        txtNumMat = new JTextField();
        txtNumMat.setColumns(10);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);

        txtApellido1 = new JTextField();
        txtApellido1.setColumns(10);

        txtDNI = new JTextField();
        txtDNI.setColumns(10);

        btnAnadir = new JButton("Añadir");
        btnAnadir.setEnabled(false);
        btnAnadir.addActionListener(e -> {
            if (!modificar)
                controlador.insertarAlumno();
            else
                controlador.modificarAlumno();
            limpiarCampos();
        });

        txtApellido2 = new JTextField();
        txtApellido2.setColumns(10);

        lblNombre = new JLabel("Nombre");

        lblNMatrcula = new JLabel("N. Matrícula");

        lblPrimerApellido = new JLabel("Primer Apellido");

        lblSegundoApellido = new JLabel("Segundo Apellido");

        lblDni = new JLabel("DNI");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(104, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblNombre)
                                        .addComponent(lblNMatrcula)
                                        .addComponent(lblPrimerApellido)
                                        .addComponent(lblSegundoApellido)
                                        .addComponent(lblDni))
                                .addGap(28)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtApellido2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtApellido1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNumMat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(124))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(181)
                                .addComponent(btnAnadir)
                                .addContainerGap(180, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(40)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtNumMat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNMatrcula))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNombre))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(txtApellido1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPrimerApellido))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtApellido2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSegundoApellido))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblDni))
                                .addGap(18)
                                .addComponent(btnAnadir)
                                .addContainerGap(46, Short.MAX_VALUE))
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
        txtNumMat.getDocument().addDocumentListener(documentListener);
        txtNombre.getDocument().addDocumentListener(documentListener);
        txtApellido1.getDocument().addDocumentListener(documentListener);
        txtDNI.getDocument().addDocumentListener(documentListener);
    }

    private void changed() {
        if (txtNumMat.getText().equals("") || txtNombre.getText().equals("") || txtApellido1.getText().equals("") || txtDNI.getText().equals("")) {
            btnAnadir.setEnabled(false);
        } else {
            btnAnadir.setEnabled(true);
        }
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtNumMat.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtDNI.setText("");
    }

    public void setAnadir() {
        limpiarCampos();
        modificar = false;
        setTitle("Añadir Alumno");
        btnAnadir.setText("Añadir");
        txtNumMat.setEditable(true);
    }

    public void setModificar(int numMat, String nombre, String apellido1, String apellido2, String dni) {
        setTitle("Modificar Alumno");
        btnAnadir.setText("Modificar");
        modificar = true;
        txtNumMat.setText(Integer.toString(numMat));
        txtNombre.setText(nombre);
        txtApellido1.setText(apellido1);
        txtApellido2.setText(apellido2);
        txtDNI.setText(dni);
        txtNumMat.setEditable(false);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public int getNumMat() {
        return Integer.parseInt(txtNumMat.getText());
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getApellido1() {
        return txtApellido1.getText();
    }

    public String getApellido2() {
        return txtApellido2.getText();
    }

    public String getDNI() {
        return txtDNI.getText();
    }
}
