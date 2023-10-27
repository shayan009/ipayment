package com.onetechsol.ipayment.data.remote.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;

public class ItemTypeAdapterFactory implements TypeAdapterFactory {

    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            public T read(JsonReader in) throws IOException {

                JsonElement jsonElement = elementAdapter.read(in);

                // String s = removeQuotesAndUnescape(jsonElement.toString());
                // Log.d("removeQuotesAndUnescape",jsonElement.toString());
                //Log.d("jsonElementOutSIde -- >", String.valueOf(jsonElement.isJsonObject()));

                if (jsonElement.isJsonObject()) {
                    //JsonObject jsonObject = jsonElement.getAsJsonObject();

                    //Log.d("jsonElementOld -- >",jsonElement.toString());
                   /* try {
                        //jsonElement = new Gson().toJsonTree(jsonElement);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }*/
                    //Log.d("jsonElementNew -- >",jsonElement.toString());
                    /*if (jsonObject.has("content")) {
                        jsonElement = jsonObject.get("content");
                        Log.d("jsonElement -- >",jsonElement.toString());
                    }*/
                }

                return delegate.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }

    private String removeQuotesAndUnescape(String uncleanJson) {
        String noQuotes = uncleanJson.replaceAll("^\"|\"$", "");

        return StringEscapeUtils.unescapeJava(noQuotes);
    }
}