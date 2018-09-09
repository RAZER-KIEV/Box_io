package software.doit.testapp.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import software.doit.testapp.model.data.Color;

@Dao
public interface ColorDao {

    @Query("SELECT * FROM color")
    List<Color> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Color colors);

    @Delete
    void delete(Color color);

    @Update
    void update(Color color);
}
