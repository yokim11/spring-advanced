package hello.advanced.app.v1;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {

        TraceStatus status = null;
        String currentClassName = this.getClass().getName();
        try {

            status = trace.begin(currentClassName);
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok " + itemId;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 다시 던져 주어야 한다
        }
    }
}
