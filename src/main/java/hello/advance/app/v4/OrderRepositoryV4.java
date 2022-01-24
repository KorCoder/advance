package hello.advance.app.v4;

import hello.advance.trace.TraceStatus;
import hello.advance.trace.logtrace.LogTrace;
import hello.advance.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId){

        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                // 저장 로직
                if (itemId.equals("ex")){
                    throw new IllegalArgumentException("예외 발생");
                }
                sleep(1000);

                return null;
            }
        };

        template.execute("OrderRepository.save()");
    }

    private void sleep(int mills) {
        try{
            Thread.sleep(mills);
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
