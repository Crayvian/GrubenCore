package net.overcrave.GrubenCore.Events;

import com.Zrips.CMI.events.CMIAfkEnterEvent;
import com.Zrips.CMI.events.CMIAfkLeaveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

public class AFKCounter implements Listener {
    @EventHandler
    public void onAFKEnter(CMIAfkEnterEvent event){
        Player p = event.getPlayer();
        LocalDateTime currentTime = LocalDateTime.now();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MC_BG_PL_AFKCheck", "Baugrube", "Mitsuki19%");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM  `afkEnter`");
            while(rs.next()){
                String name = rs.getString("userName");
                if(name == p.getName()){
                    //USER HAS BEEN AFK BEFORE
                    //SET AFK ENTER TIME TO NOW
                }else{
                    //USER HAS NEVER BEEN AFK BEFORE
                    //CREATE NEW COLUMN FOR USER AND SET AFK TIME TO NOW
                }
            }
            con.close();
        }catch(Exception e){

        }
    }

    @EventHandler
    public void onAFKLeave(CMIAfkLeaveEvent event){
        Player p = event.getPlayer();
        LocalDateTime currentTime = LocalDateTime.now();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MC_BG_PL_AFKCheck", "Baugrube", "Mitsuki19%");
            Statement stmt = con.createStatement();
            ResultSet afkEnter = stmt.executeQuery("SELECT * FROM  `afkEnter`");
            ResultSet afkTimer = stmt.executeQuery("SELECT * FROM  `afkTimer`");
            while(afkEnter.next()){
                String name = afkEnter.getString("userName");
                if(name == p.getName()){
                    Integer year = afkEnter.getInt("year");
                    Integer month = afkEnter.getInt("month");
                    Integer day = afkEnter.getInt("day");

                    //If the last day he went afk is the same as the current one, add onto his AFK timer!
                    if(year == currentTime.getYear() && month == currentTime.getMonthValue() && day == currentTime.getDayOfMonth()){
                        //ADD TIME SINCE LAST AFKENTER ONTO AFK TIMER
                        Integer enterHour = afkEnter.getInt("hour");
                        Integer enterMinute = afkEnter.getInt("hour");
                        Integer enterSecond = afkEnter.getInt("hour");
                    }else{
                        //RESET AFK TIMER TO TIME SINCE LAST AFKENTER
                    }
                }
            }
            con.close();
        }catch(Exception e){

        }
    }
}
