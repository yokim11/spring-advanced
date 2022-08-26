package hello.advanced.app.trace.template;

import hello.advanced.app.trace.template.code.AbstractTemplate;
import hello.advanced.app.trace.template.code.SubClassLogic1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    @Test
    void templateMethodV1() {
        hello.advanced.app.trace.template.code.AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        hello.advanced.app.trace.template.code.AbstractTemplate template2 = new SubClassLogic1();
        template2.execute();
    }

    @Test
    void templateMethodV2() {
        hello.advanced.app.trace.template.code.AbstractTemplate template1 = new hello.advanced.app.trace.template.code.AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        log.info("클래스 이름1 : {}", template1.getClass().getName());
        template1.execute();

        hello.advanced.app.trace.template.code.AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        log.info("클래스 이름2 : {}", template2.getClass().getName());
        template2.execute();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 수행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 수행
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }
}
