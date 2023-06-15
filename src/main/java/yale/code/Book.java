package yale.code;

public class Book {
  public Book(){}
  public Book(String title, String isbn, String language){
    this.title = title;
    this.isbn = isbn;
    this.language = language;
  }

  private String title;
  private String isbn;
  private String language;
  private String image;
  private String date;

  public void setTitle(String title){
    this.title = title;
  }

  public void setIsbn(String isbn){
    this.isbn = isbn;
  }

  public void setLanguage(String language){
    this.language = language;
  }

  public String getTitle(){
    return this.title;
  }

  public String getIsbn(){
    return this.isbn;
  }

  public String getLanguage(){
    return this.language;
  }


}
