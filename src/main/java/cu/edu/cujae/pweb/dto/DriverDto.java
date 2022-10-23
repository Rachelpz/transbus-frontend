package cu.edu.cujae.pweb.dto;

public class DriverDto {
    private String driver_id;
    private String driver_name;
    private String dni;
    private String address;
    private String phone_number;
    private String district;
    private String brand;
    private boolean newRecord;

    public DriverDto() {
        super();
    }

    public DriverDto(String driver_id, String driver_name, String dni, String address, String phone_number, String district, String brand, boolean newRecord) {
        this.driver_id = driver_id;
        this.driver_name = driver_name;
        this.dni = dni;
        this.address = address;
        this.phone_number = phone_number;
        this.district = district;
        this.brand = brand;
        this.newRecord = newRecord;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isNewRecord() {
        return newRecord;
    }

    public void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
    }
}
