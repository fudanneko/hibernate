package core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.sql.DataSource;

public class Constants {
	public static DataSource DATASOURCE;
	public static final Gson GSON = new GsonBuilder().registerTypeAdapter(Instant.class, new InstantTypeAdapter()).create();
	public static final String JSON_MIME_TYPE = "application/json";
	public static final String PREFIX_WEB_INF = "/WEB-INF";
}
