import java.util.HashMap;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;
import javax.mail.MessagingException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Objects;
import java.io.File;
    public class Email_Client {
        public static void main(String[] args) throws IOException, MessagingException {
            System.out.println("Enter your Email Address: "); //get the email address and password f the email application user
            Scanner MailAdd = new Scanner(System.in);
            String mailadd = MailAdd.nextLine();
            System.out.println("Enter your Password: ");
            Scanner Mailpass = new Scanner(System.in);
            String mailpass = Mailpass.nextLine();
            Mail mymail = new Mail();
            mymail.setMailAdd(mailadd);
            mymail.setMailpassword(mailpass);
            //send birthday greetings if anyone have birthday today in the client list
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date today = new Date();
            String B_day = formatter.format(today);
            Text_file obj = new Text_file();
            obj.sendgreeting(B_day);
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("""
 Enter option type:\s
 1 - Adding a new recipient
 2 - Sending an email
 3 - Printing out all the recipients who have birthdays
 4 - Printing out details of all the emails sent
 5 - Printing out the number of recipient objects in the application
 6 - Exit the Application""");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        // input format - Official: nimal,nimal@gmail.com,ceo
                        // Use a single input to get all the details of a recipient
                        // code to add a new recipient
                        // store details in clientList.txt file
                        // Hint: use methods for reading and writing files
                        System.out.println("Enter the new recipient Details :: ");
                        Scanner input = new Scanner(System.in);
                        String ClientList = input.nextLine();
                        Text_file save = new Text_file();
                        save.setSave_file(ClientList);
                        continue;
                    case 2:
                        // input format - email, subject, content
                        // code to send an email
                        System.out.println("Compose your mail (Mail address,Subject,Content) :: ");
                        Scanner input_mail = new Scanner(System.in);
                        String mail = input_mail.nextLine();
                        Text_file smail = new Text_file();
                        smail.sendMail(mail);
                        continue;
                    case 3:
                        // input format - yyyy/MM/dd (ex: 2018/09/17)
                        // code to print recipients who have birthdays on the given date
                        Scanner input_Bdate = new Scanner(System.in);
                        System.out.println("input the date :: ");
                        String Bdate = input_Bdate.nextLine();
                        Text_file B_guy = new Text_file();
                        B_guy.getBirthday(Bdate);
                        continue;
                    case 4:
                        // input format - yyyy/MM/dd (ex: 2018/09/17)
                        // code to print the details of all the emails sent on the input date
                        Scanner insert_Bdate = new Scanner(System.in);
                        System.out.println("input the date :: ");
                        String date = insert_Bdate.nextLine();
                        Text_file B_mail = new Text_file();
                        B_mail.readmail(date);
                        continue;
                    case 5:
                        // code to print the number of recipient objects in the application
                        Text_file textFile = new Text_file();
                        textFile.recipientlist();
                        continue;
                    case 6:
                        break;
                    default:
                        System.out.println("Invalid Input!");
                        continue;
                }
                Scanner selection = new Scanner(System.in);
                System.out.println("Do you want to Exit? (Y/N)");
                String select = selection.nextLine();
                if (select.equals("N")) {
                    System.out.println("Application Restarted!");
                } else {
                    System.out.println("Exit!");
                    break;
                }
                // start email client
                // code to create objects for each recipient in clientList.txt
                // use necessary variables, methods and classes
            }
        }
    }

