package cu.edu.cujae.pweb.dto;

import java.util.Date;

public class RequestDto {
    private Integer request_id;
    private Date date;
    private String petitioner_name;
    private String group;
    private String service;
    private String vehicle;
    private String request_name;
    private boolean newRecord;

    public RequestDto() {
        super();
    }

    public RequestDto(Integer request_id, Date date, String petitioner_name, String group, String service, String vehicle, String request_name, boolean newRecord) {
        this.request_id = request_id;
        this.date = date;
        this.petitioner_name = petitioner_name;
        this.group = group;
        this.service = service;
        this.vehicle = vehicle;
        this.request_name = request_name;
        this.newRecord = newRecord;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPetitioner_name() {
        return petitioner_name;
    }

    public void setPetitioner_name(String petitioner_name) {
        this.petitioner_name = petitioner_name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getRequest_name() {
        return request_name;
    }

    public void setRequest_name(String request_name) {
        this.request_name = request_name;
    }

    public boolean isNewRecord() {
        return newRecord;
    }

    public void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
    }
}