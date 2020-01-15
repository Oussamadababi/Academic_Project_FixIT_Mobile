///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Api;
//
////import Service.Session;
//
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//
///**
// *
// * @author lenovo
// */
////throws MessagingException
//public class Mail {
//    public static void sendMail11(String to,String sujet,String contenu) throws MessagingException {
//    
//        
//        String login = "fixitapplication@aol.com";
//        String pass="bunbpbkvgsrbjqpq";
//        String host = "smtp.aol.com";
//        Properties prop = System.getProperties();
//        prop.put("mail.smtp.starttls.enable", "true");
//        prop.put( "mail.smtp.host", host );
//        prop.put("mail.smtp.user", login);
//        prop.put("mail.smtp.password", pass);
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.ssl.trust", "smtp.aol.com");
//       
//        Session session = Session.getInstance(prop,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(login, pass);
//                    }
//                });
//     // Session session1 = Session.getDefaultInstance(prop);
//        MimeMessage msg = new MimeMessage(session);
//        msg.setFrom(new InternetAddress(login));
//         
//        InternetAddress toAdresse = new InternetAddress(to);
//                
//        msg.setRecipient(Message.RecipientType.TO, toAdresse);
//        msg.setSubject(sujet);
//        
//        msg.setContent(contenu,"text/html; charset=utf-8");
//        Transport transport = session.getTransport("smtp");
//        transport.connect(host, login, pass);
//        transport.sendMessage(msg, msg.getAllRecipients());
//        transport.close();
//    
//          
//        
//        
//    }
//    
//}
//
//   
//
