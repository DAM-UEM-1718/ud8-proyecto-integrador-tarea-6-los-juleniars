package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Controlador;

import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class VistaPrincipalAdministrativo extends JFrame implements Vista {

    private JPanel contentPane;
    private Controlador controlador;
    private JTable table;

    private JLabel lblBienvenido;

    public VistaPrincipalAdministrativo() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipalAdministrativo.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 525, 381);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JButton btnConfig = new JButton("Configuración de Usuario");
        btnConfig.addActionListener(e -> controlador.mostarConfiguracion());

        JLabel lblAsignadas = new JLabel("Alumnos con prácticas asignadas: ");

        JLabel lblPorAsignar = new JLabel("Alumnos con prácticas por asignar:");

        JLabel lblNumeroAlumnos = new JLabel("0");
        lblNumeroAlumnos.setForeground(new Color(50, 205, 50));

        JLabel lblAlumnosPorAsignar = new JLabel("2");
        lblAlumnosPorAsignar.setForeground(Color.RED);

        JLabel lblEstadsticas = new JLabel("Estadísticas");
        lblEstadsticas.setFont(new Font("Tahoma", Font.BOLD, 14));

        JLabel lblClasesConPrcticas = new JLabel("Clases con prácticas por asignar:");

        JLabel lblClases = new JLabel("1");
        lblClases.setForeground(Color.RED);

        JButton btnTutores = new JButton("Tutores");
        btnTutores.addActionListener(e -> controlador.mostrarTutores());

        JButton btnGrupos = new JButton("Grupos");
        btnGrupos.addActionListener(e -> controlador.mostrarGrupos());

        JButton btnEmpresas = new JButton("Empresas");
        btnEmpresas.addActionListener(e -> controlador.mostrarEmpresas());

        JScrollPane scrollPane = new JScrollPane();

        lblBienvenido = new JLabel("Bienvenida Ana Manzanero");

        JLabel lblDetalle = new JLabel("Detalle");
        lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 14));

        JButton btnEditarPrcticas = new JButton("Editar Prácticas");
        btnEditarPrcticas.addActionListener(e -> controlador.mostrarPracticas());

        JButton btnCerrarSesin = new JButton("Cerrar Sesión");
        btnCerrarSesin.addActionListener(e -> controlador.cerrarSesion());
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblAsignadas)
                                                        .addComponent(lblPorAsignar))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblAlumnosPorAsignar, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblNumeroAlumnos, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblBienvenido)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(lblClasesConPrcticas, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(lblClases, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(lblEstadsticas))
                                                .addGap(95)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(btnEmpresas)
                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addComponent(btnConfig)
                                                                        .addPreferredGap(ComponentPlacement.RELATED))
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(lblDetalle, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                                .addComponent(btnCerrarSesin)
                                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                                        .addComponent(btnTutores)
                                                                                        .addGap(18)
                                                                                        .addComponent(btnGrupos)))))))
                                        .addComponent(btnEditarPrcticas)
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE))
                                .addGap(60))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(14)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblBienvenido)
                                        .addComponent(btnConfig))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblEstadsticas)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblAsignadas)
                                        .addComponent(lblNumeroAlumnos)
                                        .addComponent(lblDetalle, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblPorAsignar)
                                        .addComponent(lblAlumnosPorAsignar)
                                        .addComponent(btnTutores)
                                        .addComponent(btnGrupos))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblClasesConPrcticas)
                                                .addComponent(lblClases))
                                        .addComponent(btnEmpresas))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnEditarPrcticas)
                                        .addComponent(btnCerrarSesin))
                                .addGap(147))
        );

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                        {"DAM 2", "Pedro Camacho", "2"},
                        {"ASIR 2", "Ra\u00FAl Rodr\u00EDguez", "5"},
                },
                new String[]{
                        "Grupo", "Tutor", "Alumnos Por Asignar"
                }
        ));
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setMinWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(115);
        table.getColumnModel().getColumn(2).setMinWidth(115);
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public JLabel getLblBienvenido() {
        return lblBienvenido;
    }
}
