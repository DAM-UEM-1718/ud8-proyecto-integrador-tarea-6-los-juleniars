package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaTutores extends JPanel implements Vista {

    private Controlador controlador;
    private Modelo modelo;

    //private JPanel contentPane;
    private JTable table;
    private JButton btnAnadirTutor;
    private JButton btnEliminarTutor;
    private JButton btnVolver;


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

        btnAnadirTutor = new JButton("Añadir Tutor");
        btnAnadirTutor.addActionListener(e -> controlador.mostrarAnadirUsuario());

        btnEliminarTutor = new JButton("Eliminar Tutor");
        btnEliminarTutor.addActionListener(e -> {
        });

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> controlador.cerrarTutores());
        GroupLayout gl_contentPane = new GroupLayout(this);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                                        .addComponent(lblTitulo, Alignment.LEADING)
                                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAnadirTutor)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnEliminarTutor)
                                                .addPreferredGap(ComponentPlacement.RELATED, 398, Short.MAX_VALUE)
                                                .addComponent(btnVolver)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(23)
                                .addComponent(lblTitulo)
                                .addGap(18)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAnadirTutor)
                                        .addComponent(btnVolver)
                                        .addComponent(btnEliminarTutor))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);
        setLayout(gl_contentPane);
    }

    public void cargarTabla() {
        table.setModel(modelo.getTablaTutores());
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }


    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
