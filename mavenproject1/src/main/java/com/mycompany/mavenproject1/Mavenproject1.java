/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Libro {
    private String titulo;
    private String autor;
    private int añoPub;

    public Libro(String titulo, String autor, int añoPub) {
        this.titulo = titulo;
        this.autor = autor;
        this.añoPub = añoPub;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAñoPub() {
        return añoPub;
    }

    public void setAñoPub(int añoPub) {
        this.añoPub = añoPub;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + ", Autor: " + autor + ", Año de Publicacion: " + añoPub;
    }
}

public class Mavenproject1 {
    private static List<Libro> libros = new ArrayList<>();

    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Gestion de Libros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Crear un JTabbedPane para organizar las pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Pestaña 1: Agregar Libro
        JPanel panelAgregar = new JPanel(new GridLayout(4, 2));
        JTextField tituloField = new JTextField();
        JTextField autorField = new JTextField();
        JTextField añoField = new JTextField();
        JButton agregarButton = new JButton("Agregar Libro");

        panelAgregar.add(new JLabel("Titulo:"));
        panelAgregar.add(tituloField);
        panelAgregar.add(new JLabel("Autor:"));
        panelAgregar.add(autorField);
        panelAgregar.add(new JLabel("Año de Publicacion:"));
        panelAgregar.add(añoField);
        panelAgregar.add(new JLabel()); 
        panelAgregar.add(agregarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = tituloField.getText();
                String autor = autorField.getText();
                int añoPub = Integer.parseInt(añoField.getText());
                libros.add(new Libro(titulo, autor, añoPub));
                JOptionPane.showMessageDialog(frame, "Libro agregado exitosamente.");
                tituloField.setText("");
                autorField.setText("");
                añoField.setText("");
            }
        });

        // Pestaña 2: Mostrar Libros
        JPanel panelMostrar = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JButton mostrarButton = new JButton("Mostrar Libros");

        panelMostrar.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panelMostrar.add(mostrarButton, BorderLayout.SOUTH);

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (libros.isEmpty()) {
                    textArea.setText("No hay libros en la biblioteca.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Libro libro : libros) {
                        sb.append(libro.toString()).append("\n");
                    }
                    textArea.setText(sb.toString());
                }
            }
        });

        // Pestaña 3: Buscar por Autor
        JPanel panelBuscar = new JPanel(new BorderLayout());
        JTextField buscarAutorField = new JTextField();
        JButton buscarButton = new JButton("Buscar");
        JTextArea resultadoBusqueda = new JTextArea();
        resultadoBusqueda.setEditable(false);

        panelBuscar.add(buscarAutorField, BorderLayout.NORTH);
        panelBuscar.add(new JScrollPane(resultadoBusqueda), BorderLayout.CENTER);
        panelBuscar.add(buscarButton, BorderLayout.SOUTH);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String autor = buscarAutorField.getText();
                StringBuilder sb = new StringBuilder();
                boolean encontrado = false;
                for (Libro libro : libros) {
                    if (libro.getAutor().equalsIgnoreCase(autor)) {
                        sb.append(libro.toString()).append("\n");
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    sb.append("No se encontraron libros de ese autor.");
                }
                resultadoBusqueda.setText(sb.toString());
            }
        });

        // Pestaña 4: Modificar Año de Publicación
        JPanel panelModificar = new JPanel(new GridLayout(3, 2));
        JTextField modificarTituloField = new JTextField();
        JTextField nuevoAñoField = new JTextField();
        JButton modificarButton = new JButton("Modificar Año");

        panelModificar.add(new JLabel("Titulo del Libro:"));
        panelModificar.add(modificarTituloField);
        panelModificar.add(new JLabel("Nuevo Año de Publicacion:"));
        panelModificar.add(nuevoAñoField);
        panelModificar.add(new JLabel()); // Espacio vacío
        panelModificar.add(modificarButton);

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = modificarTituloField.getText();
                int nuevoAño = Integer.parseInt(nuevoAñoField.getText());
                boolean encontrado = false;
                for (Libro libro : libros) {
                    if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                        libro.setAñoPub(nuevoAño);
                        encontrado = true;
                        JOptionPane.showMessageDialog(frame, "Año de publicación actualizado exitosamente.");
                        break;
                    }
                }
                if (!encontrado) {
                    JOptionPane.showMessageDialog(frame, "Libro no encontrado.");
                }
            }
        });

        // Agregar las pestañas al JTabbedPane
        tabbedPane.addTab("Agregar Libro", panelAgregar);
        tabbedPane.addTab("Mostrar Libros", panelMostrar);
        tabbedPane.addTab("Buscar por Autor", panelBuscar);
        tabbedPane.addTab("Modificar Año", panelModificar);

        // Agregar el JTabbedPane a la ventana
        frame.add(tabbedPane);

        // Mostrar la ventana
        frame.setVisible(true);
    }
}