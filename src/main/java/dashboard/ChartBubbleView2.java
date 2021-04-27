/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import gestion.ArticuloGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.BrandPrice;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;

/**
 *
 * @author wmolina
 */
@Named(value = "chartBubbleView2")
@SessionScoped
public class ChartBubbleView2 implements Serializable {

    private BubbleChartModel bubbleModel2;

    public BubbleChartModel getBubbleModel2() {
        return bubbleModel2;
    }

    @PostConstruct
    public void init() {
        createBubbleModels();
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void createBubbleModels() {
        bubbleModel2 = new BubbleChartModel();

        ArrayList<BrandPrice> list = ArticuloGestion.getBrandPrice();

        for (BrandPrice s : list) {
            bubbleModel2.add(new BubbleChartSeries(s.getBrand(), s.getPrice(), s.getTotal(), s.getTotal()));

        }

        bubbleModel2.setTitle("Ejemplo 2");
        bubbleModel2.setShadow(false);
        bubbleModel2.setBubbleGradients(true);
        bubbleModel2.setBubbleAlpha(0.8);
        bubbleModel2.getAxis(AxisType.X).setTickAngle(-50);
        Axis yAxis = bubbleModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Labels");
        yAxis.setMin(0);
        yAxis.setMax(100);
        yAxis.setTickAngle(50);

    }
}
