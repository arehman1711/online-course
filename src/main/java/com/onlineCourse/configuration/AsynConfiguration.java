package com.onlineCourse.configuration;
import java.util.concurrent.Executor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
@Configuration
public class AsynConfiguration extends AsyncConfigurerSupport {
   @Override
   public Executor getAsyncExecutor() {
      ThreadPoolTaskExecutor executor = new 
                ThreadPoolTaskExecutor();
      executor.setCorePoolSize(3);
      executor.setMaxPoolSize(4);
      executor.setThreadNamePrefix("asyn-task-thread-");
      executor.setWaitForTasksToCompleteOnShutdown(true);
      executor.initialize();
      return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler  
         getAsyncUncaughtExceptionHandler() {
     return (ex, method, params) -> {
        System.out.println("Exception: " + ex.getMessage());
        System.out.println("Method Name: " + method.getName());
        ex.printStackTrace();
     };
  }
}