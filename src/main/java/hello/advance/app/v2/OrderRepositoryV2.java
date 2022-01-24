package hello.advance.app.v2;

import hello.advance.trace.TraceId;
import hello.advance.trace.TraceStatus;
import hello.advance.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId){

        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId, "OrderRepository.save()");

            // 저장 로직
            if (itemId.equals("ex")){
                throw new IllegalArgumentException("예외 발생");
            }
            sleep(1000);

            trace.end(status);

        } catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 던져줘야한다 로그때문에 예외가 따로처리되면 안된다.
        }
    }

    private void sleep(int mills) {
        try{
            Thread.sleep(mills);
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
