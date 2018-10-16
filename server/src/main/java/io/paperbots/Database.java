
package io.paperbots;

import java.util.Properties;

import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;

public class Database {
	public static Jdbi setupDatabase (boolean test) {
		final String url = "jdbc:mysql://127.0.0.1/paperbots";
		final String user = "root";
		final String pwd = Paperbots.PAPERBOTS_DB_PWD;
		final Jdbi jdbi;

		Properties properties = new Properties();
		properties.setProperty("user", user);
		properties.setProperty("password", pwd);
		properties.setProperty("useSSL", "false");
		jdbi = Jdbi.create(url, properties);

		Flyway flyway = Flyway.configure().dataSource(url, user, pwd).load();
		if (test) flyway.clean();
		flyway.migrate();

		return jdbi;
	}
}