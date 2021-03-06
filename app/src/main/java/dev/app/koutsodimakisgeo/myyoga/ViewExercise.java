package dev.app.koutsodimakisgeo.myyoga;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dev.app.koutsodimakisgeo.myyoga.Database.YogaDB;
import dev.app.koutsodimakisgeo.myyoga.Utils.Common;

public class ViewExercise extends AppCompatActivity {

    int image_id;
    String name;

    TextView timer,title;
    ImageView detail_image;

    Button btnStart;

    YogaDB yogaDB ;

    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);

        yogaDB = new YogaDB(this);

        timer = (TextView)findViewById(R.id.timer);
        title = (TextView)findViewById(R.id.title);
        detail_image = (ImageView)findViewById(R.id.detail_image);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (!isRunning){

                    btnStart.setText("DONE");

                    int timeLimit = 0;
                    if (yogaDB.getSettingMode() == 0)
                        timeLimit = Common.TIME_LIMIT_EASY;
                    else if (yogaDB.getSettingMode() == 1)
                    timeLimit = Common.TIME_LIMIT_MEDIUM;
                      else if (yogaDB.getSettingMode() == 2)
                    timeLimit = Common.TIME_LIMIT_HARD;

                    new CountDownTimer(timeLimit,1000){

                        @Override
                        public void onTick(long millisUntilFinished) {
                            timer.setText(""+millisUntilFinished / 1000);
                        }

                        @Override
                        public void onFinish() {
                            //ads here
                            Toast.makeText(ViewExercise.this,"Finish",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                }else{
                    Toast.makeText(ViewExercise.this,"Finish",Toast.LENGTH_SHORT).show();
                    finish();
                }
                isRunning = !isRunning;
            }
        });
        timer.setText("");

        if (getIntent() != null ){
            image_id = getIntent().getIntExtra("image_id",-1);
            name=getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title.setText(name);

        }
    }
}
