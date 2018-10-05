package be.bluemagma.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import be.bluemagma.web.projection.ProjectedByArgumentResolver;

/**
 * Configuration for all web related things
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new ProjectedByArgumentResolver());
  }
}
