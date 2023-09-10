package com.onlineCourse.configuration;
import java.util.concurrent.Executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
@Configuration
@Slf4j
public class AsyncConfiguration extends AsyncConfigurerSupport {
   @Override
   public Executor getAsyncExecutor() {
      ThreadPoolTaskExecutor executor = new 
                ThreadPoolTaskExecutor();
      executor.setCorePoolSize(3);
      executor.setMaxPoolSize(4);
      executor.setThreadNamePrefix("async-task-thread-");
      executor.setWaitForTasksToCompleteOnShutdown(true);
      executor.initialize();
      return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler  
         getAsyncUncaughtExceptionHandler() {
     return (ex, method, params) -> {
        log.info("Exception in Async Process. Exception: " + ex.getMessage() + "Method Name: " + method.getName());
        ex.printStackTrace();
     };
  }
}