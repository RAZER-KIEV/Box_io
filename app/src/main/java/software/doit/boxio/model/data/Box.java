package software.doit.boxio.model.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import software.doit.boxio.model.db.convertors.ColorConverter;


@Entity
public class Box {
    // in cm
    @PrimaryKey
    private int id;

    private int length, width, height;

    @TypeConverters(ColorConverter.class)
    private Color color;

    private String name;

    public Box(int length, int width, int height, Color color, String name) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = color;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", color=" + color +
                ", name='" + name + '\'' +
                '}';
    }
}
