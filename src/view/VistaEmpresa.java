package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import controller.Controlador;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaEmpresa extends JFrame implements Vista {

    private JPanel contentPane;
    private JTable table;
    private Controlador controlador;
    private JButton btnAnadirEmpresa;
    private JButton btnEliminarEmpresa;
    private JButton btnVolver;


    public VistaEmpresa() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAlumnos.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 788, 392);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
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

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> controlador.cerrarEmpresas());
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                                        .addComponent(lblTitulo, Alignment.LEADING)
                                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAnadirEmpresa)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnEliminarEmpresa)
                                                .addPreferredGap(ComponentPlacement.RELATED, 398, Short.MAX_VALUE)
                                                .addComponent(btnVolver)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(23)
                                .addComponent(lblTitulo)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAnadirEmpresa)
                                        .addComponent(btnVolver)
                                        .addComponent(btnEliminarEmpresa))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null},
                },
                new String[]{
                        "N. Convenio", "Raz\u00F3n Social", "F. Firma", "Direcci\u00F3n", "Representante", "Mail"
                }
        ));
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setMinWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(97);
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
