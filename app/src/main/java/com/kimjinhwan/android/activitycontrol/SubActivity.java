package com.kimjinhwan.android.activitycontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);


        //이전 Activity에서 넘어온 intent 객체
        Intent intent = getIntent();    //여기는 null이 안된다.
        //값의 묶음을 꺼낸다.
        Bundle bundle = intent.getExtras(); //여기는 전달된 값이 없으면 null이 된다.
        //3. 단일 값을 꺼낸다. 꺼내기 전에 null 체크를 해줘야 한다.
        if(bundle != null){
            value = bundle.getString("key");
            //3.1 값이 있으면 textView에 출력한다.
            textView.setText(value);

        }
        //위의 두 줄을 합쳐놓은 method : 자체적으로 bundle에 대한 null 처리 로직을 포함하고 있다.
        //String value = intent.getStringExtra("key");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity에서 넘겨받은 값을 int로 변환
                int num1 = Integer.parseInt(value);
                //현재 Activity에 입력된 값을 받아서
                String temp = editText.getText().toString();
                int num2 = Integer.parseInt(temp);
                int result = num1 + num2;
                /* 값 반환하기 */

                //결과값을 intent에 담아서
                Intent intent = new Intent();           //시스템 자원을 생성할 필요가 없으므로 괄호 안에 값을 주지 않음.여기에서 intent는 이미 있는 액티비티에 값을 전달하는 역할만 하기 때문에)
                intent.putExtra("result", result);

                // setResult에 넘겨준다.
                setResult(RESULT_OK, intent);

                //3. 현재 activity를 종료한다.
                finish();

            }
        });

    }
}
