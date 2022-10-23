package cu.edu.cujae.pweb.dto;

public class FuelDto {
    private String fuel_id;
    private String fuel_name;
    private boolean newRecord;

    public FuelDto() {
        super();
    }

    public FuelDto(String fuel_id, String fuel_name, boolean newRecord) {
        super();
        this.fuel_id = fuel_id;
        this.fuel_name = fuel_name;
        this.newRecord = newRecord;
    }

    public String getFuel_id() {
        return fuel_id;
    }

    public void setFuel_id(String fuel_id) {
        this.fuel_id = fuel_id;
    }

    public String getFuel_name() {
        return fuel_name;
    }

    public void setFuel_name(String fuel_name) {
        this.fuel_name = fuel_name;
    }

    public boolean isNewRecord() {
        return newRecord;
    }

    public void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
    }

}
