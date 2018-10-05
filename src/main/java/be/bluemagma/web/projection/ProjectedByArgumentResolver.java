package be.bluemagma.web.projection;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

/**
 * Resolver for all controller arguments that are annoted with {@link ProjectedBy}. The resolver will use the HTTP Accept header to find
 * the associated projection. If the Accept header is empty or does not match the regex pattern, it will return the projection defined
 * in the {@link ProjectedBy#def()} parameter.
 */
@Slf4j
public class ProjectedByArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(final MethodParameter parameter) {
    if (!parameter.hasParameterAnnotations()) {
      return false;
    }

    return parameter.getParameterAnnotation(ProjectedBy.class) != null;
  }

  @Override
  public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
    final String acceptHeader = webRequest.getHeader(HttpHeaders.ACCEPT);

    final ProjectedBy parameterAnnotation = parameter.getParameterAnnotation(ProjectedBy.class);

    if (acceptHeader == null || acceptHeader.isEmpty()) {
      return parameterAnnotation.def();
    }

    if (!this.matchesAcceptHeaderPattern(acceptHeader)) {
      return parameterAnnotation.def();
    }

    final String className = this.extractClassName(acceptHeader, parameter);

    try {
      return Class.forName(className);
    } catch (Exception e) {
      log.error("Could not create projection with class name: {}", className);
      log.error("", e);

      return parameterAnnotation.def();
    }
  }

  private boolean matchesAcceptHeaderPattern(final String acceptHeader) {
    String requiredAcceptHeaderPattern = "application/vnd.[a-z-]+\\+json";
    final Pattern pattern = Pattern.compile(requiredAcceptHeaderPattern);
    return pattern.matcher(acceptHeader).matches();
  }

  private String extractClassName(final String acceptHeader, final MethodParameter parameter) {
    final String projectionNameWithDashes = acceptHeader.split("\\.")[1].split("\\+")[0];
    final StringBuilder builder = new StringBuilder();

    Stream.of(projectionNameWithDashes.split("-"))
        .forEach(token -> {
          final String capitalizedToken = StringUtils.capitalize(token);
          builder.append(capitalizedToken);
        });

    return String.format(
        "%s.projection.%s",
        parameter.getMethod().getDeclaringClass().getPackage().getName(),
        builder.toString()
    );
  }
}
