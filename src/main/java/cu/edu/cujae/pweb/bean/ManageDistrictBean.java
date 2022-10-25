package cu.edu.cujae.pweb.bean;


import cu.edu.cujae.pweb.dto.DistrictDto;
import cu.edu.cujae.pweb.service.DistrictService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;
import java.util.UUID;

@Component
@ManagedBean
@ViewScoped
public class ManageDistrictBean {
    private DistrictDto districtDto;
    private DistrictDto selectedDistrict;
    private List<DistrictDto> districts;

    @Autowired
    private DistrictService districtService;

    public ManageDistrictBean() {
    }

    @PostConstruct
    public void init() {
        districts = districts == null ? districtService.getDistricts() : districts;

    }

    public void openNew() {

        this.selectedDistrict = new DistrictDto();
    }

    public void openForEdit() {

    }

    public void saveDistrict() {
        if (this.selectedDistrict.getDistrict_id() == null) {
            this.selectedDistrict.setDistrict_id(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.selectedDistrict.setNewRecord(true);


            this.districts.add(this.selectedDistrict);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_district_added");
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_district_edited");
        }

        PrimeFaces.current().executeScript("PF('manageDistrictDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-districts");
    }

    public void deleteDistrict() {
        try {
            this.districts.remove(this.selectedDistrict);
            this.selectedDistrict = null;
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
        return districts;
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
}
