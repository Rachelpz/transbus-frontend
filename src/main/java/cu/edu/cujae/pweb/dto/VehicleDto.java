package cu.edu.cujae.pweb.dto;

public class VehicleDto {

    private String vehicle_id;
    private String plate_numb;
    private Float planned_fuel;
    private String first_driver;
    private String second_driver;
    private String brand;
    private boolean newRecord;


    public VehicleDto() {
        super();
    }

    public VehicleDto(String vehicle_id, String plate_numb, Float planned_fuel, String first_driver, String second_driver, String brand, boolean newRecord) {
        super();
        this.vehicle_id = vehicle_id;
        this.plate_numb = plate_numb;
        this.planned_fuel = planned_fuel;
        this.first_driver = first_driver;
        this.second_driver = second_driver;
        this.brand = brand;
        this.newRecord = newRecord;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getPlate_numb() {
        return plate_numb;
    }

    public void setPlate_numb(String plate_numb) {
        this.plate_numb = plate_numb;
    }

    public Float getPlanned_fuel() {
        return planned_fuel;
    }

    public void setPlanned_fuel(Float planned_fuel) {
        this.planned_fuel = planned_fuel;
    }

    public String getFirst_driver() {
        return first_driver;
    }

    public void setFirst_driver(String first_driver) {
        this.first_driver = first_driver;
    }

    public String getSecond_driver() {
        return second_driver;
    }

    public void setSecond_driver(String second_driver) {
        this.second_driver = second_driver;
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
