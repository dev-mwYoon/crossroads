FileList.prototype.forEach = Array.prototype.forEach;
FileList.prototype.indexOf = Array.prototype.indexOf;
FileList.prototype.map = Array.prototype.map;
FileList.prototype.filter = Array.prototype.filter;

/*사진 첨부*/

/*첨부된 이미리즐을 배열에 넣고 미리보기 */
imageLoader = function(file){
    var sel_files = [];
    sel_files.push(file);
    var reader = new FileReader();
    reader.onload = function(ee){
        let img = document.createElement('img');
        img.setAttribute("src", ee.target.result);
        img.setAttribute("class", "review-images");
        img.setAttribute("id", `${file.lastModified}`); // image태그에 id로 lastModiferd값 추가
        document.querySelector("div#image_container").appendChild(img);
        if(document.querySelector('#image_container').childElementCount >= 1){
            console.log("들어옴");
            $('#add-image').text("이미지 재등록");
        }
    }

    reader.readAsDataURL(file);
}



function setThumbnail(event) {
    document.querySelector("div#image_container").replaceChildren();
    // var reader = new FileReader();
    // reader.onload = function(event) {
    var img = document.createElement("img");
    var images=  document.querySelector("#image1");

    var files = event.target.files;
    var fileArr = Array.prototype.slice.call(files);
    if(document.querySelector('#image_container').childElementCount >= 3){
        $(".modal-wrapper").css('display', 'block');
        $(".modal-message").text("이미지는 최대 3개까지 입력 가능합니다.");
    } else{
        if(images.files.length > 3){
            $(".modal-wrapper").css('display', 'block');
            $(".modal-message").text("이미지는 최대 3개까지 입력 가능합니다.");
        } else {
            for(f of fileArr){
                imageLoader(f);
            }
        }
    }
}


// 이미지 태그 삭제
document.querySelector("#image_container").addEventListener("click", function(event){
    var images =  document.querySelector("#image1"); // 추가된 FileList
    const removeTargetId = event.target.id; // 클릭한 이벤트 객체의 id값
    const dataTransfer = new DataTransfer(); // dataTransfer : FileList에 다시 담기 전에 담아두는 객체 선언

    /* dataTransfer에 선택한 파일 제외하고 input[type=file] 담기  */
    Array.from(images.files)
        .filter(image => image.lastModified != removeTargetId)
        .forEach(image => {
            console.log("test");
            console.log(image);
            dataTransfer.items.add(image);
        });

    images.files = dataTransfer.files;
    console.log(dataTransfer.files);
    console.log(images.files);
    document.querySelector("div#image_container").removeChild(event.target); // 보여지는 화면에만 삭제
});


// 별점
const rating = document.querySelector('.rating');
const score = document.querySelector('.score');

/* 별점 옆에 점수 */
// rating.addEventListener('change', function(e) {
//   score.textContent = `${e.target.value}점`;
// });


// 리뷰 작성 확인 및 제출
$("#complete-button").on('click', function(){
    let flag1 = true;
    let flag2 = true;
    console.log("들어옴");
    var $title = $("#input-title");
    var $content = $("#input-content");

    if($title.val().length < 1){
        // alert("제목을 작성해주세요.");
        $(".modal-wrapper").css('display', 'block');
        $(".modal-message").text("제목을 작성해주세요.");
        $("#input-title").focus();
        flag1 = false;
    } else if($content.val().length < 1){
        // alert("내용을 작성해주세요.");
        $(".modal-wrapper").css('display', 'block');
        $(".modal-message").text("내용을 작성해주세요.");
        $("#input-content").focus();
        flag2 = false;
    }

    if(flag1 && flag2){
        // alert("완료");
        document.reviewForm.submit();
    }

})


/* 모달 창 띄우기, 끄기 */
$(".modal-close-btn").on("click", function(){
    $(".modal-wrapper").css('display', 'none');
})


/*게시판 작성 저장하기*/
$('#complete-button').on('click', function () {
    // 이미지 업로드 및 UUID 받기
    var formData = new FormData();
    $('input[type=file]').each(function (index, fileInput) {
        $.each(fileInput.files, function (i, file) {
            formData.append('file', file);
        });
    });

    var uuids = [];
    $.ajax({
        url: '/files/upload',
        type: 'POST',
        enctype: 'multipart/form-data',
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            uuids = data;
            // 이미지 UUID를 hidden input에 저장
            for (var i = 0; i < uuids.length; i++) {
                var hiddenInput = $('<input>')
                    .attr('type', 'hidden')
                    .attr('name', 'fileUUIDs')
                    .val(uuids[i]);
                $('form[name=boardForm]').append(hiddenInput);
            }
            // 게시글 저장 요청
            $('form[name=boardForm]').submit();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
});