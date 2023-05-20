package hello.boot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tobyboot2Application {

  @Bean
  ApplicationRunner run(ConditionEvaluationReport report) {
    return args -> {
      System.out.println(report.getConditionAndOutcomesBySource().entrySet().stream()
        .filter(co -> co.getValue().isFullMatch())
        .filter(co -> !co.getKey().contains("Jmx"))
        .map(co -> {
          System.out.println(co.getKey());
          co.getValue().forEach(c -> {
            System.out.println("\t" + c.getOutcome());
          });
          System.out.println();

          return co;
        }).count());
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(Tobyboot2Application.class, args);
  }

}
