// 유효성 검사
function check() {
  if ($.trim($("#signup-username").val()) == "") {
    alert("회원아이디를 입력하세요!");
    $("#signup-username").val("").focus();
    return false;
  }
  if ($.trim($("#signup-password").val()) == "") {
    alert("회원비번을 입력하세요!");
    $("#signup-password").val("").focus();
    return false;
  }
  if ($.trim($("#signup-confirm-password").val()) == "") {
    alert("회원비번확인을 입력하세요!");
    $("#signup-confirm-password").val("").focus();
    return false;
  }
  if (
    $.trim($("#signup-password").val()) !=
    $.trim($("#signup-confirm-password").val())
  ) {
    alert("비번이 다릅니다!");
    $("#signup-password").val("");
    $("#signup-confirm-password").val("");
    $("#signup-password").focus();
    return false;
  }
  if ($.trim($("#signup-name").val()) == "") {
    alert("회원이름을 입력하세요!");
    $("#signup-name").val("").focus();
    return false;
  }

  if ($.trim($("#signup-phone").val()) == "") {
    alert("휴대전화번호를 입력하세요!");
    $("#signup-phone").val("").focus();
    return false;
  }
  if ($.trim($("#join_mailid").val()) == "") {
    alert("메일 아이디를 입력하세요!");
    $("#join_mailid").val("").focus();
    return false;
  }
  if ($.trim($("#signup-email").val()) == "") {
    alert("메일 주소를 입력하세요!");
    $("#signup-email").val("").focus();
    return false;
  }
}

// 아이디 중복 체크
function id_check() {
  $("#idcheck").hide(); //idcheck span 아이디 영역을 숨긴다.
  var memid = $("#signup-username").val();
  //1.입력글자 길이 체크
  if ($.trim($("#signup-username").val()).length < 4) {
    var newtext = '<font color="red">아이디는 4자 이상이어야 합니다.</font>';
    $("#idcheck").text("");
    $("#idcheck").show();
    $("#idcheck").append(newtext); //span 아이디 영역에 경고문자 추가
    $("#signup-username").val("").focus();
    return false;
  }
  if ($.trim($("#signup-username").val()).length > 12) {
    var newtext = '<font color="red">아이디는 12자 이하이어야 합니다.</font>';
    $("#idcheck").text("");
    $("#idcheck").show();
    $("#idcheck").append(newtext);
    $("#signup-username").val("").focus();
    return false;
  }
  //입력아이디 유효성 검사
  if (!validate_userid(memid)) {
    var newtext =
      '<font color="red">아이디는 영문소문자,숫자,_ 조합만 가능합니다.</font>';
    $("#idcheck").text("");
    $("#idcheck").show();
    $("#idcheck").append(newtext);
    $("#signup-username").val("").focus();
    return false;
  }
  $('input[name="preferredLocations"]').change(function () {
    // 체크된 체크박스의 개수를 세기 위한 변수
    var checkedCount = $('input[name="preferredLocations"]:checked').length;

    // 만약 체크된 체크박스의 개수가 3개 미만이면 메시지를 표시
    if (checkedCount < 3) {
      alert("3개이상 선택하세요");
      return false;
    }
  });
  //아이디 중복확인  나중에 처리 해야 할듯?
  $.ajax({
    type: "POST",
    url: "member_idcheck.do",
    data: { memid: memid },
    success: function (data) {
      //           alert("return success="+data);
      if (data == 1) {
        //중복 ID
        var newtext = '<font color="red">중복 아이디입니다.</font>';
        $("#idcheck").text("");
        $("#idcheck").show();
        $("#idcheck").append(newtext);
        $("#signup-username").val("").focus();
        return false;
      } else {
        //사용 가능한 ID
        var newtext = '<font color="blue">사용가능한 아이디입니다.</font>';
        $("#idcheck").text("");
        $("#idcheck").show();
        $("#idcheck").append(newtext);
        $("#signup-password").focus();
      }
    },
    error: function (e) {
      alert("data error" + e);
    },
  }); //$.ajax
}

// 정규 표현식 검사 나중에 처리해야 할듯?
function validate_userid(memid) {
  var pattern = new RegExp(/^[a-z0-9_]+$/); //영문 소문자,숫자 ,_가능,정규표현식

  return pattern.test(memid);
}
