package com.azhar.kisahnabi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.kisahnabi.R;
import com.azhar.kisahnabi.adapter.MainAdapter;
import com.azhar.kisahnabi.model.ModelMain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.onSelectData {

    RecyclerView rvName;
    MainAdapter mainAdapter;
    List<ModelMain> modelMain = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvName = findViewById(R.id.rvList);
        rvName.setHasFixedSize(true);
        rvName.setLayoutManager(new LinearLayoutManager(this));

        setupToolbar();
        loadJSON();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbMain);
        toolbar.setTitle("Kisah Nabi");
        setSupportActionBar(toolbar);
    }

    private void loadJSON() {
        try {
            InputStream stream = getAssets().open("kisahnabi.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String tContents = new String(buffer, StandardCharsets.UTF_8);
            try {
                JSONArray obj = new JSONArray(tContents);
                for (int i = 0; i < obj.length(); i++) {
                    JSONObject temp = obj.getJSONObject(i);
                    ModelMain dataApi = new ModelMain();
                    dataApi.setName(temp.getString("name"));
                    dataApi.setThn_kelahiran(temp.getString("thn_kelahiran"));
                    dataApi.setUsia(temp.getString("usia"));
                    dataApi.setDescription(temp.getString("description"));
                    dataApi.setTmp(temp.getString("tmp"));
                    dataApi.setImage_url(temp.getString("image_url"));
                    modelMain.add(dataApi);
                    mainAdapter = new MainAdapter(MainActivity.this, modelMain, this);
                    rvName.setAdapter(mainAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ignored) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelected(ModelMain modelMain) {
        Intent intnt = new Intent(MainActivity.this, DetailActivity.class);
        intnt.putExtra("paramDtl", modelMain);
        startActivity(intnt);
    }
}
