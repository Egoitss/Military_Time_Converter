package nbs_mc;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MilitaryClock extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ZonedDateTime zonedDayTimelocal;
	ZonedDateTime zonedDayTimezulu;
	ZoneOffset zoneOffSet;
	JLabel localTitle;
	JLabel localLabel;
	JLabel zuluTitle;
	JLabel zuluLabel;
	String local;
	String zulu;
	
	
	MilitaryClock() {
		Image img = Toolkit.getDefaultToolkit().getImage("G:\\CODING\\RIGA_CODING\\JAVA\\Military_Clock\\src\\horn.png");  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("MILITARY Clock");
		this.setIconImage(img); 
		this.setLayout(new FlowLayout());
		this.setSize(424, 300);
		this.setResizable(false);

		localTitle = new JLabel();
		localTitle.setFont(new Font("Arial", Font.PLAIN, 50));
		localTitle.setBackground(new Color(122,152,130));
		localTitle.setForeground(new Color(0,0,1));
		localTitle.setOpaque(true);

		localLabel = new JLabel();
		localLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		localLabel.setBackground(new Color(122,152,130));
		localLabel.setForeground(new Color(0,0,1));
		localLabel.setOpaque(true);

		zuluTitle = new JLabel();
		zuluTitle.setFont(new Font("Arial", Font.PLAIN, 50));
		zuluTitle.setBackground(new Color(122,152,130));
		zuluTitle.setForeground(new Color(0,0,1));
		zuluTitle.setOpaque(true);

		zuluLabel = new JLabel();
		zuluLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		zuluLabel.setBackground(new Color(122,152,130));
		zuluLabel.setForeground(new Color(0,0,1));
		zuluLabel.setOpaque(true);

		this.add(localTitle);
		this.add(localLabel);
		this.add(zuluTitle);
		this.add(zuluLabel);
		this.setVisible(true);

		setTime();
	}

	

	public void setTime() {
		while (true) {

			localTitle.setText("       Local Time       ");

			zonedDayTimelocal = ZonedDateTime.now();
			local = dateInput(zonedDayTimelocal);
			localLabel.setText(local);

			zuluTitle.setText("       Zulu Time       ");

			ZoneId zone = ZoneId.of("Europe/London");
			zonedDayTimezulu = ZonedDateTime.now(zone);
			zulu = dateInput(zonedDayTimezulu);
			zuluLabel.setText(zulu);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	public static String dateInput(ZonedDateTime inputDTM) {
		String inputTime = String.valueOf(inputDTM);
		String opordDate = String.valueOf(inputTime.substring(8, 10));
		String opordHours = String.valueOf(inputTime.substring(11, 13));
		String opordMinutes = String.valueOf(inputTime.substring(14, 16));
		String opordYear = String.valueOf(inputTime.substring(0, 4));

		String zoneOffset = String.valueOf(inputDTM.getOffset());
		String z = "Z";
		String militaryZone;
		if (zoneOffset.equals(z)) {
			militaryZone = zoneOffset;
		} else {
			int timeZoneCounter = Integer.valueOf(zoneOffset.substring(1, 3));
			String[] militaryZoneArray = { "Z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "M", "N", "O",
					"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y" };
			militaryZone = militaryZoneArray[timeZoneCounter];
		}
		int opordMonth = Integer.valueOf(inputTime.substring(5, 7));
		String[] monthArray = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
		String militaryMonth = monthArray[opordMonth - 1];

		String output = opordDate + opordHours + opordMinutes + militaryZone + militaryMonth + opordYear;
		return output;

	}

}
