import java.io.*;

class SendMail {
    //save the sent mail details in a text file in the order of sent time.
    static void send(String date, String mail, String subject,String content) throws IOException{
        FileWriter DMbase = new FileWriter("mailList.txt",true);
        FileReader read_mail = new FileReader("mailList.txt");
        BufferedReader Mreader = new BufferedReader(read_mail);
        PrintWriter save_mail = new PrintWriter(DMbase);
        String Mline = Mreader.readLine();
        while (Mline != null) {
            Mline = Mreader.readLine();
        }
        save_mail.println(date+","+mail + "," + subject+", content: "+content);
        save_mail.close();
    }
}