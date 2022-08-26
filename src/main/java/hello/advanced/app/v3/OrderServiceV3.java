package hello.advanced.app.v3;

import hello.advanced.app.trace.TraceId;
import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.hellotrace.HelloTraceV2;
import hello.advanced.app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        String currentClassName = this.getClass().getName() + ".orderItem()";
        try {
            status = trace.begin(currentClassName);
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 다시 던져중어야 한다
        }

    }
}
