package Main;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

import ModulAdministrator.DateAngajatAdministrator;
import ModulContabilitate.SalariuAngajat;

public class Main {

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.err.println("An Exception occured during JDBC Driver loading." + " Details are provided below:");
			ex.printStackTrace(System.err);
		}

		DefaultDBConnection connection = new DefaultDBConnection("jdbc:mysql://localhost/policlinici?user=");

		//DateAngajatAdministrator angajat = new DateAngajatAdministrator(connection, 3);
		//angajat.setVisible(true);
		FirstPage mainFrame = new FirstPage(connection);
		mainFrame.setVisible(true);

	}

}
