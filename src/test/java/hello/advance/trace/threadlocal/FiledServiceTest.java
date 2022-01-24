package hello.advance.trace.threadlocal;

import hello.advance.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FiledServiceTest {
    private FieldService fieldService = new FieldService();

    @Test
    void field(){
        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
//        sleep(2000); // 동시성 문제가 발생 x
        sleep(100); // 동시성 문제 발생 o
        threadB.start();

        sleep(3000); // 메인쓰레드 종료대기
        log.info("main exit");

    }

    public void sleep(int mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
