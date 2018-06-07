package view;

/**
 * Clase para contener vistas junto a su título para su uso en JTabbedPane
 * @author Los Juleniars
 */
public class ListaVistas {

    private String titulo;
    private Vista vista;

    /**
     * Constructor de la clase
     *
     * @param titulo El título que aparecerá en el JTabbedPane
     * @param vista  El objeto vista correspondiente
     */
    public ListaVistas(String titulo, Vista vista) {
        this.titulo = titulo;
        this.vista = vista;
    }

    /**
     * @return El título de la vista
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return La vista
     */
    public Vista getVista() {
        return vista;
    }
}
