let sec = 15;
let time = sec * 1000;

Timer.value = sec;

function TIMER() {
  PlAYTIME = setInterval(function () {
    time = time - 1000; //1초씩 줄어듦

    if (sec > 0) { //sec=60 에서 1씩 빼서 출력해준다.
      sec = sec - 1;
      Timer.value = sec;

    }
    if (sec === 0) { //다시 15초
      sec = 15;
      Timer.value = sec;
      document.getElementById('QR').contentDocument.location.reload(true);
    }

  }, 1000); //1초마다
}


TIMER();
