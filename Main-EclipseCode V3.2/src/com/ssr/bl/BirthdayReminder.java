package com.ssr.bl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;

import com.ssr.alarm_service.AlarmController;
import com.ssr.dbm.Reminder;
import com.ssr.dbm.ReminderDAO;

public class BirthdayReminder {
	public void createBirthdayReminder(String birthdayof, String AI,
			String date, String time, Context con) {

		date = date.trim();
		time = time.trim();
		// (Context con, int day,int month,int year,int am_pm,int hour,int min)
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm",
				Locale.getDefault());
		Date dt = null;
		try {
			dt = formatter.parse(date + " " + time);

			// String st = "day: " + dt.getDate() + " month: " +
			// (dt.getMonth()+1)
			// + " year: " + (dt.getYear()+1900) + " hour:" + dt.getHours()
			// + " minute:" + dt.getMinutes();

			Reminder rem = new Reminder();
			rem.setType(ReminderType.BirthdayRem);
			rem.setBirthdayof(birthdayof);
			rem.setAI(AI);
			rem.setDate(date);
			rem.setTime(time);

			// Toast.makeText(con, "d:"+date+" t:"+time ,
			// Toast.LENGTH_LONG).show();

			ReminderDAO dao = new ReminderDAO(con);
			dao.insert(rem);

			AlarmController alc = new AlarmController();
			alc.startAlarm(con, dt.getDate(), dt.getMonth(),
					(dt.getYear() + 1900), 1, dt.getHours(), dt.getMinutes());

			// Toast.makeText(con, "Alarm Time: "+st ,
			// Toast.LENGTH_LONG).show();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// alc.startAlarm(con,dt.);
	}
}
