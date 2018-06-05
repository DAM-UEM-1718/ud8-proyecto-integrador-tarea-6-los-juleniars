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

public class VistaAnadirAlumno extends JFrame implements Vista {

    private Modelo modelo;
    private Controlador controlador;

    private JPanel contentPane;
    private JTextField txtNumMat;
    private JTextField txtNombre;
    private JTextField txtApellido1;
    private JTextField txtDNI;
    private JTextField txtApellido2;

    private JButton btnAnadir;

    public VistaAnadirAlumno() {

        setTitle("Añadir Alumno");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAnadirTutor.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            controlador.insertarAlumno();
            limpiarCampos();
        });

        txtApellido2 = new JTextField();
        txtApellido2.setColumns(10);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(21)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(btnAnadir)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtApellido1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtNumMat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtApellido2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(317, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(40)
                                .addComponent(txtNumMat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(txtApellido1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(3)
                                .addComponent(txtApellido2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnAnadir)
                                .addContainerGap(25, Short.MAX_VALUE))
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
        txtApellido2.getDocument().addDocumentListener(documentListener);
        txtDNI.getDocument().addDocumentListener(documentListener);
    }

    private void changed() {
        if (txtNumMat.getText().equals("") || txtNombre.getText().equals("") || txtApellido1.getText().equals("") || txtApellido2.getText().equals("") || txtDNI.getText().equals("")) {
            btnAnadir.setEnabled(false);
        } else {
            btnAnadir.setEnabled(true);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtNumMat.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtDNI.setText("");
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
