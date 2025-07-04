package test.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // 接口A - QPS限流为2
    @GetMapping("/api/a")
    @SentinelResource(value = "apiA", blockHandler = "handleApiABlock", fallback = "fallback")
    public String apiA() {
        return "This is API A response";
    }

    // 接口B - QPS限流为5
    @GetMapping("/api/b")
    @SentinelResource(value = "apiB", blockHandler = "handleApiBBlock")
    public String apiB() {
        return "This is API B response";
    }

    // 接口A的限流处理方法
    public String handleApiABlock(BlockException ex) {
        return "API A is blocked by Sentinel. Too many requests!";
    }

    // 接口B的限流处理方法
    public String handleApiBBlock(BlockException ex) {
        return "API B is blocked by Sentinel. Too many requests!";
    }

    // 降级处理方法,当apiA抛出非BlockException的异常触发降级
    public String fallback(Throwable ex) {
        return "降级A";
    }
}