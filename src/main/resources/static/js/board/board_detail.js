/**
 * 
 */

console.log("detail")

const actions1 = document.getElementsByClassName("action"); // 유사배열 완전배열x map filter foreach 못씀
const actions2 = document.querySelectorAll(".action"); // 배열이라 배열메소드 사용가능
const frm = document.getElementById("frm");

// for(초기식; 조건식; 증감식)
// for(a of actions1)
// for in actions1 // object안 멤버 꺼낼때 사용
// foreach

for(a of actions1){
	a.addEventListener("click", function(e){
		let k = e.target;
		let kind = k.getAttribute("data-kind");
		if(kind=='d'){
			frm.setAttribute("method", "POST");
			frm.setAttribute("action", "./delete");
			frm.submit();
		} else if (kind=='u'){
			frm.setAttribute("action", "./update");
			frm.submit();
		} else {
			frm.setAttribute("action", "./reply");
			frm.submit();
		}
			
	});
}