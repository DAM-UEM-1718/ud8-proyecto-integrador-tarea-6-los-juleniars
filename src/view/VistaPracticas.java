package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;

import controller.Controlador;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaPracticas extends JFrame implements Vista {

    private JPanel contentPane;
    private JTable table;
    private JTextField txtEmpresa;
    private Controlador controlador;


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

        JButton btnEliminarPracticas = new JButton("Eliminar Prácticas");
        btnEliminarPracticas.addActionListener(e -> {

        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> controlador.cerrarPracticas());

        JComboBox comboBox = new JComboBox();
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
                                                .addComponent(btnEliminarPracticas)))
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
                                        .addComponent(btnEliminarPracticas))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        /*table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"Iv\u00E1n Hern\u00E1ndez", "Apple Espa\u00F1a S.A.", null, null, null, null, null, null, null},
        		{"V\u00EDctorJim\u00E9nez", "Facebook Inc.", null, null, null, null, null, null, null},
        		{"Andr\u00E9s Murillas", "Twitter Inc.", null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"Alumnos", "Empresa", "Fecha Inicio", "Fecha Fin", "Tutor Empresa", "Horario", "Localizaci\u00F3n", "Erasmus", "Estado"
        	}
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(91);
        table.getColumnModel().getColumn(1).setPreferredWidth(116);*/
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public JTable getTable() {
        return table;
    }
}
