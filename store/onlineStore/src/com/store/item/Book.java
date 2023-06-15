package com.store.item;

public class Book extends Item {
    private String producer;
    private String description;
    private String category;
    private String releaseDate;

    public Book(String dollId, String name, int unitPrice){
        super(dollId, name, unitPrice);
    }
    public Book(String bookId, String name, int unitPrice, String producer, String description, String category, String releaseDate){
        super(bookId,
                name,
                unitPrice);
        this.producer = producer;
        this.description = description;
        this.category = category;
        this.releaseDate = releaseDate;
    }
    public String getBookId(){
        return bookId;
    }
    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getUnitPrice(){
        return unitPrice;
    }
    public void setUnitPrice(int unitPrice){
        this.unitPrice = unitPrice;
    }
    public String getProducer(){
        return producer;
    }
    public void setProducer(String producer){
        this.producer = producer;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public String getReleaseDate(){
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }


}
