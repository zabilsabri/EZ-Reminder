import java.time.LocalDate;

public class App {

    private String name;
    private LocalDate deadline;
    private String details;

    public App(String name, LocalDate deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String details){
        this.details = details;
    }

}