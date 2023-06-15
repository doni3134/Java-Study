package com.store.cart;

import com.store.item.Book;
public class CartItem {
    public Book itemBook;
    public String bookID;
    public int quantity;
    public int totalPrice;

    public CartItem(){

    }

    public CartItem(Book bookList){
        this.itemBook = bookList;
        this.bookID = bookList.getBookId();
        this.quantity = 1;
        updateTotalPrice();

    }
    public Book getItemBook(){
        return itemBook;
    }
    public void setItemBook(Book itemBook){
        this.itemBook = itemBook;
    }
    public void setTotalPrice(int totalPrice){
        this.totalPrice = totalPrice;
    }
    public String getBookID(){
        return bookID;
    }
    public void setBookID(String bookID){
        this.bookID = bookID;
        this.updateTotalPrice();
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
        this.updateTotalPrice();
    }
    public int getTotalPrice(){
        return totalPrice;
    }
    public void updateTotalPrice(){
        totalPrice = this.itemBook.getUnitPrice()*this.quantity;
    }


}
