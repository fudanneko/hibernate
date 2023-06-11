package core.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.Instant;

public class InstantTypeAdapter extends TypeAdapter<Instant> {
    @Override
    public void write(JsonWriter out, Instant value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.getEpochSecond());
        }
    }

    @Override
    public Instant read(JsonReader in) throws IOException {
        long epochSeconds = in.nextLong();
        return Instant.ofEpochSecond(epochSeconds);
    }
}

