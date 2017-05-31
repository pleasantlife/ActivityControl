# ActivityControl
Activity 간에 값을 주고받기

## startActivityForResult

이 함수로 Activity를 실행하면 실행된 Activity가 종료되면서 아래의 onActivityResult 함수를 호출해준다.

'''java
// 액티비티를 실행하는 버튼을 구분하기 위한 플래그
public static final int BUTTON_START = 99;

Intent intent = new Intent(this, SubActivity.class);
startActivityForResult(intent, BUTTON_RESULT);
'''


## setResult
호출되는 SubActivity.class에 작성되는 코드

'''java

Intent intent = new intent(); // 이미 생성된 Activity를 사용하기 때문에 Context를 필요로 하지 않음.
intent.putExtra("result", "결과값");

//RESULT_OK는 부모 Activity에 이미 정의되어 있는 플래그값으로 처리가 성공적이라는 것을 의미한다.
//setResult 함수는 현재 Activity에 Intent를 저장하기 때문에 19번째 줄에서 언급한 것과 같이 Context를 따로 필요로 하지 않는다.
setResult(RESULT_OK, intent);

'''

## onActivityResult

'''java
// requestCode = startActivityForResult를 실행한 주체를 구분하기 위한 플래그
// resultCode = 결과처리의 성공여부 | RESULT_OK = 성공
// data = 돌려받은 값이 담겨 있는 Intent
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

'''
