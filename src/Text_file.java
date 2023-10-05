import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
class Text_file extends SendMail {
    private int R_count = 0;
    public void setSave_file(String ClientList) throws IOException {
        String[] arrClient = ClientList.split(": ");
        FileWriter Dbase = new FileWriter("clientList.txt",true);
        FileReader read_file = new FileReader("clientList.txt");
        BufferedReader reader = new BufferedReader(read_file);
        PrintWriter save_file = new PrintWriter(Dbase);
        String line = reader.readLine();
        boolean Already = false;
        while (line != null){
            if (line.equalsIgnoreCase(ClientList)) {
                Already = true;
                break;
            }
            line = reader.readLine();
        }
        //code to print the detail if the detail is not in the clientlist textfile
        if(Already){
            System.out.println("Details already saved!");
        }
        else{
            switch (arrClient[0]) {
                case "Official" -> {
                    String[] Detail0 = arrClient[1].split(",");
                    if (Detail0.length == 3) {
                        Official_recipient recoff = new Official_recipient(Detail0[0],Detail0[1],Detail0[2]);
                        save_file.println("Official: "+recoff.getName()+","+recoff.getEmail()+","+recoff.getDesignation());
                    } else {
                        System.out.println("Invalid Input");
                    }
                }
                case "Office_friend"-> {
                    String[] Detail1 = arrClient[1].split(",");
                    if (Detail1.length == 4) {
                        Office_friend recf = new Office_friend(Detail1[0],Detail1[1],Detail1[2],Detail1[3]);
                        save_file.println("Office_friend: "+recf.getName()+","+recf.getEmail()+","+recf.getDesignation()+","+recf.getB_date());
                    } else {
                        System.out.println("Invalid Input");
                    }
                }
                case "Personal"-> {
                    String[] Detail2 = arrClient[1].split(",");
                    if (Detail2.length == 4) {
                        Personal_recipient recp = new
                                Personal_recipient(Detail2[0],Detail2[1],Detail2[2],Detail2[3]);
                        save_file.println("Personal: "+recp.getName()+","+recp.getNickName()+","+recp.getEmail()+","+recp.getB_date());
                    } else {
                        System.out.println("Invalid Input");
                    }
                }
                default -> System.out.println("Invalid Input");
            }
            save_file.close();
        }
    }

    public void sendMail(String mail) throws IOException, MessagingException {
        //code to send manual message
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String[] element = mail.split(",",3);
        File Dbase = new File("clientList.txt");
        FileReader read_file = new FileReader(Dbase);
        BufferedReader reader = new BufferedReader(read_file);
        String line = reader.readLine();
        String Recname = null;
        boolean exist = false;
        while (line != null) {
            String[] first = line.split(": ");
            String[] Detail = first[1].split(",");
            if ((first[0].equals("Official") && Detail[1].equals(element[0])) || (first[0].equals("Office_friend")
                    && Detail[1].equals(element[0])) || (first[0].equals("Personal") && Detail[2].equals(element[0]))) {
                exist = true;
                Recname = Detail[0];
                break;
            }
            line = reader.readLine();
        }
        if(exist){
            Mail.sendmail(element[0],element[1],element[2]);
            SendMail.send(formatter.format(date),Recname, element[1],element[2]);
        }
        else{
            System.out.println("Email Address Doesn't Exist!");
        }
    }
    public void getBirthday (String Bdate) throws IOException {
        //traverse the client list and get the clients who have birthday in the given date
        File Dbase = new File("clientList.txt");
        FileReader read_file = new FileReader(Dbase);
        BufferedReader reader = new BufferedReader(read_file);
        String line = reader.readLine();
        boolean Exist = false;
        while (line != null){
            String[] first = line.split(": ");
            String[] Detail = first[1].split(",");
            if(first[0].equals("Office_friend")){
                Office_friend recf = new Office_friend(Detail[0], Detail[1], Detail[2], Detail[3]);
                recf.setB_date();
                if(Bdate.split("/",2)[1].equals(recf.getB_date().split("/",2)[1])) {
                    recf.GetBirthdayGuy();
                    Exist = true;
                }
            } else if (first[0].equals("Personal")) {
                Personal_recipient recp = new Personal_recipient(Detail[0], Detail[1], Detail[2], Detail[3]);
                recp.setB_date();
                if(Bdate.split("/",2)[1].equals(recp.getB_date().split("/",2)[1])) {
                    recp.GetBirthdayGuy();
                    Exist = true;
                }
            }
            line = reader.readLine();
        }
        if(!Exist){
            System.out.println("No one have birthday at :: "+ Bdate);
        }
    }
    public void readmail(String date) throws IOException {
        //code to print the mails that sent in the given date
        FileWriter Mbase = new FileWriter("mailList.txt",true);
        FileReader read_mail = new FileReader("mailList.txt");
        BufferedReader Mreader = new BufferedReader(read_mail);
        String Mline = Mreader.readLine();
        while (Mline!=null){
            if(Mline.split(",",2)[0].equals(date)){
                String line = Mline.split(",",2)[1];
                System.out.println(line.split(", content: ")[0]);
                Mline = Mreader.readLine();
            }
            else{
                Mline = Mreader.readLine();
            }
        }
        read_mail.close();
    }
    public void sendgreeting (String date) throws IOException, MessagingException {
        //code to send the birthday greeting automatically when the application starts
        FileWriter Dwbase = new FileWriter("clientList.txt",true);
        File Dbase = new File("clientList.txt");
        FileReader read_file = new FileReader(Dbase);
        BufferedReader reader = new BufferedReader(read_file);
        String line = reader.readLine();
        while (line != null){
            FileWriter file = new FileWriter("mailList.txt",true);
            PrintWriter save_mail = new PrintWriter(file);
            FileReader read_mail = new FileReader("mailList.txt");
            BufferedReader Mreader = new BufferedReader(read_mail);
            String Mline = Mreader.readLine();
            String[] arr = line.split(": ");
            String[] person = arr[1].split(",");
            switch (arr[0]) {
                case "Office_friend" -> {
                    Office_friend recf = new Office_friend(person[0], person[1], person[2], person[3]);
                    boolean notexist = true;
                    while (Mline != null) {
                        String[] rec = Mline.split(",");
                        if (Objects.equals(rec[0], recf.getName())) {
                            notexist = false;
                            break;
                        }
                        Mline = Mreader.readLine();
                    }
                    if (notexist){
                        String[] Bday = date.split("/",2);
                        if (Bday[1].equals(recf.getB_date().split("/",2)[1])) {
                            Mail.sendmail(recf.getEmail(), "Birthday Wish", "Wish you a Happy Birthday"+", "+recf.getName() );
                                    SendMail.send(date,recf.getName(), "Birthday Wish","Wish you a Happy Birthday"+", "+recf.getName());
                        }
                    }
                    save_mail.close();
                }
                case "Personal" -> {
                    Personal_recipient recp = new Personal_recipient(person[0],person[1],person[2],person[3]);
                    boolean notexist = true;
                    while (Mline != null) {
                        String[] rec = Mline.split(",");
                        if (Objects.equals(rec[0], recp.getName())) {
                            notexist = false;
                            break;
                        }
                        Mline = Mreader.readLine();
                    }
                    if(notexist){
                        String[] Bday = date.split("/",2);
                        if (Bday[1].equals(recp.getB_date().split("/",2)[1])) {
                            Mail.sendmail(recp.getEmail(), "Birthday Wish","Wish you a Happy Birthday and Hugs and love on your birthday"+", "+recp.getName());
                                    SendMail.send( date,recp.getName(), "Birthday Wish","Wish you a Happy Birthday and Hugs and love on your birthday"+", "+recp.getName());
                        }
                    }
                    save_mail.close();
                }
            }
            line = reader.readLine();
        }
    }
    public void recipientlist() throws IOException{
        //count the recipients in the client list
        File Dbase = new File("clientList.txt");
        FileReader read_file = new FileReader(Dbase);
        BufferedReader reader = new BufferedReader(read_file);
        String line = reader.readLine();
        while (line != null){
            R_count++;
            line = reader.readLine();
        }
        System.out.println("Number of recipient objects :: "+ R_count);
    }
}
