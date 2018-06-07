package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Vista que muestra la tabla de alumnos
 *
 * @author Los Juleniars
 */
public class VistaAlumnos extends JPanel implements Vista {


    private Controlador controlador;
    private Modelo modelo;

    private JTable table;
    private JTextField txtMatricula;
    private JButton btnBuscar;
    private JLabel lblTitulo;
    private JButton btnEliminarAlumno;
    private JButton btnModificarAlumno;
    private JButton btnAadirAlumno;


    public VistaAlumnos() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setBounds(100, 100, 693, 392);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JScrollPane scrollPane = new JScrollPane();

        txtMatricula = new JTextField();
        txtMatricula.setToolTipText("");
        txtMatricula.setColumns(10);

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> {

        });

        lblTitulo = new JLabel("Editar Alumnos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));

        btnAadirAlumno = new JButton("Añadir Alumno");
        btnAadirAlumno.addActionListener(e -> {
            controlador.mostarAnadirAlumno();
        });

        btnModificarAlumno = new JButton("Modificar Alumno");
        btnModificarAlumno.setEnabled(false);
        btnModificarAlumno.addActionListener(e -> {
            controlador.mostrarModificarAlumno();
        });

        btnEliminarAlumno = new JButton("Eliminar Alumno");
        btnEliminarAlumno.setEnabled(false);
        btnEliminarAlumno.addActionListener(e -> controlador.eliminarAlumno());

        JComboBox comboBox = new JComboBox();

        // Crea y define el GroupLayout
        GroupLayout gl_contentPane = new GroupLayout(this);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblTitulo)
                                                        .addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18)
                                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(btnBuscar))
                                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAadirAlumno)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnModificarAlumno)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnEliminarAlumno)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(23)
                                .addComponent(lblTitulo)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscar))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAadirAlumno)
                                        .addComponent(btnModificarAlumno)
                                        .addComponent(btnEliminarAlumno))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            btnModificarAlumno.setEnabled(!table.getSelectionModel().isSelectionEmpty());
            btnEliminarAlumno.setEnabled(!table.getSelectionModel().isSelectionEmpty());
        });
        scrollPane.setViewportView(table);
        setLayout(gl_contentPane);
    }

    /**
     * @return El número de matrícula seleccionado en la tabla
     */
    public int getNumMatSeleccionado() {
        return (Integer) table.getValueAt(table.getSelectedRow(), 0);
    }

    /**
     * @return El nombre del alumno seleccionado en la tabla
     */
    public String getNombreSeleccionado() {
        return (String) table.getValueAt(table.getSelectedRow(), 1);
    }

    /**
     * @return El primer apellido seleccionado en la tabla
     */
    public String getApellido1Seleccionado() {
        //Al estar los dos apellidos concatenados, los separa a tarvés del primer espacio y coge la primera parte
        return ((String) table.getValueAt(table.getSelectedRow(), 2)).split(" ")[0];
    }

    /**
     * @return El segundo apellido seleccionado en la tabla
     */
    public String getApellido2Seleccionado() {
        //Al estar los dos apellidos concatenados, los separa a tarvés del primer espacio y coge la segunda parte
        String[] apellido2 = ((String) table.getValueAt(table.getSelectedRow(), 2)).split(" ");
        return apellido2.length > 1 ? apellido2[1] : "";
    }

    /**
     * @return El DNI seleccionado en la tabla
     */
    public String getDniSeleccionado() {
        return (String) table.getValueAt(table.getSelectedRow(), 3);
    }

    /**
     * Carga la tabla desde el modelo
     */
    public void cargarTabla() {
        table.setModel(modelo.getTablaAlumnos());
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
