package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Vista que muestra la tabla de grupos
 *
 * @author Los Juleniars
 */
public class VistaGrupos extends JPanel implements Vista {

    private Controlador controlador;
    private Modelo modelo;

    //private JPanel contentPane;
    private JTable table;

    private JLabel lblError;

    public VistaGrupos() {
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

        JLabel lblTitulo = new JLabel("Editar Grupos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));

        JButton btnAnadirGrupo = new JButton("A�adir Grupo");
        btnAnadirGrupo.addActionListener(e -> controlador.mostrarAnadirGrupo());

        JButton btnEliminarGrupo = new JButton("Eliminar Grupo");
        btnEliminarGrupo.setEnabled(false);
        btnEliminarGrupo.addActionListener(e -> controlador.eliminarGrupo());

        JButton btnModificarGrupo = new JButton("Modificar Grupo");
        btnModificarGrupo.addActionListener(e -> controlador.mostrarModificarGrupo());
        btnModificarGrupo.setEnabled(false);

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
                                                .addPreferredGap(ComponentPlacement.RELATED, 571, Short.MAX_VALUE)
                                                .addComponent(lblError))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAnadirGrupo)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnModificarGrupo)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnEliminarGrupo)))
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
                                        .addComponent(btnAnadirGrupo)
                                        .addComponent(btnModificarGrupo)
                                        .addComponent(btnEliminarGrupo))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);
        setLayout(gl_contentPane);
        table.getSelectionModel().addListSelectionListener(e -> {
            btnModificarGrupo.setEnabled(!table.getSelectionModel().isSelectionEmpty());
            btnEliminarGrupo.setEnabled(!table.getSelectionModel().isSelectionEmpty());
        });
    }

    public void errorEliminar() {
        lblError.setText("No se puede eliminar un grupo con alumnos asignados.");
    }

    public String getNombreSeleccionado() {
        return (String) table.getValueAt(table.getSelectedRow(), 1);
    }

    public int getCodigoSeleccionado() {
        return (Integer) table.getValueAt(table.getSelectedRow(), 0);
    }

    public void cargarTabla() {
        lblError.setText("");
        table.setModel(modelo.getTablaGrupos());
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public JTable getTable() {
        return table;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
