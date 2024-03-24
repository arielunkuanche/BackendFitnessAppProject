package springboot.dailyfitness.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="fitness_records")
public class FitnessRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;
    private String exerciseName;
    private int timeSpent;

    @ManyToOne
    @JoinColumn(name = "areaId")
    private Area area;

    
    // public FitnessRecord(){}
    // public FitnessRecord(long id, LocalDate date, String exerciseName, int timeSpent, Area area) {
    //     this.id = id;
    //     this.date = date;
    //     this.exerciseName = exerciseName;
    //     this.timeSpent = timeSpent;
    //     this.area = area;
    // }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getExerciseName() {
        return exerciseName;
    }
    public void setExerciseName(String exercise_name) {
        this.exerciseName = exercise_name;
    }
    public int getTimeSpent() {
        return timeSpent;
    }
    public void setTimeSpent(int time_spent) {
        this.timeSpent = time_spent;
    }

    public Area getArea() {
        return area;
    }
    public void setArea(Area area) {
        this.area= area;
    }

    // @Override
    // public String toString(){
    //     if(area != null)
    //         return "Fitness record: " + date + exerciseName + timeSpent + area.getAreaName();
    //     else
    //         return "Fitness record: " + date + exerciseName + timeSpent;
    // }

}
