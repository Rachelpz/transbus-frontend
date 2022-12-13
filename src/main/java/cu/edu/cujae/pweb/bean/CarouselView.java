package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.DriverDto;
import cu.edu.cujae.pweb.service.DriverServiceImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CarouselView implements Serializable {

    private List<DriverDto> drivers;

//    private List<ResponsiveOption> responsiveOptions;

    @Inject
    private DriverServiceImpl service;


    public void init() {
        drivers = service.getDrivers();
//        responsiveOptions = new ArrayList<>();
//        responsiveOptions.add(new ResponsiveOption("1024px", 3, 3));
//        responsiveOptions.add(new ResponsiveOption("768px", 2, 2));
//        responsiveOptions.add(new ResponsiveOption("560px", 1, 1));
    }

    public List<DriverDto> getDrivers() {
        return drivers;
    }

    public void setService(DriverServiceImpl service) {
        this.service = service;
    }

//    public List<ResponsiveOption> getResponsiveOptions() {
//        return responsiveOptions;
//    }

//    public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions) {
//        this.responsiveOptions = responsiveOptions;
//    }
}