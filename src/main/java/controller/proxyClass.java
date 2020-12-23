package controller;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import models.Contact;
import models.Filter;
import models.Folder;
import models.Mail;
import models.Sort;

import java.io.*;
import java.util.LinkedList;

public class proxyClass implements IApp {
    App app = App.getInstance();

   
    public boolean signin(String email, String password) {

        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader("accounts\\email.json");
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
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonArray = (JSONArray) json.get("Accounts");
        for (Object jsonAccount : jsonArray) {
            //creating a JSONObjects
            jsonObject = (JSONObject) jsonAccount;
            if (jsonObject.get("Email").equals(email)) {
                if (jsonObject.get("Password").equals(password)){
                    String username = String.valueOf(jsonObject.get("Username"));
                    app.signin(email,password);
                    app.setUserName(username);
                    return true;
                }
                System.out.println("Wrong password!");
                return false;
            }
        }
        System.out.println("Wrong Email!");
        return false;
    }

   /////////////////////////////////////////////////////////////////////////////////////
    public boolean signup(Contact contact) {
        File accounts = new File("accounts");
        String ss = "accounts\\email.json";
        File mail = new File(ss);
        if (!accounts.exists()) {
            accounts.mkdir();
            try {
                accounts.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                mail.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        if (mail.length() == 0) {
            object.put("Accounts", jsonArray);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Email", contact.getEmail());
            jsonObject.put("Username", contact.getUsername());
            jsonObject.put("Password", contact.getPassword());
            jsonArray.add(jsonObject);
        } else {
            JSONParser jsonParser = new JSONParser();
            FileReader reader = null;
            try {
                reader = new FileReader("accounts\\email.json");
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
            JSONArray jsonAccounts = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonArray = (JSONArray) json.get("Accounts");
            for (Object jsonAccount : jsonArray) {
                //creating a JSONObjects
                jsonObject1 = (JSONObject) jsonAccount;
                if (jsonObject1.get("Email").equals(contact.getEmail())) {
                    System.out.println("Email is already exist !");
                    return false;
                }
            jsonAccounts.add(jsonObject1);
            }
            //saving new array
            jsonObject.put("Email", contact.getEmail());
            jsonObject.put("Username", contact.getUsername());
            jsonObject.put("Password", contact.getPassword());
            jsonAccounts.add(jsonObject);
            object.put("Accounts", jsonAccounts);
        }
            try {
                FileWriter fileWriter = new FileWriter("accounts\\email.json");
                fileWriter.write(object.toString());
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            app.signup(contact);
            return true;
        }


	public void setViewingOptions(Folder folder, Filter filter, Sort sort) {
		// TODO Auto-generated method stub
		
	}

	public Mail[] listEmails(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteEmails(LinkedList mails) {
		// TODO Auto-generated method stub
		
	}

	public void moveEmails(LinkedList mails, Folder des) {
		// TODO Auto-generated method stub
		
	}

	public boolean compose(Mail email) {
		// TODO Auto-generated method stub
		return false;
	}
}