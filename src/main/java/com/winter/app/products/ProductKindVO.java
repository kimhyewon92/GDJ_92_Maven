package com.winter.app.products;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductKindVO {
	
	private Long kindNum;
	private String kindName;
	
	// 1:N (예금상품의 종류가 뭐가있는지..확인..)
	// private List<ProductVO> list;
}
