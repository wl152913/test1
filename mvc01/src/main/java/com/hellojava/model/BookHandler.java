package com.hellojava.model;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellojava.business.IBookService;
import com.hellojava.entity.Book;

@Controller
public class BookHandler {
	@Autowired
	private IBookService bookService;
	
	@RequestMapping("/loadAll")
	public String loadAllHandler(Map map) {
		//Model request
		List<Book> bookList=bookService.loadAll();
		map.put("bookList", bookList);
		return "index.jsp";
	}
}
