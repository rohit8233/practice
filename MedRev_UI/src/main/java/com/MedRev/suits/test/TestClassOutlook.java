package com.MedRev.suits.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SearchTerm;

import org.apache.commons.codec.binary.Base64;

import com.testautomationguru.utility.PDFUtil;

public class TestClassOutlook {

	public static void main(String[] args) {
		String str="sdfvsdf68fsdfsf8999fsdf09";
		try {
			String url = gmailUtils("amarendra.srivastava@programmers.io","Pixere@1234","", Folder.READ_WRITE);
			System.out.println(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String gmailUtils(String username, String password, String server, int 
			emailFolder) throws Exception {
		String host = "imap.gmail.com";
		String foundUrl = "";
		Properties props = new Properties();        
		  props.setProperty("mail.imap.ssl.enable", "true");     
		  Session mailSession = Session.getInstance(props); 
		  mailSession.setDebug(true);
		  Store mailStore = mailSession.getStore("imap");
		  mailStore.connect("outlook.office365.com", username, password);

		Session emailSession = Session.getDefaultInstance(props);

		// create the imap store object and connect to the imap server

		Folder folder = mailStore.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);
		/*
		 * SearchTerm search = new SearchTerm() {
		 * 
		 * @Override public boolean match(Message msg) { try { return
		 * (!msg.isSet(Flag.SEEN) && msg.getSubject().
		 * contains("Remember to tell us about your recent visit with your provider"));
		 * } catch (MessagingException e) { e.printStackTrace(); } return false; } };
		 */
		System.out.println("Total Messages:" + folder.getMessageCount());
		System.out.println("Unread Messages:" + folder.getUnreadMessageCount());



		Message [] messages = folder.getMessages();


		for (int i = messages.length -1; i > 0; i--) {
			if (!messages[i].isSet(Flag.SEEN)) {
				System.out.println(messages[i].getSubject());
				//System.out.println("Body: \n" + getTextFromMessage(messages[i]));
				String link = getTextFromMessage(messages[i]);
				Pattern p = Pattern.compile("\\b(http|https)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",Pattern.CASE_INSENSITIVE);
				Matcher matcher = p.matcher(link);

				int flagfound = 0;
				while (matcher.find()) {
					int matchStart = matcher.start(1);
					int matchEnd = matcher.end();
					foundUrl = link.substring(matchStart, matchEnd);
					if (foundUrl.contains("api-test.medrevup.com/survey") ) {
						messages[i].setFlag(Flag.SEEN, true);
						return foundUrl;
					}
				}
				System.out.println(foundUrl);

			}

		}
		/*
		 * for (Message mail : messages) { if (!mail.isSet(Flag.SEEN)) {
		 * 
		 * System.out.println("***************************************************");
		 * System.out.println("MESSAGE : \n");
		 * 
		 * System.out.println("Subject: " + mail.getSubject());
		 * System.out.println("From: " + mail.getFrom()[0]); System.out.println("To: " +
		 * mail.getAllRecipients()[0]); System.out.println("Date: " +
		 * mail.getReceivedDate()); System.out.println("Size: " + mail.getSize());
		 * System.out.println("Flags: " + mail.getFlags());
		 * System.out.println("ContentType: " + mail.getContentType());
		 * System.out.println("Body: \n" + getEmailBody(mail));
		 * System.out.println("Has Attachments: " + hasAttachments(mail));
		 * 
		 * } }
		 */
		return  foundUrl;
	}

	public static boolean hasAttachments(Message email) throws Exception {

		// suppose 'message' is an object of type Message
		String contentType = email.getContentType();
		System.out.println(contentType);

		if (contentType.toLowerCase().contains("multipart/mixed")) {
			// this message must contain attachment
			Multipart multiPart = (Multipart) email.getContent();

			for (int i = 0; i < multiPart.getCount(); i++) {
				MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i);
				if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
					System.out.println("Attached filename is:" + part.getFileName());

					MimeBodyPart mimeBodyPart = (MimeBodyPart) part;
					String fileName = mimeBodyPart.getFileName();

					String destFilePath = System.getProperty("user.dir") + "\\Resources\\";

					File fileToSave = new File(fileName);
					mimeBodyPart.saveFile(destFilePath + fileToSave);

					// download the pdf file in the resource folder to be read by PDFUTIL api.

					PDFUtil pdfUtil = new PDFUtil();
					String pdfContent = pdfUtil.getText(destFilePath + fileToSave);

					System.out.println("******---------------********");
					System.out.println("\n");
					System.out.println("Started reading the pdfContent of the attachment:==");


					System.out.println(pdfContent);

					System.out.println("\n");
					System.out.println("******---------------********");

					Path fileToDeletePath = Paths.get(destFilePath + fileToSave);
					Files.delete(fileToDeletePath);
				}
			}

			return true;
		}

		return false;
	}

	public static String getEmailBody(Message email) throws IOException, MessagingException {

		String line, emailContentEncoded;
		StringBuffer bufferEmailContentEncoded = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));
		while ((line = reader.readLine()) != null) {
			bufferEmailContentEncoded.append(line);
		}

		System.out.println("**************************************************");

		System.out.println(bufferEmailContentEncoded);

		System.out.println("**************************************************");

		emailContentEncoded = bufferEmailContentEncoded.toString();

		if (email.getContentType().toLowerCase().contains("multipart/related")) {

			emailContentEncoded = emailContentEncoded.substring(emailContentEncoded.indexOf("base64") + 6);
			emailContentEncoded = emailContentEncoded.substring(0, emailContentEncoded.indexOf("Content-Type") - 1);

			System.out.println(emailContentEncoded);

			String emailContentDecoded = new String(new Base64().decode(emailContentEncoded.toString().getBytes()));
			return emailContentDecoded;
		}

		return emailContentEncoded;

	}

	private static String getTextFromMessage(Message message) throws MessagingException, IOException {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);                     
		}
		return result;
	}

	private static  String getTextFromMimeMultipart(
			MimeMultipart mimeMultipart)  throws MessagingException, IOException{
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
			} else if (bodyPart.getContent() instanceof MimeMultipart){
				result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
			}
		}
		return result;
	}

}
