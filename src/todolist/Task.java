package todolist;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
public class Task {
	private int priority;
	private String description;
	private Date dueDate;
	private Date statusDate;
	private String status;
	public Task(int priority, String description, Date dueDate, Date statusDate, String status) {
		this.priority = priority;
		this.description = description;
		this.dueDate = dueDate;
		this.statusDate = statusDate;
		this.status = status;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPriority() {
		return priority;
	}
	public String getDescription() {
		return description;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public Date getStatusDate() {
		return statusDate;
	}
	public String getStatus() {
		return status;
	}
	public String toPrintFormat() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String taskString = "";
		String dueDateString = dateFormat.format(dueDate);
		String statusDateString;
		taskString += "Priority:\t\t" + priority + "\n";
		taskString += "Description:\t"  + description + "\n";
		taskString += "Due Date:\t\t" + dueDateString + "\n";
		if(status.equals("Complete")) {
			statusDateString = dateFormat.format(statusDate);
			taskString += "Date Finished:\t" + statusDateString + "\n";
		}else if(status.equals("In Progress")) {
			statusDateString = dateFormat.format(statusDate);
			taskString += "Date Started:\t" + statusDateString + "\n";
		}
		taskString += "Status:\t\t\t" + status + "\n";
		return taskString;
	}
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String dueDateString = dateFormat.format(dueDate);
		String output = "";
		output += priority + ",";
		output += description + ",";
		output += dueDateString + ",";
		if(statusDate != null) {
			String statusDateString = dateFormat.format(statusDate);
			output += statusDateString;
		}
		output += ",";
		output += status;
		return output;
	}
	public static void sort(ArrayList<Task> taskList, String sortBy) {
		Collections.sort(taskList, new Comparator<Task>() {
			public int compare(Task taskOne, Task taskTwo) {
				if(sortBy == "Priority") {
					int priority1 = taskOne.getPriority();
					int priority2 = taskTwo.getPriority();
					return Integer.compare(priority1, priority2);
				}else if(sortBy == "Description") {
					String description1 = taskOne.getDescription();
					String description2 = taskTwo.getDescription();
					return description1.compareTo(description2);
				}else {
					Date dueDate1 = taskOne.getDueDate();
					Date dueDate2 = taskTwo.getDueDate();
					return dueDate1.compareTo(dueDate2);
				}
			}
		});
	}
	public static void addTask(ArrayList<Task> taskList, int priority, String description, Date dueDate, Date statusDate, String status) {
		Task createdTask = new Task(priority, description, dueDate, statusDate, status);
		taskList.add(createdTask);
	}
	public static void editTask(Task task,int priority, String description, Date dueDate, Date statusDate, String status ) {
		task.setPriority(priority);
		task.setDescription(description);
		task.setDueDate(dueDate);
		task.setStatusDate(statusDate); 	
		task.setStatus(status);
	}
	public static void deleteTask(Task task, ArrayList<Task> taskList) {
		int taskIndex = taskList.indexOf(task);
		int priority = taskList.get(taskIndex).getPriority();
		taskList.remove(taskIndex);
		for(Task iteratingTask: taskList) {
			int iteratingPriority = iteratingTask.getPriority();
			if(iteratingPriority > priority) {
				iteratingTask.setPriority(iteratingPriority - 1 );
			}
		}
	}
	public static ArrayList<Task> loadFile() {
		ArrayList<Task> taskList = new ArrayList<Task>();
		File file = new File("savefile.txt");
		boolean fileExists = file.exists();
		if(fileExists) {
			FileReader fileReader;
			BufferedReader bufferedReader;
			try {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
				String line = bufferedReader.readLine();
				while(line != null && line.length() != 0) {
					String[] task = line.split(",");
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					int priority = Integer.parseInt(task[0]);
					String description = task[1];
					Date dueDate = dateFormat.parse(task[2]);
					Date statusDate = null;
					if(task[3].length()!= 0) {
						statusDate = dateFormat.parse(task[3]);
					}
					String status = task[4];
					Task loadedTask = new Task(priority, description, dueDate, statusDate, status);
					taskList.add(loadedTask);
					line = bufferedReader.readLine();
				}
				bufferedReader.close();
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return taskList;
	}
	public static void saveFile(ArrayList<Task> taskList) {
		File file = new File("saveFile.txt");
		try {
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			String output = "";
			for(Task task: taskList) {
				output += task.toString() + "\n";
			}
			bufferedWriter.write(output);
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void savePrintFile(ArrayList<Task> taskList) {
		File file = new File("printFile.txt");
		try {
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			String output = "";
			for(Task task: taskList) {
				output += task.toPrintFormat();
				output += "\n";
			}
			bufferedWriter.write(output);
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
