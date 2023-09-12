package com.MedRev.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import com.packages.common.Config;

public class GmailUtility {

	public static String gmailUtils(String username, String password, String server, int emailFolder) throws Exception {
		String foundUrl = "";
		// create properties
		Properties properties = new Properties();

		properties.put("mail.imap.host", Config.getGAMILHost());
		properties.put("mail.imap.port", Config.getGMAILPort());
		properties.put("mail.imap.starttls.enable", "true");
		properties.put("mail.imap.ssl.trust", Config.getGAMILHost());

		Session emailSession = Session.getDefaultInstance(properties);

		// create the imap store object and connect to the imap server
		Store store = emailSession.getStore("imaps");

		store.connect(Config.getGAMILHost(), username, password);

		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);
		System.out.println("Total Messages:" + folder.getMessageCount());
		System.out.println("Unread Messages:" + folder.getUnreadMessageCount());

		Message[] messages = folder.getMessages();

		for (int i = messages.length - 1; i > 0; i--) {
			if (!messages[i].isSet(Flag.SEEN)) {
				/*
				 * System.out.println(messages[i].getSubject()); System.out.println("Body: \n" +
				 * getTextFromMessage(messages[i]));
				 */
				String link = getTextFromMessage(messages[i]);
				Pattern p = Pattern.compile("\\b(http|https)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",
						Pattern.CASE_INSENSITIVE);
				Matcher matcher = p.matcher(link);

				while (matcher.find()) {
					int matchStart = matcher.start(1);
					int matchEnd = matcher.end();
					foundUrl = link.substring(matchStart, matchEnd);
					if (foundUrl.contains("api-test.medrevup.com/survey")) {
						messages[i].setFlag(Flag.SEEN, true); // Todo Make TRUE in final code
						return foundUrl;
					}
				}
			}

		}
		return foundUrl;
	}

	/**
	 * Function to return Email Subject Respond By Email
	 * 
	 * @param username
	 * @param password
	 * @param server
	 * @param emailFolder
	 * @param subjectLine
	 * @return
	 * @throws Exception
	 */

	public static String getSubjectFromGmail(String username, String password, String server, int emailFolder,
			String subjectLine) throws Exception {
		String foundSubject = "";
		// create properties
		Properties properties = new Properties();

		properties.put("mail.imap.host", Config.getGAMILHost());
		properties.put("mail.imap.port", Config.getGMAILPort());
		properties.put("mail.imap.starttls.enable", "true");
		properties.put("mail.imap.ssl.trust", Config.getGAMILHost());

		Session emailSession = Session.getDefaultInstance(properties);

		// create the imap store object and connect to the imap server
		Store store = emailSession.getStore("imaps");

		store.connect(Config.getGAMILHost(), username, password);

		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);
		//System.out.println("Total Messages:" + folder.getMessageCount());
		//System.out.println("Unread Messages:" + folder.getUnreadMessageCount());

		Message[] messages = folder.getMessages();

		for (int i = messages.length - 1; i > 0; i--) {
			if (!messages[i].isSet(Flag.SEEN)) {
				// System.out.println(messages[i].getSubject());
				if (messages[i].getSubject().equals(subjectLine)) {
					foundSubject = messages[i].getSubject();
					messages[i].setFlag(Flag.SEEN, true);
					break;
				}

			}
		}
		return foundSubject;
	}

	/**
	 * Function to return Email Subject Survey Response
	 * 
	 * @param username
	 * @param password
	 * @param server
	 * @param emailFolder
	 * @return
	 * @throws Exception
	 */

	public static String getSubjectFromGmailSurveyResponse(String username, String password, String server,
			int emailFolder) throws Exception {
		String foundSubject = "";
		// create properties
		Properties properties = new Properties();

		properties.put("mail.imap.host", Config.getGAMILHost());
		properties.put("mail.imap.port", Config.getGMAILPort());
		properties.put("mail.imap.starttls.enable", "true");
		properties.put("mail.imap.ssl.trust", Config.getGAMILHost());

		Session emailSession = Session.getDefaultInstance(properties);

		// create the imap store object and connect to the imap server
		Store store = emailSession.getStore("imaps");

		store.connect(Config.getGAMILHost(), username, password);

		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);
		System.out.println("Total Messages:" + folder.getMessageCount());
		System.out.println("Unread Messages:" + folder.getUnreadMessageCount());
		Message[] messages = folder.getMessages();
		for (int i = messages.length - 1; i > 0; i--) {
			if (!messages[i].isSet(Flag.SEEN)) {
				// System.out.println(messages[i].getSubject());
				if (messages[i].getSubject().equals("Survey Response")) {
					foundSubject = messages[i].getSubject();
					messages[i].setFlag(Flag.SEEN, true);
					break;
				}
			}
		}
		return foundSubject;
	}

	/**
	 * Function to return Email BodyMessage
	 * 
	 * @param username
	 * @param password
	 * @param server
	 * @param emailFolder
	 * @param subjectBody
	 * @return
	 * @throws Exception
	 */

	public static String getBodyMessageFromGmail(String username, String password, String server, int emailFolder,
			String subjectBody) throws Exception {
		String foundBodymessage = "";
		// create properties
		Properties properties = new Properties();

		properties.put("mail.imap.host", Config.getGAMILHost());
		properties.put("mail.imap.port", Config.getGMAILPort());
		properties.put("mail.imap.starttls.enable", "true");
		properties.put("mail.imap.ssl.trust", Config.getGAMILHost());

		Session emailSession = Session.getDefaultInstance(properties);

		// create the imap store object and connect to the imap server
		Store store = emailSession.getStore("imaps");

		store.connect(Config.getGAMILHost(), username, password);

		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);
		System.out.println("Total Messages:" + folder.getMessageCount());
		System.out.println("Unread Messages:" + folder.getUnreadMessageCount());

		Message[] messages = folder.getMessages();

		for (int i = messages.length - 1; i > 0; i--) {
			if (!messages[i].isSet(Flag.SEEN)) {
				// System.out.println(messages[i].getSubject());
				if (messages[i].getSubject().equals(subjectBody)) {
					foundBodymessage = getTextFromMessage(messages[i]);
					messages[i].setFlag(Flag.SEEN, true);
					break;
				}

			}
		}
		return foundBodymessage;
	}

	/***
	 * Get Body message from Gmail
	 * 
	 * @param message
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static String getTextFromMessage(Message message) throws MessagingException, IOException {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}

	/**
	 * Get Body message from Gmail
	 * 
	 * @param mimeMultipart
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}

}
