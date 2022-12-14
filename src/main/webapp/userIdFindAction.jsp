<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.sound.midi.MidiMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.mail.Transport"%>
<%@ page import="javax.mail.Message"%>
<%@ page import="javax.mail.Address"%>
<%@ page import="javax.mail.internet.InternetAddress"%>
<%@ page import="javax.mail.internet.MimeMessage"%>
<%@ page import="javax.mail.Session"%>
<%@ page import="javax.mail.Authenticator"%>
<%@ page import="java.util.Properties"%>
<%@ page import="userInfo.UserInfoDAO"%>
<%@ page import="util.SHA256"%>
<%@ page import="util.Gmail"%>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String userName = null;
	String userTel = null;
	
	if(request.getParameter("userName") != null){
		userName = (String) request.getParameter("userName");
	}

	if(request.getParameter("userTel") != null){
		userTel = (String) request.getParameter("userTel");
	}
	
	UserInfoDAO userInfoDAO = new UserInfoDAO();
	String userID = userInfoDAO.getUserId(userName, userTel);
	String userEmail = userInfoDAO.getUserEmailByNameAndTel(userName, userTel);
	
	if(userID != null){
		String host = "http://localhost:8080/BookLendingProject/";
		String from = "gksmftorsj@gamil.com";
		String to = userEmail;
		String subject = "지니북스 아이디찾기";
		String content = "아이디찾기 결과입니다: " + userID + "<br><br>" +
		"<button style='background-color: #2196f3; width: 12rem; height: 40px;'><a href=" + host + " userLogin.jsp"
		+ " style='color: white; text-decoration: none; font-size: 25px; font-weight: bold;'>로그인하러가기</a></button>";
		
		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		p.put("mail.smtp.ssl.protocols", "TLSv1.2"); 
		
		try{
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html;charset=UTF8");
			Transport.send(msg);
		} catch(Exception e) {
			e.printStackTrace();
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생했습니다. 입력한 정보를 다시 확인해주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="userNavbar.jsp"%>
	<div class="container mt-3" style="max-width: 560px;">
		<div class="alert alert-success mt-4" role="alert">
			요청하신 아이디가 전송되었습니다. 회원가입시 입력했던 이메일에 들어가셔서 확인해주세요.
		</div>
	</div>

	<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
		Copyright &copy; 2022지니북스 Rights Reserved. </footer>

</body>

</html>