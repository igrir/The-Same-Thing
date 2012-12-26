package com.cengek.thesamething.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class RMS {

    private String recordScore = "THESAMETTHING1_SCORE";

    private RecordStore recordStore = null;

    public void openRecord(String recordName) {
        try {
            recordStore = RecordStore.openRecordStore(recordName, true);
        } catch (RecordStoreException ex) {
        }
    }

    public void saveScore(int score) {
        try {
            RecordStore.deleteRecordStore(recordScore);
        } catch (RecordStoreException ex) {
        }
        openRecord(recordScore);
        addRecord(score);
        closeRecord();
    }
    
    public void addRecord(int input) {
        byte[] byteAdd = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            dos.writeInt(input);
        } catch (IOException ex) {
        }
        byteAdd = baos.toByteArray();
        try {
            recordStore.addRecord(byteAdd, 0, byteAdd.length);
        } catch (RecordStoreException ex) {
        }
    }

    public int getScore(){
    	int score = 0;
    	openRecord(recordScore);
    	try{
    		if (recordStore.getNumRecords() > 0) {
    			byte[] data = null;
    			try{
    				data = recordStore.getRecord(1);
    				try{
    					ByteArrayInputStream bais = new ByteArrayInputStream(data);
    					DataInputStream dis = new DataInputStream(bais);
    					score = dis.readInt();
    				}catch (Exception e) {
						// TODO: handle exception
    					e.printStackTrace();
					}
    			}catch (RecordStoreException e) {
					// TODO: handle exception
    				e.printStackTrace();
				}
    		}
    	}catch (RecordStoreNotOpenException e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	closeRecord();
    	return score;
    }
    
    public void closeRecord() {
        try {
            recordStore.closeRecordStore();
        } catch (RecordStoreException ex) {
        }
    }
}

