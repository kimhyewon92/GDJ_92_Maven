/**
 * 
 */


const cartAdd = document.getElementById("cartAdd")
const add = document.getElementById("add")


cartAdd.addEventListener("click", ()=>{
	let params = new URLSearchParams();
	params.append("productNum", cartAdd.getAttribute("data-product-num"));
	
	fetch("/member/cartAdd", {
		method:"post",
		body: params
	})
	.then(r=>r.json())
	.then(r=>{
		if(r>0){
			let result = confirm("장바구니 목록")
			if(result){
				location.href="/member/cartList"
			}
		}
	})
	.catch(e=>{
		alert("등록 실패")
	})
	
	
})