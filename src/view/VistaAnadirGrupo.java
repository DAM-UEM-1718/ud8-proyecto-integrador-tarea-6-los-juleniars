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
 * Vista de la aplicación que permite añadir grupos
 *
 * @author Los Juleniars
 */
public class VistaAnadirGrupo extends JFrame implements Vista {

    private Controlador controlador;
    private Modelo modelo;

    private JPanel contentPane;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JLabel lblCiclo;
    private JButton btnAnadir;

    private JComboBox<Modelo.ComboItem> cmbCiclo;
    private JComboBox<Modelo.ComboItem> cmbTutor;

    private boolean modificar;

    public VistaAnadirGrupo() {
        setTitle("Añadir Grupo");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAnadirTutor.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JLabel lblCodigo = new JLabel("Código");
        txtCodigo = new JTextField();
        txtCodigo.setColumns(10);


        btnAnadir = new JButton("Añadir");
        btnAnadir.setEnabled(false);
        btnAnadir.addActionListener(e -> {
            if (!modificar)
                controlador.anadirGrupo();
            else
                controlador.modificarGrupo();
            controlador.mostrarGrupos();
        });

        txtNombre = new JTextField();
        txtNombre.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre");

        lblCiclo = new JLabel("Ciclo");

        cmbCiclo = new JComboBox<Modelo.ComboItem>();

        cmbTutor = new JComboBox<Modelo.ComboItem>();

        JLabel lblTutor = new JLabel("Tutor");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(127)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(lblTutor)
                                                        .addComponent(lblCiclo)
                                                        .addComponent(lblCodigo))
                                                .addGap(18)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(cmbCiclo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cmbTutor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addComponent(btnAnadir)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                        .addComponent(lblNombre)
                                                        .addGap(18)
                                                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(156, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(37)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNombre)
                                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblCodigo)
                                        .addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblCiclo)
                                        .addComponent(cmbCiclo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(cmbTutor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTutor))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnAnadir)
                                .addContainerGap(82, Short.MAX_VALUE))
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
        txtCodigo.getDocument().addDocumentListener(documentListener);
    }

    private void changed() {
        if (txtCodigo.getText().equals("") || txtNombre.getText().equals("")) {
            btnAnadir.setEnabled(false);
        } else {
            btnAnadir.setEnabled(true);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCodigo.setText("");
    }

    public void setAnadir() {
        limpiarCampos();
        modificar = false;
        setTitle("Añadir Grupo");
        btnAnadir.setText("Añadir");
        txtCodigo.setEditable(true);
    }

    public void setModificar(String nombre, int codigo) {
        setTitle("Modificar Grupo");
        btnAnadir.setText("Modificar");
        modificar = true;
        txtNombre.setText(nombre);
        txtCodigo.setText(Integer.toString(codigo));
        txtCodigo.setEditable(false);
    }

    public void cargarCmbs() {
        cmbCiclo.setModel(modelo.getModeloCmbCiclos());
        cmbTutor.setModel(modelo.getModeloCmbTutores());
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public int getCodigo() {
        return Integer.parseInt(txtCodigo.getText());
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public int getCiclo() {
        return Integer.parseInt(((Modelo.ComboItem) cmbCiclo.getSelectedItem()).getValue());
    }

    public String getTutor() {
        return ((Modelo.ComboItem) cmbTutor.getSelectedItem()).getValue();
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
