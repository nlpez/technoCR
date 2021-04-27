/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import gestion.ClienteGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import model.Conexion;
import model.GraficoGeneroCliente;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author wmolina
 */
@Named(value = "chartAnimatedView1")
@SessionScoped
public class ChartAnimatedView1 implements Serializable {

//    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private LocalDateTime fecha = LocalDateTime.now();
    Date date = new Date();

    SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
    int currentYear = Integer.parseInt(getYearFormat.format(date));

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }
//
//    public LineChartModel getAnimatedModel1() {
//        return animatedModel1;
//    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

//    private LineChartModel initLinearModel() {
//        LineChartModel model = new LineChartModel();
//
//        LineChartSeries series2 = new LineChartSeries();
//        LineChartSeries series1 = new LineChartSeries();
//
//        String label = "N/A";
//        String label1 = "N/A";
//
//        ArrayList<GraficoGeneroCliente> list = ClienteGestion.getGraficoGeneroCliente();
//
//        int mayor = list.get(0).getTotal();
//
//        ArrayList<String> genders = new ArrayList<>();
//
//        list.forEach(linea -> {
//            genders.add(linea.getGenero());
//        });
//
//        List<String> distinctGenders = genders.stream().distinct().collect(Collectors.toList());
//
//        for (String s : distinctGenders) {
//            if (s.equalsIgnoreCase("M")) {
//                label = "Masculino";
//            }
//            if (s.equalsIgnoreCase("F")) {
//                label1 = "Femenino";
//            }
//        }
//        series1.setLabel(label);
//        series2.setLabel(label1);
//
//        for (GraficoGeneroCliente row : list) {
//            if (row.getGenero().equalsIgnoreCase("M")) {
//                series1.set(currentYear, row.getTotal());
//            }
//            if (row.getGenero().equalsIgnoreCase("F")) {
//                series2.set(currentYear, row.getTotal());
//            }
//            if (mayor < row.getTotal()) {
//                mayor = row.getTotal();
//            }
//          
//        }
//        model.addSeries(series1);
//        model.addSeries(series2);
//        return model;
//    }
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries girls = new ChartSeries();
        ChartSeries boys = new ChartSeries();
        String label = "N/A";
        String label1 = "N/A";

        ArrayList<GraficoGeneroCliente> list = ClienteGestion.getGraficoGeneroCliente();

        int mayor = list.get(0).getTotal();

        ArrayList<String> genders = new ArrayList<>();

        list.forEach(linea -> {
            genders.add(linea.getGenero());
        });

        List<String> distinctGenders = genders.stream().distinct().collect(Collectors.toList());

        for (String s : distinctGenders) {
            if (s.equalsIgnoreCase("M")) {
                label = "Masculino";
            }
            if (s.equalsIgnoreCase("F")) {
                label1 = "Femenino";
            }
        }
        boys.setLabel(label);
        girls.setLabel(label1);

        for (GraficoGeneroCliente row : list) {
            if (row.getGenero().equalsIgnoreCase("M")) {
                boys.set(currentYear, row.getTotal());
            }
            if (row.getGenero().equalsIgnoreCase("F")) {
                girls.set(currentYear, row.getTotal());
            }
            if (mayor < row.getTotal()) {
                mayor = row.getTotal();
            }
            currentYear = currentYear + 1000;
        }
        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private void createAnimatedModels() {
//        animatedModel1 = initLinearModel();
//        animatedModel1.setTitle("Ingreso de clientes");
//        animatedModel1.setAnimate(true);
//        animatedModel1.setLegendPosition("se");
//
//        yAxis.setMin(1);
//        yAxis.setMax(30);
//        xAxis.setMin(currentYear);
//        xAxis.setMax(currentYear);

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Ingreso de Clientes");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        Axis xAxis = animatedModel2.getAxis(AxisType.X);
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }

}
