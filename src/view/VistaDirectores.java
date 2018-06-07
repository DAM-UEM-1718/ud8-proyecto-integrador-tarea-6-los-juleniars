package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaDirectores extends JPanel implements Vista {

    private Controlador controlador;
    private Modelo modelo;

    private JTable table;
    private JButton btnAnadirDirector;
    private JButton btnEliminarTutor;
    private JButton btnModificarDirector;
    private JLabel lblError;


    public VistaDirectores() {
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

        JLabel lblTitulo = new JLabel("Editar Directores");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));

        btnAnadirDirector = new JButton("Añadir Director");
        btnAnadirDirector.addActionListener(e -> controlador.mostrarAnadirDirector());

        btnEliminarTutor = new JButton("Eliminar Director");
        btnEliminarTutor.setEnabled(false);
        btnEliminarTutor.addActionListener(e -> controlador.eliminarDirector());

        btnModificarDirector = new JButton("Modificar Director");
        btnEliminarTutor.setEnabled(false);
        btnModificarDirector.addActionListener(e -> controlador.mostrarModificarDirector());
        btnEliminarTutor.setEnabled(false);
        btnModificarDirector.setEnabled(false);

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
                                                .addPreferredGap(ComponentPlacement.RELATED, 545, Short.MAX_VALUE)
                                                .addComponent(lblError))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAnadirDirector)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnModificarDirector)
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
                                        .addComponent(btnAnadirDirector)
                                        .addComponent(btnModificarDirector)
                                        .addComponent(btnEliminarTutor))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            btnModificarDirector.setEnabled(!table.getSelectionModel().isSelectionEmpty());
            btnEliminarTutor.setEnabled(!table.getSelectionModel().isSelectionEmpty());
        });
        scrollPane.setViewportView(table);
        setLayout(gl_contentPane);
    }

    public void cargarTabla() {
        lblError.setText("");
        table.setModel(modelo.getTablaDirectores());
    }

    public void errorEliminar() {
        lblError.setText("Error al eliminar el director.");
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
