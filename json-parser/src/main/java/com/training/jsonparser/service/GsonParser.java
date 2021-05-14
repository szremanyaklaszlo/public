package com.training.jsonparser.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.training.jsonparser.domain.SportEvent;

public class GsonParser {

    private final static Type SPORT_EVENT_LIST = new TypeToken<ArrayList<SportEvent>>() {
    }.getType();
    private Gson gson = buildGson();

    private Gson buildGson() {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .create();
    }

    public void writeSportEvent(List<SportEvent> sportEvents) {
        try (Writer writer = new FileWriter("football.json")) {
            gson.toJson(sportEvents, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SportEvent> readSportEvent(String fileName) {
        List<SportEvent> sportEvents = new ArrayList<>();
        try (Reader reader = new FileReader(fileName)) {
            sportEvents = gson.fromJson(reader, SPORT_EVENT_LIST);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sportEvents;
    }

}
