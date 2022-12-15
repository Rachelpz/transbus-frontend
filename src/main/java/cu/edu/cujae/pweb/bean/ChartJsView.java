package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.GroupDto;
import cu.edu.cujae.pweb.service.BrandServiceImpl;
import cu.edu.cujae.pweb.service.GroupServiceImpl;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.service.GroupService;
import cu.edu.cujae.pweb.service.BrandService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ChartJsView implements Serializable {

    private PieChartModel pieModel;
    private LineChartModel lineModel;
    private LineChartModel cartesianLinerModel;
    private BarChartModel barModel;

    private List<GroupDto> groups;
    private List<BrandDto> brands;


    @Autowired
    private GroupService groupService;

    @Autowired
    private BrandService brandService;

    public ChartJsView() {

    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //    Pie Model
    public PieChartModel getPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        //        Touristic Groups Paxamount
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();

        //        Touristic Groups Names
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();

        groups = groupService.getGroups();

        for (int i = 0; i < groups.size(); i++) {
            values.add(groups.get(i).getPaxamount());
            labels.add(groups.get(i).getGroup_name());
        }

        dataSet.setData(values);
        data.setLabels(labels);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgba(75, 192, 192)");
        bgColors.add("rgba(54, 162, 235)");
        bgColors.add("rgba(153, 102, 255)");
        bgColors.add("rgba(201, 203, 207)");
        bgColors.add("rgba(153, 99, 192)");
        bgColors.add("rgba(255, 162, 86)");
        bgColors.add("rgba(54, 205, 192)");
        bgColors.add("rgba(75, 162, 235)");
        bgColors.add("rgba(270, 163, 237)");
        bgColors.add("rgba(155, 203, 107)");
        bgColors.add("rgba(241, 133, 207)");
        bgColors.add("rgba(201, 103, 107)");
        dataSet.setBackgroundColor(bgColors);

        //Options
        PieChartOptions options = new PieChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Cantidad de pasajeros por grupo");
        options.setTitle(title);

        pieModel.setOptions(options);
        pieModel.setData(data);

        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    //    Line Model
    public LineChartModel getLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        this.brands = brandService.getBrands();

        for (int i = 0; i < brands.size(); i++) {
            values.add(brands.get(i).getSeats_numb());
            labels.add(brands.get(i).getBrand_name().trim());
        }

        dataSet.setData(values);
        dataSet.setFill(true);
        dataSet.setLabel("Número de asientos");
        dataSet.setBorderColor("rgb(75, 192, 192)");
//        dataSet.setTension(0.1);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Cantidad de asientos por marca");
        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);

        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    //    Bar Model
    public BarChartModel getBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Número de pasajeros");

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        this.groups = groupService.getGroups();

        for (int i = 0; i < groups.size(); i++) {
            values.add(groups.get(i).getPaxamount());
            labels.add(groups.get(i).getGroup_name().trim());
        }

        barDataSet.setData(values);
        data.setLabels(labels);
        barModel.setData(data);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132, 0.2)");
        bgColors.add("rgb(54, 162, 235, 0.2)");
        bgColors.add("rgb(255, 205, 86, 0.2)");
        bgColors.add("rgba(75, 192, 192, 0.2)");
        bgColors.add("rgba(54, 162, 235, 0.2)");
        bgColors.add("rgba(153, 102, 255, 0.2)");
        bgColors.add("rgba(201, 203, 207, 0.2)");
        bgColors.add("rgba(153, 99, 192, 0.2)");
        bgColors.add("rgba(255, 162, 86, 0.2)");
        bgColors.add("rgba(54, 205, 192, 0.2)");
        bgColors.add("rgba(75, 162, 235, 0.2)");
        bgColors.add("rgba(270, 163, 237, 0.2)");
        bgColors.add("rgba(155, 203, 107, 0.2)");
        bgColors.add("rgba(241, 133, 207, 0.2)");
        bgColors.add("rgba(201, 103, 107, 0.2)");
        barDataSet.setBackgroundColor(bgColors);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgba(75, 192, 192)");
        borderColor.add("rgba(54, 162, 235)");
        borderColor.add("rgba(153, 102, 255)");
        borderColor.add("rgba(201, 203, 207)");
        borderColor.add("rgba(153, 99, 192)");
        borderColor.add("rgba(255, 162, 86)");
        borderColor.add("rgba(54, 205, 192)");
        borderColor.add("rgba(75, 162, 235)");
        borderColor.add("rgba(270, 163, 237)");
        borderColor.add("rgba(155, 203, 107)");
        borderColor.add("rgba(241, 133, 207)");
        borderColor.add("rgba(201, 103, 107)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
//        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Cantidad de pasajeros por grupo");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
//        legendLabels.setFontStyle("italic");
//        legendLabels.setFontColor("#2980B9");
//        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);

        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

}