package com.hypebeast.sdk.api.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by hesk on 28/7/15.
 */
public class MissingCharacterConversion extends TypeAdapter<String> {
    private boolean hasHtmlEscapers = false;

    public MissingCharacterConversion(boolean hasHtmlEscapers) {
        this.hasHtmlEscapers = hasHtmlEscapers;
    }

    public MissingCharacterConversion() {
        this(false);
    }

    public String read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return "";
        }
        String beforeDecoding = reader.nextString();
        //  String decodedValue1 = URLDecoder.decode(str, "UTF-8");
        // return Html.fromHtml((String) str).toString();
        if (hasHtmlEscapers) {
            return beforeDecoding;
            //  return URLDecoder.decode(beforeDecoding);
            // return StringEscapeUtils.unescapeEcmaScript(beforeDecoding);
        } else {
            return beforeDecoding.replace("&#038;", "&");
        }
    }

    public void write(JsonWriter writer, String value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        //   String xy = value.getX() + "," + value.getY();
        writer.value(value);
    }

}
