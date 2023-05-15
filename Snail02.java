package algorian;

public class Snail02 {
    public static void main(String[] args) {

        int[][] array;
        array = new int[11][11];
        int k, c, i, j, f, q, count;

        k = 0; c = 1; i = 1; j = 0; q=6;
//int q = 6 을 f =5 로 바꿔서 해보기
        count = 0;

        while (count < 11) {
            for (f = 1; f<q; f++) {
                k += 1;
                j += c;
                array[i][j]=k;

            }
            q -=1;
            for (f=1; f<q; f++) {
                k+=1;
                i=i+c;
                array[i][j]=k;

            }
            c = c * -1;
            count +=1;
        }
        for (i =1;  i<6; i++) {
            for (j = 1; j < 6; j++) {
                System.out.printf("%3s", array[i][j]);
            }
            System.out.println(); //가독성을 위함
        }
    }
}
