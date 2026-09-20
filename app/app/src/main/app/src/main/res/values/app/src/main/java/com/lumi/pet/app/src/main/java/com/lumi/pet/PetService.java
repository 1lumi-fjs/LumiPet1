package com.lumi.pet;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private int dp(int value) {
        return (int)(value *
                getResources()
                .getDisplayMetrics()
                .density + 0.5f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        LinearLayout root =
                new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL);

        root.setGravity(Gravity.CENTER);

        root.setPadding(
                dp(24),
                dp(24),
                dp(24),
                dp(24));

        TextView title =
                new TextView(this);

        title.setText("🎀 露米桌宠");

        title.setTextSize(30);

        root.addView(title);

        TextView info =
                new TextView(this);

        info.setText(
                "银白长发 · 淡紫色眼睛\n" +
                "尖耳 · 双角 · 细长尾巴\n\n" +
                "Android 桌宠第一版");

        info.setTextSize(17);

        root.addView(info);

        Button permission =
                new Button(this);

        permission.setText(
                "① 开启悬浮窗权限");

        permission.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse(
                                    "package:" +
                                    getPackageName()
                            )
                    );

            startActivity(intent);
        });

        root.addView(permission);

        Button start =
                new Button(this);

        start.setText("② 启动露米");

        start.setOnClickListener(v -> {

            if (Settings.canDrawOverlays(this)) {

                startService(
                        new Intent(
                                this,
                                PetService.class
                        )
                );

            } else {

                permission.performClick();
            }
        });

        root.addView(start);

        Button stop =
                new Button(this);

        stop.setText("③ 收起露米");

        stop.setOnClickListener(v ->
                stopService(
                        new Intent(
                                this,
                                PetService.class
                        )
                )
        );

        root.addView(stop);

        setContentView(root);
    }
}
