package com.store.cart;

import com.store.item.Book;

import java.util.ArrayList;

public class Cart implements CartInterface{
    public ArrayList<CartItem> mCartItem = new ArrayList<CartItem>();

    public static int mCartCount = 0;
    public Cart(){
    }
    public void printBookList(ArrayList<Book> booklist){
        for(int i = 0; i < booklist.size(); i++){
            Book bookitem = booklist.get(i);
            System.out.print(bookitem.getBookId() + " | ");
            System.out.print(bookitem.getName() + " | ");
            System.out.print(bookitem.getUnitPrice() + " | ");
            System.out.print(bookitem.getProducer() + " | ");
            System.out.print(bookitem.getDescription() + " | ");
            System.out.print(bookitem.getReleaseDate() + " | ");
        }
    }

    public void insertBook(Book book){
        CartItem bookitem = new CartItem(book);
        mCartItem.add(bookitem);
        mCartCount = mCartItem.size();
    }
    public void deleteBook(){
        mCartItem.clear();
        mCartCount = 0;
    }
    public void printCart(){
        System.out.println("장바구니 상품 목록 :");
        System.out.println("----------------------------------------------------");
        System.out.println(" 제품 ID \t|      수량 \t|         합계");

        for (int i = 0; i < mCartItem.size(); i++){
            System.out.print("     " + mCartItem.get(i).getBookID() + "\t| ");
            System.out.print("     " + mCartItem.get(i).getQuantity() + "\t| ");
            System.out.print("     " + mCartItem.get(i).getTotalPrice() + "\t| ");
            System.out.println("  ");
        }
        System.out.println("----------------------------------------------------");
    }

    public boolean isCartInBook(String bookId){
        boolean flag = false;

        for (int i = 0; i < mCartItem.size(); i++) {
            if (bookId.equals(mCartItem.get(i).getBookID())){
                mCartItem.get(i).setQuantity(mCartItem.get(i).getQuantity() + 1);
                flag = true;
            }

        }
        return flag;
    }

    public void removeCart(int numId){
        mCartItem.remove(numId);
        mCartCount = mCartItem.size();
    }
    public ArrayList<CartItem> getmCartItem(){
        return mCartItem;
    }
    public void setmCartItem(ArrayList<CartItem> mCartItem){
        this.mCartItem = mCartItem;
    }

}
