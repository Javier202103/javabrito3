package descuentosteatrogui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DescuentosTeatroFrame extends JFrame {
    // Campos de texto para ingresar personas
    private JTextField[] campos = new JTextField[5];
    private JTextArea areaResultado;
    private JButton botonCalcular;
    private JButton botonLimpiar;


    // Datos fijos
    private final double[] descuentos = {0.35, 0.25, 0.10, 0.25, 0.35};
    private final double precioEntrada = 100.0;

    public DescuentosTeatroFrame() {
        setTitle("Cálculo de Descuentos - Teatro");
        setSize(450, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Etiquetas de las categorías
        String[] categorias = {
            "Categoría 1 (5 a 14 años):",
            "Categoría 2 (15 a 19 años):",
            "Categoría 3 (20 a 45 años):",
            "Categoría 4 (46 a 65 años):",
            "Categoría 5 (66 en adelante):"
        };

        // Crear etiquetas y campos
        for (int i = 0; i < 5; i++) {
            JLabel etiqueta = new JLabel(categorias[i]);
            etiqueta.setBounds(30, 20 + i * 35, 200, 25);
            add(etiqueta);

            campos[i] = new JTextField();
            campos[i].setBounds(250, 20 + i * 35, 100, 25);
            add(campos[i]);
        }

        // Botón Calcular
        botonCalcular = new JButton("Calcular");
        botonCalcular.setBounds(150, 200, 120, 30);
        add(botonCalcular);
        
        
        

        // Área de resultados
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBounds(30, 250, 380, 100);
        add(scroll);

        // Evento del botón
        botonCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularPerdidas();
            }
        });
    }
    
    

    // Método para calcular
    private void calcularPerdidas() {
        try {
            int[] personas = new int[5];
            double[] perdidas = new double[5];
            double total = 0;
            areaResultado.setText("");

            for (int i = 0; i < 5; i++) {
                personas[i] = Integer.parseInt(campos[i].getText());
                perdidas[i] = personas[i] * precioEntrada * descuentos[i];
                total += perdidas[i];
                areaResultado.append("Categoría " + (i + 1) + ": $" + String.format("%.2f", perdidas[i]) + "\n");
            }

            areaResultado.append("------------------------\n");
            areaResultado.append("TOTAL PERDIDO: $" + String.format("%.2f", total));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese solo números enteros válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
