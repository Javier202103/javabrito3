import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Aplicación de Colegiatura con Interfaz Gráfica (JFrame).
 */
public class CalculadoraColegiaturaGUI extends JFrame implements ActionListener {

    // 1. Declaramos los componentes de la interfaz
    private JLabel lblTipoAlumno, lblPromedio, lblMateriasReprobadas, lblResultado;
    private JTextField txtTipoAlumno, txtPromedio, txtMateriasReprobadas;
    private JButton btnCalcular;

    public CalculadoraColegiaturaGUI() {
        // Configuramos la ventana principal (JFrame)
        super("Calculadora de Colegiatura");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Usamos un JPanel para organizar los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // Usamos un GridLayout para organizar en filas y columnas
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 2. Creamos los componentes y los agregamos al panel
        lblTipoAlumno = new JLabel("Tipo de alumno (preparatoria/profesional):");
        txtTipoAlumno = new JTextField();
        panel.add(lblTipoAlumno);
        panel.add(txtTipoAlumno);

        lblPromedio = new JLabel("Promedio del alumno:");
        txtPromedio = new JTextField();
        panel.add(lblPromedio);
        panel.add(txtPromedio);

        lblMateriasReprobadas = new JLabel("Número de materias reprobadas:");
        txtMateriasReprobadas = new JTextField();
        // Inicialmente, este campo no es visible. Se mostrará si se necesita.
        lblMateriasReprobadas.setVisible(false);
        txtMateriasReprobadas.setVisible(false);
        panel.add(lblMateriasReprobadas);
        panel.add(txtMateriasReprobadas);

        // Creamos el botón y le asignamos un "escuchador de eventos" (ActionListener)
        btnCalcular = new JButton("Calcular Colegiatura");
        btnCalcular.addActionListener(this); // El "this" indica que esta misma clase maneja el evento del botón
        panel.add(btnCalcular);

        // Agregamos una etiqueta para mostrar el resultado
        lblResultado = new JLabel(" ");
        panel.add(lblResultado);

        // Agregamos el panel a la ventana
        this.add(panel);
    }

    // 3. Este método se ejecutará cuando el usuario haga clic en el botón
    @Override
    public void actionPerformed(ActionEvent e) {
        // Aquí movemos la lógica de tu método main
        try {
            // Leemos los datos desde los campos de texto
            String tipoAlumno = txtTipoAlumno.getText();
            double promedio = Double.parseDouble(txtPromedio.getText());

            int materiasReprobadas = 0;
            // Manejamos la visibilidad del campo de materias reprobadas
            if (tipoAlumno.equalsIgnoreCase("preparatoria") && promedio <= 7) {
                lblMateriasReprobadas.setVisible(true);
                txtMateriasReprobadas.setVisible(true);
                // Si el campo está visible, leemos su valor
                String reprobadasStr = txtMateriasReprobadas.getText();
                if (!reprobadasStr.isEmpty()) {
                    materiasReprobadas = Integer.parseInt(reprobadasStr);
                }
            } else {
                lblMateriasReprobadas.setVisible(false);
                txtMateriasReprobadas.setVisible(false);
            }

            // --- Lógica de cálculo (adaptada de tu código original) ---
            int unidadesACursar = 0;
            double porcentajeDescuento = 0.0;
            double costoTotal = 0.0;
            double costoPorCincoUnidades = 0.0;

            if (tipoAlumno.equalsIgnoreCase("preparatoria")) {
                costoPorCincoUnidades = 45000.00;
                if (promedio >= 9.5) {
                    unidadesACursar = 55;
                    porcentajeDescuento = 0.25;
                } else if (promedio >= 9) {
                    unidadesACursar = 50;
                    porcentajeDescuento = 0.10;
                } else if (promedio > 7) {
                    unidadesACursar = 50;
                    porcentajeDescuento = 0.0;
                } else {
                    if (materiasReprobadas <= 3) {
                        unidadesACursar = 45;
                    } else {
                        unidadesACursar = 40;
                    }
                    porcentajeDescuento = 0.0;
                }
            } else if (tipoAlumno.equalsIgnoreCase("profesional")) {
                costoPorCincoUnidades = 35000.00;
                unidadesACursar = 55;
                if (promedio >= 9.5) {
                    porcentajeDescuento = 0.20;
                } else {
                    porcentajeDescuento = 0.0;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error: El tipo de alumno no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cálculo final
            double subtotal = (unidadesACursar / 5.0) * costoPorCincoUnidades;
            double montoDescuento = subtotal * porcentajeDescuento;
            costoTotal = subtotal - montoDescuento;

            // Formateamos el resultado para mostrarlo
            NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "VE"));
            formatoMoneda.setMinimumFractionDigits(2);
            formatoMoneda.setMaximumFractionDigits(2);

            // 4. Mostramos el resultado en la etiqueta de la interfaz
            String resultadoTexto = "<html>El alumno podrá cursar: <b>" + unidadesACursar + "</b> unidades.<br>";
            if (porcentajeDescuento > 0) {
                resultadoTexto += "Se aplicó un descuento del: <b>" + (int)(porcentajeDescuento * 100) + "%</b><br>";
            } else {
                resultadoTexto += "No se aplicó ningún descuento.<br>";
            }
            resultadoTexto += "El total a pagar es: <b>" + formatoMoneda.format(costoTotal) + "</b></html>";

            lblResultado.setText(resultadoTexto);

        } catch (NumberFormatException ex) {
            // Manejo de error si el usuario ingresa texto en un campo numérico
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingrese valores numéricos válidos para el promedio y las materias reprobadas.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Este es el punto de entrada de la aplicación
        // Creamos una instancia de nuestra clase de la interfaz
        CalculadoraColegiaturaGUI gui = new CalculadoraColegiaturaGUI();
        gui.setVisible(true); // Hacemos que la ventana sea visible
    }
}