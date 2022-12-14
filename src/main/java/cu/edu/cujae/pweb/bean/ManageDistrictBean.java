package cu.edu.cujae.pweb.bean;


import cu.edu.cujae.pweb.dto.DistrictDto;
import cu.edu.cujae.pweb.dto.DriverDto;
import cu.edu.cujae.pweb.service.DistrictService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class ManageDistrictBean {
    private DistrictDto districtDto;
    private DistrictDto selectedDistrict;
    private List<DistrictDto> districts;
    private List<DriverDto> driversById;
    private Integer districtId;

    @Autowired
    private DistrictService districtService;

    public ManageDistrictBean() {

    }

    public void openNew() {
        this.selectedDistrict = new DistrictDto();
    }

    public void openForEdit() {

    }

    public void saveDistrict() {
        if (this.selectedDistrict.getDistrict_id() == null) {
            this.districtService.createDistrict(this.selectedDistrict);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_district_added");
        } else {
            this.districtService.updateDistrict(this.selectedDistrict);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_district_edited");
        }

        this.districts = districtService.getDistricts();

        PrimeFaces.current().executeScript("PF('manageDistrictDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-districts");
    }

    public void deleteDistrict() {
        try {
            this.districtService.deleteDistrict(this.selectedDistrict.getDistrict_id());
            this.selectedDistrict = null;

            this.districts = districtService.getDistricts();

            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_district_removed");
            PrimeFaces.current().ajax().update("form:dt-districts");
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
        }
    }

    public DistrictDto getDistrictDto() {
        return districtDto;
    }

    public void setDistrictDto(DistrictDto districtDto) {
        this.districtDto = districtDto;
    }

    public DistrictDto getSelectedDistrict() {
        return selectedDistrict;
    }

    public void setSelectedDistrict(DistrictDto selectedDistrict) {
        this.selectedDistrict = selectedDistrict;
    }


    public List<DistrictDto> getDistricts() {
        this.districts = districtService.getDistricts();
        return this.districts;
    }

    public void setDistricts(List<DistrictDto> districts) {
        this.districts = districts;
    }

    public DistrictService getDistrictService() {
        return districtService;
    }

    public void setDistrictService(DistrictService districtService) {
        this.districtService = districtService;
    }

    public List<DriverDto> getDriversById(Integer districtId) {
        this.driversById = districtService.getDriversByIdDistrict(districtId);
        return this.driversById;
    }

    public void setDriversById(List<DriverDto> driversById) {
        this.driversById = driversById;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer driverId) {
        this.districtId = driverId;
    }
}
