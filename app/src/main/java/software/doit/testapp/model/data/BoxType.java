package software.doit.testapp.model.data;

import software.doit.testapp.R;
import software.doit.testapp.other.utils.Const;

public enum BoxType {

    SMALL(R.array.small_box_size, Const.SMALL_BOX_INDEX, R.array.small_box_colors),
    MEDIUM(R.array.medium_box_size, Const.MEDIUM_BOX_INDEX, R.array.medium_box_colors),
    LARGE(R.array.large_box_size, Const.LARGE_BOX_INDEX, R.array.large_box_colors);

    private int sizesId;
    private int sizeIndex;
    private int colorsId;


    BoxType(int sizesId, int sizeIndex, int colorsId) {
        this.sizesId = sizesId;
        this.sizeIndex = sizeIndex;
        this.colorsId = colorsId;

    }

    public int getSizesId() {
        return sizesId;
    }

    public int getColorsId() {
        return colorsId;
    }

    public int getSizeIndex() {
        return sizeIndex;
    }
}
