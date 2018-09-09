package software.doit.boxio.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import software.doit.boxio.model.data.Box;

@Dao
public interface BoxDao {

    @Query("SELECT * FROM box")
    List<Box> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Box box);

    @Delete
    void delete(Box box);

    @Update
    void update(Box box);

}
