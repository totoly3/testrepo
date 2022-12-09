<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<STYle> 
  .dropBox h1 {
        font-size: 1.8rem;
      }
      
      /* ----------- */
      
      @import url('https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css');
      
      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: Pretendard, 'Malgun Gothic', sans-serif;
      }

</STYle>
  
</head>
<body>
    <div class="dropBox">
        <h1>이곳에 파일을 드롭해주세요. </h1>
      </div>

      <SCript>

const $drop = document.querySelector(".dropBox");
const $title = document.querySelector(".dropBox h1");

// 드래그한 파일 객체가 해당 영역에 놓였을 때
$drop.ondrop = (e) => {
  e.preventDefault();
  $drop.className = "dropBox";
   
  // 파일 리스트
  const files = [...e.dataTransfer?.files];

  $title.innerHTML = files.map(v => v.name).join("<br>");
}

// ondragover 이벤트가 없으면 onDrop 이벤트가 실핻되지 않습니다.
$drop.ondragover = (e) => {
  e.preventDefault();
}

// 드래그한 파일이 최초로 진입했을 때
$drop.ondragenter = (e) => {
  e.preventDefault();
 
  $drop.classList.add("active");
}

// 드래그한 파일이 영역을 벗어났을 때
$drop.ondragleave = (e) => {
  e.preventDefault();
  
  $drop.classList.remove("active");
}
      </SCript>
</body>
</html>