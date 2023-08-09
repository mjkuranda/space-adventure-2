package com.github.mjkuranda.spaceadventure2.states.highscore;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface HighScoreManageable {
    /***
     * Checks and tries to add a new record to the list of the bests
     * @param record New record after game over
     */
    void inputRecord(HighScoreRecord record);

    /***
     * Fetch records from file
     * @return Record list of the bests
     */
    List<HighScoreRecord> fetchRecords();

    /***
     * Saves the list of the bests to the file
     */
    void updateRecords();

    /***
     * Clear the list and the file
     */
    void resetRecords();

    /***
     * Returns list of the bests
     * @return Record list of the bests
     */
    List<HighScoreRecord> getRecords();
}

public class HighScoreHandler implements HighScoreManageable {

    public static final int MAX_RECORDS_COUNT = 10;

    private static final String RESULT_FILE_PATH = "src/main/resources/hs-bests.dat";

    public static HighScoreHandler handler;

    private List<HighScoreRecord> records;

    public static HighScoreHandler getInstance() {
        if (handler == null) {
            handler = new HighScoreHandler();
        }

        return handler;
    }

    private HighScoreHandler() {
        this.records = new ArrayList<>();
    }

    @Override
    public void inputRecord(HighScoreRecord record) {
        records.add(record);
        Collections.sort(records);

        if (records.size() > MAX_RECORDS_COUNT) {
            records.remove(10);
        }

        updateRecords();
    }

    @Override
    public List<HighScoreRecord> fetchRecords() {
        try {
            FileInputStream fis = new FileInputStream(RESULT_FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            HighScoreRecordListDto dto = (HighScoreRecordListDto) ois.readObject();
            ois.close();

            this.records = dto.getRecords();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return records;
    }

    @Override
    public void updateRecords() {
        HighScoreRecordListDto dto = new HighScoreRecordListDto(records);

        try {
            FileOutputStream fos = new FileOutputStream(RESULT_FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dto);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void resetRecords() {
        records.clear();
        updateRecords();
    }

    @Override
    public List<HighScoreRecord> getRecords() {
        return records;
    }
}
