package software.doit.boxio.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import javax.inject.Singleton;

import software.doit.boxio.model.data.Box;
import software.doit.boxio.model.data.Color;
import software.doit.boxio.model.data.User;
import software.doit.boxio.model.db.dao.BoxDao;
import software.doit.boxio.model.db.dao.ColorDao;
import software.doit.boxio.model.db.dao.UserDao;

@Singleton
@Database(entities = {User.class, Box.class, Color.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract BoxDao boxDao();

    public abstract ColorDao colorDao();
}
