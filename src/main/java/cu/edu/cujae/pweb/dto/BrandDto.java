package cu.edu.cujae.pweb.dto;

public class BrandDto {

    private String brand_id;
    private String brand_name;
    private Integer seats_numb;
    private String fuel_type;
    private Double fuel_consumtion;
    private String fuel;
    private boolean newRecord;


    public BrandDto() {
        super();
    }

    public BrandDto(String brand_id, String brand_name, Integer seats_numb, String fuel_type, Double fuel_consumtion, String fuel, boolean newRecord) {
        super();
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.seats_numb = seats_numb;
        this.fuel_type = fuel_type;
        this.fuel_consumtion = fuel_consumtion;
        this.fuel = fuel;
        this.newRecord = newRecord;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public Integer getSeats_numb() {
        return seats_numb;
    }

    public void setSeats_numb(Integer seats_numb) {
        this.seats_numb = seats_numb;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public Double getFuel_consumtion() {
        return fuel_consumtion;
    }

    public void setFuel_consumtion(Double fuel_consumtion) {
        this.fuel_consumtion = fuel_consumtion;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public boolean isNewRecord() {
        return newRecord;
    }

    public void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
    }
}
