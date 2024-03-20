import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * 测试日志扩展类，实现测试执行前和执行后的日志记录。
 */
@Slf4j
public class TestLoggerExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    /**
     * 在测试方法执行前执行的操作。
     *
     * @param context 扩展上下文，提供测试方法等相关信息。
     */
    @Override
    public void beforeTestExecution(ExtensionContext context) {
        String methodName = context.getRequiredTestMethod().getName();
        log.info("Test method starting: [" + methodName + "]");
    }

    /**
     * 在测试方法执行后执行的操作。
     *
     * @param context 扩展上下文，提供测试方法等相关信息。
     */
    @Override
    public void afterTestExecution(ExtensionContext context) {
        String methodName = context.getRequiredTestMethod().getName();
        log.info("Test method finished: [" + methodName + "]");
    }
}
