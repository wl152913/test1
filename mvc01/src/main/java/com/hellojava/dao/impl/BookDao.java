package com.hellojava.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hellojava.dao.IBookDao;
import com.hellojava.entity.Book;

@Repository  
//@Repository、@Service、@Controller，它们分别对应存储层Bean，业务层Bean，和展示层Bean
public class BookDao extends JdbcDaoSupport implements IBookDao {
	@Autowired
	public void init(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public List<Book> loadAll() {
		String sql="select * from book";
		List<Book> bookList=new ArrayList<>();
		System.out.println(bookList);
 		this.getJdbcTemplate().query(sql, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				do {
					Book book =new Book();
					book.setBookId(rs.getInt("bookId"));
					book.setBookName(rs.getString("bookName"));
					book.setBookAuthor(rs.getString("bookAuthor"));
					book.setBookPrice(rs.getDouble("bookPrice"));
					book.setBookInfo(rs.getString("bookInfo"));
					bookList.add(book);
					
				}while(rs.next());			
			}
		});
		return bookList;
	}
	
	
}
