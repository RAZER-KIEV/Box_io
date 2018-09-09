package software.doit.testapp.model.db.convertors;

import android.arch.persistence.room.TypeConverter;

import software.doit.testapp.model.data.Color;

public class ColorConverter {

    @TypeConverter
    public String fromColor(Color color) {
        return color.getColorName();
    }

    @TypeConverter
    public Color toColor(String data) {
        return new Color(data);
    }
}
