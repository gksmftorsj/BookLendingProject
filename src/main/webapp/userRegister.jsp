<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.input-form {
      max-width: 680px;

      margin-top: 80px;
      padding: 32px;

      background: #fff;
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
      -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
    }
</style>
<body>
	<%@ include file="userNavbar.jsp"%>
	
	<div class="container mt-5">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-3">회원가입</h4>
        <form class="validation-form" novalidate method="post" action="./userRegisterAction.jsp">
          <div class="row">
            <div class="col-md-4 mb-3">
              <label for="userName">이름</label>
              <input type="text" class="form-control" id="userName" name="userName" required>
              <div class="invalid-feedback">
                이름을 입력해주세요.
              </div>
            </div>
            <div class="col-md-4 mb-3">
              <label for="userID">아이디</label>
              <input type="text" class="form-control" id="userID" name="userID" required>
              <div class="invalid-feedback">
                아이디를 입력해주세요.
              </div>
            </div>
            <div class="col-md-4 mb-3">
              <label for="userPassword">비밀번호</label>
              <input type="password" class="form-control" id="userPassword" name="userPassword" required>
              <div class="invalid-feedback">
                비밀번호을 입력해주세요.
              </div>
            </div>
          </div>
		<div class="row">
          <div class="col-md-6 mb-3">
            <label for="userEmail">이메일</label>
            <input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="test@example.com" required>
            <div class="invalid-feedback">
              이메일을 입력해주세요.
            </div>
          </div>
          <div class="col-md-6 mb-3">
            <label for="userTel">연락처</label>
            <input type="tel" class="form-control" id="userTel" name="userTel" placeholder="010-1234-5678" required>
            <div class="invalid-feedback">
              연락처를 입력해주세요.
            </div>
          </div>
        </div>

          <div class="mb-3">
            <label for="userAddress">주소</label>
            <input type="text" class="form-control" id="userAddress" name="userAddress" placeholder="서울특별시 강남구" required>
            <div class="invalid-feedback">
              주소를 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="userAddressDetail">상세주소<span class="text-muted">&nbsp;(필수 아님)</span></label>
            <input type="text" class="form-control" id="userAddressDetail" name="userAddressDetail" placeholder="상세주소를 입력해주세요.">
          </div>

          <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="aggrement" required>
            <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
          </div>
          <div class="mb-4"></div>
          <button class="btn btn-primary btn-lg btn-block" type="submit">가입 완료</button>
        </form>
      </div>
    </div>
    <footer class="my-3 text-center text-small">
      <p class="mb-1">&copy; 2022 지니북스</p>
    </footer>
  </div>
  <script>
    window.addEventListener('load', () => {
      const forms = document.getElementsByClassName('validation-form');

      Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  </script>

</body>
</html>