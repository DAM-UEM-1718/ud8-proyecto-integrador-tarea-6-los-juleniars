package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaEmpresa extends JPanel implements Vista {

    private Controlador controlador;
    private Modelo modelo;

    private JPanel contentPane;
    private JTable table;
    private JButton btnAnadirEmpresa;
    private JButton btnEliminarEmpresa;


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

        btnAnadirEmpresa = new JButton("Añadir Empresa");
        btnAnadirEmpresa.addActionListener(e -> controlador.mostrarAnadirEmpresa());

        btnEliminarEmpresa = new JButton("Eliminar Empresa");
        btnEliminarEmpresa.addActionListener(e -> {
        });
        GroupLayout gl_contentPane = new GroupLayout(this);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                                        .addComponent(lblTitulo)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAnadirEmpresa)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnEliminarEmpresa)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(23)
                                .addComponent(lblTitulo)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAnadirEmpresa)
                                        .addComponent(btnEliminarEmpresa))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);
        setLayout(gl_contentPane);
    }

    public void cargarTabla() {
        table.setModel(modelo.getTablaEmpresas());
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
