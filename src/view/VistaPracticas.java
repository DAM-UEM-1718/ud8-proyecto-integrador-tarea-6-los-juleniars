package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;

public class VistaPracticas extends JFrame implements Vista {

    private JPanel contentPane;
    private JTable table;
    private JTextField txtEmpresa;
    private JButton btnModificar;
    private JButton btnEliminarPracticas;

    private Controlador controlador;
    private Modelo modelo;


    public VistaPracticas() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAlumnos.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 847, 489);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JScrollPane scrollPane = new JScrollPane();

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> {
        });

        JLabel lblTitulo = new JLabel("Prácticas");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));

        txtEmpresa = new JTextField();
        txtEmpresa.setColumns(10);

        JButton btnAsignar = new JButton("Asignar Prácticas");
        btnAsignar.addActionListener(e -> controlador.mostrarAsignarPracticas());

        btnEliminarPracticas = new JButton("Eliminar Prácticas");
        btnEliminarPracticas.setEnabled(false);
        btnEliminarPracticas.addActionListener(e -> controlador.eliminarPracticas());

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> controlador.cerrarPracticas());

        JComboBox comboBox = new JComboBox();

        btnModificar = new JButton("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(e -> controlador.mostrarModificarPracticas());

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblTitulo)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGap(10)
                                                                .addComponent(txtEmpresa, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addPreferredGap(ComponentPlacement.RELATED, 604, Short.MAX_VALUE)
                                                                .addComponent(btnVolver))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGap(18)
                                                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(btnBuscar))))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAsignar)
                                                .addGap(18)
                                                .addComponent(btnEliminarPracticas)
                                                .addGap(18)
                                                .addComponent(btnModificar)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(23)
                                                .addComponent(lblTitulo))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnVolver)))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscar))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAsignar)
                                        .addComponent(btnEliminarPracticas)
                                        .addComponent(btnModificar))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(evento -> changed());

        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);
    }

    private void changed() {
        btnModificar.setEnabled(!table.getSelectionModel().isSelectionEmpty());
        btnEliminarPracticas.setEnabled(!table.getSelectionModel().isSelectionEmpty());
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void cargarTablas() {
        table.setModel(modelo.getTablaPracticas());
        table.removeColumn(table.getColumnModel().getColumn(9));
        table.removeColumn(table.getColumnModel().getColumn(9));
    }

    public int getFilaSeleccionada() {
        return table.getSelectedRow();
    }

    public String getNombreAlumnoSeleccionado() {
        return (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
    }

    public String getNombreEmpresaSeleccionada() {
        return (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
    }

    public String getTutorEmpresaSeleccionado() {
        return (String) table.getModel().getValueAt(table.getSelectedRow(), 2);
    }

    public Date getFechaInicioSeleccionada() {
        return (Date) table.getModel().getValueAt(table.getSelectedRow(), 3);
    }

    public Date getFechaFinSeleccionada() {
        return (Date) table.getModel().getValueAt(table.getSelectedRow(), 4);
    }

    public String getHorarioSeleccionado() {
        return (String) table.getModel().getValueAt(table.getSelectedRow(), 5);
    }

    public String getLocalizacionSeleccionada() {
        return (String) table.getModel().getValueAt(table.getSelectedRow(), 6);
    }

    public boolean getErasmusSeleccionada() {
        return (Boolean) table.getModel().getValueAt(table.getSelectedRow(), 7);
    }

    public String getEstadoSeleccionado() {
        return (String) table.getModel().getValueAt(table.getSelectedRow(), 8);
    }

    public int getNumMatSeleccionado() {
        int numMat;
        if (table.getModel().getValueAt(table.getSelectedRow(), 9) instanceof String) {
            numMat = Integer.parseInt((String) table.getModel().getValueAt(table.getSelectedRow(), 9));
        } else {
            numMat = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 9);
        }
        return numMat;
    }

    public int getNumConvSeleccionado() {
        int numMat;
        if (table.getModel().getValueAt(table.getSelectedRow(), 10) instanceof String) {
            numMat = Integer.parseInt((String) table.getModel().getValueAt(table.getSelectedRow(), 10));
        } else {
            numMat = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 10);
        }
        return numMat;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
