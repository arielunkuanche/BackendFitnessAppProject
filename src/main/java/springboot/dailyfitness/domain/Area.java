package springboot.dailyfitness.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long areaId;
    private String areaName;

    @JsonIgnore 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private List<FitnessRecord> records;

    public Area() {
    }

    public Area(String areaName) {
        super();
        this.areaName = areaName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<FitnessRecord> getRecords() {
        return records;
    }

    public void setRecords(List<FitnessRecord> records) {
        this.records = records;
    }

}
