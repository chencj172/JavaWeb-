package com.chenchangjie.service.impl;

import com.chenchangjie.entity.Book;
import com.chenchangjie.entity.Borrow;
import com.chenchangjie.repository.BookRepository;
import com.chenchangjie.repository.BorrowRepository;
import com.chenchangjie.repository.impl.BookRepositoryImpl;
import com.chenchangjie.repository.impl.BorrowRepositoryImpl;
import com.chenchangjie.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository = new BookRepositoryImpl();
    private BorrowRepository borrowRepository = new BorrowRepositoryImpl();
    private final static int LIMIT = 6;

    @Override
    public List<Book> FindAll(int page) {
        int index = (page - 1) * LIMIT;
        return bookRepository.FindAll(index,LIMIT);
    }

    @Override
    public int getPages() {
        int Cnt =  bookRepository.count();
        if(Cnt % LIMIT == 0){
            return Cnt / LIMIT;
        }else{
            return Cnt / LIMIT + 1;
        }
    }

    @Override
    public void Borrow(int bookId,int readerId) {
        //borrow表中还需要borrowtime，returntime
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime = sdf.format(date);
        //还书时间加上两周
        Calendar calendar = Calendar.getInstance();
        int dates = calendar.get(Calendar.DAY_OF_YEAR) + 14;  //当前时间加上14天
        calendar.set(Calendar.DAY_OF_YEAR,dates);   //在set回去
        Date date1 = calendar.getTime();  //转化成日期对象
        String returnTime = sdf.format(date1);
        borrowRepository.Borrow(bookId,readerId,borrowTime,returnTime,null,0);
    }

    @Override
    public List<Borrow> ShowBorrowBookByReadId(int readerID,int page) {
        int index = (page - 1) * LIMIT;
        return borrowRepository.findBorrowBookByReadId(readerID,index,LIMIT);
    }

    @Override
    public int getBorrowBooksPages(int readerId) {
        int Cnt = borrowRepository.countBorrow(readerId);
        if(Cnt % LIMIT == 0){
            return Cnt / LIMIT;
        }else{
            return Cnt / LIMIT + 1;
        }
    }

    @Override
    public List<Borrow> findAllBorrowByState(int page,int state) {
        int index = (page - 1) * LIMIT;
        return borrowRepository.findAllBorrowByState(index,LIMIT,state);
    }

    @Override
    public int getBorrowPagesByState(int state) {
        int Cnt = borrowRepository.countByState(state);
        if(Cnt % LIMIT == 0){
            return Cnt / LIMIT;
        }else{
            return Cnt / LIMIT + 1;
        }
    }

    @Override
    public void handleBorrow(Integer borrowId, Integer adminId, Integer state) {
        borrowRepository.handle(borrowId,adminId,state);
    }
}
