package ch09;

// TODO: 2023-05-24 개발자 김경돈
public class TryCatch4Mojo {
    public static void main(String[] args) {

        Reso reso = new Reso();

        try(reso) {
            reso.show();
        }catch (Exception e){
            System.out.println("예외처리");
        }

    }
}
class Reso implements AutoCloseable {
    void show(){
        System.out.println("자원 사용");
    }
    public void close() throws Exception{
        System.out.println("자원닫기");
    }
}
