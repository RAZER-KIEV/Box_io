package software.doit.testapp.view.main;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import software.doit.testapp.R;
import software.doit.testapp.databinding.ActivityMainBinding;
import timber.log.Timber;

public class MainActivity extends DaggerAppCompatActivity {

    ActivityMainBinding binding;

    @Inject
    MainActivityViewModel mainActivityViewModel;

    private ArrayAdapter<String> sizeAdapter, colorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mainActivityViewModel);
        if (binding.getViewModel() != null) {
            binding.getViewModel().collectDataForSizeSpinner();
        }
        binding.getViewModel().toastMessage.observe(this, o -> {
            Timber.d("toastMessage changed ");
            Toast.makeText(MainActivity.this, o, Toast.LENGTH_SHORT).show();
        });
        observeSizes();
        observeColors();
    }


    private void observeColors() {
        binding.getViewModel().colors.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                colorAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, strings);
                colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                Spinner spinner = binding.colorSpinner;
                spinner.setAdapter(colorAdapter);
                spinner.setPrompt("Colors");
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        binding.getViewModel().onColorChosen(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        Timber.d("colorAdapter.onNothingSelected");
                    }
                });
            }
        });
    }

    private void observeSizes() {
        binding.getViewModel().boxSizes.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                sizeAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, strings);
                sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                Spinner spinner = findViewById(R.id.sizeSpinner);
                spinner.setAdapter(sizeAdapter);
                spinner.setPrompt("Size");
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        binding.getViewModel().onSizeChosen(i);
                        Timber.d("sizeAdapter.onItemSelected " + i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        Timber.d("sizeAdapter.onNothingSelected");
                    }
                });
                spinner.setSelection(-1);
                spinner.setSelected(true);
            }
        });

        binding.getViewModel().chosenSizeIndex.observe(this, integer -> {
            Timber.d("chosenSizeIndex.onChanged.integer = " + integer);
            binding.colorSpinner.setVisibility(View.VISIBLE);
            binding.choseColorTv.setVisibility(View.VISIBLE);
        });
    }
}
