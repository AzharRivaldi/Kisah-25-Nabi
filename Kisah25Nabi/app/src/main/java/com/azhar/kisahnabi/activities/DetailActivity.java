package com.azhar.kisahnabi.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.azhar.kisahnabi.R;
import com.azhar.kisahnabi.model.ModelMain;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailActivity extends AppCompatActivity {

    ModelMain mdlDetail;
    String dtlName, dtlThn, dtlUsia, dtlDesc, dtlTmp;
    TextView tvThn, tvUsia, tvDesc, tvTmp;
    Toolbar tbDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        tbDetail = findViewById(R.id.tbDetail);
        setSupportActionBar(tbDetail);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mdlDetail = (ModelMain) getIntent().getSerializableExtra("paramDtl");

        final ProgressBar progressBar = findViewById(R.id.progress);
        ImageView imgHeader = findViewById(R.id.cover);
        Glide.with(this)
                .load(mdlDetail.getImage_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imgHeader);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);

        if (mdlDetail != null) {
            dtlName = mdlDetail.getName();
            dtlThn = mdlDetail.getThn_kelahiran();
            dtlUsia = mdlDetail.getUsia();
            dtlDesc = mdlDetail.getDescription();
            dtlTmp = mdlDetail.getTmp();

            collapsingToolbarLayout.setTitle(mdlDetail.getName());

            tvTmp = findViewById(R.id.tmp);
            tvTmp.setText(dtlTmp);

            tvThn = findViewById(R.id.thn);
            tvThn.setText(dtlThn);

            tvUsia = findViewById(R.id.usia);
            tvUsia.setText(dtlUsia);

            tvDesc = findViewById(R.id.description);
            tvDesc.setText(dtlDesc);

        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
