package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Vista que muestra los datos de la tabla tutores
 *
 * @author Los Juleniars
 */
public class VistaTutores extends JPanel implements Vista {

    private Controlador controlador;
    private Modelo modelo;

    //private JPanel contentPane;
    private JTable table;
    private JButton btnAnadirTutor;
    private JButton btnEliminarTutor;
    private JButton btnModificarTutor;
    private JLabel lblError;


    public VistaTutores() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAlumnos.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        setBounds(100, 100, 693, 392);
        //contentPane = new JPanel();
        setBorder(new EmptyBorder(5, 5, 5, 5));
        //setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JScrollPane scrollPane = new JScrollPane();

        JLabel lblTitulo = new JLabel("Editar Tutores");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));

        btnAnadirTutor = new JButton("A�adir Tutor");
        btnAnadirTutor.addActionListener(e -> controlador.mostrarAnadirTutor());

        btnEliminarTutor = new JButton("Eliminar Tutor");
        btnEliminarTutor.setEnabled(false);
        btnEliminarTutor.addActionListener(e -> controlador.eliminarTutor());

        btnModificarTutor = new JButton("Modificar Tutor");
        btnEliminarTutor.setEnabled(false);
        btnModificarTutor.addActionListener(e -> controlador.mostrarModificarUsuario());
        btnEliminarTutor.setEnabled(false);
        btnModificarTutor.setEnabled(false);

        lblError = new JLabel("");
        lblError.setForeground(Color.RED);
        GroupLayout gl_contentPane = new GroupLayout(this);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblTitulo)
                                                .addPreferredGap(ComponentPlacement.RELATED, 521, Short.MAX_VALUE)
                                                .addComponent(lblError))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAnadirTutor)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnModificarTutor, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnEliminarTutor)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(23)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblTitulo)
                                                .addGap(18))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblError)
                                                .addPreferredGap(ComponentPlacement.RELATED)))
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAnadirTutor)
                                        .addComponent(btnModificarTutor)
                                        .addComponent(btnEliminarTutor))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            btnModificarTutor.setEnabled(!table.getSelectionModel().isSelectionEmpty());
            btnEliminarTutor.setEnabled(!table.getSelectionModel().isSelectionEmpty());
        });
        scrollPane.setViewportView(table);
        setLayout(gl_contentPane);
    }

    public void cargarTabla() {
        lblError.setText("");
        table.setModel(modelo.getTablaTutores());
    }

    public void errorEliminar() {
        lblError.setText("Primero debe eliminar los grupos de este tutor.");
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public String getUsuarioSeleccionado() {
        return (String) table.getValueAt(table.getSelectedRow(), 1);
    }

    public String getNombreSeleccionado() {
        return (String) table.getValueAt(table.getSelectedRow(), 0);
    }

    public String getMailSeleccionado() {
        return (String) table.getValueAt(table.getSelectedRow(), 2);
    }

    public String getNIFSeleccionado() {
        return (String) table.getValueAt(table.getSelectedRow(), 3);
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
