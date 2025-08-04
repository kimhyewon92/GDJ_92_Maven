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
	
	@GetMapping("update")
	public void update(ProductVO productVO, Model model) throws Exception {
//		int result = productService.update()
	}
	
	@PostMapping("update")
	public String update() throws Exception {
		
		return "products/product_form";
	}
	
	
}
