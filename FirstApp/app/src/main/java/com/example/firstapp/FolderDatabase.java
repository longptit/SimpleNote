package com.example.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FolderDatabase extends SQLiteOpenHelper {

    public FolderDatabase(@Nullable Context context) {
        super(context, "DATABASE", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // db = database
        // cậu lệch tạo bảng sqlite
        // ID primary key cho nên id sẽ tự động tăng khi mình thêm dữ liệu vào
        String query = "CREATE TABLE FOLDER(ID INTEGER PRIMARY KEY, TITLE TEXT,CONTENT TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    private static ContactDatabase instance;
//
//    public synchronized static ContactDatabase getInstance(Context context){
//        if (instance == null){
//            instance = new ContactDatabase(context);
//        }
//        return instance;
//    }

    public void addFolder(Folder folder) {
        // lấy ra db để mình có thể thao tác được với
        SQLiteDatabase db = this.getWritableDatabase();
        // tạo content value để thêm dữ liệu vào db
        // lưu ý vì id là primary key => id sẽ tự có giá trị và sẽ tự động tăng giá trị lên khi thêm dữ liệu vào
        // cho nên không cần put id vào
        ContentValues contentValues = new ContentValues();
        contentValues.put("TITLE", folder.getTitle());
        contentValues.put("DETAIL", folder.getDetail());

        db.insert("FOLDER", null, contentValues);
        // đóng
        db.close();
    }

    public ArrayList<Folder> getAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        // câu lệch query

        String query = "SELECT * FROM FOLDER";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Folder> folderArrayList = new ArrayList<>();
        // looping through all rows and adding to list
        // đưa con trỏ về hàng đầu tiên
        if (cursor.moveToFirst()) {

            // dùng vòng lặp do while để đọc dữ liệu từng hàng
            do {
                Folder folder = new Folder();
                // lấy giá trị của từng cột trong database
                // cursor.getColumnIndex("ID")  dùng để lấy ra vị trí của cột trong database
                // cursor.getInt dùng để lấy ra giá trị int theo vị trí của cột
                // nếu cột đấy kiểu dữ liệu là String thì dùng cusor.getrString

                folder.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                folder.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                folder.setDetail(cursor.getString(cursor.getColumnIndex("CONTENT")));
                folderArrayList.add(folder);


                // khi kết thúc khối lệnh. gọi cursor.moveToNext() để chuyển con trỏ đến hàng tiếp theo
            } while (cursor.moveToNext());
        }

        db.close();
        // return note list
        return folderArrayList;
    }


    public void deleteFolder(Folder folder) {
        SQLiteDatabase db = this.getWritableDatabase();
        // xoá hàng theo id, vì id là khoá chính nên điều kiện để xoá hàng thì mình sẽ truyền id vào
        db.delete("FOLDER", "ID = " + folder.getId(), null);
        db.close();
    }

    public void updateFolder(Folder folder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("TITLE", folder.getTitle());
        values.put("DETAIL", folder.getDetail());
        // tương tụ như insẻrt nhưng mình truyền thêm conntent value vào để cập nhật
        db.update("FOLDER", values, "ID = " + folder.getId(), null);
        db.close();
    }
}

