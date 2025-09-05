<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat Page</title>
</head>
<body>
	<h1>Chat Page</h1>
	<div>
		<input type="text" id="msg">
		<button id="send">send</button>
	</div>
	
	<script type="text/javascript">
		const send = document.getElementById("send");
		const msg = document.getElementById("msg");
		
		// webSocket 연결
		const socket = new WebSocket("ws://192.168.1.14/chat")
		
// 		on 이벤트가 발생했을때
// 		socket.on =
		socket.addEventListener("open", (e)=>{
			console.log("socket 연결 성공")
		})
		
		socket.addEventListener("message", (e)=>{
			console.log("메세지 수신")
			console.log(e.data)
		})
		
		socket.addEventListener("close", (e)=>{
			console.log("연결 해제")
		})
		
		socket.addEventListener("error", (e)=>{
			console.log("에러 발생")
		})
		
		send.addEventListener("click", ()=>{
			let m = msg.value;
			socket.send(m);
		})
	</script>
</body>
</html>