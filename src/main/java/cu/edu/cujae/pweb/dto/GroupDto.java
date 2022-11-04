package cu.edu.cujae.pweb.dto;

public class GroupDto {

    private Integer group_id;
    private String group_name;
    private Integer paxamount;
    private String country_name;
    private boolean newRecord;


    public GroupDto() {
        super();
    }

    public GroupDto(Integer group_id, String group_name, Integer paxamount, String country_name, boolean newRecord) {
        super();
        this.group_id = group_id;
        this.group_name = group_name;
        this.paxamount = paxamount;
        this.country_name = country_name;
        this.newRecord = newRecord;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Integer getPaxamount() {
        return paxamount;
    }

    public void setPaxamount(Integer paxamount) {
        this.paxamount = paxamount;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public boolean isNewRecord() {
        return newRecord;
    }

    public void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
    }
}
