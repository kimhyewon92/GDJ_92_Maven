/**
 * 
 */

const checkAll = document.getElementById("checkAll");
const ch = document.querySelectorAll(".ch");
const cartFrm = document.getElementById("cartFrm")
const cartDelete = document.getElementById("cartDelete")
const add = document.getElementById("add")

cartDelete.addEventListener("click", ()=>{
	//체크된 것들이 하나 이상인지 검증
	cartFrm.submit();
})

add.addEventListener("click", ()=>{
	//체크된 것들이 하나 이상인지 검증
	cartFrm.setAttribute("action", "/account/add")
	cartFrm.submit();
	//frm.action=""
	//location.href = "/account/add";  // 원하는 URL로 이동
})

// checkAll.onClick = function();
checkAll.addEventListener("click", ()=>{
	
	ch.forEach((item)=>{
		item.checked = checkAll.checked;
	});
})

ch.forEach((item)=>{
	item.addEventListener("click", ()=>{
		let flag=true;
		ch.forEach((c)=>{
			if(!c.checked){
				flag=false;
				return;
			}
		})
		checkAll.checked = flag;
		
		/*checkAll.checked = document.querySelectorAll(".ch:checked").length === ch.length;*/
		
	})
})

cartDelete.addEventListener("click", ()=>{
	ch.forEach((c)=>{
		if(c.checked){
			// value="${vo.productNum}
			
		}
	})
})