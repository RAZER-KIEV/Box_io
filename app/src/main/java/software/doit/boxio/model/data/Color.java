package software.doit.boxio.model.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Color {

    @PrimaryKey
    @NonNull
    private String colorName;

    public Color(String colorName) {
        this.colorName = colorName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public String toString() {
        return "Color{" +
                "colorName=" + colorName +
                '}';
    }
}
