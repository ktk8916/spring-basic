package hello.core.singleton;

public class SingletonService {

    //static 선언으로 SingletonService를 자바 실행과 같이 메모리에 올림
    private static final SingletonService instance = new SingletonService();

    //SingletonService가 필요한 곳에서 사용할 수 있게 하는 instance를 반환하는 getInstance함수
    public static SingletonService getInstance(){
        return instance;
    }

    //생성자를 private으로 만들어서 다른 곳에선 생성할 수 없도록 막음
    private SingletonService(){};
    
    public void logic(){
        System.out.println("SingletonService logic 실행!");
    }

}
