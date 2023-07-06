package com.gd.w2.todo.dto;
import java.time.LocalDate;
public class TodoDTO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private Long getTno(){
        return tno;
    }
    public void setTno(Long tno){
        this.tno = tno;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public LocalDate getDueDate(){
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }
    public boolean isFinished(){
        return finished;
    }
    public void setFinished(boolean finished){
        this.finished = finished;
    }
    @Override
    public String toString(){
        return "TodoDTO{" +
                "tno =" + tno +
                ", tilte='" + title +'\''+
                ", dueDate=" + dueDate +
                ", finished=" + finished +
                "}";
    }
}
