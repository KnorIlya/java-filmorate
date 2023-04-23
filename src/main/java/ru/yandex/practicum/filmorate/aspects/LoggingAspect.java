package ru.yandex.practicum.filmorate.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.annotation.LogExecution;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;


@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Pointcut("@annotation(ru.yandex.practicum.filmorate.annotation.LogExecution)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        LogExecution annotation = method.getAnnotation(LogExecution.class);
        String className = method.getDeclaringClass().getSimpleName();
        LogLevel level = annotation.value();
        String methodName = method.getName();
        Object[] args = proceedingJoinPoint.getArgs();

        String argsMessage = getArgsMessage(
                className,
                methodName,
                ((CodeSignature) proceedingJoinPoint.getSignature()).getParameterNames(),
                args,
                annotation.withArgs()
        );
        log(level, argsMessage);

        Instant start = Instant.now();
        Object proceed = proceedingJoinPoint.proceed();
        Instant end = Instant.now();

        String resultMessage = getResultMessage(
                className,
                methodName,
                getDuration(start, end, annotation.chronoUnit()),
                annotation.withDuration()
        );
        log(level, resultMessage);

        return proceed;
    }

    private static void log(LogLevel level, String message) {
        switch (level) {
            case DEBUG:
                log.debug(message);
                break;
            case TRACE:
                log.trace(message);
                break;
            case WARN:
                log.warn(message);
                break;
            case ERROR:
            case FATAL:
                log.error(message);
                break;
            default:
                log.info(message);
                break;
        }
    }

    private static String getArgsMessage(String className,
                                         String methodName,
                                         String[] params,
                                         Object[] args,
                                         boolean withArgs) {
        StringJoiner message = new StringJoiner(" ")
                .add("Method").add(className + "." + methodName).add("called");
        if (withArgs && Objects.nonNull(params) && Objects.nonNull(args) && params.length == args.length) {
            Map<String, Object> values = new HashMap<>(params.length);
            for (int i = 0; i < params.length; i++) {
                values.put(params[i], args[i]);
            }
            message.add("with args:").add(values.toString());
        }
        return message.toString();
    }

    private static String getResultMessage(String className,
                                           String methodName,
                                           String duration,
                                           boolean withDuration) {
        StringJoiner message = new StringJoiner(" ")
                .add("Method").add(className + "." + methodName).add("executed");
        if (withDuration) {
            message.add("in").add(duration);
        }
        return message.toString();
    }

    private static String getDuration(Instant start, Instant end, ChronoUnit chronoUnit) {
        return String.format(
                "%s %s",
                chronoUnit.between(start, end),
                chronoUnit.name().toLowerCase()
        );
    }


}
