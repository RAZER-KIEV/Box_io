package software.doit.testapp.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import javax.inject.Singleton;

import software.doit.testapp.model.data.Box;
import software.doit.testapp.model.data.Color;
import software.doit.testapp.model.data.User;
import software.doit.testapp.model.db.dao.BoxDao;
import software.doit.testapp.model.db.dao.ColorDao;
import software.doit.testapp.model.db.dao.UserDao;

@Singleton
@Database(entities = {User.class, Box.class, Color.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract BoxDao boxDao();

    public abstract ColorDao colorDao();
}
