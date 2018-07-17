package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;

/**
 * Vista que muestra la tabla de empresas
 *
 * @author Los Juleniars
 */
public class VistaEmpresa extends JPanel implements Vista {

    private Controlador controlador;
    private Modelo modelo;

    private JPanel contentPane;
    private JTable table;
    private JButton btnAnadirEmpresa;
    private JButton btnEliminarEmpresa;
    private JLabel lblError;
    private JButton btnModificarEmpresa;


    public VistaEmpresa() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAlumnos.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        setBounds(100, 100, 788, 392);
        //contentPane = new JPanel();
        setBorder(new EmptyBorder(5, 5, 5, 5));
        //setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JScrollPane scrollPane = new JScrollPane();

        JLabel lblTitulo = new JLabel("Editar Empresas");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));

        btnAnadirEmpresa = new JButton ("A�adir Empresa");
        btnAnadirEmpresa.addActionListener(e -> controlador.mostrarAnadirEmpresa());

        btnEliminarEmpresa = new JButton("Eliminar Empresa");
        btnEliminarEmpresa.addActionListener(e -> controlador.eliminarEmpresa());

        lblError = new JLabel("");
        lblError.setForeground(Color.RED);

        btnModificarEmpresa = new JButton("Modificar Empresa");
        btnModificarEmpresa.addActionListener(e -> controlador.mostrarModificarEmpresa());
        btnModificarEmpresa.setEnabled(false);
        btnEliminarEmpresa.setEnabled(false);
        GroupLayout gl_contentPane = new GroupLayout(this);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblTitulo)
                                                .addPreferredGap(ComponentPlacement.RELATED, 647, Short.MAX_VALUE)
                                                .addComponent(lblError))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAnadirEmpresa)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(btnModificarEmpresa)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(btnEliminarEmpresa)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(23)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblTitulo)
                                        .addComponent(lblError))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAnadirEmpresa)
                                        .addComponent(btnModificarEmpresa)
                                        .addComponent(btnEliminarEmpresa))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);
        setLayout(gl_contentPane);
        table.getSelectionModel().addListSelectionListener(e -> {
            btnModificarEmpresa.setEnabled(!table.getSelectionModel().isSelectionEmpty());
            btnEliminarEmpresa.setEnabled(!table.getSelectionModel().isSelectionEmpty());
        });
    }

    public void errorEliminar() {
        lblError.setText("Debe de eliminar antes los datos que dependen de esta empresa.");
    }

    public void cargarTabla() {
        lblError.setText("");
        table.setModel(modelo.getTablaEmpresas());
    }

    public int getNumConvenioSeleccionado() {
        return (Integer) table.getValueAt(table.getSelectedRow(), 0);
    }

    public String getNombreSeleccionad() {
        return (String) table.getValueAt(table.getSelectedRow(), 1);
    }

    public Date getFechaSeleccionada() {
        return (Date) table.getValueAt(table.getSelectedRow(), 2);
    }

    public String getLocalidadSeleccionad() {
        return (String) table.getValueAt(table.getSelectedRow(), 3);
    }

    public String getDireccionSeleccionad() {
        return (String) table.getValueAt(table.getSelectedRow(), 4);
    }

    public String getRepresentanteSeleccionado() {
        return (String) table.getValueAt(table.getSelectedRow(), 5);
    }

    public String getMailSeleccionado() {
        return (String) table.getValueAt(table.getSelectedRow(), 6);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
