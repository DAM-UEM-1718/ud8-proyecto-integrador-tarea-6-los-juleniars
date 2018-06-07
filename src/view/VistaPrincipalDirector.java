package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Pantalla principal de los directores
 *
 * @author Los Juleniars
 */
public class VistaPrincipalDirector extends JPanel implements Vista {

    private Controlador controlador;
    private Modelo modelo;

    //private JPanel contentPane;
    private JTable table;

    private JLabel lblBienvenido;
    private JLabel lblNumeroAlumnos;
    private JLabel lblAlumnosPorAsignar;
    private JLabel lblClases;
    private JComboBox<Integer> cmbAnoAcademico;

    public VistaPrincipalDirector() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        //setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipalDirector.class.getResource("/img/uem.png")));
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 648, 381);
        //contentPane = new JPanel();
        setBorder(new EmptyBorder(5, 5, 5, 5));
        //setContentPane(contentPane);

        JButton btnConfig = new JButton("Configuración de Usuario");
        btnConfig.addActionListener(e -> controlador.mostarConfiguracion());

        JLabel lblAsignadas = new JLabel("Alumnos con prácticas asignadas: ");

        JLabel lblPorAsignar = new JLabel("Alumnos con prácticas por asignar:");

        lblNumeroAlumnos = new JLabel("0");
        lblNumeroAlumnos.setForeground(new Color(50, 205, 50));

        lblAlumnosPorAsignar = new JLabel("2");
        lblAlumnosPorAsignar.setForeground(Color.RED);

        JLabel lblEstadsticas = new JLabel("Estadísticas");
        lblEstadsticas.setFont(new Font("Tahoma", Font.BOLD, 14));

        JLabel lblClasesConPrcticas = new JLabel("Clases con prácticas por asignar:");

        lblClases = new JLabel("1");
        lblClases.setForeground(Color.RED);

        JScrollPane scrollPane = new JScrollPane();

        lblBienvenido = new JLabel("Bienvenida Ana Manzanero");

        JButton btnEditarPrcticas = new JButton("Editar Prácticas");
        btnEditarPrcticas.addActionListener(e -> controlador.mostrarPracticas());

        JButton btnCerrarSesin = new JButton("Cerrar Sesión");
        btnCerrarSesin.addActionListener(e -> controlador.cerrarSesion());

        cmbAnoAcademico = new JComboBox<>();

        cmbAnoAcademico.addActionListener(e -> controlador.cambiarAno());

        JLabel lblAnoAcademico = new JLabel("Año académico:");
        GroupLayout gl_contentPane = new GroupLayout(this);
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
                                                .addComponent(lblBienvenido)
                                                .addPreferredGap(ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                                                .addComponent(lblAnoAcademico)
                                                .addGap(40)
                                                .addComponent(cmbAnoAcademico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblClasesConPrcticas, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(lblClases, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblEstadsticas)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                                                .addComponent(btnConfig))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnEditarPrcticas)
                                                .addPreferredGap(ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
                                                .addComponent(btnCerrarSesin)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(12)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(cmbAnoAcademico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblAnoAcademico))
                                        .addComponent(lblBienvenido))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblEstadsticas)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(lblAsignadas)
                                                        .addComponent(lblNumeroAlumnos))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(lblPorAsignar)
                                                        .addComponent(lblAlumnosPorAsignar))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(lblClasesConPrcticas)
                                                        .addComponent(lblClases))
                                                .addGap(15)
                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnConfig))
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
        setLayout(gl_contentPane);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setLblBienvenido() {
        lblBienvenido.setText("Bienvenido " + modelo.getNombreUsuarioFormal());
    }

    public void cargarTabla() {
        lblAlumnosPorAsignar.setText(Integer.toString(modelo.getNumeroPorAsignar()));
        lblClases.setText(Integer.toString(modelo.getClasesPorAsignar()));
        table.setModel(modelo.getTablaDashboardDirector());
    }

    public void cargarAnoAcademico() {
        cmbAnoAcademico.setModel(modelo.getModeloCmbAnos());
    }

    public int getAnoAcademico() {
        return (Integer) cmbAnoAcademico.getSelectedItem();
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
