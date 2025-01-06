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
