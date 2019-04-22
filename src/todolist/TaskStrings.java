package todolist;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
public class TaskStrings {
	private int priority;
	private String description;
	private String dueDate;
	private String statusDate;
	private String status;
	
	public TaskStrings(Task input) {
		this.priority = input.getPriority();
		this.description = input.getDescription();
		this.dueDate = input.getDueDateString();
		this.statusDate = input.getStatusDateString();
		this.status = input.getStatus();
	}
	public int getPriority() {
		return priority;
	}
	public String getDescription() {
		return description;
	}
	public String getDueDate() {
		return dueDate;
	}
	public String getStatusDate() {
		return statusDate;
	}
	public String getStatus() {
		return status;
	}
	
}
