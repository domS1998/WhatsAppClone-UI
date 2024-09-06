package org.main.util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Util {

    public static Timestamp convertStringToTimestamp(String strDate) throws ParseException {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            // you can change format of date
            Date date = formatter.parse(strDate);
            Timestamp timeStampDate = new Timestamp(date.getTime());
            return timeStampDate;
    }

    // file to byte
    public static byte[] imagePathToBytes(String fileName){
        // relativer Pfad from working directory
        File file = new File("src/main/resources/"+fileName);
        BufferedImage bImage = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            System.out.println(file.getCanonicalPath());
            bImage = ImageIO.read(file);
            ImageIO.write(bImage, "png", bos);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return  null;
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }

    // image to bytes
    public static  byte[] imageToBytes(Image img){
        int w = (int) img.getWidth();
        int h = (int) img.getHeight();
        byte[] buf = new byte[w * h * 4];
        img.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), buf, 0, w * 4);
        return buf;
    }

    // bytes to fx image
    public static Image bytesToImage (byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        BufferedImage bImage2 = null;
        try { bImage2 = ImageIO.read(bis); }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        Image image = SwingFXUtils.toFXImage(bImage2, null);
        return image;
    }

    // Bytes to File
    public static void bytesToFile(byte[] bytes, String fileName, String formatSuffix) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2,formatSuffix, new File(fileName));
    }


    public static String timestampToTimeString(Timestamp timestamp){

        LocalDateTime date = timestamp.toLocalDateTime();

        // aktuelles Datum holen
        LocalDate curDate = LocalDate.now();
        int curDay = curDate.getDayOfMonth();

        // Tag
        int day = date.getDayOfMonth();
        String dayStr = ""+day;
        if (date.getDayOfMonth() < 10) {dayStr = "0"+dayStr;}

        // Monat
        int month = date.getMonthValue();
        String monthStr = ""+month;
        if (date.getMonthValue() < 10) {monthStr = "0"+monthStr;}

        // Jahr
        String yearStr = ""+date.getYear();

        // Wochentag, Gestern oder Stunde anzeigen anstatt Datum

//        // Wenn weniger als 7 Tage her
//        if ( (curDay - day < 7) ||  ) {
//
//        }

            return "" + dayStr + "." + monthStr + "." + yearStr;

    }

    public static String timestampToMessageTime(Timestamp timestamp){

        LocalDateTime time = timestamp.toLocalDateTime();
        int hour = time.getHour();
        int minute = time.getMinute();
        String minStr = minute+"";


        String result = "";
        if (hour > 12) {
            if (minute < 10){
                minStr = "0"+minute;
            }
            result = hour + ":" + minStr +"  AM";
        }
        else {
            result = hour % 12 + ":"+minute+" PM";
        }
        return result;
    }


}
