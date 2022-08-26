package hello.advanced.app.v3;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.hellotrace.HelloTraceV2;
import hello.advanced.app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId) {

        TraceStatus status = null;
        String currentClassName = this.getClass().getName() + ".request()";
        try {

            status = trace.begin(currentClassName);
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok " + itemId;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 다시 던져중어야 한다
        }
    }
}
