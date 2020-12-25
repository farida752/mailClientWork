package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import datastructures.Priority_Queue;
import datastructures.Queue;

public class Mail {
	
	private String subject;
	private String sender;
	private String date;
	private String priority;
	private String content;
	private LinkedList attachments ;
	private Queue recievers;
	
	
	
	
	public Mail(String subject, String sender, String date, String priority, String content, LinkedList attachments,
			Queue recievers) {
		super();
		this.subject = subject;
		this.sender = sender;
		this.date = date;
		this.priority = priority;
		this.content = content;
		this.attachments = attachments;
		this.recievers = recievers;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LinkedList getAttachments() {
		return attachments;
	}
	public void setAttachments(LinkedList attachments) {
		this.attachments = attachments;
	}
	public Queue getRecievers() {
		return recievers;
	}
	public void setRecievers(datastructures.Queue listOfRecievers) {
		this.recievers = listOfRecievers;
	}

	@Override
	public String toString() {
		return "Mail [subject=" + subject + ", sender=" + sender + ", date=" + date + ", priority=" + priority
				+ ", content=" + content + ", attachments=" + attachments + ", recievers=" + recievers + "]";
	}
	
	public void sendMailToInbox() {
		if (subject.trim().isEmpty()) {
			subject = " ";

		}
		// to receivers

		while (recievers.size() != 0) {
			String receiver = (String) recievers.dequeue();
			String path = "accounts//" + receiver + "//Inbox//index.json";
			File index = new File(path);

			JSONArray jsonArray = new JSONArray();
			JSONObject object = new JSONObject();
			if (index.length() == 0) {
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("Subject", subject);
				jsonObject.put("Sender", sender);
				jsonObject.put("Date", date);
				jsonObject.put("Priority", priority);
				object.put("Mails", jsonArray);
				jsonArray.add(jsonObject);
			} else {
				JSONParser jsonParser = new JSONParser();
				FileReader reader = null;
				try {
					reader = new FileReader(path);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				JSONObject json = null;
				try {
					json = (JSONObject) jsonParser.parse(reader);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				JSONArray jsonInboxMails = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				JSONObject jsonObject1 = new JSONObject();
				jsonArray = (JSONArray) json.get("Mails");
				for (Object jsonMails : jsonArray) {
					jsonObject1 = (JSONObject) jsonMails;
					jsonInboxMails.add(jsonObject1);
				}
				// saving new array
				jsonObject.put("Subject", subject);
				jsonObject.put("Sender", sender);
				jsonObject.put("Date", date);
				jsonObject.put("Priority", priority);
				jsonInboxMails.add(jsonObject);
				object.put("Mails", jsonInboxMails);
			}
			try {
				FileWriter fileWriter = new FileWriter(path);
				fileWriter.write(object.toString());
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			File re = new File("accounts//" + receiver + "//Inbox" + "//" + sender + subject + date);
			File se = new File("accounts//" + sender + "//sent" + "//" + sender + subject + date);

			copyFolder(se, re);

			path = "accounts//" + receiver + "//Inbox//" + sender + subject + date + "//message.json";
			JSONParser jsonParser = new JSONParser();
			FileReader reader = null;
			try {
				reader = new FileReader(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			JSONObject json = null;
			try {
				json = (JSONObject) jsonParser.parse(reader);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			JSONObject jsonObj1 = new JSONObject();
			JSONObject jsonObject1 = new JSONObject();
			
			jsonObj1 = (JSONObject) json.get("Message");
			String str = (String) jsonObj1.get("Content");
			object.clear();
			object.put("Content", str);
			object.put("Receivers", receiver);
			JSONObject JSONToAdd=new JSONObject();
			JSONToAdd.put("Message",object);
			try {
				FileWriter fileWriter = new FileWriter(path);
				fileWriter.write(JSONToAdd.toString());
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			addReceiverToContact(receiver);
		}
	}

	public void sendMailToSentbox() {
		// to sender
		if (subject.trim().isEmpty()) {
			subject = " ";

		}
		String mailName = sender + subject + date;// mail name
		String path = "accounts//" + sender + "//sent//" + mailName;

		File mail = new File(path);
		mail.mkdirs();

		path = path + "//message.json";
		File messageFile = new File(path);
		try {
			messageFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// write the message
		JSONArray array = new JSONArray();
		JSONArray jsonArray = new JSONArray();
		JSONObject object = new JSONObject();
		//object.put("Message", jsonArray);
		JSONObject jsonObject = new JSONObject();
		//jsonObject.put("Content", content);
		Queue n = new Queue();

		while (!recievers.isEmpty()) {
			String s = (String) recievers.dequeue();
			array.add(s);
			n.enqueue(s);
		}
		//recievers = n;
		while(n.isEmpty()==false) {
			recievers.enqueue(n.dequeue());
		}
		//jsonObject.put("Receivers", array);
		JSONObject newObj=new JSONObject();
		newObj.put("Content",content);
		newObj.put("Receivers",array);
		JSONObject JSONObjectToAdd=new JSONObject();
		JSONObjectToAdd.put("Message",newObj );
		try {
			FileWriter fileWriter = new FileWriter(path);
			fileWriter.write(JSONObjectToAdd.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		path = "accounts//" + sender + "//sent//" + mailName + "//attachments";
		File attachmentsFile = new File(path);
		attachmentsFile.mkdirs();

		path = path + "//list.json";
		File attachList = new File(path);

		try {
			attachList.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jsonArray.clear();
		object.clear();

		object.put("Attachments", jsonArray);
		while (!attachments.isEmpty()) {
			jsonArray.add(attachments.poll());
		}
		try {
			FileWriter fileWriter = new FileWriter(path);
			fileWriter.write(object.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		path = "accounts//" + sender + "//sent//index.json";
		File index = new File(path);

		jsonArray.clear();
		object.clear();
		jsonObject.clear();

		if (index.length() == 0) {
			object.put("Mails", jsonArray);
			jsonObject.put("Subject", subject);
			jsonObject.put("Sender", sender);
			jsonObject.put("Date", date);
			jsonObject.put("Priority", priority);
			jsonArray.add(jsonObject);
		} else {
			JSONParser jsonParser = new JSONParser();
			FileReader reader = null;
			try {
				reader = new FileReader(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			JSONObject json = null;
			try {
				json = (JSONObject) jsonParser.parse(reader);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			JSONArray jsonSentMails = new JSONArray();
			JSONObject jsonObject1 = new JSONObject();
			jsonArray = (JSONArray) json.get("Mails");
			if(jsonArray!=null) {
			for (Object jsonMails : jsonArray) {
				// creating a JSONObjects
				jsonObject1 = (JSONObject) jsonMails;
				jsonSentMails.add(jsonObject1);
			}
			}
			// saving new array
			jsonObject.put("Subject", subject);
			jsonObject.put("Sender", sender);
			jsonObject.put("Date", date);
			jsonObject.put("Priority", priority);
			jsonSentMails.add(jsonObject);
			object.put("sentMails", jsonSentMails);
		}
		try {
			FileWriter fileWriter = new FileWriter(path);
			fileWriter.write(object.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addReceiverToContact(String receiver) {
		String path = "accounts//" + sender + "//contacts.json";
		File contactsFile = new File(path);
		JSONObject object = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if (contactsFile.length() == 0) {
			jsonArray.add(receiver);
			object.put("Contacts", jsonArray);
		} else {
			JSONParser jsonParser = new JSONParser();
			FileReader reader = null;
			try {
				reader = new FileReader(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			JSONObject json = null;
			try {
				json = (JSONObject) jsonParser.parse(reader);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			jsonArray.clear();
			object.clear();
			jsonArray = (JSONArray) json.get("Contacts");
			boolean found = false;
			if(jsonArray!=null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				if (jsonArray.get(i).equals(receiver)) {
					found = true;
					break;
				}
			}}
			if (found == false) {
				jsonArray.add(receiver);
				object.put("Contacts", jsonArray);
			}
		}

		try {
			FileWriter fileWriter = new FileWriter(path);
			fileWriter.write(object.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMailToDraft() {
		if (subject.trim().isEmpty()) {
			subject = " ";

		}
		String mailName = sender + subject + date;
		String path = "accounts//" + sender + "//Drafts//" + mailName;

		File mail = new File(path);
		mail.mkdirs();

		path = path + "\\message.json";
		// write the message
		JSONArray array = new JSONArray();
		JSONArray jsonArray = new JSONArray();
		JSONObject object = new JSONObject();
		object.put("Message", jsonArray);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Content", content);
		while (!recievers.isEmpty()) {
			array.add(recievers.dequeue());
		}
		jsonObject.put("Receivers", array);
		jsonArray.add(jsonObject);
		try {
			FileWriter fileWriter = new FileWriter(path);
			fileWriter.write(object.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// attachments

		path = "accounts//" + sender + "//Drafts//" + mailName + "//attachments//list.json";
		File attachList = new File(path);

		try {
			attachList.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jsonArray.clear();
		object.clear();

		object.put("Attachments", jsonArray);
		while (!attachments.isEmpty()) {
			jsonArray.add(attachments.poll());
		}
		try {
			FileWriter fileWriter = new FileWriter(path);
			fileWriter.write(object.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// index file

		path = "accounts//" + sender + "//Drafts//index.json";
		File index = new File(path);

		jsonArray.clear();
		object.clear();
		jsonObject.clear();

		if (index.length() == 0) {
			object.put("Mails", jsonArray);
			jsonObject.put("Subject", subject);
			jsonObject.put("Sender", sender);
			jsonObject.put("Date", date);
			jsonObject.put("Priority", priority);
			jsonArray.add(jsonObject);
		} else {
			JSONParser jsonParser = new JSONParser();
			FileReader reader = null;
			try {
				reader = new FileReader(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			JSONObject json = null;
			try {
				json = (JSONObject) jsonParser.parse(reader);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			JSONArray jsonDraftMails = new JSONArray();
			JSONObject jsonObject1 = new JSONObject();
			jsonArray = (JSONArray) json.get("Mails");
			for (Object jsonMails : jsonArray) {
				jsonObject1 = (JSONObject) jsonMails;
				jsonDraftMails.add(jsonObject1);
			}
			// saving new array
			jsonObject.put("Subject", subject);
			jsonObject.put("Sender", sender);
			jsonObject.put("Date", date);
			jsonObject.put("Priority", priority);
			jsonDraftMails.add(jsonObject);
			object.put("Mails", jsonDraftMails);
		}
		try {
			FileWriter fileWriter = new FileWriter(path);
			fileWriter.write(object.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void deleteMailFromDarfts() {
		File f = new File("accounts//" + sender + "//Drafts//" + sender + subject + date);
		deleteFile(f);
	}

	public static void deleteFile(File element) {
		if (element.isDirectory()) {
			for (File sub : element.listFiles()) {
				deleteFile(sub);
			}
		}
		element.delete();
	}

	public static void copyFolder(File source, File destination) {
		if (source.isDirectory()) {
			if (!destination.exists()) {
				destination.mkdirs();
			}

			String files[] = source.list();

			for (String file : files) {
				File srcFile = new File(source, file);
				File destFile = new File(destination, file);

				copyFolder(srcFile, destFile);
			}
		} else {
			InputStream in = null;
			OutputStream out = null;

			try {
				in = new FileInputStream(source);
				out = new FileOutputStream(destination);

				byte[] buffer = new byte[1024];

				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
				in.close();
				out.close();
			} catch (Exception e) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
