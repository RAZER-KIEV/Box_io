package software.doit.boxio.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import software.doit.boxio.model.data.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    Single<List<User>> getAll();

    @Query("SELECT * FROM user WHERE email LIKE :mail")
    Single<User> findByEmail(String mail);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
}
