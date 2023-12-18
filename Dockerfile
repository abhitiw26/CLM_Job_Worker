FROM openjdk:22-slim
EXPOSE 9080
ADD target/clm-job-worker-1.0.jar clm-job-worker-1.0.jar
ENTRYPOINT [ "java", "-jar", "/clm-job-worker-1.0.jar" ]
