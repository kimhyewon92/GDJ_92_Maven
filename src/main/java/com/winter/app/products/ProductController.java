package com.winter.app.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.notice.NoticeService;

@Controller
@RequestMapping(value="/products/*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("list")
	public void list(Model model) throws Exception {
		List<ProductVO> list = productService.list();
		model.addAttribute("list", list);
	}
	
	@GetMapping("detail")
	public void detail(ProductVO productVO, Model model) throws Exception {
		ProductVO detailVO = productService.detail(productVO);
		model.addAttribute("detailVO", detailVO);
	}
	
	@GetMapping("add")
	public String add() throws Exception {
		return "products/product_form";
	}
	
	@PostMapping("add")
	public String add(ProductVO productVO, Model model) throws Exception {
		int result = productService.insert(productVO);
		
		String msg = "상품 등록 실패";
		
		if (result > 0) {
			msg="상품 등록이 완료되었습니다.";
		}
		
		String url = "./list";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
	
	@GetMapping("update")
	public String updatePage(ProductVO productVO, Model model) throws Exception {
		productVO = productService.detail(productVO);
		model.addAttribute("productVO", productVO);
		return "products/product_form";
	}
	
	@PostMapping("update")
	public String update(ProductVO productVO, Model model) throws Exception {
		int result = productService.update(productVO);
		
		String msg = "상품 수정 실패";
		
		if (result > 0) {
			msg="상품 수정이 완료되었습니다.";
		}
		
		String url = "./list";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
	
	@PostMapping("delete")
	public String delete(ProductVO productVO, Model model) throws Exception {
		int result = productService.delete(productVO);
		
		String msg = "상품 삭제 실패";
		
		if (result > 0) {
			msg = "상품 삭제가 완료되었습니다.";
		}
		
		String url = "./list";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
	
}
