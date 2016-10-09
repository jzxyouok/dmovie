package com.example.supertramp.dmovie.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.supertramp.dmovie.orm.bean.BriefVideo;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/**
 * Created by supertramp on 16/7/23.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private final static String DB_NAME = "sqlite_dmovie.db";
    private static final int DB_VERSION = 1;
    private static DatabaseHelper helper;

    private Dao<BriefVideo, Integer> bvDao;

    public DatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {

            TableUtils.createTable(connectionSource, BriefVideo.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

        try {

            TableUtils.dropTable(connectionSource, BriefVideo.class, true);
            onCreate(sqLiteDatabase, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static synchronized DatabaseHelper getHelper(Context context) {

        context = context.getApplicationContext();
        if (helper == null)
        {
            synchronized (DatabaseHelper.class)
            {
                helper = new DatabaseHelper(context);
            }
        }
        return helper;

    }

    //获取BriefVideoDAO
    public Dao<BriefVideo, Integer> getBvDao() throws SQLException
    {
        if (bvDao == null)
        {
            bvDao = getDao(BriefVideo.class);
        }
        return bvDao;
    }

    public void updateTable(String url, int id)
    {
        try
        {

            UpdateBuilder<BriefVideo, Integer> update = bvDao.updateBuilder();
            update.updateColumnValue("image", url).where().eq("id", id);
            connectionSource.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void close()
    {
        super.close();
        bvDao = null;
    }
}
