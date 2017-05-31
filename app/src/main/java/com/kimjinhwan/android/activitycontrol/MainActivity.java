package com.kimjinhwan.android.activitycontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.kimjinhwan.android.activitycontrol.R.id.editText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart;
    Button btnResult;
    Intent intent;
    EditText editNumber;

    public static final int BUTTON_START = 98;
    public static final int BUTTON_RESULT = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, SubActivity.class);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnResult = (Button) findViewById(R.id.btnResult);

        editNumber = (EditText) findViewById(R.id.editNumber);

        btnStart.setOnClickListener(this);
        btnResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            //일반적인 Activity Start
            case R.id.btnStart:
                startActivity(intent);
                break;
            //값을 돌려받는 Activity Start
            case R.id.btnResult:
                intent.putExtra("key",editNumber.getText().toString());
                                            //변수 = 값.
                startActivityForResult(intent, BUTTON_RESULT);          //BUTTON_RESULT는 호출자(버튼)을 구분하는 구분자.
                //start SubActivity > SubActivity.finish()>결과값을 돌려준다>MainActivity.onActivityResult(결과값)
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {                 //Intent에 결과값이 담겨 옴.
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
        switch(requestCode) {
            case BUTTON_RESULT:
                //Intent인 data에서 result 변수로 값을 꺼내는데
                //값이 없을 경우 디폴트값으로 -1을 사용한다.
                int result = data.getIntExtra("result", -1);
                editNumber.setText("결과값 =" + result);
                break;
            case BUTTON_START:
                Toast.makeText(this, "Start 버튼을 눌렀다가 돌아옴.", Toast.LENGTH_SHORT).show();
                break;
        }
        }

    }
}
