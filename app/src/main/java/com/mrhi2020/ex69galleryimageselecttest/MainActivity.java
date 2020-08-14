package com.mrhi2020.ex69galleryimageselecttest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= findViewById(R.id.iv);
    }

    public void clickFAB(View view) {

        //Gallery app 실행하는 인텐트 객체 생성
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        //선택된 이미지 결과를 가지고 돌아오도록..
        startActivityForResult(intent, 10);
    }

    //startActivityForResult()로 실행한 인텐트가 돌아오면
    //자동으로 실행되는 메소드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                //이미지를 선택했다면..
                if(resultCode != RESULT_CANCELED ){
                    //돌아온 인텐트 객체(data)에게 선택된 이미지의 Uri를 달라고.
                    Uri uri= data.getData();
                    if(uri==null) Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();

                    Glide.with(this).load(uri).into(iv);
                }
                break;
        }

    }
}
