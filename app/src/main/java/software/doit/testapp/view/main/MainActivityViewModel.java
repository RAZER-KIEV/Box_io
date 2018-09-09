package software.doit.testapp.view.main;

import android.arch.lifecycle.MutableLiveData;
import android.view.View;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

import software.doit.testapp.model.data.Box;
import software.doit.testapp.model.data.BoxType;
import software.doit.testapp.model.data.Color;
import software.doit.testapp.model.repository.Repository;
import software.doit.testapp.other.utils.Const;
import software.doit.testapp.view.BaseViewModel;
import timber.log.Timber;

public class MainActivityViewModel extends BaseViewModel {

    public Box chosenBox;

    //data for spinners:
    MutableLiveData<List<String>> boxSizes = new MutableLiveData<>();
    MutableLiveData<Integer> chosenSizeIndex = new MutableLiveData<>();

    MutableLiveData<List<String>> colors = new MutableLiveData<>();
    MutableLiveData<Integer> chosenColorIndex = new MutableLiveData<>();

    MutableLiveData<Boolean> addNameOnBox = new MutableLiveData<>();

    List<Integer> smallBoxSizes;
    List<Integer> mediumBoxSizes;
    List<Integer> largeBoxSizes;

    private BoxType chosenBoxType;

    MainActivityViewModel(Repository repository) {
        super(repository);
    }

    public void collectDataForSizeSpinner() {
        List<String> boxSizes = new ArrayList<>();
        boxSizes.add(buildSizeString(smallBoxSizes = repository.getSmallBoxSizes()));
        boxSizes.add(buildSizeString(mediumBoxSizes = repository.getMediumBoxSizes()));
        boxSizes.add(buildSizeString(largeBoxSizes = repository.getLargeBoxSizes()));
        Timber.d("collectDataForSizeSpinner. \nboxSizes = " + boxSizes);
        this.boxSizes.setValue(boxSizes);
    }

    public void collectDataForColorSpinner() {
        List<String> boxColors = new ArrayList<>();
        List<Color> colorList = null;
        if (chosenSizeIndex.getValue() != null) {
            switch (chosenSizeIndex.getValue()) {
                case Const.SMALL_BOX_INDEX:
                    colorList = repository.getSmallBoxColors();
                    chosenBoxType = BoxType.SMALL;
                    break;
                case Const.MEDIUM_BOX_INDEX:
                    chosenBoxType = BoxType.MEDIUM;
                    colorList = repository.getMediumBoxColors();
                    break;
                case Const.LARGE_BOX_INDEX:
                    chosenBoxType = BoxType.LARGE;
                    colorList = repository.getLargeBoxColors();
                    break;
            }
            if (colorList != null) {
                for (Color color : colorList) {
                    boxColors.add(color.getColorName());
                }
            }
            Timber.d("collectDataForColorSpinner. boxColors = " + boxColors);
            this.colors.setValue(boxColors);
        }
    }

    private String buildSizeString(List<Integer> sizes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sizes.size(); i++) {
            sb.append(sizes.get(i)).append(" cm");
            if (i != sizes.size() - 1)
                sb.append(" x ");
        }
        return sb.toString();
    }

    public void onSizeChosen(int index) {
        collectDataForColorSpinner();
        chosenSizeIndex.setValue(index);
    }

    public void onColorChosen(int index) {
        chosenColorIndex.setValue(index);
    }

    public void onAddNameChanged(CompoundButton var1, boolean isChecked) {
        Timber.d("onAddNameChanged. isChecked = " + isChecked);
        addNameOnBox.setValue(isChecked);
    }

    public void orderBox(View view) {
        Timber.d("orderBox. checkInfo() = " + checkInfo());
        if (!checkInfo()) return;
        Box orderedBox = buildOrderedBox();
        Timber.d("orderedBox.orderedBox = " + orderedBox);
        if (orderedBox != null)
            repository.orderTheBox(orderedBox)
                    .subscribe(orderBoxResponse -> {
                        Timber.d("You successfully ordered the Box");
                        toastMessage.setValue("You successfully ordered the Box!");
                    }, throwable -> {
                        Timber.d("Order fail");
                        toastMessage.setValue("Order fail!" + throwable.getMessage());
                    });
    }

    private Box buildOrderedBox() {
        Box box = null;
        String name = "";
        List<Integer> sizes = null;
        if (chosenBoxType == BoxType.SMALL)
            sizes = smallBoxSizes;
        else if (chosenBoxType == BoxType.MEDIUM)
            sizes = mediumBoxSizes;
        else if (chosenBoxType == BoxType.LARGE)
            sizes = largeBoxSizes;
        if (sizes != null) {
            if (addNameOnBox.getValue() != null && addNameOnBox.getValue()) {
                name = repository.getCurrentUser().getName();
            }
            box = new Box(sizes.get(0), sizes.get(1), sizes.get(2), new Color(colors.getValue().get(chosenColorIndex.getValue())), name);
        }
        return box;
    }

    private boolean checkInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (chosenSizeIndex.getValue() == null) {
            stringBuilder.append("You need to chose a size of the box.");
        }
        if (chosenColorIndex.getValue() == null) {
            stringBuilder.append("You need to chose a color of the box.");
        }
        if (stringBuilder.length() > 0) {
            toastMessage.setValue(stringBuilder.toString());
            Timber.d("checkInfo.stringBuilder.toString() = " + stringBuilder.toString());
            return false;
        }
        return true;
    }
}
