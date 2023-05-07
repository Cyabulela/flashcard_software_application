package com.example.flashcardsoftwareapplication.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.flashcardsoftwareapplication.data.dto.FlashCardDto;
import com.example.flashcardsoftwareapplication.util.DatabaseConstants;

import java.util.ArrayList;
import java.util.List;

public class FlashCardDatabase extends SQLiteOpenHelper {

    private SQLiteDatabase database;
    private final String CREATE_TABLE = "create table " + DatabaseConstants.DATABASE_TABLE_NAME
            + "(" + DatabaseConstants.FLASHCARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DatabaseConstants.TITLE + " TEXT NOT NULL,"
            + DatabaseConstants.NOTES + " TEXT,"
            + DatabaseConstants.CREATED_DATE + " TEXT NOT NULL);";

    public FlashCardDatabase(Context context) {
        super(context , DatabaseConstants.DATABASE_NAME , null , DatabaseConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.DATABASE_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //Add flashcard on database
    public void insectFlashcard(FlashCardDto flashCardDto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.TITLE , flashCardDto.getTitle());
        contentValues.put(DatabaseConstants.NOTES , flashCardDto.getNotes());
        contentValues.put(DatabaseConstants.CREATED_DATE , flashCardDto.getDate());
        database = this.getWritableDatabase();
        database.insert(DatabaseConstants.DATABASE_TABLE_NAME , null , contentValues);
    }

    //Update flashcard on database
    public void updateFlashcard( FlashCardDto flashCardDto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.TITLE , flashCardDto.getTitle());
        contentValues.put(DatabaseConstants.NOTES , flashCardDto.getNotes());
        contentValues.put(DatabaseConstants.CREATED_DATE , flashCardDto.getDate());
        database = this.getWritableDatabase();
        database.update(DatabaseConstants.DATABASE_TABLE_NAME , contentValues ,
                DatabaseConstants.FLASHCARD_ID + " =?" ,
                new String[]{String.valueOf(flashCardDto.getFlashcardID())});
    }

    //Delete flashcard on database
    public boolean deleteFlashcard(FlashCardDto flashCardDto) {
        database = this.getWritableDatabase();
        int result = database.delete(DatabaseConstants.DATABASE_TABLE_NAME ,
                DatabaseConstants.FLASHCARD_ID + " =?",
                new String[]{String.valueOf(flashCardDto.getFlashcardID())});
        return result == 1;
    }

    //get all flashcard in the database
    public List<FlashCardDto> getAllFlashcards() {
        List<FlashCardDto> flashCardDtos = new ArrayList<>();
        String query = "select * from " + DatabaseConstants.DATABASE_TABLE_NAME;
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query , null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String notes = cursor.getString(2);
                String date = cursor.getString(3);
                FlashCardDto flashCardDto = new FlashCardDto(title , notes , date , id);
                flashCardDtos.add(flashCardDto);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return flashCardDtos;
    }
}
