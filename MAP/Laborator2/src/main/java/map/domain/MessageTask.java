package map.domain;

import map.utils.Utils;

import java.time.LocalDateTime;

public class MessageTask extends Task{

    private String message;
    private String from;
    private String to;
    private LocalDateTime date;

    public MessageTask(String taskID, String description, String message, String from, String to, LocalDateTime date) {
        super(taskID,description);
        this.message = message;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setMessage(String newMessage) {
        this.message = newMessage;
    }

    public void setFrom(String newFrom) {
        this.from = newFrom;
    }

    public void setTo(String newTo) {
        this.to = newTo;
    }

    public void setDate(LocalDateTime newDate) {
        this.date = newDate;
    }

    public String getFormattedDate() {
        return date.format(Utils.dateFormatter);
    }

    @Override
    public void execute() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "id=" + getTaskID() + "|" +
                "description=" + getDescription() + "|" +
                "message=" + getMessage() + "|" +
                "from" + getFrom() + "|" +
                "to=" + getTo() + "|" +
                "date=" + getFormattedDate();
    }
}
