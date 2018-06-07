package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Vista que muestra los datos de la tabla de prácticas
 *
 * @author Los Juleniars
 */
public class VistaPracticas extends JPanel implements Vista {

    private JTable table;
    private JButton btnModificar;
    private JButton btnEliminarPracticas;
    private JLabel lblFechaLimite;

    private Controlador controlador;
    private Modelo modelo;


    public VistaPracticas() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAlumnos.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        setBounds(100, 100, 847, 489);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        //setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JScrollPane scrollPane = new JScrollPane();

        JLabel lblTitulo = new JLabel("Prácticas");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));

        JButton btnAsignar = new JButton("Asignar Prácticas");
        btnAsignar.addActionListener(e -> controlador.mostrarAsignarPracticas());

        btnEliminarPracticas = new JButton("Eliminar Prácticas");
        btnEliminarPracticas.setEnabled(false);
        btnEliminarPracticas.addActionListener(e -> controlador.eliminarPracticas());

        btnModificar = new JButton("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(e -> controlador.mostrarModificarPracticas());

        JLabel lblTituloFecha = new JLabel("Fecha Límite");

        lblFechaLimite = new JLabel("");

        GroupLayout gl_contentPane = new GroupLayout(this);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblTitulo)
                                                .addPreferredGap(ComponentPlacement.RELATED, 541, Short.MAX_VALUE)
                                                .addComponent(lblTituloFecha)
                                                .addGap(18)
                                                .addComponent(lblFechaLimite)
                                                .addGap(65))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAsignar)
                                                .addGap(18)
                                                .addComponent(btnEliminarPracticas)
                                                .addGap(18)
                                                .addComponent(btnModificar)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(23)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblTitulo)
                                        .addComponent(lblTituloFecha)
                                        .addComponent(lblFechaLimite))
                                .addGap(40)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAsignar)
                                        .addComponent(btnEliminarPracticas)
                                        .addComponent(btnModificar))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(evento -> changed());

        scrollPane.setViewportView(table);
        setLayout(gl_contentPane);
    }

    private void changed() {
        btnModificar.setEnabled(!table.getSelectionModel().isSelectionEmpty());
        btnEliminarPracticas.setEnabled(!table.getSelectionModel().isSelectionEmpty());
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void cargarTablas() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        lblFechaLimite.setText(df.format(modelo.getFechaLimite()));
        table.setModel(modelo.getTablaPracticas());
        table.removeColumn(table.getColumnModel().getColumn(9));
        table.removeColumn(table.getColumnModel().getColumn(9));
        coloresTabla();
        for (int column = 0; column < table.getColumnCount(); column++) {
            final TableColumnModel columnModel = table.getColumnModel();
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300)
                width = 300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    private void coloresTabla() {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                Date hoy = new Date();
                Date fechaLimite = modelo.getFechaLimite();
                long diff = fechaLimite.getTime() - hoy.getTime();
                boolean anexo2 = (Boolean) table.getModel().getValueAt(row, 11);
                boolean anexo3 = (Boolean) table.getModel().getValueAt(row, 12);
                boolean anexo4 = (Boolean) table.getModel().getValueAt(row, 13);
                boolean anexo5 = (Boolean) table.getModel().getValueAt(row, 14);
                if (anexo2 && anexo3 && anexo4 && anexo5) {
                    setBackground(Color.GREEN);
                    setForeground(Color.BLACK);
                } else if (anexo2 || anexo3 || anexo4 || anexo5) {
                    setBackground(Color.ORANGE);
                    setForeground(Color.BLACK);
                } else if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) < 30L) {
                    setBackground(Color.RED);
                    setForeground(Color.WHITE);
                } else if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) < 45L) {
                    setBackground(Color.ORANGE);
                    setForeground(Color.BLACK);
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
                return this;
            }
        });
    }

    public int getFilaSeleccionada() {
        return table.getSelectedRow();
    }

    public String getNombreAlumnoSeleccionado() {
        return (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
    }

    public String getNombreEmpresaSeleccionada() {
        return (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
    }

    public String getTutorEmpresaSeleccionado() {
        return (String) table.getModel().getValueAt(table.getSelectedRow(), 2);
    }

    public Date getFechaInicioSeleccionada() {
        return (Date) table.getModel().getValueAt(table.getSelectedRow(), 3);
    }

    public Date getFechaFinSeleccionada() {
        return (Date) table.getModel().getValueAt(table.getSelectedRow(), 4);
    }

    public String getHorarioSeleccionado() {
        return (String) table.getModel().getValueAt(table.getSelectedRow(), 5);
    }

    public String getLocalizacionSeleccionada() {
        return (String) table.getModel().getValueAt(table.getSelectedRow(), 6);
    }

    public boolean getErasmusSeleccionada() {
        return (Boolean) table.getModel().getValueAt(table.getSelectedRow(), 7);
    }

    public String getEstadoSeleccionado() {
        return (String) table.getModel().getValueAt(table.getSelectedRow(), 8);
    }

    public int getNumMatSeleccionado() {
        int numMat;
        if (table.getModel().getValueAt(table.getSelectedRow(), 9) instanceof String) {
            numMat = Integer.parseInt((String) table.getModel().getValueAt(table.getSelectedRow(), 9));
        } else {
            numMat = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 9);
        }
        return numMat;
    }

    public int getNumConvSeleccionado() {
        int numMat;
        if (table.getModel().getValueAt(table.getSelectedRow(), 10) instanceof String) {
            numMat = Integer.parseInt((String) table.getModel().getValueAt(table.getSelectedRow(), 10));
        } else {
            numMat = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 10);
        }
        return numMat;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
