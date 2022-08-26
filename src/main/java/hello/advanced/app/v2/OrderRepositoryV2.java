package hello.advanced.app.v2;

import hello.advanced.app.trace.TraceId;
import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.hellotrace.HelloTraceV1;
import hello.advanced.app.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId) {
        TraceStatus status = null;
        String currentClassName = this.getClass().getName();
        try {
            status = trace.beginSync(traceId, currentClassName + ".save()");
            //저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 다시 던져중어야 한다
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
