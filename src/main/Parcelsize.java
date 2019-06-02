package main;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;


@XmlRootElement
public class Parcelsize {
    private String length;
    private String width;
    private String height;
    private String size;

    public Parcelsize() {

    }

    public Parcelsize(String length, String width, String height, String size) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.size = size;
    }


    public void calculateSize() {
        int girth = (Integer.parseInt(this.getLength())) + (Integer.parseInt(this.getWidth()) * 2) + (Integer.parseInt(this.getHeight()) *2);

        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            Properties p = new Properties();
            try {
                p.load(new BufferedInputStream(new FileInputStream("java.properties")));
                String dbPath = p.getProperty("mySqlPath");

                conn = DriverManager.getConnection(dbPath);
                System.out.println("Database Connection established...");
                PreparedStatement ps = conn.prepareStatement("SELECT size_type FROM parcel_size WHERE ? BETWEEN girth_min_cm AND girth_max_cm;");
                ps.setInt(1, girth);
                ResultSet result = ps.executeQuery();

                //Statement statement = conn.createStatement();
                //ResultSet result = statement.executeQuery("SELECT size_type FROM parcel_size WHERE 35 BETWEEN girth_min_cm AND girth_max_cm;");
                result.next();
                this.size = result.getString(1);

            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (Exception ex){
            System.err.println("Cannot connect to database server");
            ex.printStackTrace();
        }
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
